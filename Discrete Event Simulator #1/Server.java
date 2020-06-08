import java.util.List;
import java.util.ArrayList;

class Server {
    private double busyTill;
    private Customer customer;
    private Customer customerWaiting;

    protected double getTime() {
        return this.busyTill;
    }

    protected Customer getCustomer() {
        return this.customer;
    }

    protected Customer getWaitingCustomer() {
        return this.customerWaiting;
    }
    
    protected void setTime(double time) {
        this.busyTill = time;
    }
    
    protected void setCustomer(Customer c) {
        this.customer = c;
    }

    protected void setWaitingCustomer(Customer c) {
        this.customerWaiting = c;
    }

    protected boolean canServe() {
        return this.customer == null;
    }

    protected boolean canQueue() {
        return !this.canServe() && this.customerWaiting == null;
    }

    protected boolean queueIsEmpty() {
        return this.canQueue();
    }

    protected void serve(Customer customer) {
        if (this.canServe()) {
            this.busyTill = customer.getTime() + Customer.SERVICE_TIME;
            this.setCustomer(customer);
        }
    }

    protected Customer proceed(double time) {
        this.setCustomer(this.getWaitingCustomer().setTime(time).served());
        this.setWaitingCustomer(null);
        return this.getCustomer();
    }

    @Override
    public String toString() {
        return "Stats: " + this.busyTill + " " + this.customer + " " + this.customerWaiting + "\n";
    }
}
