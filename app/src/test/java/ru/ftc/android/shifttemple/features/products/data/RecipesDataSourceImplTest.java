package ru.ftc.android.shifttemple.features.products.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created: samokryl
 * Date: 04.07.18
 * Time: 9:54
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipesDataSourceImplTest {

    private BooksDataSource dataSource;

    private final BooksApi api = mock(BooksApi.class);
    private final Product firstProduct = mock(Product.class);
    private final Product secondProduct = mock(Product.class);
    private final List<Product> productList = new ArrayList<>();
    private final String bookId = "123";

    @Before
    public void setUp() {
        productList.add(firstProduct);
        productList.add(secondProduct);

        dataSource = new BooksDataSourceImpl(api);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(api);
    }

    @Test
    public void getBooks() {
        Carry<List<Product>> carry = mock(Carry.class);
        Call<List<Product>> call = mock(Call.class);
        when(api.getBookList()).thenReturn(call);

        dataSource.getBooks(carry);

        verify(api).getBookList();
    }

    @Test
    public void getBook() {
        Carry<Product> carry = mock(Carry.class);
        Call<Product> call = mock(Call.class);
        when(api.getBook(bookId)).thenReturn(call);

        dataSource.getBook(bookId, carry);

        verify(api).getBook(bookId);

    }

    @Test
    public void createBook() {
        Carry<Product> carry = mock(Carry.class);
        Call<Product> call = mock(Call.class);
        when(api.createBook(secondProduct)).thenReturn(call);

        dataSource.createBook(secondProduct, carry);

        verify(api).createBook(secondProduct);
    }

    @Test
    public void deleteBook() {
        Carry<Success> carry = mock(Carry.class);
        Call<Success> call = mock(Call.class);
        when(api.deleteBook(bookId)).thenReturn(call);

        dataSource.deleteBook(bookId, carry);

        verify(api).deleteBook(bookId);
    }
}