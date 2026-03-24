package p42.androidbooksclient.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import p42.androidbooksclient.db.Repository;
import p42.androidbooksclient.model.Tag;

public class TagViewModel extends ViewModel {

    private final Repository _repository;
    private MutableLiveData<List<Tag>> _tags = new MutableLiveData<>();

    public TagViewModel() {
        _repository = new Repository();
    }

    public void fetchAllTags() {
        _repository.getAllTags(_tags);
    }

    public LiveData<List<Tag>> getTags() {
        return _tags;
    }
}