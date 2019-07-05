package ru.ftc.android.shifttemple.features.products.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public interface BooksDataSource {

    void getBooks(Carry<List<Product>> carry);

    void getBook(String id, Carry<Product> carry);

    void createBook(Product product, Carry<Product> carry);

    void deleteBook(String id, Carry<Success> carry);
}
