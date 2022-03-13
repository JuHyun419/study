package chapter10.customdsl.builder;

import chapter10.customdsl.Order;
import chapter10.customdsl.Stock;
import chapter10.customdsl.Trade;

import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {

    public static Order order(String customer, Trade ... trades) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Trade.Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Trade.Type.SELL);
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type type) {
        return new Trade(type, stock, quantity, price);
    }

    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        return new Stock(symbol, market);
    }

    public static String on(String market) {
        return market;
    }

}
