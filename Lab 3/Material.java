class Material {
    private final String name; //name of the material...steel,iron...
    private final double density;

    Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public double getDensity() {
        return this.density; 
    }
}
