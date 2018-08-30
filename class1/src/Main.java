import java.util.Random;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        int row = 6;
        int col = 7;
        int numbToWin = 4;

        CF cf = new CF(row, col, numbToWin);
        int player = 0;
        char curPiece;
        int selectedCol;
        boolean isPlaced = false;

        while (cf.over()) {

            // odd number player will select X and even player will select O
            if (player % 2 == 0) {
                curPiece = 'O';
            } else {
                curPiece = 'X';
            }

            Random random = new Random();
            selectedCol = random.nextInt(col);

            isPlaced = cf.play(selectedCol, curPiece);

            // if the piece cannot be placed,
            // check if the map is full, if full return tie
            // else retry to play
            while (isPlaced == false) {
                boolean mapIsFull = cf.checkMapFull();
                if (mapIsFull == true) {
                    // print, then return
                    cf.print(curPiece, selectedCol);
                    return;
                } else {
                    // not full, select the next random column
                    selectedCol = random.nextInt(col);
                    isPlaced = cf.play(selectedCol, curPiece);
                }
            }

            // if the O or X is placed, then print the map
            if (isPlaced == true) {
                cf.check(curPiece, cf.getPlacedRow(), selectedCol);
                cf.print(curPiece, selectedCol);
            }

            player++;
        }

    }
}
