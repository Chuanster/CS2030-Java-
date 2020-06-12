import java.util.List;
import java.util.ArrayList;

class Servers {
    static List<Server> servers = new ArrayList<>();

    /*
    ** create the number of servers as required from the input
    */
    static List<Server> getServers(int number) {
        List<Server> servers = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            Servers.servers.add(new Server(i));
        }
        return Servers.servers;
    }
    
    /*
    ** find if there is any server available to serve a customer
    ** else a special server will be returned(a server that does nothing)
    */
    static Server anyCanServe() {
        for (Server s: Servers.servers) {
            if (s.canServe()) {
                return s;
            }
        }
        return null;
    }
    
    /*
    ** find if there is any server that a customer can queue 
    ** else a special server will be returned(a server that does nothing)
    */
    static Server anyCanQueue() {
        for (Server s: Servers.servers) {
            if (s.canQueue()) {
                return s;
            }
        }
        return null;
    }
    
    /*
    ** find a availale server or find the server thats be assigned to the customer
    */
    static Server findServer(Event event) {
        Customer customer = event.getCustomer();
        Customer.State state = customer.getState();
        switch (state) {
            case ARRIVES:
                if (Servers.anyCanServe() != null) {
                    return Servers.anyCanServe();
                } else if (Servers.anyCanQueue() != null) {
                    return Servers.anyCanQueue();
                } else {
                    return new Server(-1);
                }
            case SERVED: case DONE:
                return event.getServer();
            default:
                return new Server(-1);
        }
    }

}
