import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {

    static int numberOfServers; // number of servers to create

    public static void main(String[] args) {
        Main.simulate(Main.getCustomers(), Servers.getServers(numberOfServers));
    }
    
    /*
    ** Get the arrival event from the input
    */
    private static PriorityQueue<Event> getCustomers() {
        Scanner sc = new Scanner(System.in);
        Main.numberOfServers = sc.nextInt();
        PriorityQueue<Event> pq = new PriorityQueue<>(new CustomerComparator());
        pq.add(new Event(new Customer(pq.size() + 1, sc.nextDouble())));
        while (sc.hasNextDouble()) {
            pq.add(new Event(new Customer(pq.size() + 1, sc.nextDouble())));
        }
        sc.close();
        Data.counter = pq.size();
        return pq;
    }

    /* 
    ** Customer will have different state(stored in an Event object), 
    ** which indicates which stage he is at. 
    ** Different actions will be taken to change 
    ** his status and update the corresponding server so the 
    ** the system knows what event to create next
    */
    private static void simulate(PriorityQueue<Event> pq, List<Server> servers) {
        while (!pq.isEmpty()) {
            Event event = pq.remove();
            Customer customer = event.getCustomer();
            Server server = Servers.findServer(event); 
            Data.collect(customer);
            System.out.println(event);
            
            switch (customer.getState()) {
                case ARRIVES:
                    if (server.canServe()) {
                        event.set(customer.served(), server);
                    } else if (server.canQueue()) {
                        server.setWaitingCustomer(customer.waits());
                        event.set(customer.waits(), server);
                    } else {
                        event.setCustomer(customer.leaves());
                    }
                    pq.add(event);
                    break;
                case SERVED:
                    server.serve(customer);
                    event.set(customer.done(), server);
                    pq.add(event);
                    break;
                case DONE:
                    if (server.queueIsEmpty()) {
                        server.setCustomer(null);
                    } else {
                        event.setCustomer(server.proceed(customer.getTime()));
                        pq.add(event);
                    }
                    break;
            }
        }
        Data.displayStats();
    }
}
