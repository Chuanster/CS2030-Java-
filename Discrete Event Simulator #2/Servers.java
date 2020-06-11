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

    static Server anyCanServe() {
        for (Server s: Servers.servers) {
            if (s.canServe()) {
                return s;
            }
        }
        return null;
    }
    
    static Server anyCanQueue() {
        for (Server s: Servers.servers) {
            if (s.canQueue()) {
                return s;
            }
        }
        return null;
    }
    
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
