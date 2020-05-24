class Loader {
    protected final int iD;
    protected final int busyTill; // this Loader will be occupied till this time
    protected final Cruise cruise; // cruise this Loader is serving

    Loader(int iD) {
        this.iD = iD;
        this.busyTill = 0;
        this.cruise = null;
    }
    
    Loader(int iD, int busyTill, Cruise cruise) {
        this.iD = iD;
        this.busyTill = busyTill;
        this.cruise = cruise;
    }

    public int getID() {
        return this.iD;
    }

    public boolean canServe(Cruise a) {
        return (cruise == null ? true : a.getArrivalTime() >= this.busyTill);
    }

    public Loader serve(Cruise c) {
        if (c == null) {
            return new Loader(this.iD);
        } else {
            return (this.canServe(c) ? new Loader(this.iD, c.getServiceCompletionTime(), c) : null);
        }
    }

    @Override
    public String toString() {
        if (this.cruise == null) {
            return String.format("Loader %d", this.iD);
        } else {
            return String.format("Loader %d serving %s", this.iD, this.cruise);
        }
    }
}
