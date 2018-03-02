package ing.tictactoe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe traite une nouvelle requête de jeu ou de connexion du serveur
 */
public class TicTacToeWorker extends HttpWorker {

    private TicTacToe game;
    private String httpResponse;

    /**
     * Constructeur de la classe TicTacToeWorker
     * @param socket socket client
     * @param pathHeader emplacement du dossier public_html
     * @param ttt référence au jeu TicTacToe
     */
    public TicTacToeWorker(Socket socket, String pathHeader, TicTacToe ttt) {
        super(socket, pathHeader);
        this.game = ttt;
        this.httpResponse = "";
    }

    
    @Override
    public void fileNotFoundHandler(String path) {
    	
    	int winner = -1;
    	if(Pattern.matches("GET /connection?.*",path) == true){
			System.out.println("CONNECTION ");
			
			if (game.getNbPlayer() < 2){
				int player = game.addPlayer();
				
				System.out.println("Player: "+player);
				this.httpResponse = constructHttpResponse("Le joueur "+player+" est ajouté", player);
				
			}else {
				System.out.println("Partie Pleine");
				this.httpResponse = constructHttpResponse("La partie est pleine", -1);
			}
			
			
		}
		else if(Pattern.matches("GET /update?.*",path) == true){
			
			
			System.out.println("UPDATE");
			
			String pId = path.split("=")[1].split(" ")[0];
			winner = game.getWinner();
			if ( winner == -1 ){
				httpResponse = constructHttpResponse("Le joueur "+pId+" joue", Integer.parseInt(pId));
			}else if (winner == 2){
				httpResponse = constructHttpResponse("Match nul", game.getNbPlayer());
			}else {
				httpResponse = constructHttpResponse("Le joueur "+ winner +" a gagné", game.getNextToPlay());
			}
			
		}
		else if(Pattern.matches("GET /move?.*",path) == true){
			System.out.println("MOVE");
			
			String playerId = path.split("&")[0].split("=")[1];
			String i = path.split("&")[1].split("=")[1];
			String j = path.split("&")[2].split("=")[1].split(" ")[0];
			
			game.doMove(Integer.parseInt(playerId),Integer.parseInt(i), Integer.parseInt(j));
			httpResponse = constructHttpResponse("Le joueur "+playerId+" a joué", Integer.parseInt(playerId));
			
		}
		else if(Pattern.matches("GET /reset/?.*",path) == true) {
			System.out.println("RESET ");
			game.reset();
			game.setNbPlayer(2);
			httpResponse = constructHttpResponse("Redemarrer le Jeu", 0);
		} 
		
			
		try {
			output.writeUTF(httpResponse);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    /**
     * Construit la réponse HTTP + XML du serveur
     * L'état du jeu est entièrement décrit dans la réponse
     * Le client mettra à jour son interface graphique à la réception de cette réponse
     * @param message : message à envoyer
     * @param playerId : identifiant du joueur
     * @return La réponse HTTP
     */
    private String constructHttpResponse(String message, int playerId) {

        // construction du header de la réponse
        String httpResponse = HttpServer.constructHttpHeader(200, FileType.XML);

        httpResponse = httpResponse + "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";
        httpResponse = httpResponse + "<RESPONSE>\r\n";
        httpResponse = httpResponse + "<message>" + message + "</message>\r\n";
        httpResponse = httpResponse + "<nbPlayer>" + Integer.toString(game.getNbPlayer()) + "</nbPlayer>\r\n";
        httpResponse = httpResponse + "<playerId>" + Integer.toString(playerId) + "</playerId>\r\n";
        httpResponse = httpResponse + "<winnerId>" + Integer.toString(game.getWinner()) + "</winnerId>\r\n";
        httpResponse = httpResponse + "<winnerLine>"+ game.getWinnerLine() +"</winnerLine>\r\n";
        httpResponse = httpResponse + "<nextToPlay>"+ Integer.toString(game.getNextToPlay())+"</nextToPlay>\r\n";

        for(int i = 0; i < game.GRID_SIZE; i++) {
            for(int j = 0; j < game.GRID_SIZE; j++) {
                httpResponse = httpResponse + "<cell>"+ Integer.toString(game.getCell(i,j))+"</cell>\r\n";
            }
        }

        httpResponse = httpResponse + "</RESPONSE>\r\n";

        return httpResponse;
    }

}

