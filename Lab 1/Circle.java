import java.util.List;

class Circle {
    private final Point center;
    private final double radius;

    private Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    
    boolean contains(Point p) { // check whether a point lies within this circle
        return this.center.distTo(p) <= this.radius;
    }

    int contains(Point[] points) { // check how many points lies within
        int counter = 0;
        for (Point p:points) {
            if (this.contains(p)) {
                counter++;
            }
        }
        return counter;
    }
    
    static Circle getCircle(Point center, double radius) { 
        return (radius > 0 ? new Circle(center, radius) : null);
    }

    @Override
    public String toString() {
        return String.format("circle of radius %s centered at %s", radius, center.toString());
    }
}
