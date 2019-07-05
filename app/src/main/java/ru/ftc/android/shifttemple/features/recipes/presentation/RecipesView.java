package ru.ftc.android.shifttemple.features.recipes.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;

public interface RecipesView extends MvpView {
    void showProgress();

    void startRecipeActivity(ShortRecipe shortRecipe);

    void hideProgress();

    void showRecipesList(List<ShortRecipe> list);

    void showError(String message);
}
