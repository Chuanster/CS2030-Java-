class BigCruise extends Cruise {
    private static final int forLength = 40; // one loader per 40m in length of the cruise
    private static final int forPassenger = 50; // one min to serve 50 passengers

    BigCruise(String iD, int arrivalTime, int length, int passengers) {
        super(iD, arrivalTime, (int)Math.ceil(length / (double)forLength),
                (int)Math.ceil(passengers / (double)forPassenger));
    }
}
