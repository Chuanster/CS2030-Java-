class SmallCruise extends Cruise {
    private static final int loaderRequired = 1; // require only 1 Loader
    private static final int serviceTime = 30; // service time is fixed at 30mins

    SmallCruise(String iD, int arrivalTime) {
        super(iD, arrivalTime, loaderRequired, serviceTime);
    }
}
