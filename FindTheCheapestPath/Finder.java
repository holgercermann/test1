package FindTheCheapestPath;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Point;

enum Direction {
    UP    {public String toString() {return "up";} },
    DOWN  {public String toString() {return "down";} },
    LEFT  {public String toString() {return "left";} },
    RIGHT {public String toString() {return "right";} }
}
class TaskElem  {
    int x,y,last_x,last_y;
    Direction dir;
    public TaskElem(int x, int y, int last_x, int last_y, Direction dir) {
        this.x = x;        this.y = y;        this.last_x = last_x;        this.last_y = last_y;       this.dir = dir;
    }
}

class CircularList {
    int len = 0, posEnd = -1, posStart = -1, taskArrayLen = 100000;
    HashSet<Long> containList = new HashSet<>(5000);
    TaskElem[] taskArray;
    CircularList(int len) {
        taskArrayLen = len;
        taskArray = new TaskElem[taskArrayLen];
    }

    public void add(TaskElem taskElem) {
        Long hash = taskElem.x * (long)1000000 + (long)taskElem.y  * (long)10000 +  (long)taskElem.last_x  * (long)100 +  (long)taskElem.last_y ;
        if (!containList.add(hash)) return;
        if (++len==taskArrayLen) throw new RuntimeException();
        posEnd = (posEnd + 1) % taskArrayLen;
        taskArray[posEnd] = taskElem;
    }
    public boolean isEmpty() {
        return len == 0;
    }
    public TaskElem removeFirst() {
        if (len==0) throw new RuntimeException();        
        len--;
        posStart = (posStart + 1) % taskArrayLen;
        Long hash = (long)taskArray[posStart].x * (long)1000000 + (long)taskArray[posStart].y  * (long)10000 + (long)taskArray[posStart].last_x  * (long)100 + (long)taskArray[posStart].last_y;
        containList.remove(hash);
        return taskArray[posStart];
    }

}

public class Finder {    
    static int[][] originalMap;
    static int[][] aggregatedMap;
    static int[][] trackBack_x = new int[300][300];
    static int[][] trackBack_y = new int[300][300];
    static Direction[][] trackBack_Dir= new Direction[300][300];
    static int pruningLimit = 0;
    static Point start;
    static Point finish;
    static CircularList q;

    static List<String> cheapestPath(int[][] t, Point s, Point f) {
        finish=f;
        start=s;
        //System.out.println(start.toString() + " -> " + finish.toString() + " @ " + t.length + ":" + t[0].length);  
        //for (int i = 0; i < t.length; i++) { for (int j = 0; j < t[0].length; j++) System.out.print("\t" + t[i][j]); System.out.println(); }

        if (finish.equals(start)) 
            return (List<String>)new ArrayList<String>();
        pruningLimit = 0;
        originalMap = t;
        aggregatedMap = new int[t.length][t[0].length];
        q = new CircularList(t.length * t[0].length * 2 + 100);
        q.add(new TaskElem(start.x, start.y, -1, -1, null));
        do {
            TaskElem current = q.removeFirst();
            traverse(current.x, current.y, current.last_x, current.last_y, current.dir);
        } while (!q.isEmpty());
        List<String> erg = new ArrayList<String>();
        collect(start, finish.x, finish.y, erg);
        //System.out.println("Result: " + erg.toString()); System.out.println(" Cost = " + aggregatedMap[f.x][f.y]);
        //for (int i = 0; i < t.length; i++) { for (int j = 0; j < t[0].length; j++) System.out.print("\t" + aggregatedMap[i][j]); System.out.println(); }
        return erg;
    }

    private static void collect(Point start, int x, int y, List<String> erg )
    {
        if (!(start.x == x && start.y == y))
        {
            erg.add(0, trackBack_Dir[x][y].toString());
            collect(start, trackBack_x[x][y], trackBack_y[x][y], erg);
        }
    }

    private static void traverse(int x, int y, int old_x, int old_y, Direction dir) {
        //if ((y == 6 && x == 0) || (y == 6 && x == 1)) System.out.println(" ... [" + old_x + "," + old_y + "] " + " --> [" + x + "," + y + "] - " + dir.toString() + " = " + cost + " -> " + (cost + originalMap[x][y]) + ", is " + aggregatedMap[x][y]);
        int cost = (old_x<0 ? 0 : aggregatedMap[old_x][old_y]);
        int newCost = cost + originalMap[x][y];
        if (pruningLimit > 0 && newCost > pruningLimit)            
            return;
        if ((aggregatedMap[x][y] > 0) && (aggregatedMap[x][y] <= newCost))
            return;
        aggregatedMap[x][y] = newCost;
        trackBack_x[x][y] = old_x;
        trackBack_y[x][y] = old_y;
        trackBack_Dir[x][y] = dir;
        if (finish.x == x && finish.y == y)
        {
            pruningLimit = newCost;
        } else {
            if (x>0) 
                q.add(new TaskElem(x - 1, y, x, y, Direction.UP));
            if (y>0) 
                q.add(new TaskElem(x, y - 1, x, y, Direction.LEFT));
            if (x<originalMap.length-1) 
                q.add(new TaskElem(x + 1, y, x, y, Direction.DOWN));
            if (y<originalMap[0].length-1) 
                q.add(new TaskElem(x, y + 1, x, y, Direction.RIGHT));
        
        }
    }
}