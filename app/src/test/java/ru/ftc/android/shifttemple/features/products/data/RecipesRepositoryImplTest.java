package ru.ftc.android.shifttemple.features.products.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created: samokryl
 * Date: 04.07.18
 * Time: 10:15
 */

@RunWith(MockitoJUnitRunner.class)
public class RecipesRepositoryImplTest {

    private BooksRepository repository;

    private final BooksDataSource dataSource = mock(BooksDataSource.class);

    @Before
    public void setUp() throws Exception {
        repository = new BooksRepositoryImpl(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void loadBooks() {
        Carry<List<Product>> carry = mock(Carry.class);

        repository.loadBooks(carry);

        verify(dataSource).getBooks(carry);
    }

    @Test
    public void loadBook() {
        final String bookId = "123";
        Carry<Product> carry = mock(Carry.class);

        repository.loadBook(bookId, carry);

        verify(dataSource).getBook(bookId, carry);
    }

    @Test
    public void createBook() {
        final Product product = mock(Product.class);
        Carry<Product> carry = mock(Carry.class);

        repository.createBook(product, carry);

        verify(dataSource).createBook(product, carry);
    }

    @Test
    public void deleteBook() {
        final String bookId = "123";
        Carry<Success> carry = mock(Carry.class);

        repository.deleteBook(bookId, carry);

        verify(dataSource).deleteBook(bookId, carry);
    }
}