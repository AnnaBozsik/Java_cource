  package qa.java_cource.sandbox;

  public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public double distance (Point p1) {
      double a1 = (p1.x - this.x) * (p1.x - this.x);
      double a2 = (p1.y - this.y) * (p1.y - this.y);
      double b = a1 + a2;
      double c = Math.sqrt (b);
      return Math.round(c * 100) / 100D;
    }
  }
