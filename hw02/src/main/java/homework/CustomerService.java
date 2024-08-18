package homework;

import java.util.*;

public class CustomerService {
    private final TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        var key = new Customer(map.firstEntry().getKey().getId(), map.firstEntry().getKey().getName(), map.firstEntry().getKey().getScores());
        var value = map.firstEntry().getValue();
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var entry = map.higherEntry(customer);
        if (entry == null) {
            return null;
        }
        var key = new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores());
        var value = entry.getValue();
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
