package ru.ftc.android.shifttemple.features.products.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.products.domain.BooksInteractor;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:43
 */
//TODO тут работа с запросами , можно тут добавлять реакции, вызовы через presenter
//TODO Реакции на пользовательские действия
final class ProductsListPresenter extends MvpPresenter<ProductListView> {

    private final BooksInteractor interactor;

    //for test without network
    private List<Product> productsData = new ArrayList<>();

    ProductsListPresenter(BooksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        //TODO Статически заданные начальные поля продуктов, вызывается при переходе на активити
        productsData.add(new Product("First","Other0","12"));
        productsData.add(new Product("Second ","Other1","12"));
        productsData.add(new Product("Third","Other2","12"));
        loadProducts();
    }


    //TODO реакция на взаимодействие с продуктом
    void onProductTypeDelete(Product product) {
        productsData.remove(product);
        loadProducts();
    }

    void onProductAdd(Product product) {
        product.setCount(product.getCount() + 1);
    }

    void onProductReduce(Product product) {
        int count = product.getCount() - 1;
        if(count == 0){
            onProductTypeDelete(product);
            return;
        }
        product.setCount(count);
    }



    void onProductTypeAdd(String name){

    }








    private void loadProducts() {

        view.showProgress();

        view.showProductList(productsData);

        //
        interactor.loadBooks(new Carry<List<Product>>() {
            @Override
            public void onSuccess(List<Product> result) {
                view.showProductList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                List<Product> res = new ArrayList<>();
                //TODO here is products definition
                res.add(new Product("First Thing","Other","12"));
                res.add(new Product("First Thing","Other","12"));
                res.add(new Product("First Thing","Other","12"));
                view.showProductList(res);
                //view.showError(throwable.getMessage());
            }

        });
    }




    //все ниже оставлено для примера для примера
    void onBookSelected(Product product) {
        view.showProgress();
        /*interactor.loadRecipe(product.getId(), new Carry<Product>() {

            @Override
            public void onSuccess(Product result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });*/
    }

    void onBookLongClicked(Product product) {
        view.showProgress();


        /*interactor.deleteRecipe(product.getId(), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadProducts();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });*/
    }

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void onCreateBookClicked() {
        int id = atomicInteger.incrementAndGet();
        String name = "Name_" + id;
        String author = "Author_" + id;
        int pages = 7 * id;

        Product product = new Product(name, author, String.valueOf(pages));
        interactor.createBook(product, new Carry<Product>() {
            @Override
            public void onSuccess(Product result) {
                loadProducts();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

}