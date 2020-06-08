import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Main.simulate(Main.getCustomers());
    }
    
    /*
    ** Get the arrival time from the input
    */
    static PriorityQueue<Customer> getCustomers() {
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
        Server server = new Server(); 
        while (!pq.isEmpty()) {
            Customer customer = pq.remove();
            Customer.State state = customer.getState();
            Data.collect(customer);
            System.out.println(customer);
            
            switch (state) {
                case ARRIVES:
                    if (server.canServe()) {
                        customer = customer.served();
                    } else if (server.canQueue()) {
                        customer = customer.waits();
                        server.setWaitingCustomer(customer);
                    } else {
                        customer = customer.leaves();
                    }
                    pq.add(customer);
                    break;
                case SERVED:
                    server.serve(customer);
                    pq.add(customer.done());
                    break;
                case DONE:
                    if (server.queueIsEmpty()) {
                        server.setCustomer(null);
                    } else {
                        pq.add(server.proceed(customer.getTime()));
                    }
                    break;
            }
        }
        Data.displayStats();
    }
}
