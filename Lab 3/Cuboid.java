class Cuboid implements Shape3D {
    protected final double height;
    protected final double width;
    protected final double length;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public Cuboid setHeight(double x)  {
        return new Cuboid(x, this.width, this.length);
    }
    
    public Cuboid setWidth(double y) {
        return new Cuboid(this.height, y, this.length);
    }
    
    public Cuboid setLength(double z) {
        return new Cuboid(this.height, this.width, z);
    }  

    @Override
    public double getVolume() {
        return this.height * this.width * this.length;
    }
    
    @Override
    public double getSurfaceArea() {
        return this.height * this.width * 2 + this.height * this.length * 2 + this.width * this.length * 2;
    }

    @Override
    public String toString() {
        return String.format("Cuboid [%.2f x %.2f x %.2f]", this.height, this.width, this.length);
    }


}
