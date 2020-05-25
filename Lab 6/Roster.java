class Roster extends KeyableMap<String,String,Student> {

    Roster(String ay) { // ay = academic year
        super(ay);
    }

    public String getGrade(String name, String modName, String as) throws NoSuchRecordException {
        try {
            return this.get(name).get(modName).get(as).getGrade();
        } catch (NullPointerException ex) {
            throw new NoSuchRecordException(String.format("No such record: %s %s %s", name, modName, as);
        }
    }

    @Override
    public Roster put(Student s) {
        return (Roster)super.put(s);
    }
}
