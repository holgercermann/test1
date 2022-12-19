
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DuplicateEncoder {
	static String encode(String word){
        word=word.toUpperCase();
        String result = "";
        for (char c : word.toCharArray()) {
            int i1 = word.indexOf(c) ;
            int i2 = word.lastIndexOf(c) ;
            result+= (i1==i2 ? "(" : ")");
        }
    return result;
  }


    @Test
    public void test() {
      assertEquals(")()())()(()()(",
            DuplicateEncoder.encode("Prespecialized"));
      assertEquals("))))())))",DuplicateEncoder.encode("   ()(   "));
    }
}
