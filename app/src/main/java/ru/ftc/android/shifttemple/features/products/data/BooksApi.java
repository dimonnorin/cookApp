package ru.ftc.android.shifttemple.features.products.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface BooksApi {

    /*Параметры запроса добавляются с помощью аннотации
    @Query к параметру метода. Они автоматически добавляются в конце URL-адреса.*/

    @GET("books")
    Call<List<Product>> getBookList();

    @GET("books/{id}")// String id замещает {id}
    Call<Product> getBook(@Path("id") String id);

    @POST("books")//Body использует объект product в качестве тела запроса,отправляем его
    Call<Product> createBook(@Body Product product);



    @DELETE("books/{id}")
    Call<Success> deleteBook(@Path("id") String id);

}
