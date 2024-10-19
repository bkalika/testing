package it.discovery.service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockMakers;

import java.time.Duration;

import static it.discovery.util.TestModelUtils.createBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MainBookServiceTest {

    BookService bookService;

    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock(withSettings().mockMaker(MockMakers.PROXY));
        bookService = new MainBookService(bookRepository, true);
    }

    @Test
    void saveBook_cacheEnabled_bookAvailableInCache() {
        Book book = createBook();
        bookService.saveBook(book);

        Awaitility.await().pollDelay(Duration.ofMillis(10))
                .pollDelay(Duration.ofMillis(10))
                .atMost(Duration.ofMillis(1000))
                .until(() -> bookService.findBookById(book.getId()) != null);

        assertThat(bookService.findBookById(book.getId())).isEqualTo(book);

        verify(bookRepository).saveBook(book);
    }

    @Test
    void findBookById_validId_success() {
        final int bookId = 10;
        var book = createBook();
        book.setId(bookId);

        when(bookRepository.findBookById(bookId)).thenReturn(book);

        assertThat(bookService.findBookById(bookId)).isSameAs(book);

        verify(bookRepository).findBookById(bookId);
    }

    @Test
    void findBooks() {
    }

    @Test
    void getRepository() {
    }

    @Test
    void isCachingEnabled() {
    }
}