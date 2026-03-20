package p42.androidbooksclient.db;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TagService {
    @GET("tags")
    Call<ResponseBody> getTags();

    @GET("books/{id}/tags")
    Call<ResponseBody> getTagsOfBook(@Path("id") String bookID);

    @POST("books/{book_id}/tags/{tag_id}")
    Call<ResponseBody> associateTagToBook(@Path("book_id") String bookID, @Path("tag_id") String tagID);

    @DELETE("books/{book_id}/tags/{tag_id}")
    Call<ResponseBody> dissociateTagToBook(@Path("book_id") String bookID, @Path("tag_id") String tagID);
}
