class Module extends KeyableMap<String,String,Assessment> {

    Module(String modName) {
        super(modName);
    }

    @Override
    public Module put(Assessment a) {
        return (Module)super.put(a);
    }
}
