import java.util.Scanner;

public class Main {
    public static Circle createCircle(Point p1, Point p2, double radius) {
        Point mid = p1.midPoint(p2);
        double distance = p1.distTo(mid);
        double angle = p1.angleTo(p2) + Math.PI / 2;
        if (distance > radius || distance <= 0) {
            return null;
        } else {
            distance = Math.sqrt(radius * radius - distance * distance);
            return Circle.getCircle(mid.moveTo(angle, distance), radius);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfPoints = sc.nextInt();
        Point[] points = new Point[numOfPoints];
        int max = 0;

        for (int i = 0; i < numOfPoints; i++) {
            points[i] = new Point(sc.nextDouble(), sc.nextDouble());
        }

        sc.close();

        for (int i = 0; i < numOfPoints; i++) {
            for (int j = 0; j < numOfPoints; j++) {
                Circle c = createCircle(points[i], points[j], 1);
                if (c != null || i != j) {
                    max = c.contains(points) > max ? c.contains(points) : max;
                }
            }
        }

        System.out.println("Maximum Disc Coverage: " + max);
    }
}

