package ru.ftc.android.shifttemple.features.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.products.presentation.ProductsFragment;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */
//BookListView: available actions//TODO rename

    //replace BookListView,add another MvpView
public final class MainActivity extends BaseActivity implements MainView {


    //TODO старт MAIN ACTIVITY (там Tab layout)
    public static void start(final Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    private ProductsFragment productsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();//TODO INIT MAIN View is here
    }

    private void initView() {

        /*adapter = new ProductAdapter(this, new ProductAdapter.SelectProductListener() {


            //Реакция на выбор продукта
            //можно убрать
            @Override
            public void onDelete(Product book) {
                presenter.onBookSelected(book);
            }

            @Override
            public void onBookLongClick(Product book) {
                presenter.onBookLongClicked(book);
            }
        });*/





        //tab system
        /*TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager =  findViewById(R.id.view_pager);*/


        //ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());



        //TODO replace this
        //presenter not init
        //so hard
        /*viewPagerAdapter.addFragment(new ProductsFragment(), "Products");
        viewPagerAdapter.addFragment(new RecipesFragment(),"Recipes");*/

        //viewPager.setAdapter(viewPagerAdapter);
       //tabLayout.setupWithViewPager(viewPager);


    }



    //Adapter for TabLayout
    /*class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }*/



    /*@Override
    protected MvpPresenter<BookListView> getPresenter() {
        //заглушка
        //presenter = PresenterFactory.createPresenter(this);
        return null;
    }*/

    @Override
    protected MvpPresenter getPresenter() {
        //заглушка
        //presenter = PresenterFactory.createPresenter(this);
        return null;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
