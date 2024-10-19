package it.discovery.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static it.discovery.model.util.TestModelUtils.getBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    private Order order;
    private Book book;
    private List<OrderItem> orderItems;

    @BeforeEach
    void setUp() {
        book = getBook();
        orderItems = List.of(
                new OrderItem(book, 1),
                new OrderItem(book, 1)
        );
        order = new Order(orderItems);
    }

    @Test
    void getTotalPriceOrderWithItemsReturnPrice() {
        assertEquals(800, order.getTotalPrice());
    }

    @Test
    void getTotalPriceOrderWithoutItemsReturnZero() {
        var emptyOrder = new Order(List.of());
        assertEquals(0, emptyOrder.getTotalPrice());
    }

    @Test
    void InitializationItemsIsNullThrowsException() {
        assertThrows(NullPointerException.class, () -> new Order(null));
    }

    @Test
    void createOrder() {
        book = getBook();
        Order orderCreated = Order.createOrder(book, 2);

        List<OrderItem> orderItemsExpected = List.of(
                new OrderItem(book, 2)
        );
        Order orderExpected = new Order(orderItemsExpected);
        assertEquals(orderExpected, orderCreated);
    }
}
