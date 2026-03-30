package p42.androidbooksclient.db;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.model.Book;
import p42.androidbooksclient.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    private static Repository _instance = null;
    private static final String API_URL = "http://127.0.0.1:3000/";
    private AuthorService authorService;
    private BookService bookService;
    private TagService tagService;
    private Retrofit retrofit;

    private Repository(){
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        authorService = retrofit.create(AuthorService.class);
        bookService = retrofit.create(BookService.class);
        tagService = retrofit.create(TagService.class);
    }

    public static Repository getInstance() {
        if(_instance == null){
            _instance = new Repository();
        }
        return _instance;
    }

    // ================== AUTHORS ======================

    public void getAllAuthors(MutableLiveData<List<Author>> foundAuthors){
        authorService.getAuthors().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Author> authors = getListAuthors(json);

                        foundAuthors.setValue(authors);
                    }
                    catch (JSONException | IOException e){
                        Log.e("Retrofit", "getAllAuthors parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getAllAuthors : " + t.getMessage());
            }
        });
    }

    public void getAllAuthorsFilter(MutableLiveData<List<Author>> foundAuthors, String filter){
        authorService.getAuthorsFilter(filter).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Author> authors = getListAuthors(json);
                        foundAuthors.setValue(authors);
                    }
                    catch (JSONException | IOException e){
                        Log.e("Retrofit", "getAllAuthorsFilter parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getAllAuthorsFilter : " + t.getMessage());
            }
        });
    }

    private List<Author> getListAuthors(JSONArray json) throws JSONException {
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

        return authors;
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
                    catch (JSONException | IOException e){
                        Log.e("Retrofit", "getOneAuthor parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getOneAuthor : " + t.getMessage());
            }
        });
    }

    public void createAuthor(MutableLiveData<Author> createdAuthor, String firstname, String lastname){
        // Création du JSON pour le body de la Request
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("firstname", firstname);
            jsonObject.put("lastname", lastname);
        }
        catch (JSONException e){
            Log.e("Repository", "createAuthor JSON error : " + e.getMessage());
            return;
        }

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json")
        );

        authorService.createAuthor(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        Author author = new Author(
                                json.getInt("id"),
                                json.getString("firstname"),
                                json.getString("lastname")
                        );

                        createdAuthor.setValue(author);
                    }
                    catch(JSONException | IOException e){
                        Log.e("Repository", "createAuthor parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on createAuthor : " + t.getMessage());
            }
        });
    }

    public void deleteAuthor(String authorID){
        authorService.deleteOneAuthor(authorID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.i("Repository", "Author " + authorID + " deleted");
                } else {
                    Log.e("Repository", "deleteAuthor failed with status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on deleteAuthor : " + t.getMessage());
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
                        List<Book> books = getListBooks(json);
                        foundBooks.setValue(books);
                    }
                    catch (JSONException | IOException e){
                        Log.e("Retrofit", "getAllBooks parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getAllBooks : " + t.getMessage());
            }
        });
    }

    public void getAllBooksFilter(MutableLiveData<List<Book>> foundBooks, String filter){
        bookService.getBooksFilter(filter).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONArray json = new JSONArray(response.body().string());
                        List<Book> books = getListBooks(json);
                        foundBooks.setValue(books);
                    }catch (JSONException | IOException e) {
                        Log.e("Retrofit", "getAllBooksFilter parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getAllBooksFilter : " + t.getMessage());
            }
        });
    }

    private List<Book> getListBooks(JSONArray json) throws JSONException {
        List<Book> books = new ArrayList<>();

        for(int i=0; i<json.length(); i++){
            JSONObject jsonObject = json.getJSONObject(i);
            Book book = new Book(
                    jsonObject.getInt("id"),
                    jsonObject.getString("title"),
                    jsonObject.isNull("publication_year") ? null : jsonObject.getInt("publication_year"),
                    jsonObject.getInt("authorId")
            );
            books.add(book);
        }

        return books;
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
                                json.isNull("publication_year") ? null : json.getInt("publication_year"),
                                json.getInt("authorId")
                        );

                        getTagsOfBook(foundBook, book, bookID);
                    }
                    catch (JSONException | IOException e) {
                        Log.e("Retrofit", "getOneBook parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getOneBook : " + t.getMessage());
            }
        });
    }

    public void deleteBook(String bookID){
        bookService.deleteOneBook(bookID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.i("Repository", "Book " + bookID + " deleted");
                } else {
                    Log.e("Repository", "deleteBook failed with status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on deleteBook : " + t.getMessage());
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
                                    jsonObject.isNull("publication_year") ? null : jsonObject.getInt("publication_year"),
                                    jsonObject.getInt("authorId")
                            );
                            books.add(book);
                        }
                        foundBooks.setValue(books);
                    }
                    catch (JSONException | IOException e) {
                        Log.e("Retrofit", "getBooksOfAuthor parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getBooksOfAuthor : " + t.getMessage());
            }
        });
    }

    public void createBookOfAuthor(MutableLiveData<Book> createdBook, String authorID, String title, Integer publicationYear, List<String> tagsID){
        // Création du JSON pour le body de la Request
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("title", title);
            if(publicationYear != null){
                jsonObject.put("publication_year", publicationYear);
            }
        }
        catch(JSONException e){
            Log.e("Repository", "createBookOfAuthor JSON error: " + e.getMessage());
            return;
        }

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json")
        );

        bookService.createBookOfAuthor(authorID, body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body().string());
                        Book book = new Book(
                                json.getInt("id"),
                                json.getString("title"),
                                null,
                                json.getInt("authorId")
                        );

                        if(json.has("publication_year") && !json.isNull("publication_year")){
                            book.setPublicationYear(json.getInt("publication_year"));
                        }

                        if(tagsID.isEmpty()){
                            createdBook.setValue(book);
                            return;
                        }

                        for(String tagID : tagsID){
                            associateTagToBook(createdBook, book, book.getID().toString(), tagID);
                        }
                    }catch (JSONException | IOException e) {
                        Log.e("Retrofit", "createAuthor parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on createBookOfAuthor : " + t.getMessage());
            }
        });
    }

    public void updateBook(MutableLiveData<Book> updatedBook, String bookID, String title, Integer publicationYear){
        // Création du JSON pour le body de la Request
        JSONObject jsonObject = new JSONObject();
        try{
            if(title != null){
                jsonObject.put("title", title);
            }

            if(publicationYear != null){
                jsonObject.put("publication_year", publicationYear);
            }
        }
        catch(JSONException e){
            Log.e("Repository", "updateBook JSON error: " + e.getMessage());
            return;
        }

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json")
        );

        bookService.updateOneBook(bookID, body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body().string());
                        Book book = new Book(
                                json.getInt("id"),
                                json.getString("title"),
                                json.isNull("publication_year") ? null : json.getInt("publication_year"),
                                json.getInt("authorId")
                        );

                        updatedBook.setValue(book);
                    }
                    catch(JSONException | IOException e){
                        Log.e("Retrofit", "updateBook parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on updateBook : " + t.getMessage());
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
                    catch (JSONException | IOException e) {
                        Log.e("Retrofit", "getAllTags parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getAllTags : " + t.getMessage());
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
                    catch (JSONException | IOException e) {
                        Log.e("Retrofit", "getTagsOfBook parse error : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on getTagsOfBook : " + t.getMessage());
            }
        });
    }

    public void associateTagToBook(MutableLiveData<Book> associatedBook, Book book, String bookID, String tagID){
        tagService.associateTagToBook(bookID, tagID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body().string());
                        JSONArray tagsArray = json.getJSONArray("tags");
                        List<Tag> tags = new ArrayList<>();

                        for(int i=0; i<tagsArray.length(); i++){
                            JSONObject jsonObject = tagsArray.getJSONObject(i);
                            Tag tag = new Tag(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("name")
                            );
                            tags.add(tag);
                        }

                        book.setTags(tags);
                        associatedBook.setValue(book);
                    }
                    catch(JSONException | IOException e){
                        Log.e("Retrofit", "associateTagToBook parse error" + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on associateTagToBook : " + t.getMessage());
            }
        });
    }

    public void dissociateTagToBook(MutableLiveData<Book> dissociatedBook, Book book,String bookID, String tagID){
        tagService.dissociateTagToBook(bookID, tagID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body().string());
                        JSONArray tagsJson = json.getJSONArray("tags");
                        List<Tag> tags = new ArrayList<>();
                        for(int i=0; i<tagsJson.length(); i++){
                            Tag tag = new Tag(
                                    tagsJson.getJSONObject(i).getInt("id"),
                                    tagsJson.getJSONObject(i).getString("name")
                            );
                            tags.add(tag);
                        }
                        book.setTags(tags);
                        dissociatedBook.setValue(book);
                    }
                    catch(JSONException | IOException e){
                        Log.e("Retrofit", "dissociateTagToBook parse error" + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on dissociateTagToBook : " + t.getMessage());
            }
        });
    }

    public void createTag(MutableLiveData<Tag> createdTag, String name){
        // Création du JSON pour le body de la Request
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", name);
        }
        catch(JSONException e){
            Log.e("Repository", "createTag JSON error: " + e.getMessage());
            return;
        }

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json")
        );

        tagService.createTag(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body().string());
                        Tag tag = new Tag(
                                json.getInt("id"),
                                json.getString("name")
                        );

                        createdTag.setValue(tag);
                    }
                    catch(JSONException | IOException e){
                        Log.e("Retrofit", "createTag parse error" + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Repository", "Retrofit error on createTag : " + t.getMessage());
            }
        });
    }
}
