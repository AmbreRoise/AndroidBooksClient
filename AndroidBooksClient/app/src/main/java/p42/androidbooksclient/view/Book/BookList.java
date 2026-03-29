package p42.androidbooksclient.view.Book;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

import p42.androidbooksclient.R;
import p42.androidbooksclient.viewmodel.BookViewModel;

public class BookList extends Fragment implements BookListAdapter.OnNoteListener{

    public BookList() {
        super(R.layout.fragment_book_list);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        view.findViewById(R.id.fabAddBook).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_bookList_to_bookCreate);
        });

        BookViewModel bookData = new ViewModelProvider(this).get(BookViewModel.class);

        RecyclerView recycler = view.findViewById(R.id.bookList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        BookListAdapter adapter = new BookListAdapter(new ArrayList<>(),this, R.id.action_bookList_to_bookDescription);
        recycler.setAdapter(adapter);

        bookData.fetchAllBooks();
        bookData.getBooks().observe(getViewLifecycleOwner(), data -> {
            adapter.updateData(data);
        });

    }
    public void onNoteClick(int bookId){
    }
}