package IntegerDepth;

public class MySolution {
    public int computeDepth(int n) {
        System.out.println(n);
        long bitset = 0;
        final long target = 0B1111111111;
        for (int multiplier = 1; ; multiplier++)
        {
            int nbr = n * multiplier;
            do { 
                bitset |= 1 << (nbr % 10);
                nbr = nbr / 10;
            } while (nbr>0);
            System.out.println(n + "\t" + multiplier + "\t" + n * multiplier + "\t" + Long.toBinaryString(bitset));
            if (bitset == target)
                return multiplier;
        }
    }
}

