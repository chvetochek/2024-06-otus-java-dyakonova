package homework;

import java.util.*;

public class CustomerReverseOrder {

    private final ArrayDeque<Customer> list = new ArrayDeque<>();

    public void add(Customer customer) {
        list.push(customer);
    }

    public Customer take() {
        return list.pop();
    }
}
