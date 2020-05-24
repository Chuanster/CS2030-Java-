class Point {
    private double[] coord; //coordinates

    Point(double x, double y) {
        this.coord = new double [] {x,y};
    }

    double[] getCoord() { // return the coordinates of this point
        return this.coord;
    }

    Point midPoint(Point p) { // midpoint between 2 poins
        double mx = (this.coord[0] + p.coord[0]) / 2;
        double my = (this.coord[1] + p.coord[1]) / 2;
        return new Point(mx,my);
    }

    double distTo(Point r) { // distance between 2 points
        double dx = this.coord[0] - r.coord[0];
        double dy = this.coord[1] - r.coord[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    double angleTo(Point q) { // angle in radian, from 1 point to another
        double xdiff = q.coord[0] - this.coord[0];
        double ydiff = q.coord[1] - this.coord[1];
        return (double)(Math.atan2(ydiff, xdiff));
    }

    Point moveTo(double angle, double dist) { // move this point to a specific coordinates
        double dcos = dist * (Math.cos(angle));
        double dsin = dist * (Math.sin(angle));
        return new Point(this.coord[0] + dcos, this.coord[1] + dsin);
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.coord[0], this.coord[1]);
    }

}

