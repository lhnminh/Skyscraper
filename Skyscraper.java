import java.util.Scanner;

public class Skyscraper {
/* Testing for 7 x 7 
7 1 5 6 3 2 4  
5 4 3 7 6 1 2 
6 5 1 2 4 7 3 
3 7 4 1 2 5 6 
2 3 6 5 7 4 1 
4 2 7 3 1 6 5 
1 6 2 4 5 3 7 

//FIXME: General overview in README

Upper bound of dimension

*/
    private static int puzzle[][]; // variable for the puzzle

    public static void print(int[][] puzzle){
        // This method prints out the pizzle without visibility
        for (int i = 0; i < puzzle.length; ++i){
            for (int j = 0; j < puzzle.length; ++j){
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();   
        }
    }

    public static boolean verifyPlacement(int[][] x){
        // This method verify the placement of the puzzle
        boolean stat = true;
        for (int i = 0; i < x.length; ++ i){ //This for loop represent the rows (iterating through rows)
            for (int j = 0; j < x.length; ++j){ //This for loop represent the columns (iterating through columns)
                for(int l = 0; l < x.length; ++l){ //This for loop represent the item from the columns/rows
                    if (x[i][j] == x[i][l] && j != l){  //iterating through each item of a rows
                        stat = false;
                    }
                    if (x[j][i] == x[l][i] && l != j){ //iterating through each item of a column
                        stat = false;
                    }
                    if (x[i][j] > x.length || x[j][i] > x.length){ //Checking whether it is larger than the dimensioin
                        stat = false;
                    }
                }
            }
        }

        return stat;
    }

    public static void printWithVisibility(int[][] puzzle, int[][] s){
        System.out.println(); // A new line to separate from the original 
        System.out.print("  "); // Spacing for the top row
        for (int i = 0; i < puzzle.length; ++i){
            System.out.print(s[i][0] + " ");
        }
        System.out.println();


        System.out.print("  "); //Printing the small space before the dashes

        for (int i = 0; i < (puzzle.length*2 - 1 ); ++i){
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < puzzle.length; ++i){
            System.out.print(s[i][2] + "|");
            for (int j = 0; j < puzzle.length; ++j){
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.print("|" + s[i][3]);
            System.out.println();   
        }


        System.out.print("  "); //Printing the small space before the dashes

        for (int i = 0; i < puzzle.length*2 - 1; ++i){
            System.out.print( "-");
        }

        System.out.println();

        System.out.print("  ");

        for (int i = 0; i < puzzle.length; ++i){
            System.out.print(s[i][1] + " ");
        }
    }


    private static int[][] visibilityScore(int[][] x){
        int i = 0; //variable for for loop
        int j = 0;
        int lmax; // variable for the CURRENT tallest building from the left (hence lmax) the same for others
        int l;  // varaible for the FINAL number for the solution of the left (hence l)
        int rmax;
        int r;

        int upmax; //variable for the CURRENT tallest building from the up to down 
        int unmax;
        int len = x.length - 1; //for shorter index
        int up;  
        int un;
        int[][] sol = new int[x.length][4]; //Arrays of arrys for the edge numbers 

        
        for (i =0; i < x.length; ++i){
            r = 1; // minimum edge number is 1
            l = 1;
            up = 1;
            un = 1;

            lmax = x[i][0];  //the respective max for each direction of view
            rmax = x[i][len];
            upmax = x[0][i];
            unmax = x[len][i];
            
            for (j = 0; j < x.length; ++j){
                if (lmax < x[i][j]){ //iterating from left to right
                    lmax = x[i][j];
                    l += 1;    
                }
                if (rmax < x[i][len - j]){ //iterating from right to left
                    rmax = x[i][len - j];
                    r += 1;
                }
                if (upmax < x[j][i]){  //iterating from up to down
                    upmax = x[j][i];
                    up += 1;   
                }
                if (unmax < x[len - j][i]){  //iterating from down to up
                    unmax = x[len-j][i];
                    un += 1;
                }
            }

            sol[i][0] = up;  //I set up the arrays to have the upper edge number to be 0, others are placed accordingly
            sol[i][1] = un;
            sol[i][2] = l;
            sol[i][3] =  r;
        }
        return sol;
    }


    public static void loadPuzzle(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the dimension of the puzzle");
        int dim = scnr.nextInt();
        puzzle = new int[dim][dim];
        System.out.println("Please enter the puzzle solution");
        for (int i = 0; i < dim; ++i){
            for (int j = 0; j < dim; ++j){
                int add = scnr.nextInt();
                puzzle[i][j] = add; //done with the puzzle
            }
        }
    }
    public static void main(String[] args) {

        loadPuzzle();
        int[][] vscore = visibilityScore(puzzle);
        System.out.println();
        System.out.println("You inputed: ");
        System.out.println();
        print(puzzle);
        System.out.println();

        if (verifyPlacement(puzzle)){
            System.out.println("The puzzle's placement is correct");
            printWithVisibility(puzzle, vscore);
        }
        else{
            System.out.println("The puzzle's placement is incorrect");
        }
    }
}
