//EHAB ABDALLA - LINEAR ALGEBRA PROJECT
import java.util.Scanner;
import java.util.Arrays;


class Main {

    //This method will take some SQUARE 2D matrix and solve it to RREF using Gauss-Jordan Elimination
    public static int[][] Gauss_Jordan_Elimination_ONTO_SquareMatrices(int[][] matrix) {
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

    public static void Gauss_Jordan_Elimination_Onto_NONSquareMatrices(int[][] matrix) {
         //NEED TO WRITE A METHOD THAT TAKES A NON SQUARE MATRIX AND SOLVES IT TO RREF AND DISPLAY THE SOLUTION LIKE THIS [ A ] = [x][b] , Where A is mxn or nxm. X and B are sized nx1. 
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
    public static void printAfterRREF(int[][] rref){
        System.out.println("Matrix after RREF: ");
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
    
    public static void main(String[] args){
        System.out.println("Please enter # of rows: ");
        int rows;
        Scanner rinput = new Scanner(System.in);
        rows = rinput.nextInt();

        System.out.println("Please enter # of columns: ");
        int cols;
        Scanner cinput = new Scanner(System.in);
        cols = cinput.nextInt();

        int[][] matrix = new int [rows][cols];

        LetUserPopulateArray(matrix);
        DisplayMatrixBeforeAnything(matrix, rows, cols);
        System.out.println();

        if(isSquare(matrix)){
            System.out.println("This is a square matrix. Now let's see if it's two by two..");
            if(isTwobyTwo(matrix)){
                System.out.println("It is also 2x2.. So.. ");
                System.out.println();
                System.out.println("The inverse of the 2x2 Matrix: ");
                InverseOfTwoByTwo(matrix);
                TwoByTwoDeterminant(matrix);;
                System.out.println();
                printAfterRREF(Gauss_Jordan_Elimination_ONTO_SquareMatrices(matrix));
            }
            else{
                System.out.println("It is not 2x2");
                Calculate_Determinant_Of_BiggerThan2x2_Square_Matrices(matrix);
                printAfterRREF(Gauss_Jordan_Elimination_ONTO_SquareMatrices(matrix));
            }
        }

        if(!isSquare(matrix)){
            System.out.println("The matrix is not square.");
            Gauss_Jordan_Elimination_Onto_NONSquareMatrices(matrix);
        }

        
    }
}
