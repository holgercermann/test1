package BattleField;


public class BattleField {
    
    enum Direction {NONE, VERTICAL_HEAD, HORIZONTAL_HEAD}

    public static boolean fieldValidator(int[][] field) {
        
        int[] shipsCapacity = new int[] {4, 3, 2, 1};
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (field[x][y] == 1)
                {
                    Direction dir = isHead(field, x, y);
                    if (dir != Direction.NONE)
                    {
                        int len = getLength(field, x, y, dir);
                        System.out.println("Identified head on " + x + "|" + y + " with length " + len);
                        if (len>4)
                        {
                            System.out.println("ERROR: Ships length invalid: " + (len));
                            return false;
                        }
                        shipsCapacity[len-1]--;
                        if (shipsCapacity[len-1] < 0)
                        {
                            System.out.println("ERROR: Ships length on quote: " + (len));
                            return false;
                        }
                        if (!checkSurrounding(field, x, y, dir, len))
                        {
                            System.out.println("ERROR: Surrounding constraints");
                            return false;
                        }
                    }
                }
            }
                
        }
        for (int i = 0; i < shipsCapacity.length; i++) {
            if (shipsCapacity[i]>0)
            {
                System.out.println("ERROR: Not all ships placed!, len = " + (i+1));
                return false;
            }
        }
        return true;
    }

    private static boolean checkSurrounding(int[][] field, int x, int y, Direction dir, int len) {
        int xMod = 0, yMod = 0;
        if (dir == Direction.HORIZONTAL_HEAD) xMod = 1; else yMod=1;
        for (int i = -1; i < len+1; i++) {
            //1 at  field[x + i*xMod][y + i*yMod]
            for (int side = -1; side<=1;  side += 2)
            {
                int cx = x + yMod * side + i * xMod;
                int cy = y + xMod  * side+ i * yMod;
                if (cx >= 0 && cy >= 0 && cx <10 && cy<10)
                {
                    System.out.println(" .. Checking " + cx + "|" + cy + " for " + (x + i*xMod) + "|" + (y + i*yMod));
                    if (field[cx][cy] == 1)
                        return false;
                }
            }
        }
        return true;
    }

    private static int getLength(int[][] field, int x, int y, Direction dir) {
        int len=0;
        while (x<10 && y<10 && field[x][y] > 0)
        {
            len++;
            if (dir == Direction.HORIZONTAL_HEAD) 
                x++; 
            else 
                y++;
        }
        return len;
    }

    private static Direction isHead(int[][] field, int x, int y) {
        if (x>0 && field[x-1][y] > 0)   return Direction.NONE;
        if (y>0 && field[x][y-1] > 0)   return Direction.NONE;
        if (x<9 && field[x+1][y] > 0)   return Direction.HORIZONTAL_HEAD;
        if (y<9 && field[x][y+1] > 0)   return Direction.VERTICAL_HEAD;
        return Direction.VERTICAL_HEAD;
    }
}