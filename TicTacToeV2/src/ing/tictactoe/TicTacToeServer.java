package ing.tictactoe;

import java.io.IOException;
import java.net.Socket;

/**
 * Cette classe crée un serveur de jeu de Tic Tac Toe
 */
public class TicTacToeServer extends HttpServer {
    private TicTacToe game;

    public TicTacToeServer(int listeningPort, String pathHeader, TicTacToe ttt) {
        super(listeningPort, pathHeader);
        this.game = ttt;
    }


    /**
     *  Cette méthode attend une requête et créée un nouveau thread pour prendre en charge la requête
     */
    @Override
    void waitingForRequest() {
    	try {
			Socket connectionSocket = welcomeSocket.accept();
			TicTacToeWorker myTttThread = new TicTacToeWorker(connectionSocket, pathHeader,game);
		    myTttThread.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
