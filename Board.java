// MATTHEW MIGLIO
// Board.java

//Makes the board object that is used by 
//either algorithm when finding solutions
//designates methods for placing queens,
// moving columns, and checking axies of attack as to check for board solutions.

public class Board {
    private int boardSize;
    private boolean[][] boardArray;
    private int queenTotal = 0;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        boardArray = new boolean[boardSize][boardSize];
    }

    public int getSize() {
        return this.boardSize;
    }

    public void addQueenToBoard(int targetColumn, int targetRow) {
        // handles if already a queen somehow
        if (boardArray[targetColumn][targetRow])
            return;
        // if its not a queen make it a queen
        boardArray[targetColumn][targetRow] = true;
        // increment queenTotal
        queenTotal++;
    }

    public void swapColumns(int givenIndex, int targetIndex) {
        for (var current_row = 0; current_row < boardSize; current_row++) {
            // every row in the two columns, swap each boolean var
            // get list of rows, AKA whole board
            boolean[] currentRowValue = boardArray[current_row];
            // get temporary row (a copy of givenIndex's row)
            boolean temporary = currentRowValue[givenIndex];
            // set givenIndex's row equal to currentIndex's row
            currentRowValue[givenIndex] = currentRowValue[targetIndex];
            // set currentIndex's row equal to temporary's row
            currentRowValue[targetIndex] = temporary;
        }
    }


    public boolean checkIfBoardIsSolved() {
        // if there are more queens thant rows its impossible
        if (queenTotal > boardSize) {
            return false;
        }

        // get coord of every queen on the board
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                // for every slot on the board
                if (boardArray[row][column]) {
                    // for every queen on the board
                    // do horizontal attack check
                    if (!checkHorizontal(column))
                        return false;

                    // do veritcal attack check
                    if (!checkVertical(row))
                        return false;

                    // do uprightdiagonal attack check
                    if (!checkUpRightDiagonal(row, column))
                        return false;

                    // do upleftdiagonal attack check
                    if (!checkUpLeftDiagonal(row, column))
                        return false;
                }
            }
        }
        return true;
    }


    public boolean checkHorizontal(int column) {
        // check if any queens in horizontal
        // AKA check if any TRUEs appear in current row
        int queensInThisRow = 0;
        // add up every queen in this row
        for (int searchRow = 0; searchRow < boardSize; searchRow++) {
            boolean horizontalSearchSlot = boardArray[searchRow][column];
            if (horizontalSearchSlot)
                queensInThisRow++;
        }
        return (queensInThisRow <= 1);
    }


    public boolean checkVertical(int row) {
        // check if any queens in veritcal
        // AKA check if any TRUEs appear in current column
        int queensInThisColumn = 0;
        // add up every queen in this column
        for (int searchColumn = 0; searchColumn < boardSize; searchColumn++) {
            boolean verticalSearchSlot = boardArray[row][searchColumn];
            if (verticalSearchSlot)
                queensInThisColumn++;
        }
        return (queensInThisColumn <= 1);
    }


    public boolean checkUpRightDiagonal(int row, int column) {
        // check if any queens in up/right axis
        int queensInUpRightAxis = 1;// init as 1 bc we skip looking at itself because search is
                                    // split into two parts (upp and lower)
        // check upper region
        int upperSearchXIndexUpRight = row + 1;
        int upperSearchYIndexUpRight = column - 1;
        while ((upperSearchXIndexUpRight < boardSize) && (upperSearchYIndexUpRight > -1)) {
            // for each coord in the upper sector of the up/right axis from the given queen coord
            boolean currentSlot = boardArray[upperSearchXIndexUpRight][upperSearchYIndexUpRight];
            if (currentSlot)
                queensInUpRightAxis++;
            // increment
            upperSearchYIndexUpRight--;
            upperSearchXIndexUpRight++;
        }
        // check lower region
        int lowerSearchXIndexUpRight = row - 1;
        int lowerSearchYIndexUpRight = column + 1;
        while ((lowerSearchXIndexUpRight > -1) && (lowerSearchYIndexUpRight < boardSize)) {

            // for each coord in the lower sector of the up/right axis from the given queen coord
            boolean currentSlot = boardArray[lowerSearchXIndexUpRight][lowerSearchYIndexUpRight];
            if (currentSlot)
                queensInUpRightAxis++;
            // increment
            lowerSearchXIndexUpRight--;
            lowerSearchYIndexUpRight++;
        }
        return (queensInUpRightAxis <= 1);
    }


    public boolean checkUpLeftDiagonal(int row, int column) {
        // check if any queens in up/left axis
        int queensInUpLeftAxis = 1;// init as 1 bc we skip looking at itself because search is split
                                   // into two parts (upp and lower)
        // check upper region
        int upperSearchXIndexUpLeft = row - 1;
        int upperSearchYIndexUpLeft = column - 1;
        while ((upperSearchXIndexUpLeft > -1) && (upperSearchYIndexUpLeft > -1)) {
            // for each coord in the upper sector of the up/left axis from the given queen coord
            boolean currentSlot = boardArray[upperSearchXIndexUpLeft][upperSearchYIndexUpLeft];
            if (currentSlot)
                queensInUpLeftAxis++;
            // increment
            upperSearchXIndexUpLeft--;
            upperSearchYIndexUpLeft--;
        }
        // check lower region
        int lowerSearchXIndexUpLeft = row + 1;
        int lowerSearchYIndexUpLeft = column + 1;
        while ((lowerSearchXIndexUpLeft < boardSize) && (lowerSearchYIndexUpLeft < boardSize)) {
            // for each coord in the upper sector of the up/left axis from the given queen coord
            boolean currentSlot = boardArray[lowerSearchXIndexUpLeft][lowerSearchYIndexUpLeft];
            if (currentSlot)
                queensInUpLeftAxis++;
            // increment
            lowerSearchXIndexUpLeft++;
            lowerSearchYIndexUpLeft++;
        }
        // final queen amount check for upleft axis
        return (queensInUpLeftAxis <= 1);
    }


    public String toString() {
        // takes a board and turns it into a string
        String output = "-----------------------\n| ";
        for (int row = 0; row < boardSize; row++) {
            for (var column = 0; column < boardSize; column++) {
                // for every slot in board
                boolean currentSlot = boardArray[row][column];
                String currentSlotString = "";
                if (currentSlot)
                    currentSlotString = "1";
                else
                    currentSlotString = "0";
                output = output + currentSlotString;
                String gapString = "  ";
                output = output + gapString;
            }
            String nextLineString = "|\n| ";
            output = output + nextLineString;
        }
        output = output + "--------------------";
        return output;
    }

}
