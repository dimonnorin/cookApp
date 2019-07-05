package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class RecipesRepositoryImpl implements RecipesRepository {

    private final RecipesDataSource dataSource;

    public RecipesRepositoryImpl(RecipesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadRecipes(Carry<List<ShortRecipe>> carry) {
        dataSource.getRecipes(carry);
    }

    @Override
    public void loadRecipe(String id, Carry<Recipe> carry) {
        dataSource.getRecipe(id, carry);
    }

    @Override
    public void createRecipe(ShortRecipe shortRecipe, Carry<ShortRecipe> carry) {
        dataSource.createRecipe(shortRecipe, carry);
    }

    @Override
    public void deleteRecipe(String id, Carry<Success> carry) {
        dataSource.deleteRecipe(id, carry);
    }
}