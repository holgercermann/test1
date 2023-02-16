package CentreOfAttention;

import java.util.ArrayList;
//import java.util.HashMap;

public class Central_Pixels_Finder extends Image
{
    int[] distances;
    public int[] central_pixels(int color)
    {
        ArrayList<ArrayList<Integer>> group = new ArrayList<ArrayList<Integer>>(3);
        ArrayList<Integer> level =  new ArrayList<Integer>(1000000);
        distances = new int[(width * height)];
        int currentLevel = 1;
        for (int i = 0; i < distances.length; i++) {
            if ((pixels[i] == color) && isAdjacent(i))
            {
                distances[i] = 1;
                level.add(i);
            }
        }
        group.add(level);

        int[] non_existent_ctr = { };
        if (level.size() == 0)
            return non_existent_ctr;
            
        while (level.size()>0)
        {
            System.out.println(level.toString());
            ArrayList<Integer> nextLevel = new ArrayList<Integer>(10000);
            for (Integer l : level) {
                if (isSameColorAndNotIn(l,l-1)) 
                    setResult(nextLevel, l-1, currentLevel);
                if (isSameColorAndNotIn(l,l+1)) 
                    setResult(nextLevel, l+1, currentLevel);
                if (isSameColorAndNotIn(l,l-width)) 
                    setResult(nextLevel, l-width, currentLevel);
                if (isSameColorAndNotIn(l,l+width)) 
                    setResult(nextLevel, l+width, currentLevel);
            }
            group.add(nextLevel);
            level = nextLevel;
            currentLevel++;
        }
        level = group.get(group.size()-2);
        int[] result = new int[level.size()];
        for (int i = 0; i < level.size(); i++) {
            result[i] = level.get(i);
        }
        return result;

    }


    private boolean isAdjacent(int l) {
        return isAdjacent(l,l-1) || isAdjacent(l,l+1) || isAdjacent(l,l-width) || isAdjacent(l,l+width);
    }


    private boolean isAdjacent(Integer original, int neighbor) {
        if ((original % width == 0) || (original % width == width - 1) || 
            (original / width == 0) || (original / width == height-1)) return true;
        if (neighbor < 0) return false;
        if (neighbor >= (width * height)) return false;
        if (pixels[original] != pixels[neighbor]) 
            return true;
        return false;
    }

    private void setResult(ArrayList<Integer> currentLevel, Integer l, int level) {
        distances[l] = level;
        currentLevel.add(l);
    }


    private boolean isSameColorAndNotIn(Integer original, int neighbor) {
        if (neighbor < 0) return false;
        if (neighbor >= (width * height)) return false;
        if (distances[neighbor] == 0 &&  pixels[original] == pixels[neighbor]) 
            return true;
        return false;
    }

}