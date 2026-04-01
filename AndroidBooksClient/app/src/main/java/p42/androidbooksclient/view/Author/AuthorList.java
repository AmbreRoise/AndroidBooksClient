package p42.androidbooksclient.view.Author;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.view.Book.BookListAdapter;
import p42.androidbooksclient.viewmodel.AuthorViewModel;

public class AuthorList extends Fragment implements AuthorListAdapter.OnNoteListener {

    public AuthorList() {
        super(R.layout.fragment_author_list);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        view.findViewById(R.id.fabAddAuthor).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_authorList_to_authorCreate);
        });

        AuthorViewModel authorData = new ViewModelProvider(this).get(AuthorViewModel.class);

        RecyclerView recycler = view.findViewById(R.id.authorList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        AuthorListAdapter adapter = new AuthorListAdapter(new ArrayList<>(), this);
        recycler.setAdapter(adapter);

        authorData.fetchAllAuthors();

        view.findViewById(R.id.BLConfirmFilter).setOnClickListener(v -> {
            String filter = ((EditText) view.findViewById(R.id.searchBookName)).getText().toString().trim();
            if(filter.isEmpty()){
                authorData.fetchAllAuthors();
            } else {
                authorData.fetchAllAuthorsFilter(filter);
            }
        });

        authorData.getAuthors().observe(getViewLifecycleOwner(), data -> {
            adapter.updateData(data);
        });

    }
    public void onNoteClick(int authorId){
        Bundle bundle = new Bundle();
        bundle.putInt("authorId", authorId);
        Navigation.findNavController(getView()).navigate(R.id.action_authorList_to_authorDescription, bundle);
    }

}