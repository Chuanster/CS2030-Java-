import java.util.List;
import java.util.ArrayList;

class Server {
    private final int iD;
    private final double busyTill;
    private Customer[] serving = new Customer[1];
    private List<Customer> queue = new ArrayList<>();

    private Server(int iD, double time) {
        this.iD = iD;
        this.busyTill = time;
    }

    public static Server of() {
        return new Server(1, 0);
    }

    public double getTime() {                     // return the time where server is going to be free
        return this.busyTill;
    }

    public boolean isQueueEmpty() {               // checking if the queue for the server is empty
        return this.queue.isEmpty();
    }

    public boolean isBusy() {                     // checking whether this server if serving a customer(return true if it is)
        return this.serving[0] != null;
    }

    public boolean needToWait() {                 // if the server is busy but the customer still can queue for it(return true)
        return this.isBusy() && this.isQueueEmpty();
    }
   
    public Customer proceed(double currentTime) { // moving on to the next customer(provided there is someone waiting)
        this.serving[0] = this.queue.get(0).setTime(currentTime).served();
        this.queue.clear();
        return this.serving[0];
    } 

    public void addToQueue(Customer a) {          // let the customer to join the queue when the server is busy
        if (this.isQueueEmpty() && this.isBusy()) {
            this.queue.add(a);
        }
    }
    
    public void done() {                          // removing the customer from "serving"
        this.serving[0] = null;
    }

    public Server serve(Customer a) {             // server will attend to this customer
        Server s = new Server(this.iD, a.getTime() + Customer.SERVICE_TIME);
        s.serving[0] = a;
        return s;
    }

    @Override
    public String toString() {
        return " Server " + this.iD;
    }
}
