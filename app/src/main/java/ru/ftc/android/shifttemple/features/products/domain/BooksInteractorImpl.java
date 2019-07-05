package ru.ftc.android.shifttemple.features.products.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.data.BooksRepository;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class BooksInteractorImpl implements BooksInteractor {
    //TODO imlemantation
    private final BooksRepository repository;

    public BooksInteractorImpl(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadBooks(Carry<List<Product>> carry) {
        repository.loadBooks(carry);
    }

    @Override
    public void loadBook(String id, Carry<Product> carry) {
        repository.loadBook(id, carry);
    }

    @Override
    public void createBook(Product product, Carry<Product> carry) {
        repository.createBook(product, carry);
    }

    @Override
    public void deleteBook(String id, Carry<Success> carry) {
        repository.deleteBook(id, carry);
    }
}