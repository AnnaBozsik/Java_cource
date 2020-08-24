package qa.java_cource.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p = new Point(3, 2, 7, 8);
    Assert.assertEquals(p.distance(),7.21);

  }
}
