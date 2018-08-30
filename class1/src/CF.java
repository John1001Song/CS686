import java.util.Random;
import java.util.logging.Logger;

public class CF {
    private int col, row, numbToWin;
    private char[][] map;
    private boolean gameContinue;
    private char winner;

    private int placedRow, placedCol;

    public CF(int row, int col, int numbToWin) {
        this.col = col;
        this.row = row;
        this.numbToWin = numbToWin;
        this.map = new char[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                this.map[x][y] = '.';
            }
        }
        this.gameContinue = true;
        this.winner = 'N';
        this.placedRow = this.placedCol = 0;

        Main.logger.info("map size: row: " + row + " col: " + col + " win: " + numbToWin);

    }

    /**
     * this play func determine if the given column could be place an O or X
     * if there is a available location, then update the map and return true
     * else return false
     *
     * @param col
     *  selected column to place an O or X
     *
     * @param OX
     *  place an O or X
     *
     * @return true or false
     * */
    public boolean play(int col, char OX) {
        char curChar;

        // check from the bottom
        for (int i = this.row-1; i >= 0; i--) {
            curChar = this.map[i][col];
            if (curChar == '.') {
                this.map[i][col] = OX;
                this.placedRow = i;
                this.placedCol = col;
                return true;
            }
        }

        return false;
    }

    /**
     * this over func will return current status if the game is finished
     *
     * @return true or false
     */
    public boolean over() {
        return gameContinue;
    }

    /**
     * this func check will check if the game is finished.
     * if finished, update this.gameContinue = false
     * else this.gameContinue = true
     *
     * @param checkChar
     *  O or X
     *
     * @param cx
     *  current row
     *
     * @param cy
     *  current column
     *
     * @return true
     * */
    public boolean check(char checkChar, int cx, int cy) {

        // indicate number in horizon line, 45 degree line, vertical line and 135 degree line
        int zero, fortyFive, ninety, oneThirty;

        zero = count(0, -1, checkChar, cx, cy) + count(0, 1, checkChar, cx, cy) - 1;
        fortyFive = count(-1, -1, checkChar, cx, cy) + count(1, 1, checkChar, cx, cy) - 1;
        ninety = count(-1, 0, checkChar, cx, cy) + count(1, 0, checkChar, cx, cy) - 1;
        oneThirty = count(1, -1, checkChar, cx, cy) + count(-1, 1, checkChar, cx, cy) - 1;

        Main.logger.info("current char: " + checkChar + System.lineSeparator()
                + "zero: " + zero + "    " + "45 degree: " + fortyFive + "   "
                + "90 degree: " + ninety + "    " + "135 degree: " + oneThirty);

        // check any direction, if there are enough O or X
        if (zero >= this.numbToWin || fortyFive >= this.numbToWin
                || ninety >= this.numbToWin || oneThirty >= this.numbToWin) {
            this.gameContinue = false;
            this.winner = checkChar;

            return true;
        } else {
            // check if all the locations are placed by O and X
            // if so, return true and update this.gameContinute = false
            // else return true
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    if (map[i][j] == '.') {
                        return true;
                    }
                }
            }
            this.gameContinue = false;
        }

        return true;
    }

    /**
     * this func checkMapFull will check if the map is full of O and X
     *
     * @return true if full; false if not
     **/
    public boolean checkMapFull() {

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (map[i][j] == '.') {
                    return false;
                }
            }
        }

        // if the map is full, then the game stops
        this.gameContinue = false;
        return true;
    }

    /**
     * this count func will count number of O or X based on provided direction
     *
     * coordinate; top left corner is 0,0
     * example: 3 by n matrix
     * row0,col0 row0,col1 row0,col2 ...
     * row1,col0 row1,col1 row1,col2 ...
     * row2,col0 row2,col1 row2,col2 ...
     * ...
     *
     * @param dx
     *  direction row -1, 0, 1
     *
     * @param dy
     *  direction col -1, 0, 1
     *
     * @param checkChar
     *  provided char
     *
     * @param cx
     *  provided current row
     *
     * @param cy
     *  provided current col
     *
     * @return number of same char
     */
    private int count(int dx, int dy, char checkChar, int cx, int cy) {
        int curRow = cx;
        int curCol = cy;
        char curChar;
        int sum = 1;

        Main.logger.info("cx: " + cx + "    " + "cy: " + cy);

        // when the current location is still in the range
        while (0 < curCol && curCol < this.col-1 && 0 < curRow && curRow < this.row-1) {
            curRow += dx;
            curCol += dy;

            curChar = this.map[curRow][curCol];

            if (curChar == checkChar) {
                sum += 1;


                Main.logger.info("curRow: " + curRow + "    " + "curCol: " + curCol + System.lineSeparator()
                        + "dx: " + dx + "    " + "dy: " + dy + System.lineSeparator()
                        + "current Char: " + checkChar + "  " + "current sum: " + sum + System.lineSeparator());

            } else {
                return sum;
            }
        }

        return sum;
    }

    /**
     * this func print will print the map content
     *
     * @param lastPiece
     *  last piece O or X
     *
     * @param cy
     *  column of the last position
     * */
    public void print(char lastPiece, int cy) {
        System.out.println("Last piece: " + lastPiece);
        System.out.println("Column: " + cy);
        System.out.println("Winner: " + this.winner);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    public int getPlacedRow() {
        return placedRow;
    }

    public int getPlacedCol() {
        return placedCol;
    }
}
