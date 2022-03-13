package chapter10.customdsl;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public Order() {
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getValue() {
        return trades.stream()
                .mapToDouble(Trade::getValue)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println("Test~");
    }
}
