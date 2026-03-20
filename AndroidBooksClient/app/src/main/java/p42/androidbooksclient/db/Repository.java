package p42.androidbooksclient.db;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
    private static final String API_URL = "http://127.0.0.1:3000/";
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
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Author> authors = new ArrayList<>();

                        for(int i=0; i<json.length(); i++){
                            JSONObject jsonObject = json.getJSONObject(i);
                            Author author = new Author(
                                jsonObject.getInt("id"),
                                jsonObject.getString("firstname"),
                                jsonObject.getString("lastname")
                            );
                            authors.add(author);
                        }

                        foundAuthors.setValue(authors);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Authors request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Author request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Repository", "Retrofit error on getAllAuthors(data)");
            }
        });
    }

    public void getOneAuthor(MutableLiveData<Author> foundAuthor, String authorID){
        authorService.getOneAuthor(authorID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body().string());
                        Author author = new Author(
                                json.getInt("id"),
                                json.getString("firstname"),
                                json.getString("lastname")
                        );
                        foundAuthor.setValue(author);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Author request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Author request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getOneAuthor: " + t.getMessage());
                Log.e("Repository", "Cause: " + t.getCause());
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
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Book> books = new ArrayList<>();

                        for(int i=0; i<json.length(); i++){
                            JSONObject jsonObject = json.getJSONObject(i);
                            Book book = new Book(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getInt("publication_year"),
                                    jsonObject.getInt("authorId")
                            );
                            books.add(book);
                        }
                        foundBooks.setValue(books);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Books request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Books request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Repository", "Retrofit error on getAllBooks(data)");
            }
        });
    }

    public void getOneBook(MutableLiveData<Book> foundBook, String bookID){
        bookService.getOneBook(bookID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        Book book = new Book(
                                json.getInt("id"),
                                json.getString("title"),
                                json.getInt("publication_year"),
                                json.getInt("authorId")
                        );

                        getTagsOfBook(foundBook, book, bookID);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Book request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Book request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Repository", "Retrofit error on getOneBook(data, bookID)");
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
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Book> books = new ArrayList<>();

                        for(int i=0; i<json.length(); i++){
                            JSONObject jsonObject = json.getJSONObject(i);
                            Book book = new Book(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getInt("publication_year"),
                                    jsonObject.getInt("authorId")
                            );
                            books.add(book);
                        }
                        foundBooks.setValue(books);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Books of author request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Books of author request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Repository", "Retrofit error on getBooksOfAuthor(data, authorID)");
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
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Tag> tags = new ArrayList<>();

                        for(int i=0; i<json.length(); i++){
                            JSONObject jsonObject = json.getJSONObject(i);
                            Tag tag = new Tag(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("name")
                            );
                            tags.add(tag);
                        }
                        foundTags.setValue(tags);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Tags request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Tags request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Repository", "Retrofit error on getAllTags(data)");
            }
        });
    }

    private void getTagsOfBook(MutableLiveData<Book> book, Book bookObj, String bookID){
        tagService.getTagsOfBook(bookID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Tag> tags = new ArrayList<>();

                        for(int i=0; i<json.length(); i++){
                            JSONObject jsonObject = json.getJSONObject(i);
                            Tag tag = new Tag(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("name")
                            );
                            tags.add(tag);
                        }

                        bookObj.setTags(tags);
                        book.setValue(bookObj);
                    }
                    catch (JSONException | IOException exception){
                        try {
                            Log.d("Tags of book request", response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Tags of book request", exception.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Repository", "Retrofit error on getTagsOfBook(data, bookID)");
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
