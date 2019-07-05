package ru.ftc.android.shifttemple.features.products.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.products.data.BooksApi;
import ru.ftc.android.shifttemple.features.products.data.BooksDataSource;
import ru.ftc.android.shifttemple.features.products.data.BooksDataSourceImpl;
import ru.ftc.android.shifttemple.features.products.data.BooksRepository;
import ru.ftc.android.shifttemple.features.products.data.BooksRepositoryImpl;
import ru.ftc.android.shifttemple.features.products.domain.BooksInteractor;
import ru.ftc.android.shifttemple.features.products.domain.BooksInteractorImpl;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 1:03
 */

final class PresenterFactory {
    static ProductsListPresenter createPresenter(Context context) {
        final BooksApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(BooksApi.class);//создаем api а основе возможных запросов и url

        //запросы с RestApi
        final BooksDataSource dataSource = new BooksDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final BooksInteractor interactor = new BooksInteractorImpl(repository);
        //TODO interactor created here
        return new ProductsListPresenter(interactor);
    }
}