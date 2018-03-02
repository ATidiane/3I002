package ing.tictactoe;

import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 * Cette classe traite une nouvelle requête du serveur
 */
public class HttpWorker extends Thread {

    private Socket socket = null;
    protected BufferedReader input = null;
    protected DataOutputStream output = null;
    private final String pathHeader;


    public HttpWorker(Socket socket, String path_header) {
        super("HttpWorker");
        this.socket = socket;
        this.pathHeader = path_header;
    }


    public void run() {
    	try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());
			httpHandler();
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    /**
     *  Traite une requête HTTP
     */
    private void httpHandler() {
  
    	try {
    		
			String ClientSentence = input.readLine();
				
				if(Pattern.matches("GET /connection?.*",ClientSentence) == true || Pattern.matches("GET /update?.*",ClientSentence) == true 
						|| Pattern.matches("GET /move?.*",ClientSentence) == true || Pattern.matches("GET /reset/?.*",ClientSentence) == true){
					fileNotFoundHandler(ClientSentence);
				}else {
					
					String[] fichier = ClientSentence.split(" ");
					String nom = fichier[1];
					
					if (nom.length() == 1 ){
						nom = "/index.html";
					} // case when its the root
		
					String filepath = pathHeader+nom;
					File f_ressource = new File(filepath);
					String filetype = nom.split("\\.")[1].toUpperCase();
					
					if (!f_ressource.exists()){
						fileNotFoundHandler(filepath);
					} else {
						String entete = HttpServer.constructHttpHeader(200, FileType.valueOf(filetype));
						output.writeBytes(entete);
						HttpServer.outputFile(new FileInputStream(f_ressource), output);
						output.flush();
						System.out.println(ClientSentence);
					}	
					
				}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    /** Cette méthode gère une ressource non-trouvée
     * @param path : chemin de la ressource non trouvée
     */
    public void fileNotFoundHandler(String path) {
        try {
            // Envoi de l'erreur 404 : Fichier non-trouvé
            String retMessage = "<html><head></head><body>Fichier "+path+" non trouvé...</body></html>\n";

            output.writeUTF(HttpServer.constructHttpHeader(404, FileType.HTML));
            output.writeUTF(retMessage);
        } catch (Exception e) {
            System.err.println("Erreur avec le chemin "+path+" : " + e.getMessage());
        }
    }

} // class
