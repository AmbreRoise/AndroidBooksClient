package p42.androidbooksclient.db;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookService {
    @GET("/books")
    Call<ResponseBody> getBooks();

    @GET("/books/{id}")
    Call<ResponseBody> getOneBook(@Path("id") String bookID);

    @DELETE("/books/{id}")
    Call<ResponseBody> deleteOneBook(@Path("id") String bookID);

    @GET("/authors/{id}/books")
    Call<ResponseBody> getBooksOfAuthor(@Path("id") String authorID);

    @POST("/authors/{id}/books")
    Call<ResponseBody> createBookOfAuthor(@Path("id") String authorID);
}
