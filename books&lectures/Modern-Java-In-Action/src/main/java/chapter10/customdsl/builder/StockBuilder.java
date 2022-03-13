package chapter10.customdsl.builder;

import chapter10.customdsl.Stock;
import chapter10.customdsl.Trade;

public class StockBuilder {

    private MethodChainingOrderBuilder builder;
    private Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder() {
    }

    StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public Stock getStock() {
        return stock;
    }

    public TradeBuilderWithStock on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(builder, trade);
    }

    /* 10.3.3 람다 표현식을 이용한 함수 시퀀싱 */
    public void symbol(String symbol) {
        stock.setSymbol(symbol);
    }

    public void market(String market) {
        stock.setMarket(market);
    }
}
