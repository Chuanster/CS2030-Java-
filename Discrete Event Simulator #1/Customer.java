class Customer {
    enum State {
        ARRIVES,
        SERVED,
        WAITS,
        LEAVES,
        DONE;
    }

    private final int iD;
    private final double arrivalTime; // this time not just to store the arrival time, but also used to indicate the timeline  
    private final State currentState;
    public static final double SERVICE_TIME = 1; // The time taken that a customer will occupy the server for fixed time

    public Customer(int iD, double arrivalTime) {
        this.iD = iD;
        this.arrivalTime = arrivalTime;
        this.currentState = State.ARRIVES;
    }
    
    public Customer(int iD, double arrivalTime, State currentState) {
        this.iD = iD;
        this.arrivalTime = arrivalTime;
        this.currentState = currentState;
    }
    
    public int getID() {
        return this.iD;
    }
    
    public double getTime() {
        return this.arrivalTime;
    }
    
    public State getState() {
        return this.currentState; 
    }
    
    /*
    ** The following 5 methods are used to change the state of a customer
    ** Which the method name will suggests what action has taken(to the customer) 
    */
    public Customer served() {
        return new Customer(this.iD, this.arrivalTime, State.SERVED);
    }
    
    public Customer waits() {
        return new Customer(this.iD, this.arrivalTime, State.WAITS);
    }

    public Customer done() {
        return new Customer(this.iD, this.arrivalTime + SERVICE_TIME, State.DONE);
    }

    public Customer leaves() {
        return new Customer(this.iD, this.arrivalTime, State.LEAVES);
    }

    public Customer setTime(double time) {
        return new Customer(this.iD, time, this.currentState);
    }
    
    @Override
    public String toString() {
        return String.format("%.3f %s %s", 
                            this.arrivalTime, this.iD, this.currentState.name().toLowerCase());
    }
}
