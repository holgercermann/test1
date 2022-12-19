import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

public class Formula { 
 
    /** Validate than a given string has valid parenthesis and brackets syntax
     *  Parenthesis/bracket types: () and [] 
     *  Rule 1: Opening and corresponding closing brackets must match:  (  -->  ) 
     *  Rule 2: Always the most inner opening bracket must be closed first:  ([  -->  ])
     *  Rule 3: All other characters can be ignored
     *  @param t String to test
     *  @return validity of string as boolean
    */
    public static boolean hasValidBrackets(String t)
    {
        // Hint 1: You could iterate over the array, remembering the sequence of all opening elements
        // When you see a closing type of bracket, it must match the last opening element)

        //Hint 2: What is a stack?

        //ONE possible solution:
        Map <Character, Character> bracketTypes = new HashMap <> ();
        bracketTypes.put('(', ')'); 
        bracketTypes.put('[', ']');
        Stack <Character> history = new Stack<>();

        //Arrays.asList(t.toCharArray()).stream().map( c -> {

        for (Character c : t.toCharArray()) {
            if (bracketTypes.keySet().contains(c)) { //Opening bracket to be remembered for later
                history.push(c); 
            }
            if (bracketTypes.values().contains(c)) { //Closing bracket demands corresponding opening one on top of stack
                if ((history.size()==0) || !(bracketTypes.get(history.pop()).equals(c)))
                    return false;
            }
        };

        return history.size() == 0; //In the end, all opening brackets need to be closed again.
    }


    @Test
    public void testMe()
    {
        assertTrue(hasValidBrackets("(  )"                      ));
        assertTrue(hasValidBrackets("[ ( [ yes ] ) ]"           ));
        assertTrue(hasValidBrackets("( [ hello ] [ w(o)rld ] )" ));

        assertFalse(hasValidBrackets("( [ mixed ) ]"            ));
        assertFalse(hasValidBrackets(") wrong sequence ("       ));
        assertFalse(hasValidBrackets("( not balanced "          ));
    }
       
}