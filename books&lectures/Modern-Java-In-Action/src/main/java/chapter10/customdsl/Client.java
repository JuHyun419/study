package chapter10.customdsl;

import chapter10.customdsl.builder.LambdaOrderBuilder;
import chapter10.customdsl.builder.MethodChainingOrderBuilder;
import chapter10.customdsl.builder.NestedFunctionOrderBuilder;

import static chapter10.customdsl.builder.NestedFunctionOrderBuilder.at;
import static chapter10.customdsl.builder.NestedFunctionOrderBuilder.buy;
import static chapter10.customdsl.builder.NestedFunctionOrderBuilder.on;
import static chapter10.customdsl.builder.NestedFunctionOrderBuilder.sell;
import static chapter10.customdsl.builder.NestedFunctionOrderBuilder.stock;

public class Client {

    public static void main(String[] args) {
        Order order = new Order();
        order.setCustomer("BigBank");

        // 주문 1
        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrade(trade1);

        // 주문 2
        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrade(trade2);

        /* DSL */
        // 고객 설정 -> 수량(구매 or 판매) -> 주식(symbol, market) -> 가격
        Order dslOrder = MethodChainingOrderBuilder.forCustomer("BigBank")
                // 주문 1
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                // 주문 2
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();

        /* DSL - 중첩된 함수 */
        Order nestedDslOrder =
                NestedFunctionOrderBuilder.order("BigBank",
                        buy(80,
                                stock("IBM", on("NYSE")),
                                at(125.00)),
                        sell(50,
                                stock("GOOGLE", on("NASDAQ")),
                                at(375.00))
                );


        /* Functional DSL */
        Order functionalOrder = LambdaOrderBuilder.order(orders -> {
            orders.fonCustomer("BigBank");
            orders.buy(tradeBuilder -> {
                tradeBuilder.quantity(80);
                tradeBuilder.price(125.00);
                tradeBuilder.stock(stockBuilder -> {
                    stockBuilder.symbol("IBM");
                    stockBuilder.market("NYSE");
                });
            });
            orders.sell(tradeBuilder -> {
                tradeBuilder.quantity(50);
                tradeBuilder.price(375.00);
                tradeBuilder.stock(stockBuilder -> {
                    stockBuilder.symbol("GOOGLE");
                    stockBuilder.market("NASDAQ");
                });
            });
        });
    }

}
