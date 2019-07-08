package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesApi;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesDataSource;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesDataSourceImpl;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepository;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepositoryImpl;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractor;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractorImpl;

final class PresenterFactory {
    static RecipePresenter createPresenter(Context context) {
        final RecipesApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(RecipesApi.class);//создаем api а основе возможных запросов и url


        //api.getRecipe("fg").execute();execute immediately
        //запросы с RestApi
        final RecipesDataSource dataSource = new RecipesDataSourceImpl(api);
        final RecipesRepository repository = new RecipesRepositoryImpl(dataSource);
        final RecipesInteractor interactor = new RecipesInteractorImpl(repository);

        return new RecipePresenter(interactor);
    }
}