class SolidShape3D implements Solid {
    private final Shape3D shape;
    private final Material material;

    SolidShape3D(Shape3D shape, Material material) {
        this.shape = shape;
        this.material = material;
    }
    
    @Override
    public double getMass() {
        return this.shape.getVolume() * this.material.getDensity();
    }

    @Override
    public double getDensity() {
        return this.material.getDensity();
    }

    @Override
    public String toString() {
        return String.format("Solid%s with a mass of %.2f", this.shape.toString(), this.getMass()); 
    }
}
