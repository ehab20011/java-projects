//EHAB ABDALLA - PROJECT 2 - 02/25/2023
import java.util.*;

class PROJECT2_GRIDWALKER {
    public static void main(String[] args) {
        //Creating the Scanner
        Scanner input = new Scanner(System.in);
        
        // Get the number of rows and columns from the user
        System.out.print("How many rows? ");
        int RowsNum = input.nextInt();
        
        System.out.print("How many columns? ");
        int ColsNum = input.nextInt();
        
        // Create a 2D character array to represent the grid 
        char[][] grid = new char[RowsNum][ColsNum];
        
        // Fill the grid with random directions using Random() to generate random nums
        Random random = new Random();
        int target_row = random.nextInt(RowsNum); 
        int target_column = random.nextInt(ColsNum);
        
        //Now we fill the grid with random directions, with the character T in the target cell.
        for (int i = 0; i < RowsNum; i++) {
            for (int j = 0; j < ColsNum; j++) {
               //If this is the target cell, we mark it with a 'T'
                if (i == target_row && j == target_column) {
                    grid[i][j] = 'T';
                } else { //Otherwise, randomly select a direction from the char directions array 
                    char[] directions = {'U', 'D', 'L', 'R'}; //U, D, L, or R
                    int index = random.nextInt(directions.length); 
                    grid[i][j] = directions[index];
                }
            }
        }
        
        // Display the grid to the user
        System.out.println("Here is the grid:");
        for (int i = 0; i < RowsNum; i++) {
            for (int j = 0; j < ColsNum; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        
        // Get the starting position from the user
        System.out.print("Enter the starting row: ");
        int startRow = input.nextInt(); //row
        
        System.out.print("Enter the starting column: ");
        int startCol = input.nextInt(); //column
      
        // Follow the directions and mark the path with *
        int numSteps = 0; //Number of steps taken to reach T
        boolean offGrid = false; //Helps flag if we are off the grid or not
        boolean intersected = false; //Helps flag if the path intersected itself
        int currentRow = startRow; //row position of the player
        int currentCol = startCol; //column position of the player

        //Create a string Array list where I am going to store the path taken by the player
        ArrayList<String> path = new ArrayList<String>(); 
        
        //WHILE the player is still on the GRID and has not reached the character T or intersected it's own path:
        while (!offGrid && !intersected && grid[currentRow][currentCol] != 'T') {
            //We get the direction indicated by the current cell and add the current position to the path list
            char direction = grid[currentRow][currentCol];
            path.add(currentRow + "," + currentCol);
            numSteps++; //post increment the num of steps
            
            //We move the player according to the direction indicated by the current cell
            if (direction == 'U') { //UP we CurrentRow--
                currentRow--;
            } else if (direction == 'D') { //else if Down, we currentRow++
                currentRow++; 
            } else if (direction == 'L') { //To go left, we currentCol--
                currentCol--;
            } else if (direction == 'R') { //To go right, we currrentCol++
                currentCol++;
            }
            //Check using an If, if the player has gone off the grid or else if it's current position has already been visited
            if (currentRow < 0 || currentRow >= RowsNum || currentCol < 0 || currentCol >= ColsNum) {
                offGrid = true; //If the player has gone off the grid, set offGrid = true
            } else if (path.contains(currentRow + "," + currentCol)) {
                intersected = true; //If the player current position has already been visited, we set the boolean variable Intersected = true;
            }
        }
        
        // Mark the path with *
        for (String step : path) { //Iterate through each step taken by the player and store it into step
            String[] coordinates = step.split(","); //Split those steps into it's row and column coordinates
            //Convert the row and column coordinates from strings to int values using .parseInt
            int row = Integer.parseInt(coordinates[0]); 
            int col = Integer.parseInt(coordinates[1]);
            //Mark that cell in the grid with a * to indicate the player's path 
            grid[row][col] = '*';
        }
        
        // Display the final grid and message
        System.out.println("Here is the final grid:");
        for (int i = 0; i < RowsNum; i++) {
            for (int j = 0; j < ColsNum; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        
        if (offGrid) { //if user goes off the grid.. display that they went off the grid
            System.out.println("You went off the grid!");
        } else if (intersected) { //ELSE if the users path intersects itself.. display that the path intersected it self
            System.out.println("Your path intersected itself!");
        } else { //ELSE we reached T.. so we display that we reached T in whatever numSteps is set to
            System.out.println("You reached T in " + numSteps + " steps!");
        }
    }
}
    
