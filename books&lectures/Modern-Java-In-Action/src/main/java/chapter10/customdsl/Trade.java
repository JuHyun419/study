package chapter10.customdsl;

public class Trade {

    public enum Type {BUY, SELL}
    private Type type;

    private Stock stock;
    private int quantity;
    private double price;

    public Trade() {
    }

    public Trade(Type type, Stock stock, int quantity, double price) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getValue() {
        return quantity * price;
    }
}
