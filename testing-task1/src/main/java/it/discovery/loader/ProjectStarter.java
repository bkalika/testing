package it.discovery.loader;

import java.util.List;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.MainBookService;

public class ProjectStarter {
	public static void main(String[] args) {

		BookRepository repository = new DBBookRepository();
		BookService bookService = new MainBookService(repository, true);

		Book book = new Book();
		book.setName("Functional software testing");
		book.setPages(1100);
		book.setYear(2024);
		bookService.saveBook(book);

		List<Book> books = bookService.findBooks();
		System.out.println(books);
	}

}
