package ru.ftc.android.shifttemple.features.products.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class BooksRepositoryImpl implements BooksRepository {

    private final BooksDataSource dataSource;

    public BooksRepositoryImpl(BooksDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadBooks(Carry<List<Product>> carry) {
        dataSource.getBooks(carry);
    }

    @Override
    public void loadBook(String id, Carry<Product> carry) {
        dataSource.getBook(id, carry);
    }

    @Override
    public void createBook(Product product, Carry<Product> carry) {
        dataSource.createBook(product, carry);
    }

    @Override
    public void deleteBook(String id, Carry<Success> carry) {
        dataSource.deleteBook(id, carry);
    }
}