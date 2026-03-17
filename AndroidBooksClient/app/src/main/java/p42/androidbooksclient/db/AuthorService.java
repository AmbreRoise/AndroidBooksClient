package p42.androidbooksclient.db;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthorService {
    @GET("/authors")
    Call<ResponseBody> getAuthors();

    @POST("/authors")
    Call<ResponseBody> createAuthor();

    @GET("/authors/{id}")
    Call<ResponseBody> getOneAuthor(@Path("id") String authorID);

    @DELETE("/authors/{id}")
    Call<ResponseBody> deleteOneAuthor(@Path("id") String authorID);
}
