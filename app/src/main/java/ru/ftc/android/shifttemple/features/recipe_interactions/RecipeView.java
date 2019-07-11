package ru.ftc.android.shifttemple.features.recipe_interactions;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;

public interface RecipeView extends MvpView {


    void onLoadRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void showError(String message);

    void onLoadMembersIngredients(List<MemberIngredients> membersIngredients);

    void showProgress();

    void hideProgress();

}
