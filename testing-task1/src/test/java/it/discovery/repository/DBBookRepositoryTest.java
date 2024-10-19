package it.discovery.repository;

import it.discovery.model.Book;
import it.discovery.util.TestModelUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static it.discovery.util.TestModelUtils.createBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class DBBookRepositoryTest {

    BookRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DBBookRepository();
    }

    static final Random RANDOM = new Random();

    static final List<Integer> randomIds = Stream.generate(() ->
            RANDOM.nextInt(Integer.MAX_VALUE)).limit(100).toList();

    @Test
    @DisplayName("""
            When we call saveBook and pass new book then
             1. Identifier value is generate
             2. Book is saved and available in repository""")
    void saveBook_newBook_saved() {
        var book = createBook();
        repository.saveBook(book);

        assertTrue(book.getId() > 0);
        Book book1 = repository.findBookById(book.getId());
        assertNotNull(book1);
        // Compare state of book and book1
        //assertEquals(book, book1);
        assertThat(book1).usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(book);
    }

    @ParameterizedTest
    @FieldSource("randomIds")
    void findBookById_nonExistingId_null(int bookId) {
        assertThat(repository.findBookById(bookId)).isNull();
    }

    @Test
    void findBooks_emptyStorage_returnEmptyList() {
        assertThat(repository.findBooks()).isEmpty();
    }

    @Test
    void findBooks_booksPresent_returnSingleBook() {
        //assumeTrue(repository.findBooks().isEmpty());
        var size = repository.findBooks().size();
        var book = createBook();
        repository.saveBook(book);
        assertThat(repository.findBooks()).hasSize(size + 1);
        var book1 = repository.findBooks().getFirst();
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(book1.getId()).isEqualTo(book.getId());
            assertions.assertThat(book1.getPrice()).isEqualTo(book.getPrice());
        });
    }
}