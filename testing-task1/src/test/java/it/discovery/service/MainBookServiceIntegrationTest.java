package it.discovery.service;

import it.discovery.repository.DBBookRepository;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static it.discovery.util.TestModelUtils.createBook;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
public class MainBookServiceIntegrationTest {

    BookService bookService;

    @BeforeEach
    void setUp() {
        var bookRepository = new DBBookRepository();
        bookService = new MainBookService(bookRepository, true);
    }

    @Test
    void findBookById_validId_success() {
        var book = createBook();

        bookService.saveBook(book);

        Awaitility.await().pollDelay(Duration.ofMillis(10))
                .pollDelay(Duration.ofMillis(10))
                .atMost(Duration.ofMillis(1000))
                .until(() -> bookService.findBookById(book.getId()) != null);

        assertThat(bookService.findBookById(book.getId())).isSameAs(book);
    }
}
