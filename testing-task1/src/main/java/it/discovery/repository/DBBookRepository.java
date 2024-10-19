package it.discovery.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import it.discovery.model.Book;

/**
 * Handles database-related book operations
 * 
 * @author morenets
 *
 */
public class DBBookRepository implements BookRepository {
	private final Map<Integer, Book> books = new ConcurrentHashMap<>();

	private AtomicInteger counter = new AtomicInteger();

	@Override
	public void saveBook(Book book) {
		if (book.getId() == 0) {
			book.setId(counter.incrementAndGet());
		}
		
		books.put(book.getId(), book);

		System.out.println("Saved book " + book);
	}
	
	@Override
	public Book findBookById(int id) {
		return books.get(id);
	}

	@Override
	public List<Book> findBooks() {
		return List.copyOf((books.values()));
	}
	

}
