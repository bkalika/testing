package it.discovery.service;

import it.discovery.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.discovery.util.TestModelUtils.createBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MainBookServiceTest {

    BookService bookService;

    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock();
        bookService = new MainBookService(bookRepository, false);
    }

    @Test
    void saveBook() {
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