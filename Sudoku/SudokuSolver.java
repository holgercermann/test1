package Sudoku;
import java.util.ArrayList;

public class SudokuSolver {

    int[][] grid = new int[9][9];
    int[][] grid_original = new int[9][9];
    int[][] grid_compare = new int[9][9];
    int[] i_rows = new int[9];
    int[] i_cols = new int[9];
    int[] i_blocks = new int[9];
    
    Boolean isValidFast(int x, int y, int val)
    {
        if ((i_rows[x] & (1<<val)) > 0)
            return false;
        if ((i_cols[y] & (1<<val)) > 0)
            return false;
        if ((i_blocks[((int)(x/3)) + ((int)(y/3)) * 3] & (1<<val))>0)
            return false;
        return true;
    }

    void deepClone(int[][] source, int[][] target)
    {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                target[x][y] = source[x][y];
            }
        }
    }

    public SudokuSolver(int[][] grid) {
        if (grid.length!=9)
            throw new IllegalArgumentException();
        for (int i = 0; i < 9; i++) 
            if (grid[i].length!=9)
                throw new IllegalArgumentException();

        deepClone(grid, this.grid);
        deepClone(grid, this.grid_original);

        resetBlockStatistics(grid);
    }

    private void resetBlockStatistics(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            i_rows[i]=0;
            i_cols[i]=0;
            i_blocks[i]=0;
        }       
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid[x][y] > 0) {
                    i_rows[x] += (1<<grid[x][y]);
                    i_cols[y] += (1<<grid[x][y]);
                    i_blocks[((int)(x/3)) + ((int)(y/3)) * 3] += (1<<grid[x][y]);                
                }
            }
        }
    }   
    

    boolean solve (int position, int ascending) {
        if (position == 81)
            return true;

        int x = position%9;
        int y = position/9;
        if (grid[x][y] == 0)
        {
            for (int val = ((ascending == 1) ? 1 : 9); val < 10 && val>0; val+=ascending) {
                if (isValidFast(x, y, val))
                {
                    grid[x][y] = val;
                    i_rows[x] += (1<<val);
                    i_cols[y] += (1<<val);
                    i_blocks[((int)(x/3)) + ((int)(y/3)) * 3] += (1<<val);
                    //System.out.println("--> " + position + " with " + val);
                    if (solve(position+1, ascending))
                        return true;
                    else
                    {
                        //System.out.println("nope");
                        i_rows[x] -= (1<<val);
                        i_cols[y] -= (1<<val);
                        i_blocks[((int)(x/3)) + ((int)(y/3)) * 3] -= (1<<val);
                        grid[x][y] = 0;
                    }
                }
            }
        }
        else{
            if (solve(position+1, ascending))
                return true;
        }
        return false;
    }


    Boolean isValidGrid()
    {
        ArrayList<Integer> rows = new ArrayList<Integer>();
        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> block = new ArrayList<Integer>();
        //rows && columns
        for (int i = 0; i < 9; i++) {
            rows.clear();
            cols.clear();
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] > 9) {
                    System.out.println("position " + i + "|" + j + " is invalid");
                    return false;
                }
                if (grid[i][j] > 0) {
                    if (rows.contains(grid[i][j]))
                    {
                        System.out.println("row " + i + " has duplicates");
                        return false;
                    }
                    else
                        rows.add(grid[i][j]);
                }
                if (grid[j][i] > 0) {
                    if (cols.contains(grid[j][i]))
                    {
                        System.out.println("column " + i + " has duplicates");
                        return false;
                    }
                    else
                        cols.add(grid[j][i]);
                }
            }
        }
        //Blocks
        for (int b_x = 0; b_x < 3; b_x++) {
            for (int b_y = 0; b_y < 3; b_y++) {
                block.clear();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (grid[b_x * 3 + i][b_y * 3 + j] > 0) {
                            if (block.contains(grid[b_x * 3 + i][b_y * 3 + j]))
                            {
                                System.out.println("block " + b_x + "|" + b_y + " has duplicates");
                                return false;
                            }
                            else
                                block.add(grid[b_x * 3 + i][b_y * 3 + j]);
                        }
                    }
                }
            }
        }
        return true;
    }

    public int[][] solve() {

        
        System.out.println("Problem");
        dump();

        if (!isValidGrid())
            throw new IllegalArgumentException();

        boolean result = solve(0, 1);
        if (!result)
            throw new IllegalArgumentException();

        System.out.println("Solution 1");
        dump();
    
        deepClone(grid, grid_compare);
        deepClone(grid_original, grid);
        resetBlockStatistics(grid);

        System.out.println("Problem");
        dump();
        result = solve(0, -1);

        System.out.println("Solution 2 " + result);
        dump();
    
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != grid_compare[i][j])
                {
                    System.out.println("position " + i + "|" + j + " is ambiguous");
                    throw new IllegalArgumentException();
                }
            }
        }

        return grid;
    }


    private void dump() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + grid[i][j] + "");
            }
            System.out.println();
        }
    }

}

