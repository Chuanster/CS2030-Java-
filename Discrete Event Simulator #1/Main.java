import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Main.simulate(Main.getCustomers());
    }

    static PriorityQueue<Customer> getCustomers() { // to input arrival times for the customer
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Customer> pq = new PriorityQueue<>(new CustomerComparator());
        while (sc.hasNextDouble()) {
            pq.add(new Customer(pq.size() + 1, sc.nextDouble()));
        }
        sc.close();
        Data.counter = pq.size();
        return pq;
    }

    /* 
    ** Customer will have different state, 
    ** which indicates which stage he is at. 
    ** Different actions will be taken to change 
    ** his status and update the system so the 
    ** the system knows what to do next
    */
    static void simulate(PriorityQueue<Customer> pq) {
        Server server = Server.of(); 
        while (!pq.isEmpty()) {
            Customer customer = pq.remove();
            Customer.State state = customer.getState();
            Data.collect(customer);
            System.out.println(customer);
            
            switch (state) {
                case ARRIVES:
                    if (!server.isBusy()) {
                        customer = customer.served();
                    } else if (server.needToWait()) {
                        customer = customer.waits();
                        server.addToQueue(customer);
                    } else {
                        customer = customer.leaves();
                    }
                    pq.add(customer);
                    break;
                case SERVED:
                    server = server.serve(customer);
                    pq.add(customer.done());
                    break;
                case DONE:
                    if (server.isQueueEmpty()) {
                        server.done();
                    } else {
                        pq.add(server.proceed(customer.getTime()));
                    }
                    break;
            }
        }
        Data.displayStats();
    }
}
