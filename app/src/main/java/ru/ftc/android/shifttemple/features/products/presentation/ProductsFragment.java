package ru.ftc.android.shifttemple.features.products.presentation;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductListView {


    private ProductsListPresenter presenter;
    private ProductAdapter adapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ImageButton createProductButton;
    private Dialog dialog;


    private AlertDialog.Builder builder;
    //TODO WHY
    private final int ID_CHECK_PRODUCTS = 0;

    //дублирование исправить
    private List<Product> products = new ArrayList<>();

    private View fragmentView;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_products, container, false);
        // Inflate the layout for this fragment
        initView();
        return fragmentView;
    }

    /*public void setProducts(List<Product> list) {
        Log.println(Log.DEBUG, "Test","Set product");
        if(adapter != null){
            adapter.setProducts(list);
            return;
        }
        products = list;
    }*/




    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case ID_CHECK_PRODUCTS:
                final int count = 19;
                final boolean[] mCheckedItems = new boolean[count];
                for (int i = 0; i < count; ++i){
                    mCheckedItems[i] = false;
                }
                final String[] checkProductsName = { "Картофель", "Гречка", "Рис",
                        "Лук", "Чеснок", "Морковь",
                        "Перец", "Соль", "Укроп", "Свинина", "Хлеб", "Молоко", "Сыр", "Творог",
                        "Помидоры", "Огурцы", "Яюлоки", "Сахар"};
                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Выберите продукты")
                        .setCancelable(false)
                        .setMultiChoiceItems(checkProductsName, mCheckedItems,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {
                                        mCheckedItems[which] = isChecked;
                                    }
                                })
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {



                                        //реакция на выбор добавить




                                    }})
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }});
        }
        return builder.create();
    }


    private void initView(){

        presenter = PresenterFactory.createPresenter(getContext());
        //presenter.attachView(this);moved down

        progressBar = fragmentView.findViewById(R.id.books_progress);
        recyclerView = fragmentView.findViewById(R.id.products_recycle_view);

        createProductButton = fragmentView.findViewById(R.id.create_product_button);//TODO тут кнопка добавления


        dialog = onCreateDialog(ID_CHECK_PRODUCTS);

        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Вика
                dialog.show();
                //presenter.onProductTypeAdd();
            }
        });


        //TODO тут передавать Listener для кнопок
        adapter = new ProductAdapter(getContext(), new ProductAdapter.ProductListener() {
            //TODO внутри presenter через view вызов методов this
            @Override
            public void onProductTypeDelete(Product product) {
                presenter.onProductTypeDelete(product);

            }

            @Override
            public void onProductAdd(Product product) {
                presenter.onProductAdd(product);
            }

            @Override
            public void onProductReduce(Product product) {
                presenter.onProductReduce(product);
            }

            //Реакция на выбор продукта
            //можно убрать
            //@Override
            /*public void onDelete(Product product) {

                presenter.onBookSelected(product);
            }

            //@Override
            public void onBookLongClick(Product product) {
                presenter.onBookLongClicked(product);
            }*/
        });
        //adapter.setProducts(products);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.attachView(this);
        Log.println(Log.DEBUG, "Test","Init view");
    }

    @Override
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
        //recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        //progressBar.setVisibility(View.GONE);
        //recyclerView.setVisibility(View.VISIBLE);
    }



    @Override
    public void showProductList(List<Product> list) {
        //юда прилетают данные в случае успеха
        adapter.setProducts(list);
    }

    @Override
    public void showError(String message) {
        //.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
