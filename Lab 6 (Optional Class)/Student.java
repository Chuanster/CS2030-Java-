import java.util.Optional;

class Student extends KeyableMap<String,String,Module> {

    Student(String id) {
        super(id);
    }

    public Student put(String mod, String as, String grade) {
        Optional<Module> m = this.get(mod);
        m.ifPresentOrElse(x -> x.put(new Assessment(as,grade)),
            () -> this.put(new Module(mod).put(new Assessment(as, grade))));
        return this;
    }
    
    @Override
    public Student put(Module item) {
        return (Student)super.put(item);
    }
}
