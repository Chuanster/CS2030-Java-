class Student extends KeyableMap<String,String,Module> {

    Student(String id) {
        super(id);
    }
    
    @Override
    public Student put(Module mod) {
        return (Student)super.put(mod);
    }
}
