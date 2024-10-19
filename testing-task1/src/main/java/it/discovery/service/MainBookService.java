package it.discovery.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MainBookService implements BookService {
	private final BookRepository repository;
	
	private final boolean cachingEnabled;
	
	private final Map<Integer, Book> bookCache = new ConcurrentHashMap<>();

	@Override
	public void saveBook(Book book) {
		CompletableFuture.runAsync(() -> {
			repository.saveBook(book);

			if (cachingEnabled) {
				bookCache.put(book.getId(), book);
			}
		});
	}
	
	@Override
	public Book findBookById(int id) {
		if(cachingEnabled && bookCache.containsKey(id)) {
			return bookCache.get(id);
		}
		
		return repository.findBookById(id);
	}

	@Override
	public List<Book> findBooks() {
		return repository.findBooks();
	}


}
