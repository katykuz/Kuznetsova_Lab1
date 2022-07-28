package TwoDimArray;
import java.util.Scanner; //needed for user input
import java.util.Random; //needed to populate array

/**
 * TwoDimArray program creates a 2D matrix based on user input and provides
 * sums of rows, columns, and major diagonals.
 *
 * @author Ekaterina Kuznetsova
 * @version 1.0
 */
public class TwoDimArray {
    /**
     * The main method creates a Scanner object, calls upon a method to
     * loop through the program, and closes the scanner object.
     *
     * @param args A String array containing command line arguments
     */
    public static void main(String[] args) {
        //crate scanner object
        Scanner keyboard = new Scanner(System.in);

        //call welcome method
        welcome();

        //call method of do-while loop
        doRepeat(keyboard);

        //call goodbye method
        goodbye();

        //close scanner
        keyboard.close();
    }

    /**
     * Welcome method greets the user and introduces the program
     */
    public static void welcome() {
        System.out.println("Welcome to the 2D array program! You will enter " +
                "\nthe size of your matrix and I will display the randomized " +
                "\nmatrix with sums of the rows, columns, and diagonals.");
    }

    /**
     * The goodbye methods displays a farewell message and thanks them for
     * using the program.
     */
    public static void goodbye() {
        System.out.println("Thanks! Goodbye for now!");
    }

    /**
     * The doRepeat method calls necessary functions to get user info, create
     * a 2D array, perform calculations, and print the results while checking
     * if the user would like to repeat the program.
     *
     * @param keyboard  Scanner object for user input
     */
    public static void doRepeat(Scanner keyboard) {
        //declare/initialize variables
        char repeat; //
        int size;

        do {

            //call method to get user input
            size = getInput(keyboard);

            //call method to create 2D array
            int[][] matrix = makeArray(size);

            //call method to calculate rows
            int[] totalR = calcRows(matrix, size);

            //call method to calculate columns
            int[] totalC = calcCols(matrix, size);

            //call method to calculate diagonals
            int[] totalD = calcDiag(matrix, size);

            //call method to print results
            printMatrix(matrix, totalR, totalC, totalD);

            //prompt user for repeat of program
            System.out.print("\n\nRepeat with new numbers? (Y/N): ");
            repeat = keyboard.nextLine().toLowerCase().charAt(0);

          //repeat program while user inputs y
        } while (repeat == 'y');
    }

    /**
     * The getInput method prompts the user for the size of the 2D array and
     * returns the size
     *
     * @param keyboard  Scanner object keyboard for user input
     * @return int      size of array
     */
    public static int getInput(Scanner keyboard) {
        //declare integer variable for matrix size
        int size;

        //prompt user for array size and validate input, re-prompt while invalid
        do {
            System.out.print("How large of matrix? Choose 1 - 10: ");
            //collect user input
            size = keyboard.nextInt();
            //consume next line character
            keyboard.nextLine();
        } while (size < 1 || size > 10);

        //return integer value
        return size;
    }

    /**
     * The makeArray method initializes the array with the random Object
     *
     * @param size      integer value of array size
     * @return int[][]  2D populated array
     */
    public static int[][] makeArray(int size) {
        //create Random object
        Random rand = new Random();

        //initialize 2D array
        int[][] matrix = new int[size][size];

        //populate 2D array; outer loop goes through all rows
        for (int row = 0; row < matrix.length; row++) {
            //inner loop goes through all columns in the row
            for (int col = 0; col < matrix[row].length; col++) {
                //assign each element a random value up to 99
                matrix[row][col] = rand.nextInt(99);
            }
        }
        //return 2D array with random values in elements
        return matrix;

    }

    /**
     * The calcRows method takes in the 2D array and calculates the sum of
     * each row
     *
     * @param matrix    a 2D array
     * @param size      size of array
     * @return int[]    1D array of row sums
     */
    public static int[] calcRows(int[][] matrix, int size){
        //initialize 1D array to hold sums
        int[] totalR = new int[size];

        //outer loop to go through all rows
        for (int row = 0; row < matrix.length; row++) {
            //initialize accumulator
            totalR[row] = 0;
            //sum individual row
            for (int col = 0; col < matrix[row].length; col++) {
                totalR[row] += matrix[row][col];
            }
        }
        //return 1D array
        return totalR;
    }

    /**
     * The calcCows method takes in the 2D array and calculates the sum of
     * each row
     *
     * @param matrix    a 2D array
     * @param size      size of array
     * @return int[]    1D array of column sums
     */
    public static int[] calcCols(int[][] matrix, int size){
        //declare accumulator
        int[] totalC = new int[size];

        //outer loop to go through all rows
        for (int col = 0; col < matrix[0].length; col++) {
            //initialize accumulator
            totalC[col] = 0;
            //sum individual row
            for (int row = 0; row < matrix.length; row++) {
                totalC[col] += matrix[row][col];
            }
        }
        //return 1D array with column sums
        return totalC;
    }

    /**
     * The calcRows method takes in the 2D array and calculates the sum of
     * each diagonal
     *
     * @param matrix    2D randomized array
     * @param size      size of array
     * @return int[]    1D array with diagonal sums
     */
    public static int[] calcDiag(int[][] matrix, int size){
        //initialize 1D array and declare accumulators
        int[] totalD = new int[2];
        int valZed = 0, valOne = 0;

        //sum the first diagonal, from top right corner to bottom left
        //outer loop is to go through the rows
        for (int row = 0; row < matrix.length; row++) {
            //inner loop is to pick specific columns
            for (int col = (matrix.length - row - 1); col >= 0; col -= size)
                //accumulator holds specific column sums
                valZed += matrix[row][col];
        }

        //sum the second diagonal, from top left corner to bottom right
        //outer loop is to go through the rows
        for (int row = 0; row < matrix.length; row++) {
            //inner loop is to pick specific columns
            for (int col = row; col >= 0; col -= size)
                //accumulator holds specific column sums
                valOne += matrix[row][col];
        }

        //assign values to array
        totalD[0] = valZed;
        totalD[1] = valOne;

        //return 1D array
        return totalD;
    }

    /**
     * The printMatrix method takes in the 2D & 1D arrays and displays
     * them in the standard output
     *
     * @param matrix    2D randomized array
     * @param totalR    1D array of row sums
     * @param totalC    1D array of column sums
     * @param totalD    1D array of diagonal sums
     */
    public static void printMatrix(int[][] matrix, int[] totalR, int[] totalC, int[] totalD) {

        //add space to sparate matrix from prompts
        System.out.println();
        //print the 2D matrix by row, followed by the sum before printing the next row
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("\t");
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%d\t", matrix[row][col]);
            }
           System.out.printf("| %d\n", totalR[row]);
        }


        //separator for visual clarity
        System.out.print("---------");
        for (int row = 0; row< matrix.length; row++) {
            System.out.print("----");
        }
        System.out.println();

        //below matrix, print first diagonal sum
        System.out.printf("%d", totalD[0]);

        //on the same line as diagonal sum, print the column sums
        for (int col = 0; col < totalC.length; col++) {
            System.out.printf("\t%d", totalC[col]);
        }

        //print the 2nd diagonal sum
        System.out.printf("\t| %d\t", totalD[1]);

    }
}