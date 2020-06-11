class Event {
    Customer customer;
    Server server;

    Event(Customer customer) {
        this.customer = customer;
        this.server = null;
    }
    
    Event(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    Customer getCustomer() {
        return this.customer;
    }

    Server getServer() {
        return this.server;
    }
    
    void setCustomer(Customer c) {
        this.customer = c;
    }

    void setServer(Server s) {
        this.server = s;
    }

    void set(Customer c, Server s) {
        this.customer = c;
        this.server = s;
    }

    @Override
    public String toString() {
        Customer.State state = this.customer.getState();
        switch (state) {
            case SERVED:
                return String.format("%s by %d", this.customer, this.server.getID());
            case WAITS:
                return String.format("%s to be served by %d", this.customer, this.server.getID());
            case DONE:
                return String.format("%s serving by %d", this.customer, this.server.getID());
            default:
                return this.customer.toString();
        }
    }

}
