class Assessment implements Keyable<String> {
    String name; //key
    String grade; //value

    Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
    
    String getGrade() {
        return this.grade;
    }
    
    @Override
    public String getKey() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return String.format("{%s: %s}", this.name, this.grade);
    }
}
