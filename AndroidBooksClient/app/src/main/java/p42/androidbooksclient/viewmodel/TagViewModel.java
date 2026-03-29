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
    private MutableLiveData<Tag> _tag = new MutableLiveData<>();

    public TagViewModel() {
        _repository = new Repository();
    }

    public void fetchAllTags() {
        _repository.getAllTags(_tags);
    }
    public void createTag(String name) {
        _repository.createTag(_tag, name);
    }

    public LiveData<List<Tag>> getTags() {
        return _tags;
    }
    public LiveData<Tag> getTag() {
        return _tag;
    }
}