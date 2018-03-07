package mix;

public class Sudoku {
    private static final int UNASSIGNED = 0;
    private static final int max_row = 9;
    private static final int max_col = 9;
    private static int count = 0;
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        int grid[][] = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        if(sudoku.solveSudoku(grid)){
            sudoku.printGrid(grid);
        }
    }

    public void printGrid(int [][]grid){
        for(int i=0;i < max_row;i++){
            for(int j=0;j < max_col;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean solveSudoku(int [][]grid){
        int row_col[] =new int[2];
        if(!FindUnassigned(grid,row_col)){
            return true;
        }
        int row =row_col[0], col = row_col[1];
        for(int i = 1;i <= max_row;i++){
            count++;
            if(isSafe(grid, row, col, i)){
                grid[row][col] = i;
                if(solveSudoku(grid)){
                    return true;
                }
                grid[row][col] = UNASSIGNED;
            }
        }
        return false;
    }

    private boolean isSafe(int[][] grid, int row, int col, int i) {
        return !AssignedToRow(grid, row, i) &&
                !AssignedToColumn(grid, col, i) &&
                !AssignedtoSubGrid(grid, row - row%3, col - col%3, i );
    }

    private boolean AssignedtoSubGrid(int[][] grid, int row, int col, int number) {
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                count++;
                if(grid[i+row][j+col] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean AssignedToColumn(int[][] grid, int col, int number) {
        for(int i =0;i < max_row;i++){
            count++;
            if(grid[i][col] == number){
                return true;
            }
        }
        return false;
    }

    private boolean AssignedToRow(int[][] grid, int row, int number) {
        for(int i =0;i < max_col;i++){
            count++;
            if(grid[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private boolean FindUnassigned(int [][]grid, int row_col[]){
        for(int i =0;i < max_row;i++){
            for(int j=0;j < max_col;j++){
                count++;
                if(grid[i][j] == UNASSIGNED){
                    row_col[0] = i;
                    row_col[1] = j;
                    return true;
                }
            }
        }
        return false;
    }
}
