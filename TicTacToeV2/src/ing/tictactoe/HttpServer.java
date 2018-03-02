package ing.tictactoe;

/** Imports des services réseaux (Sockets) */
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Méthodes HTTP que l'on prend en charge :
 *  - NOT_SUPPORTED : méthode inconnue ou non-supportée par le serveur HTTP.
 *  - GET
 *  - HEAD
 */
enum HttpMethod {GET, HEAD, NOT_SUPPORTED}

/**
 * Format des fichiers supportés.
 * (JS : JavaScript).
*/
enum FileType {JPEG, GIF, HTML, XML,  JS, CSS, NONE}

/**
 * Classe en charge l'execution du serveur HTTP
 */
public class HttpServer  {
    private int port; // port d'écoute
    protected ServerSocket welcomeSocket = null; // socket d'écoute du serveur
    protected String pathHeader = "/";


    /**
     * Constructeur du serveur HTTP
     * @param listeningPort : port d'écoute du serveur (rappel: le port d'écoute par défaut d'un serveur HTTP est 80)
     */
    public HttpServer(int listeningPort) {
        this.port = listeningPort;
    }


    /**
     * Second constructeur permettant d'initaliser le pathHeader en plus du port d'écoute
     * @param listeningPort : port d'écoute du serveur
     * @param pathHeader : chemin du dossier "public_html
     */
    public HttpServer(int listeningPort, String pathHeader) {
        this(listeningPort);
        this.pathHeader = pathHeader;
    }


    /**
     *  Cette méthode démarre le serveur HTTP
     */
    public void run() {
    	try {
    		welcomeSocket = new ServerSocket(port);
    		while (true) {
    			waitingForRequest();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }


    /**
     *  Cette méthode attend une requête et créée un nouveau thread pour prendre en charge la requête
     */
    void waitingForRequest() {
    	try {
			Socket connectionSocket = welcomeSocket.accept();
			HttpWorker myThread = new HttpWorker(connectionSocket, pathHeader);
		    myThread.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    /**
     * Cette méthode génère l'en-tête HTTP de la réponse
     * @param returnCode : code de retour HTTP
     * @param fileType : type du fichier qui sera joint à la réponse
     */
    public static String constructHttpHeader(int returnCode, FileType fileType) {
        String s = "HTTP/1.1 ";

        switch (returnCode) {
            case 200:
                s = s + "200 OK";
                break;
            case 400:
                s = s + "400 Bad Request";
                break;
            case 403:
                s = s + "403 Forbidden";
                break;
            case 404:
                s = s + "404 Not Found";
                break;
            case 500:
                s = s + "500 Internal Server Error";
                break;
            case 501:
                s = s + "501 Not Implemented";
                break;
        }

        s = s + "\r\n"; // retour chariot (requis avec HTTP),
        s = s + "Connection: close\r\n"; // Mode non-persistant
        s = s + "Server: 3I023 HTTPServer \r\n"; //server name
        s = s + "Cache-Control: no-cache\r\n"; // ne pas mettre en cache la réponse

        switch (fileType) {
            case NONE:
                break;
            case JPEG:
                s = s + "Content-Type: image/jpeg\r\n";
                break;
            case GIF:
                s = s + "Content-Type: image/gif\r\n";
                break;
            case XML:
                s = s + "Content-Type: text/xml\r\n";
                break;
            case HTML:
                s = s + "Content-Type: text/html; charset=UTF-8\r\n";
                break;
            case CSS:
                s = s + "Content-Type: text/css\r\n";
                break;
            case JS:
                s = s + "Content-Type: application/javascript\r\n";
                break;
            default:
                s = s + "Content-Type: text/html; charset=UTF-8\r\n";
                break;
        }

        s = s + "\r\n"; // fin de l'en-tête HTTP

        /* Retourne l'en-tête */
        return s;
    } // constructHttpHeader ()
    

    /**
     * Cette méthode écrit le contenu fichier "f" dans le buffer d'émission "out"
     * @param f : fichier à copier
     * @param out : buffer d'émission dans lequel sur copier le contenu du fichier
     */
    public static void outputFile(FileInputStream f, DataOutputStream out) throws IOException {
        while (true) {
            // Le fichier est lu ligne par ligne
            int line = f.read();
            if (line == -1) {
                break; // Fin du fichier
            }
            out.write(line);
        }
    }

} // Class