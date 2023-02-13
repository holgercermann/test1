public class Snail {
    static int[][] a;
    
    public static int[] snail(int[][] array) {
        a=array;
        int len = a.length * a[0].length;
        int[] r = new int[len];
        int position = -1;
        int state = 0;
        for (int i = 0; i < (len); i++) {
            while (i>0 && onWall(state, position) || onSet(state, position))  {
                state = (state + 1) % 4;
            }
            position = position + modForDirection(state);               
            r[i] = a[position / a[0].length][position % a[0].length];
            System.out.println(a[position / a[0].length][position % a[0].length]);
            a[position / a[0].length][position % a[0].length] = -1;
        }
        return r;
    } 

    private  static boolean onSet(int state, int pos)
    {
        return (a[(pos + modForDirection(state)) / a[0].length][(pos + modForDirection(state)) % a[0].length] == -1);
    }

    private  static boolean onWall(int newState, int position)
    {
        return !(
            (newState == 0 && ( (position + 1) % a[0].length != 0)) ||
            (newState == 1 && ( position / a[0].length < a.length - 1)) ||
            (newState == 2 && ( position % a[0].length > 0)) ||
            (newState == 3 && ( position / a[0].length > 0 ))
            );
        
         
    }
    private static int modForDirection(int state)
    {
        switch (state) {
            case 0:  return 1;
            case 1:  return a[0].length;
            case 2:  return -1;
            case 3:  return -a[0].length;
            default: throw new RuntimeException();
        }
    }
}