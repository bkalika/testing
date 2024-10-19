package it.discovery.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Book in a library
 * @author morenets
 *
 */
@Getter
@Setter
@ToString
public class Book {
	private int id;
	
	private String name;
	
	/**
	 * Publishing year
	 */
	private int year;
	
	/**
	 * Total number of pages
	 */
	private int pages;
	
	private int price;
	
	private List<Order> orders;

	public void withPages(int pages) {
		if(pages <=0 ) {
			throw new IllegalArgumentException(pages + " must be positive number");
		}
		this.pages = pages;
	}
	
}
