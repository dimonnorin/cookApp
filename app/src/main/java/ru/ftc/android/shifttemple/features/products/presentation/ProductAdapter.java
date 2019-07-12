package ru.ftc.android.shifttemple.features.products.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:24
 */

final class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private final List<Product> products = new ArrayList<>();
    private final LayoutInflater inflater;
    private final ProductListener productListener;
    private final Animation clickAnimation;

    ProductAdapter(Context context, ProductListener productListener) {
        inflater = LayoutInflater.from(context);
        this.productListener = productListener;
        clickAnimation = AnimationUtils.loadAnimation(context,R.anim.card_click_anim);
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO устанавливаем шаблон для каждого holder

        final View itemView = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductHolder(itemView, productListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> productList) {
        products.clear();
        products.addAll(productList);
        notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final TextView productName;
        private final ImageButton reduceButton;
        private final TextView countText;
        private final ImageButton addButton;
        private final ImageButton deleteButton;


        private final ProductListener productListener;

        ProductHolder(View view, ProductListener productListener) {
            super(view);
            this.productListener = productListener;
            productName = view.findViewById(R.id.product_item_name);
            reduceButton = view.findViewById(R.id.reduce_button);
            countText = view.findViewById(R.id.product_text_count);
            addButton = view.findViewById(R.id.add_button);
            deleteButton = view.findViewById(R.id.delete_product_button);

            //bookAuthorView = view.findViewById(R.id.book_item_author);
        }

        void bind(final Product product) {
            productName.setText(product.getName());//TODO add shelfTime

            countText.setText(Integer.toString(product.getCount()));



            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addButton.startAnimation(clickAnimation);
                    productListener.onProductAdd(product);
                    countText.setText(Integer.toString(product.getCount()));
                }
            });

            reduceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reduceButton.startAnimation(clickAnimation);
                    productListener.onProductReduce(product);
                    countText.setText(Integer.toString(product.getCount()));
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteButton.startAnimation(clickAnimation);
                    productListener.onProductTypeDelete(product);
                }
            });


            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productListener.onDelete(product);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    productListener.onBookLongClick(product);
                    return true;
                }
            });*/

        }

    }
    //выпилить
    interface ProductListener {

        void onProductTypeDelete(Product product);

        void onProductAdd(Product product);

        void onProductReduce(Product product);

    }

}
