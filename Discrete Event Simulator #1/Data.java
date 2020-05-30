import java.util.ArrayList;
import java.util.List;

class Data {
    public static int counter = 0; // total number of customers simulated 
    public static int customerServed = 0; // total number of customer served
    public static List<Customer> customers = new ArrayList<>();

    public static void collect(Customer customer) {
        if (customer.getState() == Customer.State.SERVED) {
            customerServed++;
            Data.customers.add(customer);
        } else if (customer.getState() == Customer.State.WAITS) {
            Data.customers.add(customer);
        }
    }
    
    /* 
    ** By finding the time difference between a customer is waiting
    ** till he is served, the total waiting time can be calculated.
    ** Then divides it by the total number of customer, that waited before,
    ** the waiting time per customer can be obtained
    */
    public static double avgTime() { 
        int size = customers.size();
        double time = 0;
        for (int i = 0; i < size;i++) {
            Customer a = customers.get(i);
            if (a.getState() == Customer.State.WAITS) {
                for (int j = i + 1;j < size;j++) {
                    Customer b = customers.get(j);
                    if (b.getState() == Customer.State.SERVED && a.getID() == b.getID()) {
                        time += (b.getTime() - a.getTime());
                        break;
                    }
                }
            }
        } 
        return time / Data.customerServed;
    }

    public static void displayStats() {
        System.out.printf("[%.3f %d %d]", Data.avgTime(), Data.customerServed, (Data.counter - Data.customerServed));
    }
}
