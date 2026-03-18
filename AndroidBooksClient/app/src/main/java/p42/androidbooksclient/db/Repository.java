package p42.androidbooksclient.db;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.ResponseBody;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.model.Book;
import p42.androidbooksclient.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    private static final String API_URL = "http://localhost:3000";
    private AuthorService authorService;
    private BookService bookService;
    private TagService tagService;
    private Retrofit retrofit;

    public Repository(){
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        authorService = retrofit.create(AuthorService.class);
        bookService = retrofit.create(BookService.class);
        tagService = retrofit.create(TagService.class);
    }

    // ================== AUTHORS ======================

    public void getAllAuthors(MutableLiveData<List<Author>> foundAuthors){
        authorService.getAuthors().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getOneAuthor(MutableLiveData<Author> foundAuthor, String authorID){
        authorService.getOneAuthor(authorID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void createAuthor(MutableLiveData<Author> createdAuthor){
        authorService.createAuthor().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void deleteAuthor(MutableLiveData<Author> deletedAuthor, String authorID){
        authorService.deleteOneAuthor(authorID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    // ================== BOOKS ======================
    public void getAllBooks(MutableLiveData<List<Book>> foundBooks){
        bookService.getBooks().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getOneBook(MutableLiveData<Book> foundBook, String bookID){
        bookService.getOneBook(bookID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void deleteBook(MutableLiveData<Book> deletedBook, String bookID){
        bookService.deleteOneBook(bookID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getBooksOfAuthor(MutableLiveData<List<Book>> foundBooks, String authorID){
        bookService.getBooksOfAuthor(authorID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void createBookOfAuthor(MutableLiveData<Book> createdBook, String authorID){
        bookService.createBookOfAuthor(authorID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    // ================== TAGS ======================
    public void getAllTags(MutableLiveData<List<Tag>> foundTags){
        tagService.getTags().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getTagsOfBook(MutableLiveData<Tag> foundTag, String bookID){
        tagService.getTagsOfBook(bookID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void associateTagToBook(MutableLiveData<Book> associatedBook, String bookID, String tagID){
        tagService.associateTagToBook(bookID, tagID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void dissociateTagToBook(MutableLiveData<Book> dissociateBook, String bookID, String tagID){
        tagService.dissociateTagToBook(bookID, tagID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
