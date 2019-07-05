package ru.ftc.android.shifttemple.features.products.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public final class BooksDataSourceImpl implements BooksDataSource {

    private final BooksApi booksApi;

    public BooksDataSourceImpl(BooksApi booksApi) {
        this.booksApi = booksApi;
    }

    @Override
    public void getBooks(final Carry<List<Product>> carry) {
        //TODO создаем запрос на основе carry, в carry будет success or failure
        booksApi.getBookList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getBook(String id, Carry<Product> carry) {
        booksApi.getBook(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createBook(Product product, Carry<Product> carry) {
        booksApi.createBook(product).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteBook(String id, Carry<Success> carry) {
        booksApi.deleteBook(id).enqueue(new DefaultCallback(carry));
    }
}