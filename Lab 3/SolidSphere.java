class SolidSphere extends Sphere implements Solid {
    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    @Override
    SolidSphere setRadius(double x) {
        return new SolidSphere(x, this.density);
    }

    @Override
    public double getDensity() {
        return this.density;
    }

    @Override
    public double getMass() {
        return this.density * super.getVolume();
    }

    @Override
    public String toString() {
        return String.format("Solid%s with a mass of %.2f", super.toString(), this.getMass());
    }
}
