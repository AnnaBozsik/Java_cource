package qa.java_cource.sandbox;

public class Point {

  public int x1;
  public int y1;
  public int x2;
  public int y2;

  public Point(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public double distance() {
    int a1 = (this.x2 - this.x1) * (this.x2 - this.x1);
    int a2 = (this.y2 - this.y1) * (this.y2 - this.y1);
    int b = a1 + a2;
    double c = Math.sqrt (b);
    return Math.round(c * 100) / 100D;
  }
}
