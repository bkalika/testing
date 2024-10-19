package it.discovery.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderItem {
	
	private Book book;
	
	private int amount;

}
