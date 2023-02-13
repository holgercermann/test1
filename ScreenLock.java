import java.util.ArrayList;
import java.util.List;

public class ScreenLock {
    int possibCtr =0;
    void process(int from, ArrayList<Integer> path, int leng)
    {
        if (leng == 0)
        {
            possibCtr++;
            return;
        }
        //ArrayList<Integer> path
        ArrayList<Integer> possib=getPossiblePaths( from, path);
        for (Integer in : possib) {
            path.add(in);
            process(in, path, leng-1);
            path.remove(in);
        }

    }
    ArrayList<Integer> getPossiblePaths(int from, ArrayList<Integer> path)
    {
        ArrayList<Integer> possib = new ArrayList<Integer>();
        switch (from)
        {
            case 0:
            addChecked(path, possib, 2,1);
            addChecked(path, possib, 6,3);
            addChecked(path, possib, 8,4);
            addChecked(path, possib, 5);
            addChecked(path, possib, 7);
            break;
            case 1:
            addChecked(path, possib, 0);
            addChecked(path, possib, 2);
            addChecked(path, possib, 3);
            addChecked(path, possib, 5);
            addChecked(path, possib, 7,4);
            addChecked(path, possib, 6);
            addChecked(path, possib, 8);
            break;
            case 2:
            addChecked(path, possib, 0,1);
            addChecked(path, possib, 8,5);
            addChecked(path, possib, 6,4);
            addChecked(path, possib, 3);
            addChecked(path, possib, 7);
            break;
            case 3:
            addChecked(path, possib, 0);
            addChecked(path, possib, 1);
            addChecked(path, possib, 2);
            addChecked(path, possib, 5,4);
            addChecked(path, possib, 6);
            addChecked(path, possib, 7);
            addChecked(path, possib, 8);
            break;
            case 4:
            addChecked(path, possib, 0);
            addChecked(path, possib, 1);
            addChecked(path, possib, 2);
            addChecked(path, possib, 3);
            addChecked(path, possib, 5);
            addChecked(path, possib, 6);
            addChecked(path, possib, 7);
            addChecked(path, possib, 8);
            break;
            case 5:
            addChecked(path, possib, 0);
            addChecked(path, possib, 1);
            addChecked(path, possib, 2);
            addChecked(path, possib, 3,4);
            addChecked(path, possib, 6);
            addChecked(path, possib, 7);
            addChecked(path, possib, 8);
            break;
            case 6:
            addChecked(path, possib, 8,7);
            addChecked(path, possib, 0,3);
            addChecked(path, possib, 2,4);
            addChecked(path, possib, 5);
            addChecked(path, possib, 1);
            break;
            case 7:
            addChecked(path, possib, 0);
            addChecked(path, possib, 2);
            addChecked(path, possib, 3);
            addChecked(path, possib, 5);
            addChecked(path, possib, 1,4);
            addChecked(path, possib, 6);
            addChecked(path, possib, 8);
            break;
            case 8:
            addChecked(path, possib, 6,7);
            addChecked(path, possib, 0,4);
            addChecked(path, possib, 2,5);
            addChecked(path, possib, 1);
            addChecked(path, possib, 3);
            break;
        }
        return possib;
    }
    private void addChecked(ArrayList<Integer> path, ArrayList<Integer> possib, int in) {
        if (!path.contains(in)) possib.add(in);
    }
    private void addChecked(ArrayList<Integer> path, ArrayList<Integer> possib, int in, int overfly) {
        if (!path.contains(overfly)) 
            possib.add(overfly);
        if (path.contains(overfly) && !path.contains(in)) 
            possib.add(in);
    }
    public int calculateCombinations(char startPosition, int patternLength){
        possibCtr=0;
        int startPos = startPosition - 'A';
        System.out.println("-->" + startPos + " / " + patternLength);
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(startPos);
        if (patternLength>0)
            process(startPos, path, patternLength-1);
        System.out.println("<--" + possibCtr);
        return possibCtr;
    }
}