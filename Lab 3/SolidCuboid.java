class SolidCuboid extends Cuboid implements Solid {
    double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
    }

    @Override
    public double getMass() {
        return super.getVolume() * this.density;
    }

    @Override
    public double getDensity() {
        return this.density;
    }

    @Override
    public SolidCuboid setHeight(double x) {
        return new SolidCuboid(x, this.width, this.length, this.density);
    }

    @Override
    public SolidCuboid setWidth(double y) {
        return new SolidCuboid(this.height, y, this.length, this.density);
    }

    @Override
    public SolidCuboid setLength(double z) {
        return new SolidCuboid(this.height, this.width, z, this.density);
    }

    @Override
    public String toString() {
        return String.format("Solid%s with a mass of %.2f", super.toString(), this.getMass());
    }
}
