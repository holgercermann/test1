package FindTheCheapestPath;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class RecursiveFinder {
    
    static int[][] originalMap;
    static int[][] aggregatedMap;
    static Point[][] trackBack;
    static String[][] trackDir;
    static int pruningLimit = 0;
    static Point finish;

    static List<String> cheapestPath(int[][] t, Point start, Point f) {
        //System.out.println(start.toString() + " -> " + f.toString());  
        /*for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) System.out.print("\t" + t[i][j]);
            System.out.println();    
        } /* */
        if (f.equals(start)) 
            return (List<String>)new ArrayList<String>();

        finish = f;
        pruningLimit = 0;
        originalMap = t;
        aggregatedMap = new int[t.length][t[0].length];
        trackBack = new Point[t.length][t[0].length];
        trackDir = new String[t.length][t[0].length];
        traverse(start, start, start, 0, "");
        
        List<String> erg = new ArrayList<String>();
        collect(start, finish, erg);
        //System.out.println(erg.toString());
        return erg;
    }

    private static void collect(Point start, Point point, List<String> erg )
    {
        if (!point.equals(start))
        {
            erg.add(0, trackDir[point.x][point.y]);
            collect(start, trackBack[point.x][point.y], erg);
        }
    }

    private static void traverse(Point start, Point point, Point lastPoint, int cost, String dir) {
        //if (point == start)            return;
        int newCost = cost + originalMap[point.x][point.y];
        if (pruningLimit>0 && pruningLimit < newCost)
            return;
        if ((aggregatedMap[point.x][point.y] > 0) && (aggregatedMap[point.x][point.y] <= newCost))
            return;
        aggregatedMap[point.x][point.y] = newCost;
        trackBack[point.x][point.y] = lastPoint;
        trackDir[point.x][point.y] = dir;
        if (point.equals(finish))
        {
            pruningLimit = newCost;
        }
        {
            if (point.x>0) 
                traverse(start, new Point(point.x - 1, point.y), point, newCost, "up");
            if (point.y>0) 
                traverse(start, new Point(point.x, point.y - 1), point, newCost, "left");
            if (point.x<originalMap.length-1) 
                traverse(start, new Point(point.x + 1, point.y), point, newCost, "down");
            if (point.y<originalMap[0].length-1) 
                traverse(start, new Point(point.x, point.y + 1), point, newCost, "right");
        }
    }
}