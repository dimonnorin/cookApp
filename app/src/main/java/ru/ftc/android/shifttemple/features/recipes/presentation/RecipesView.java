package ru.ftc.android.shifttemple.features.recipes.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;

public interface RecipesView extends MvpView {
    void showProgress();

    void startRecipeActivity(Recipe recipe);

    void hideProgress();

    void showRecipesList(List<Recipe> list);

    void showError(String message);
}
