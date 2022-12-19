

public class Matrix {
    int x;
    int y;
    Integer[][] matrix;

    public Matrix(int width, int height) {
        x=width;
        y=height;
        matrix = new Integer[x][y];
        clearMarkersFromMatrix(0,0);
        generateRandomFilling();
    }

    private void clearMarkersFromMatrix(int setTo, int reservedTil) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == null || matrix[i][j]>reservedTil) 
                    matrix[i][j] = setTo;
            }
        }
    }

    private void generateRandomFilling() {
        int errCtr = 0;
        while (true) {
            int i = (int)(Math.random() * x);
            int j = (int)(Math.random() * y);
            if (matrix[i][j] != 1 ) {
                boolean success = isSettingBlockerHerePossible(i,j);
                if (success) {
                    matrix[i][j] = 1;
                    System.out.println();
                    errCtr=0;
                }
                else
                    errCtr++;
            }
            if (errCtr > 400)
            {
                cleanResidualSpots();
                return;
            }
        }
    }

    private void cleanResidualSpots() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] != 1  && isSettingBlockerHerePossible(i,j))
                    matrix[i][j] = 1;
            }
        }
    }

    private boolean isSettingBlockerHerePossible(int i, int j) {
       clearMarkersFromMatrix(0,1);
       matrix[i][j] = 2;
       return isStillReachable(0, 0);
    }
    private boolean isStillReachable(int i, int j) {
        boolean result = false;
        if ( matrix[i][j] > 0)
            return false;
        matrix[i][j] = 3;
            if (i==x-1 && j==y-1)
            return true;
        if (i > 0 )  
            result |= isStillReachable(i-1,j);  
        if (j > 0)  
            result |= isStillReachable(i,j-1);  
        if (i < x-1)  
            result |= isStillReachable(i+1,j);  
        if (j < y-1)  
            result |= isStillReachable(i,j+1);  
        //if (result) System.out.print("[" + i + "," + j + "] ");
        return result;
    }

    public static void main(String[] args) {
        new Matrix(40,130).printMatrixToConsole();
    }
    

    private void printMatrixToConsole() {
        clearMarkersFromMatrix(0,1);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(((matrix[i][j] == 0)?"X":"."));
            }
            System.out.println();
        }
    }
}
