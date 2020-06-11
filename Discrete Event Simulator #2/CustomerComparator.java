import java.util.Comparator;

class CustomerComparator implements Comparator<Event> {
    public int compare(Event event1, Event event2) {
        Customer a = event1.getCustomer();
        Customer b = event2.getCustomer();
        if (a.getTime() > b.getTime()) {
            return 1;
        } else if (a.getTime() < b.getTime()) {
            return -1;
        } else if (a.getID() - b.getID() != 0) {
            return a.getID() - b.getID();
        } else {
            return 0;
        }
    }
}
