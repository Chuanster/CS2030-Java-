class Sphere implements Shape3D {
    protected final double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    Sphere setRadius(double x) {
        return new Sphere(x);
    }

    @Override
    public double getVolume() {
        return (4 * Math.PI * Math.pow(this.radius, 3) / 3);
    } 
    
    @Override
    public double getSurfaceArea() {
        return (4 * Math.PI * Math.pow(this.radius, 2));
    }


    @Override 
    public String toString() {
        return String.format("Sphere [%.2f]",this.radius);
    }
}
