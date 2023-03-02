package del;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Point;

class TaskElem {
    public TaskElem(int x, int y, int last_x, int last_y, int cost, Direction dir) {
        this.x = x;
        this.y = y;
        this.last_x = last_x;
        this.last_y = last_y;
        this.cost = cost;
        this.dir = dir;
    }
    int x;
    int y;
    int last_x;
    int last_y;
    int cost;
    Direction dir;

    //@Override
    public int compareTo(Object o) {        return Integer.compare(cost, ((TaskElem)o).cost);    }
}

enum Direction {
    UP    {public String toString() {return "up";} },
    DOWN  {public String toString() {return "down";} },
    LEFT  {public String toString() {return "left";} },
    RIGHT {public String toString() {return "right";} }
}
public class IterativeFinder {
    
    static int[][] originalMap;
    static int[][] aggregatedMap;
    static int[][] trackBack_x = new int[500][500];
    static int[][] trackBack_y = new int[500][500];
    static Direction[][] trackDir= new Direction[500][500];
    static int pruningLimit = 0;
    static Point start;
    static Point finish;

    static LinkedList<TaskElem> q;

    static List<String> cheapestPath(int[][] t, Point s, Point f) {
        finish=f;
        start=s;
        //System.out.println(start.toString() + " -> " + finish.toString() + " @ " + t.length + ":" + t[0].length);  
        /*for (int i = 0; i < t.length; i++)         {
            for (int j = 0; j < t[0].length; j++) System.out.print("\t" + t[i][j]);
            System.out.println();    
        }*/

        if (finish.equals(start)) 
            return (List<String>)new ArrayList<String>();

        pruningLimit = 0;
        originalMap = t;
        aggregatedMap = new int[t.length][t[0].length];
        //trackBack_x =   new int[t.length][t[0].length];
        //trackBack_y =   new int[t.length][t[0].length];
        //trackDir =      new Direction[t.length][t[0].length];
        q = new LinkedList<>();
        q.add(new TaskElem(start.x, start.y, start.x, start.y, 0, null));
        do {
            TaskElem current = q.removeFirst();
            traverse(current.x, current.y, current.last_x, current.last_y, current.cost, current.dir);
        } while (!q.isEmpty());

        List<String> erg = new ArrayList<String>();
        collect(start, finish.x, finish.y, erg);
        //System.out.println("Result: " + erg.toString());
        return erg;
    }

    private static void collect(Point start, int x, int y, List<String> erg )
    {
        if (!(start.x == x && start.y == y))
        {
            erg.add(0, trackDir[x][y].toString());
            collect(start, trackBack_x[x][y], trackBack_y[x][y], erg);
        }
    }

    private static void traverse(int x, int y, int old_x, int old_y, int cost, Direction dir) {
        int newCost = cost + originalMap[x][y];
        if (pruningLimit>0 && pruningLimit < newCost)
            return;
        if ((aggregatedMap[x][y] > 0) && (aggregatedMap[x][y] <= newCost))
            return;
        aggregatedMap[x][y] = newCost;
        trackBack_x[x][y] = old_x;
        trackBack_y[x][y] = old_y;
        trackDir[x][y] = dir;
        if (finish.x == x && finish.y == y)
        {
            pruningLimit = newCost;
        } else {
        
            if (x>0) 
                q.add(new TaskElem(x - 1, y, x, y, newCost, Direction.UP));
            if (y>0) 
                q.add(new TaskElem(x, y - 1, x, y, newCost, Direction.LEFT));
            if (x<originalMap.length-1) 
                q.add(new TaskElem(x + 1, y, x, y, newCost, Direction.DOWN));
            if (y<originalMap[0].length-1) 
                q.add(new TaskElem(x, y + 1, x, y, newCost, Direction.RIGHT));
        
        }
    }
}