package ing.tictactoe;

import java.net.Socket;

public class Main {

    public static void main(String[] args) {
       // HttpServer myServer = new HttpServer(9090,"public_html");
    	TicTacToe TTT = new TicTacToe();
        TicTacToeServer mytttServer = new TicTacToeServer(9090, "public_html", TTT);
    	System.out.println("Coucou avt run");
        mytttServer.run();
        System.out.println("Coucou après run");
    }

}
