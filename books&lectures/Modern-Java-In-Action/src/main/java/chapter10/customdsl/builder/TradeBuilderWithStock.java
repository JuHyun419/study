package chapter10.customdsl.builder;

import chapter10.customdsl.Trade;

/**
 * 거래되는 주식의 단위 가격을 설정한 후 원래 주문 빌더를 반환
 */
public class TradeBuilderWithStock {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
        this.builder = builder;
        this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price) {
        trade.setPrice(price);
        return builder.addTrade(trade);
    }
}
