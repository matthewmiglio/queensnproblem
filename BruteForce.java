// Board.java
// MATTHEW MIGLIO
// CS-203-01 ASSIGNMENT 1
//TURINI
//
//Makes the board object that is used by 
//either algorithm when finding solutions
//designates methods for placing queens,
// moving columns, and checking axies of attack as to check for board solutions.


import java.util.Random;

public class BruteForce {
    public static void main(String[] args) {
        // dictate the size of the board for the program
        final var BOARD_SIZE = 10;
        // keep a tally for the amount of times the alg evaluates a board
        int evaluationTotal = 0;
        // keep looping while alg doesnt have a solution:
        boolean hasSolvedBoard = false;
        while (!(hasSolvedBoard)) {
            // gap string for printout
            System.out.println("\n\n");

            // make a new board using the random indicies made by makeListOfRandomIndices()
            Board board = new Board(BOARD_SIZE);
            Board currentBoard = fillBoard(board);

            // for each time alg checks board increment eval total int
            evaluationTotal++;

            // check if current board is a solution
            if (currentBoard.checkIfBoardIsSolved()) {
                // if current board is a solution:
                System.out.println("\n\n\nFound a solution through brute force!");
                System.out.println("Brute force method took " + Integer.toString(evaluationTotal)
                        + " evaluations to arrive at a solution.");
                // set hasSolvedBoard to true to exit the loop
                // exiting the loop just ends the program after showing the solution it found.
                hasSolvedBoard = true;
            } else {
                // if current board is NOT a solution:
                System.out.println("This board is not a solution.");
            }

            // show this loop's board.
            System.out.println(currentBoard.toString());
        }
    }


    static int[] makeListOfRandomIndices(int boardSize) {
        // make int[] to fill out with random values
        int[] randomIndexList = new int[boardSize];
        // populate this array with integers 0 to size
        for (int listIndex = 0; listIndex < boardSize; listIndex++) {
            randomIndexList[listIndex] = listIndex;
        }
        // switch these random indexes in randomIndexList around for randomness
        var randomVar = new Random();
        // for each index in randomIndexList
        for (int currentRow = 0; currentRow < boardSize; currentRow++) {
            // choose a random row index and swap it with the current row index using a temp var
            var chosenRandomIndex = randomVar.nextInt(boardSize);
            var temporary = randomIndexList[chosenRandomIndex];
            randomIndexList[chosenRandomIndex] = randomIndexList[currentRow];
            randomIndexList[currentRow] = temporary;
        }
        return randomIndexList;
    }


    static Board fillBoard(Board givenBoard) {
        // get size
        int givenBoardSize = givenBoard.getSize();
        // get random row indexes for this board size
        int[] randomRows = makeListOfRandomIndices(givenBoardSize);
        // place these random indexes
        // for each random row index, add it to the board's (queenPlacementIndex)th column at the
        // (queenPlacementIndex)th index
        for (int queenPlacementIndex =
                0; queenPlacementIndex < givenBoardSize; queenPlacementIndex++) {
            int currentIndex = randomRows[queenPlacementIndex];
            givenBoard.addQueenToBoard(currentIndex, queenPlacementIndex);
        }
        return givenBoard;
    }
}
