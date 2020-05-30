import java.util.Comparator;

class CustomerComparator implements Comparator<Customer> {
    public int compare(Customer a, Customer c) {
        if (a.getTime() > c.getTime()) {
            return 1;
        } else if (a.getTime() < c.getTime()) {
            return -1;
        } else if (a.getID() - c.getID() != 0) {
            return a.getID() - c.getID();
        } else {
            return 0;
        }
    }
}
