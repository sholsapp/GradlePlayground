import com.untitled.Main;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMain {
  @Test
  public void testLibrary() {
    Main m = new Main();
    assertEquals(42, m.library());
  }

}
