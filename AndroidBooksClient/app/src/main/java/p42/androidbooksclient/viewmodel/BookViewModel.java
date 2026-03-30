package p42.androidbooksclient.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import p42.androidbooksclient.db.Repository;
import p42.androidbooksclient.model.Book;

public class BookViewModel extends ViewModel {

    private final Repository _repository;
    private MutableLiveData<List<Book>> _books = new MutableLiveData<>();
    private MutableLiveData<Book> _book = new MutableLiveData<>();

    public BookViewModel() {
        _repository = Repository.getInstance();
    }

    public void fetchAllBooks() {
        _repository.getAllBooks(_books);
    }

    public void fetchAllBooksFilter(String filter) {
        _repository.getAllBooksFilter(_books, filter);
    }
    public void fetchOneBook(String bookID) {
        _repository.getOneBook(_book, bookID);
    }

    public void fetchBooksOfAuthor(String authorID) {
        _repository.getBooksOfAuthor(_books, authorID);
    }

    public void createBookOfAuthor(String authorID, String title, Integer publicationYear, List<String> tagsID) {
        _repository.createBookOfAuthor(_book, authorID, title, publicationYear, tagsID);
    }

    public void deleteBook(String bookID) {
        _repository.deleteBook(bookID);
    }

    public void updateBook(String bookID, String title, Integer publicationYear) {
        _repository.updateBook(_book, bookID, title, publicationYear);
    }

    public void associateTag(String bookID, String tagID) {
        _repository.associateTagToBook(_book, _book.getValue(), bookID, tagID);
    }

    public void dissociateTag(String bookID, String tagID) {
        _repository.dissociateTagToBook(_book, _book.getValue(), bookID, tagID);
    }

    public LiveData<List<Book>> getBooks() {
        return _books;
    }

    public LiveData<Book> getBook() {
        return _book;
    }
}