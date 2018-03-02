package ing.tictactoe;

/**
 * Classe représentant le déroulement du morpion
 */
public class TicTacToe {
    public final int GRID_SIZE = 3;
    private final int MAX_PLAYER = 2;

    private int grid[][] = new int[GRID_SIZE][GRID_SIZE];
    private int nbPlayer;
    private int nextToPlay = 0; // Prochain "player id" à jouer
    /**
     * Pour les numéros d'identification
     * 0 = joueur rond
     * 1 = joueur croix
     */
    private int winnerLine[][] = new int[3][2]; // Ligne du vainqueur

    public TicTacToe() {
        this.reset();
    }


    /**
     * Remet à zéro (ou initialise) le jeu
     */
    public void reset(){
        this.nbPlayer = 0;
        this.nextToPlay = 0;

        for (int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = -1;
            }
        }

        winnerLine = new int[3][2];
    }


    /**
     * Ajoute un joueur dans la partie
     * @return Retourne son rôle (0 = rond, 1 = croix) ou retourne -1 si la partie est pleine
     */
    public int addPlayer() {
        if(nbPlayer >= MAX_PLAYER) {
            return -1;
        }
        nbPlayer++;
        return nbPlayer - 1;
    }


    /**
     * Execute le coup d'un joueur
     * @param player joueur qui a joué
     * @param i ligne du coup
     * @param j colonne du coup
     * @return Si le coup n'est pas possible, retourne -1
     */
    public int doMove(int player, int i, int j) {
        if (player != nextToPlay) return -1;
        if (grid[i][j] != -1) return -1;
        if (nbPlayer < 2) return -1;
        if (getWinner() > -1) return -1;


        grid[i][j] = player;
        nextToPlay = (nextToPlay + 1)%2;
        return 1;
    }


    /**
     * Retourne la cellule (i, j)
     * @return cellule (i,j)
     * @param i ligne du coup
     * @param j colonne du coup
     */
    public int getCell(int i, int j) {
        return grid[i][j];
    }


    /**
     * Retourne l'ID du joueur gagnant
     * Pour simplifier légèrement, on suppose que GRID_SIZE = 3
     * @return -1 si la partie n'est pas finie, 2 si le résultat est un match nul
     */
    public int getWinner() {
        /* Vérification des lignes/colonnes */
        for(int i = 0; i < GRID_SIZE; i++){
            // vérification de la ligne i
            int id = grid[i][0];
            if(id ==  grid[i][1] && id == grid[i][2]) {
                if (id > -1) {
                    assignWinnerLine(i,0,i,2);
                    return id;
                }
            }

            // vérification de la colonne i
            id = grid[0][i];
            if(id ==  grid[1][i] && id == grid[2][i]) {
                if (id > -1) {
                    assignWinnerLine(0,i,2,i);
                    return id;
                }
            }
        }

        /* Vérification des diagonales */
        if (grid[0][0] == grid[1][1] &&  grid[0][0] == grid[2][2]) {
            if (grid[0][0] > -1) {
                assignWinnerLine(0,0,2,2);
                return grid[0][0];
            }
        }

        if (grid[2][0] == grid[1][1] &&  grid[2][0] == grid[0][2]) {
            if (grid[2][0] > -1) {
                assignWinnerLine(2,0,0,2);
                return grid[2][0];
            }
        }

        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == -1) {
                    return -1; // Partie toujours en cours (ou pas commencée)
                }
            }
        }

        return 2; // Match nul
    }


    /**
     * Nombre de joueurs dans la partie
     * @return nombre de joueurs dans la partie
     */
    public int getNbPlayer() {
        return nbPlayer;
    }
    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }


    /**
     * Line gagnante (pour l'affichage au niveau du client)
     * @return la ligne gagnante
     */
    public String getWinnerLine() {
        return  Integer.toString(winnerLine[0][0])
                +","+ Integer.toString(winnerLine[0][1])
                +","+ Integer.toString(winnerLine[1][0])
                +","+ Integer.toString(winnerLine[1][1])
                +","+ Integer.toString(winnerLine[2][0])
                +","+ Integer.toString(winnerLine[2][1]);
    }


    /**
     * Joueur qui va jouer prochainement
     * @return identifiant du joueur qui va jouer prochainement
     */
    public int getNextToPlay() {
        return nextToPlay;
    }

    private void assignWinnerLine(int c1, int l1, int c2, int l2) {
        winnerLine[0][0] = c1;
        winnerLine[0][1] = l1;
        winnerLine[1][0] = (c1 + c2)/2;
        winnerLine[1][1] = (l1 + l2)/2;
        winnerLine[2][0] = c2;
        winnerLine[2][1] = l2;
    }

}


