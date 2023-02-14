package StripComments;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StripComments {

	public static String stripComments(String text, String[] commentSymbols) {
        System.out.println(text);
        String result = Arrays.stream(text.split("\n"))
                     .map(s -> stripMe(s, commentSymbols).replaceAll("\\s+$",""))
                     .collect(Collectors.joining("\n"));
		return result;
	}

    private static String stripMe(String inp, String[] commentSymbols)
    {
        System.out.println("\t -> " + inp);
        for (String s : commentSymbols) {
            if (inp.indexOf(s)>-1)
                inp = inp.substring(0, inp.indexOf(s));
        }
        System.out.println("\t <- " + inp);
        return inp;
    }
}
