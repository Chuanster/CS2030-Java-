class Cruise {
    private final String iD;
    private final int arrivalTime;
    private final int loadersNeeded;
    private final int serviceTime;

    Cruise(String iD, int arrivalTime, int loadersNeeded, int serviceTime) {
        this.iD = iD;
        this.arrivalTime = arrivalTime;
        this.loadersNeeded = loadersNeeded;
        this.serviceTime = serviceTime;
    }

    int getArrivalTime() { // arrival time converted to minutes that have passed since 00:00
        return this.arrivalTime / 100 * 60 + this.arrivalTime % 100;
    }

    int getServiceCompletionTime() { // when the Cruise will be done in minutes
        return this.getArrivalTime() + this.serviceTime;
    }

    int getNumOfLoadersRequired() {
        return this.loadersNeeded;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.iD, this.arrivalTime);
    }
}
