package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesApi;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesDataSource;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesDataSourceImpl;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepository;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepositoryImpl;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractor;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractorImpl;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 1:03
 */

final class PresenterFactory {
    static RecipesListPresenter createPresenter(Context context) {
        final RecipesApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(RecipesApi.class);//создаем api а основе возможных запросов и url


        //api.getRecipe("fg").execute();execute immediately
        //запросы с RestApi
        final RecipesDataSource dataSource = new RecipesDataSourceImpl(api);
        final RecipesRepository repository = new RecipesRepositoryImpl(dataSource);
        final RecipesInteractor interactor = new RecipesInteractorImpl(repository);
        //TODO interactor created here
        return new RecipesListPresenter(interactor);
    }
}