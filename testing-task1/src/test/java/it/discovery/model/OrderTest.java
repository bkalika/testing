package it.discovery.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static it.discovery.util.TestModelUtils.createBook;
import static jdk.dynalink.linker.support.Guards.isNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order(List.of(new OrderItem(createBook(), 1)));
    }

    @Nested
    class GetTotalPriceTests {
        @Test
        void getTotalPrice_orderWithItems_returnPrice() {
            assertEquals(400, order.getTotalPrice());
        }

        @Test
        void getTotalPrice_orderWithoutItems_returnZero() {
            var emptyOrder = new Order(List.of());
            assertEquals(0, emptyOrder.getTotalPrice());
        }
    }

    @Test
    void initialization_itemsIsNull_error() {
        assertThrows(NullPointerException.class, () -> new Order(null));
    }

    @Test
    void createOrder_validArgumentValues_returnsOrder() {
        var book = createBook();
        var order1 = Order.createOrder(book, 10);
        assertThat(order1, notNullValue());
        assertThat(order1.getItems(), hasSize(1));
        assertThat(order1.getItems().getFirst().getBook(), equalTo(book));
        assertThat(order1.getItems().getFirst().getAmount(), equalTo(10));
    }

    @Test
    void complete() {
    }

    @Test
    void fail() {
    }
}