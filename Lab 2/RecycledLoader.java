class RecycledLoader extends Loader {
    private static final int restTime = 60; // time required for maintainence after serving a cruise

    RecycledLoader(int iD) {
        super(iD);
    }

    RecycledLoader(int iD, int busyTill, Cruise cruise) {
        super(iD, busyTill, cruise);
    }

    @Override
    public RecycledLoader serve(Cruise c) {
        if (c == null) {
            return new RecycledLoader(this.iD);
        } else {
            return (this.canServe(c) ? 
                new RecycledLoader(this.iD, c.getServiceCompletionTime() + restTime, c) : null);
        }
    }

    @Override
    public String toString() {
        if (this.cruise == null) {
            return String.format("Loader %d (recycled)", this.iD);
        } else {
            return String.format("Loader %d (recycled) serving %s", this.iD, this.cruise);
        }
    }
}
