package LinearAlgebraProject;

import java.util.Scanner;
import java.util.Arrays;
import java.text.DecimalFormat;

public class methods {
    //This method will take some SQUARE 2D matrix that is 2x2 and solve it to RREF using Gauss-Jordan Elimination
    public static Fraction[][] gaussJordanEliminationFor2x2SquareMatrices(int[][] matrix){
        Fraction[][] rrefMatrix = new Fraction[2][2];

        //copy the inputed matrix
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                rrefMatrix[i][j] = new Fraction(matrix[i][j], 1);
            }
        }
        // If the top-left element is 0, swap the rows
        if (rrefMatrix[0][0].numerator == 0) {
            Fraction[] temp = rrefMatrix[0];
            rrefMatrix[0] = rrefMatrix[1];
            rrefMatrix[1] = temp;
        }

        // Make the top-left element 1
        if (rrefMatrix[0][0].numerator != rrefMatrix[0][0].denominator) {
            Fraction divisor = rrefMatrix[0][0];
            rrefMatrix[0][0] = new Fraction(rrefMatrix[0][0].numerator * divisor.denominator, rrefMatrix[0][0].denominator * divisor.numerator);
            rrefMatrix[0][1] = new Fraction(rrefMatrix[0][1].numerator * divisor.denominator, rrefMatrix[0][1].denominator * divisor.numerator);
        }

        // Make the bottom-left element 0
        if (rrefMatrix[1][0].numerator != 0) {
            Fraction factor = rrefMatrix[1][0];
            rrefMatrix[1][0] = new Fraction(rrefMatrix[1][0].numerator * factor.denominator - rrefMatrix[0][0].numerator * factor.numerator,
                rrefMatrix[1][0].denominator * factor.denominator);
            rrefMatrix[1][1] = new Fraction(rrefMatrix[1][1].numerator * factor.denominator - rrefMatrix[0][1].numerator * factor.numerator,
                rrefMatrix[1][1].denominator * factor.denominator);
        }

        // Make the bottom-right element 1
        if (rrefMatrix[1][1].numerator != rrefMatrix[1][1].denominator && rrefMatrix[1][1].numerator != 0) {
            Fraction divisor = rrefMatrix[1][1];
            rrefMatrix[1][0] = new Fraction(rrefMatrix[1][0].numerator * divisor.denominator, rrefMatrix[1][0].denominator * divisor.numerator);
            rrefMatrix[1][1] = new Fraction(rrefMatrix[1][1].numerator * divisor.denominator, rrefMatrix[1][1].denominator * divisor.numerator);
        }

        // Make the top-right element 0
        if (rrefMatrix[0][1].numerator != 0) {
            Fraction factor = rrefMatrix[0][1];
            rrefMatrix[0][0] = new Fraction(rrefMatrix[0][0].numerator * factor.denominator - rrefMatrix[1][0].numerator * factor.numerator,
                    rrefMatrix[0][0].denominator * factor.denominator);
            rrefMatrix[0][1] = new Fraction(rrefMatrix[0][1].numerator * factor.denominator - rrefMatrix[1][1].numerator * factor.numerator,
                    rrefMatrix[0][1].denominator * factor.denominator);
        }
            return rrefMatrix;
    }

    //This method will take some SQUARE 2D matrix and solve it to RREF using Gauss-Jordan Elimination
    public static int[][] gaussJordanEliminationForSquareMatrices(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
    
        // Perform Gaussian elimination
        for (int i = 0; i < Math.min(numRows, numCols); i++) {
            // Find pivot row (row with the largest absolute value in the current column)
            int pivotRow = i;
            for (int j = i + 1; j < numRows; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivotRow][i])) {
                    pivotRow = j;
                }
            }
            // Swap current row with pivot row (if necessary)
            if (pivotRow != i) {
                int[] temp = matrix[i];
                matrix[i] = matrix[pivotRow];
                matrix[pivotRow] = temp;
            }
            // Check for zero pivot element
            if (matrix[i][i] == 0) {
                // Search for a non-zero pivot element in the same column
                boolean found = false;
                for (int j = i + 1; j < numRows; j++) {
                    if (matrix[j][i] != 0) {
                        // Swap rows
                        int[] temp = matrix[i];
                        matrix[i] = matrix[j];
                        matrix[j] = temp;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // No non-zero pivot element found in this column, move on to the next column
                    continue;
                }
            }
            // Reduce current row using pivot row
            int pivot = matrix[i][i];
            if (pivot != 0) {
                int gcd = gcd(matrix[i][i], pivot);
                matrix[i][i] /= gcd;
                pivot /= gcd;
                    for (int j = i + 1; j < numCols; j++) {
                        int numerator = matrix[i][j] * pivot;
                        int denominator = matrix[i][i];
                        int tempGcd = gcd(numerator, denominator);
                        matrix[i][j] = numerator / tempGcd;
                        matrix[i][i] = denominator / tempGcd;
                    }
                    for (int j = 0; j < numRows; j++) {
                        if (j != i) {
                            int factor = matrix[j][i];
                                for (int k = 0; k < numCols; k++) {
                                    int numerator = factor * matrix[i][k];
                                    int denominator = matrix[i][i];
                                    int tempGcd = gcd(numerator, denominator);
                                    matrix[j][k] -= numerator / tempGcd;
                                    matrix[j][i] = 0;
                                 }
                        }
                    }
            }
        }
        return matrix;
    }

    //This method will take some NONSQUARE 2D matrix and solve it to RREF using Gauss-Jordan Elimination
    public static void gaussJordanEliminationForNonSquareMatrices(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        double[][] doubleMatrix = new double[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                doubleMatrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < rowCount; i++) {
            if (doubleMatrix[i][i] == 0) {
                for (int j = i + 1; j < rowCount; j++) {
                    if (doubleMatrix[j][i] != 0) {
                        double[] temp = doubleMatrix[i];
                        doubleMatrix[i] = doubleMatrix[j];
                        doubleMatrix[j] = temp;
                        break;
                    }
                }
            }

            if (doubleMatrix[i][i] != 0) {
                double divisor = doubleMatrix[i][i];
                for (int j = 0; j < colCount; j++) {
                    doubleMatrix[i][j] /= divisor;
                }

                for (int j = 0; j < rowCount; j++) {
                    if (j != i) {
                        double factor = doubleMatrix[j][i];
                        for (int k = 0; k < colCount; k++) {
                            doubleMatrix[j][k] -= factor * doubleMatrix[i][k];
                        }
                    }
                }
            }
        }

        printSolution(doubleMatrix);
    }
    public static void PrintSolution(double[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        DecimalFormat df = new DecimalFormat("#.###");

        boolean hasUniqueSolution = true;
        boolean hasNoSolution = false;
        int freeVariableCount = 0;

        for (int i = 0; i < rowCount; i++) {
            boolean allZeroCoefficients = true;
            for (int j = 0; j < colCount - 1; j++) {
                if (matrix[i][j] != 0) {
                    allZeroCoefficients = false;
                    break;
                }
            }

            if (allZeroCoefficients) {
                if (matrix[i][colCount - 1] != 0) {
                    hasNoSolution = true;
                }
                freeVariableCount++;
            }
        }

        if (freeVariableCount > 0) {
            hasUniqueSolution = false;
        }

        if (hasUniqueSolution) {
            System.out.println("Unique solution:");
            for (int i = 0; i < rowCount; i++) {
                System.out.println("x" + (i + 1) + " = " + df.format(matrix[i][colCount - 1]));
            }
        } else if (hasNoSolution) {
            System.out.println("No solution.");
            printMatrix(matrix);
        } else {
            System.out.println("Infinite solutions:");
            printMatrix(matrix);
        }
    }
    public static void PrintMatrix(double[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        DecimalFormat df = new DecimalFormat("#.###");

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(df.format(matrix[i][j]) + " ");
            }
            System.out.println();
         }
        }
    
    //Check if a matrix is square
    public static boolean isSquare(int[][] matrixx){
        int rows = matrixx.length;
        int cols = matrixx[0].length;
        //If the amount of rows is not equal to the amount of columns.. 
        if (rows != cols) {
            return false; //Then we have diff size rows and cols so the matrix is not square
        }
        //This for loop is to make sure every row is the same size because then that wouldn't be square. For example, 
        // 1 1 0        As you can see row 1 has 3 inputs while row 2 has 2 inputs. This for loop will check the length of the rows and return false 
        // 1 1          if all the rows have the same length as the number of columns
        for (int i = 0; i < rows; i++) {
            if (matrixx[i].length != cols) {
                return false;
            }
        }
        return true; //Otherwise return true
    }
    //Check if a matrix is 2x2
    public static boolean isTwobyTwo(int[][] matrix){
        //this checks If the matrix is null or has a number of rows different from 2 OR either of the two rows with a number of columns different from 2.
        if(matrix==null || matrix.length != 2 || matrix[0].length != 2 || matrix[1].length != 2){
            //if ANY of those conditions produce true then we return false
            return false;
        } 
        //otherwise we check if the lenght of the first row of the matrix is equal to the length of the second row. If this is true, then we passed all conditions and we return
        // true which is what would come out of this condition anyways. This indicates the matrix is 2x2
        return matrix[0].length == matrix[1].length;
    }
    
    //Prints the 2D matrix after the user inputs the values
    public static void DisplayMatrixBeforeAnything(int[][] matr, int rows, int columns){
    System.out.println("Matrix you inputed: ");
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                System.out.print(matr[i][j] + " ");
            }
            System.out.println();

        }
    }
    //Method that allows the USER to input all the values of the matrix
    public static void LetUserPopulateArray(int[][] array) {
        Scanner scanner = new Scanner(System.in); //Using a scanner to get user input
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("Enter a value for array[" + i + "][" + j + "]: "); //Entering a value for every single [ x ] place in the 2D array / matrix.
                array[i][j] = scanner.nextInt();
            }
        }
    }
    
    //Method that will print the matrix after being converted to RREF.
    public static void printaMatrix(int[][] rref){
        for(int i=0; i<rref.length; i++){
            for(int j=0; j<rref[i].length; j++){
                System.out.print(rref[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Method that will compute the determinant of a 2x2 matrix. Keep in mind this method will only be called if matrix is 2x2.
    public static void TwoByTwoDeterminant(int[][] matrix){
        System.out.println();
        System.out.println("We can compute the determinant by doing d*a - b*a");
        int twobytwodeterminant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]); //d * a - b * a , and store it into some int variable
        System.out.println("The Determinant of the 2x2 Matrix is: " + twobytwodeterminant); //print out the determinant
    }
    //Method that will compute the inverse matrix of a 2x2 matrix. Keep in mind this method will only be called if matrix is 2x2.
    public static void InverseOfTwoByTwo(int[][] matrix){
        //first we calculate the determinant by doing d * a - b * a and store it into a variable.
        int determinant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        //We check if det = 0 because if it is then the matrix is not invertible
        if(determinant==0){
            System.out.println("The matrix is not invertible.");
            return;
        }

        //create the empty 2x2 matrix which will be the inverse of the inputed matrix
        double[][] inversematrix = new double [2][2];
        // Switch a and d ... negate b and c of the inputed matrix and set those as the values in the inversed matrix
        inversematrix[0][0] = matrix[1][1];
        inversematrix[0][1] = -matrix[0][1];
        inversematrix[1][0] = -matrix[1][0];
        inversematrix[1][1] = matrix[0][0];

        double scalar = 1.0/determinant; //Scalar is 1 over the determinant we just computed
        
        //multiply the 2x2 matrix we created by the Scalar 
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                inversematrix[i][j] *= scalar; 
            }
        }

        //Now print the inverse matrix
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                int numerator = (int) (inversematrix[i][j] * 1000); //multiply by 1000 to get the numerator of a fraction with denominator 1000
                int denominator = 1000; //set the denominator to 1000
                int gcd = gcd(numerator, denominator); //find the greatest common divisor of numerator and denominator
                numerator /= gcd; //divide numerator and denominator by the gcd to simplify the fraction
                denominator /= gcd;
                System.out.print(numerator + "/" + denominator + "  ");
            }
            System.out.println();
        }
    }
    //Method to calculate the GCD of two integers so I can use for my inverse matrix solution.
    public static int gcd(int a, int b){
        //Here we use recursion
        //Base case states that when b is equal to 0, we return a because the GCD must be 0.
        if (b == 0){ 
            return a;
        }
        //Otherwise we return the greatest common divisor by passing b into the function and the remainder of a divided by b. This will continue until the base case is reached.
        //This will keep working unitl the remainder is 0. once it's 0, the base case is met and the recursion loop stops.
        return gcd(b, a % b);
    }
    //Method to calculate the determinant of matrices bigger than 2x2.
    public static void Calculate_Determinant_Of_BiggerThan2x2_Square_Matrices(int[][] matrix) {
        int n = matrix.length; //Find the num of rows and set it equal to the int variable n
        int determinant = 1; //Declare a determinant variable
    
        // Transform matrix to triangular form using Gaussian elimination
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] == 0) { // if pivot is zero
                // find a non-zero element in the same column below the pivot
                int k = i + 1;
                while (k < n && matrix[k][i] == 0) { 
                    k++;
                }
    
                if (k == n) { // if no such element is found, that means the determinant is 0 so we set the determinant equal to 0 and break out of the loop.
                    determinant = 0;
                    break;
                } else { // Else, swap rows i and k to make the pivot element not zero 
                    int[] temp = matrix[i];
                    matrix[i] = matrix[k];
                    matrix[k] = temp;
                    determinant = -determinant; //Multiply the determinant by -1 since a row swap changes the sign of the determinant (Section 2.3 - Computing Determinants by Row Reduction)
                }
            }
            //Now we can use the pivot element to eliminate the entries below it in the same column. (Clean up the column)
            for (int j = i + 1; j < n; j++) {
                int factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < n; k++) {
                    matrix[j][k] = matrix[j][k] - factor * matrix[i][k];
                }
            }
            
            //Multiply the determinant by the pivot element after each row operation and set that equal to the determinant after the loop ends.
            determinant *= matrix[i][i];
        }
    
        //We finish the loop so we now have determined our determinant.. So we print the determinant
        System.out.println("The Determinant of the Matrix is: " + determinant);
    }
    //Method to transpose a Square 2x2 matrix
    public static int[][] TransposeSquareMatrix2x2(int[][] b){

        int[][] transposedmatrix = new int[2][2];
        transposedmatrix[0][0] = b[0][0];
        transposedmatrix[0][1] = b[1][0];
        transposedmatrix[1][0] = b[0][1];
        transposedmatrix[1][1] = b[1][1];

        return transposedmatrix;
    }
    //Method to transpose a Square matrix bigger than 2x2
    public static int[][] TransposeSquareMatrix_BiggerThan2x2(int [][] b){
        int numRows = b.length;
        int numCols = b[0].length;
        int[][] transposedmatrix = new int[numCols][numRows];

        for(int i=0; i<numCols; i++){
            for(int j=0; j<numRows; j++){
                transposedmatrix[i][j] = b[j][i];
            }
        }

        return transposedmatrix;

    }
    //Method that takes a 2D array/matrix and multiplies it by some scalar K
    public static int[][] ScalarMultiplicationOntoSquareMatrix(int[][] b, int k){

        int n  = b.length;
        int[][] multipliedmatrix = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                multipliedmatrix[i][j] = k * b[i][j];
            }
        }

        return multipliedmatrix;
    }
    //Method that transposes a nonsquare matrix
    public static int[][] TransposeOfNonSquareMatrix(int[][] b){
        int rows = b.length;
        int cols = b[0].length;

        int[][] transposedmatrix = new int[cols][rows];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                transposedmatrix[j][i] = b[i][j];
            }
        }
        return transposedmatrix;
    }
}
public class Fraction {
    int numerator;
    int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public void simplify() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
