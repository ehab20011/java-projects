//EHAB ABDALLA - LINEAR ALGEBRA PROJECT
package LinearAlgebraProject;

import LinearAlgebraProject.Methods;
import java.util.Scanner;
import java.util.Arrays;
import java.text.DecimalFormat;

class Main {
    public static void main(String[] args){
        System.out.println("Please enter # of rows: ");
        int numRows = new Scanner(System.in).nextInt();

        System.out.println("Please enter # of columns: ");
        int numColumns = new Scanner(System.in).nextInt();

        int[][] matrix = new int [numRows][numColumns];

        methods.LetUserPopulateArray(matrix);
        methods.DisplayMatrixBeforeAnything(matrix, numRows, numColumns);
        System.out.println();

        if (methods.isSquare(matrix)) { //Check if it's a square
            System.out.println("This is a square matrix. Let's see if it's 2x2...");
            //If it's 2x2
            if (methods.isTwobyTwo(matrix)) {
                System.out.println("It is 2x2. What would you like to do with the matrix?");
                System.out.println("1. Calculate the determinant");
                System.out.println("2. Convert the matrix to reduced row echelon form (RREF)");
                System.out.println("3. Calculate the inverse of the matrix");
                System.out.println("4. Calculate the transpose of the matrix");
                System.out.println("5. Multiply the matrix by a scalar");
                System.out.println();
                int input = 0;
                Scanner inputt = new Scanner(System.in);
                input = inputt.nextInt();
                switch (input) {
                    case 1:
                        methods.TwoByTwoDeterminant(matrix);
                        break;
                    case 2:
                        int[][] rrefMatrix = methods.gaussJordanEliminationFor2x2SquareMatrices(matrix);
                        methods.printaMatrix(rrefMatrix);
                        break;
                    case 3:
                        System.out.println("The inverse of the 2x2 matrix:");
                        methods.InverseOfTwoByTwo(matrix);
                        break;
                    case 4:
                        System.out.println("The transpose of the input matrix:");
                        methods.printaMatrix(methods.TransposeSquareMatrix2x2(matrix));
                        break;
                    case 5:
                        System.out.println("Please enter your scalar value:");
                        int scalar = new Scanner(System.in).nextInt();
                        methods.printaMatrix(methods.ScalarMultiplicationOntoSquareMatrix(matrix, scalar));
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            }
            else{ //Else if its not 2x2
                System.out.println("The matrix is not 2x2.");
                System.out.println("What would you like to do with the matrix?");
                System.out.println("1. Calculate the determinant");
                System.out.println("2. Convert the matrix to reduced row echelon form (RREF)");
                System.out.println("3. Calculate the transpose of the matrix");
                int input = new Scanner(System.in).nextInt();

                 switch (input) {
                    case 1:
                        methods.Calculate_Determinant_Of_BiggerThan2x2_Square_Matrices(matrix);
                        break;
                    case 2:
                        int[][] rrefMatrix = methods.gaussJordanEliminationForSquareMatrices(matrix);
                        methods.printaMatrix(rrefMatrix);
                        break;
                    case 3:
                        System.out.println("The transpose of the input matrix:");
                        methods.printaMatrix(methods.TransposeSquareMatrix_BiggerThan2x2(matrix));
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            }
        }

        //If its not square at all..
        if(!methods.isSquare(matrix)){
            System.out.println("The matrix is not square.");
            System.out.println("What would you like to do with the matrix?");
            System.out.println("1. Convert the matrix to reduced row echelon form (RREF)");
            System.out.println("2. Transpose the matrix");
            int input = new Scanner(System.in).nextInt();

            switch(input){
                case 1:
                methods.gaussJordanEliminationForNonSquareMatrices(matrix);
                case 2: 
                methods.printaMatrix(methods.TransposeOfNonSquareMatrix(matrix));
            }
         }

        
    }
}
