// MATTHEW MIGLIO
//solverNQueens.java

//Solves the queens N problem with recursive column swapping



import java.util.Random;


public class solverNQueens {
    public static void main(String[] args) {
        introductionPrintout();
        
        final var BOARD_SIZE = 5;

        int evalutationTotal = 0;

        // make new board object
        Board board = new Board(BOARD_SIZE);
        Board currentBoard = fillBoard(board);

        // print starting board
        System.out.println("Starting board:");
        String out = currentBoard.toString();
        System.out.println(out);

        // add one evaluation to tally for inition board check
        evalutationTotal++;
        if (currentBoard.checkIfBoardIsSolved()) {
            System.out.println("The initial board is a satisfactory solution.");
            System.out.println("This took 1 evaluation.");
        }

        // ITERATIVE REPAIR STRATEGY
        // test all swaps across all rotations
        for (int boardRotations = 0; boardRotations < BOARD_SIZE; boardRotations++) {
            // do a board rotation BOARD_SIZE times
            // to do all {board_size} possible starting combinations

            for (int columnIndex = BOARD_SIZE - 1; columnIndex > 0; columnIndex--) {
                currentBoard.swapColumns(columnIndex, 0);
            }
            System.out.println("rotated board:");
            String rotatedBoardString = currentBoard.toString();
            System.out.println(rotatedBoardString);

            // try all column swap combos with this board rotation
            for (int currentColumn = 0; currentColumn < BOARD_SIZE - 1; currentColumn++) {
                for (int columnToSwap =
                        currentColumn + 1; columnToSwap < BOARD_SIZE; columnToSwap++) {
                    // do column swap for this combo
                    System.out.println("Swapping currentColumn: " + currentColumn
                            + " and columnToSwap: " + columnToSwap + ".");
                    currentBoard.swapColumns(currentColumn, columnToSwap);
                    System.out.println(currentBoard.toString());

                    // increment evaluation count for checkIfSolved evaluation
                    evalutationTotal++;
                    // if solved board return
                    if (currentBoard.checkIfBoardIsSolved()) {

                        System.out.println("\n\nReached a satisfactory board!");
                        System.out.println(currentBoard.toString());
                        System.out.println("This took " + Integer.toString(evalutationTotal)
                                + " evaluations.");
                        return;
                    }
                    // if the board isnt solved redo this with swapped columns
                    else {

                        currentBoard.swapColumns(currentColumn, columnToSwap);
                    }
                }
            }

        }
        System.out.println("\nThe starting board cannot be solved. " + "\n"
                + "Program went through " + Integer.toString(evalutationTotal)
                + " evaluations to exhaust all options.");

    }


    //method for introductory printout in terminal
    public static void introductionPrintout(){
        System.out.println("Matthew Miglio, Kettering University");
        System.out.println("N-Queens Problem Solver");
        System.out.println("June 2022");
    }
    
    
    static Board fillBoard(Board givenBoard) {
        // get random index for all the queens
        int givenBoardSize = givenBoard.getSize();
        int[] randomRows = makeListOfRandomIndices(givenBoardSize);
        // put queens in at these random indexes
        for (var currentColumn = 0; currentColumn < givenBoardSize; currentColumn++) {
            givenBoard.addQueenToBoard(currentColumn, randomRows[currentColumn]);
        }
        return givenBoard;
    }

    // returns an array of given length that has all the digits 0 to length in the int[] in a random
    // order.
    static int[] makeListOfRandomIndices(int size) {
        // make int[] to fill out with random values
        var randomIndexList = new int[size];
        // populate this array with integers 0 to size
        for (var listIndex = 0; listIndex < size; listIndex++) {
            randomIndexList[listIndex] = listIndex;
        }
        // switch these random indexes in randomIndexList around for randomness
        var randomVar = new Random();
        // for each index in randomIndexList
        for (var currentRow = 0; currentRow < size; currentRow++) {
            // choose a random row index and swap it with the current row index using a temp var
            var chosenRandomIndex = randomVar.nextInt(size);
            var temporary = randomIndexList[chosenRandomIndex];
            randomIndexList[chosenRandomIndex] = randomIndexList[currentRow];
            randomIndexList[currentRow] = temporary;
        }
        return randomIndexList;
    }
}
