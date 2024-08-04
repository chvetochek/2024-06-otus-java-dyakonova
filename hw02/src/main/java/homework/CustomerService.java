package homework;

import java.util.*;

public class CustomerService {
    TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return Map.Entry.copyOf(map.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return Map.Entry.copyOf(map.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
