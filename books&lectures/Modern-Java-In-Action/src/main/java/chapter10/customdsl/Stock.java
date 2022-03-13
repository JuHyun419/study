package chapter10.customdsl;

public class Stock {

    private String symbol;
    private String market;

    public Stock() {
    }

    public Stock(String symbol, String market) {
        this.symbol = symbol;
        this.market = market;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
