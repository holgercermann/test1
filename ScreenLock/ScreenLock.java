package ScreenLock;
import java.util.ArrayList;

public class ScreenLock {
    int possibleCtr =0;
    void process(int from, ArrayList<Integer> path, int len)
    {
        if (len == 0)
        {
            possibleCtr++;
            return;
        }
        //ArrayList<Integer> path
        ArrayList<Integer> possible=getPossiblePaths( from, path);
        for (Integer in : possible) {
            path.add(in);
            process(in, path, len-1);
            path.remove(in);
        }

    }
    ArrayList<Integer> getPossiblePaths(int from, ArrayList<Integer> path)
    {
        ArrayList<Integer> possible = new ArrayList<Integer>();
        switch (from)
        {
            case 0:
            addChecked(path, possible, 2,1);
            addChecked(path, possible, 6,3);
            addChecked(path, possible, 8,4);
            addChecked(path, possible, 5);
            addChecked(path, possible, 7);
            break;
            case 1:
            addChecked(path, possible, 0);
            addChecked(path, possible, 2);
            addChecked(path, possible, 3);
            addChecked(path, possible, 5);
            addChecked(path, possible, 7,4);
            addChecked(path, possible, 6);
            addChecked(path, possible, 8);
            break;
            case 2:
            addChecked(path, possible, 0,1);
            addChecked(path, possible, 8,5);
            addChecked(path, possible, 6,4);
            addChecked(path, possible, 3);
            addChecked(path, possible, 7);
            break;
            case 3:
            addChecked(path, possible, 0);
            addChecked(path, possible, 1);
            addChecked(path, possible, 2);
            addChecked(path, possible, 5,4);
            addChecked(path, possible, 6);
            addChecked(path, possible, 7);
            addChecked(path, possible, 8);
            break;
            case 4:
            addChecked(path, possible, 0);
            addChecked(path, possible, 1);
            addChecked(path, possible, 2);
            addChecked(path, possible, 3);
            addChecked(path, possible, 5);
            addChecked(path, possible, 6);
            addChecked(path, possible, 7);
            addChecked(path, possible, 8);
            break;
            case 5:
            addChecked(path, possible, 0);
            addChecked(path, possible, 1);
            addChecked(path, possible, 2);
            addChecked(path, possible, 3,4);
            addChecked(path, possible, 6);
            addChecked(path, possible, 7);
            addChecked(path, possible, 8);
            break;
            case 6:
            addChecked(path, possible, 8,7);
            addChecked(path, possible, 0,3);
            addChecked(path, possible, 2,4);
            addChecked(path, possible, 5);
            addChecked(path, possible, 1);
            break;
            case 7:
            addChecked(path, possible, 0);
            addChecked(path, possible, 2);
            addChecked(path, possible, 3);
            addChecked(path, possible, 5);
            addChecked(path, possible, 1,4);
            addChecked(path, possible, 6);
            addChecked(path, possible, 8);
            break;
            case 8:
            addChecked(path, possible, 6,7);
            addChecked(path, possible, 0,4);
            addChecked(path, possible, 2,5);
            addChecked(path, possible, 1);
            addChecked(path, possible, 3);
            break;
        }
        return possible;
    }
    private void addChecked(ArrayList<Integer> path, ArrayList<Integer> possible, int in) {
        if (!path.contains(in)) possible.add(in);
    }
    private void addChecked(ArrayList<Integer> path, ArrayList<Integer> possible, int in, int overfly) {
        if (!path.contains(overfly)) 
            possible.add(overfly);
        if (path.contains(overfly) && !path.contains(in)) 
            possible.add(in);
    }
    public int calculateCombinations(char startPosition, int patternLength){
        possibleCtr=0;
        int startPos = startPosition - 'A';
        System.out.println("-->" + startPos + " / " + patternLength);
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(startPos);
        if (patternLength>0)
            process(startPos, path, patternLength-1);
        System.out.println("<--" + possibleCtr);
        return possibleCtr;
    }
}