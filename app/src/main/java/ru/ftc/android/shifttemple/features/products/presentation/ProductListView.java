package ru.ftc.android.shifttemple.features.products.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:22
 */

interface ProductListView extends MvpView {

    void showProgress();

    void hideProgress();

    void showProductList(List<Product> list);

    void showError(String message);

}
