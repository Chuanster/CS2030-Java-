import java.util.Optional;

class Roster extends KeyableMap<String,String,Student> {
    Roster(String acdYear) {
        super(acdYear);
    }
    
    @Override
    public Roster put(Student s) {
        return (Roster)super.put(s);
    }

    public Roster put(String name, String mod, String as, String g) {
        Optional<Student> a = this.get(name);
        a.ifPresentOrElse(x -> x.put(mod, as, g), () -> this
            .put(new Student(name)
            .put(new Module(mod)
            .put(new Assessment(as, g)))));
        return this;
    }

    public String getGrade(String n, String m, String as) throws NoSuchRecordException {
        return this.get(n)
                .flatMap(x -> x.get(m))
                .flatMap(x -> x.get(as))
                .map(x -> x.getGrade())
                .orElseThrow(() -> new NoSuchRecordException(String.format("No such record: %s %s %s", n, m, as)));
    }


}
