package chapter10.customdsl.builder;

import chapter10.customdsl.Trade;

import java.util.function.Consumer;

public class TradeBuilder {

    private MethodChainingOrderBuilder builder;
    public final Trade trade = new Trade();

    public TradeBuilder() {
    }

    TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
        this.builder = builder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(builder, trade, symbol);
    }

    /* 10.3.3 람다 표현식을 이용한 함수 시퀀싱 */
    public void quantity(int quantity) {
        trade.setQuantity(quantity);
    }

    public void price(double price) {
        trade.setPrice(price);
    }

    public void stock(Consumer<StockBuilder> consumer) {
        StockBuilder builder = new StockBuilder();
        consumer.accept(builder);
        trade.setStock(builder.getStock());
    }

}
