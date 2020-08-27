package qa.java_cource.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(3, 2);
    Point p2 = new Point(7, 8);
    Assert.assertEquals(p1.distance(p2),7.21);
  }
}