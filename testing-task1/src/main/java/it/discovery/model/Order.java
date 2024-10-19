package it.discovery.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {

	private int id;

	private LocalDateTime createdAt;

	private List<OrderItem> items;

	private OrderState state;

	private LocalDateTime completionDate;

	private String errorDescription;

	public Order(List<OrderItem> items) {
		this.items = Objects.requireNonNull(items);
		createdAt = LocalDateTime.now();
		state = OrderState.CREATED;
	}

	public int getTotalPrice() {
		return items.stream().mapToInt(item -> item.getAmount() * item.getBook().getPrice()).sum();
	}

	public static Order createOrder(Book book, int amount) {
		Objects.requireNonNull(book);
		if (amount <= 0) {
			throw new IllegalArgumentException("Invald amount: " + amount);
		}
		return new Order(List.of(new OrderItem(book, amount)));
	}

	public void complete() {
		if(state == OrderState.COMPLETED) {
			throw new IllegalStateException("Order " + id + " was already completed");
		}
		state = OrderState.COMPLETED;
		completionDate = LocalDateTime.now();
	}

	public void fail(String errorDescription) {
		if(state == OrderState.COMPLETED) {
			throw new IllegalStateException("Order " + id + " can'be failed because it was already completed");
		}

		state = OrderState.ERROR;
		this.errorDescription = errorDescription;
	}

}
