package p42.androidbooksclient.db;

import okhttp3.RequestBody;
import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthorService {
    @GET("authors")
    Call<ResponseBody> getAuthors();

    @GET("authors")
    Call<ResponseBody> getAuthorsFilter(@Query("lastname") String filter);

    @POST("authors")
    Call<ResponseBody> createAuthor(@Body RequestBody body);

    @GET("authors/{id}")
    Call<ResponseBody> getOneAuthor(@Path("id") String authorID);

    @DELETE("authors/{id}")
    Call<ResponseBody> deleteOneAuthor(@Path("id") String authorID);
}
