package p42.androidbooksclient.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import p42.androidbooksclient.db.Repository;
import p42.androidbooksclient.model.Author;

public class AuthorViewModel extends ViewModel {

    private final Repository _repository;
    private MutableLiveData<List<Author>> _authors = new MutableLiveData<>();
    private MutableLiveData<Author> _author = new MutableLiveData<>();

    public AuthorViewModel() {
        _repository = Repository.getInstance();
    }

    public void fetchAllAuthors() {
        _repository.getAllAuthors(_authors);
    }

    public void fetchOneAuthor(String authorID) {
        _repository.getOneAuthor(_author, authorID);
    }

    public void createAuthor(String firstname, String lastname) {
        _repository.createAuthor(_author, firstname, lastname);
    }

    public void deleteAuthor(String authorID) {
        _repository.deleteAuthor(authorID);
    }

    public LiveData<List<Author>> getAuthors() {
        return _authors;
    }

    public LiveData<Author> getAuthor() {
        return _author;
    }
}