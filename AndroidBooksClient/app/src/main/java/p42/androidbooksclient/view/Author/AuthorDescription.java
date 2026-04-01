package p42.androidbooksclient.view.Author;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import p42.androidbooksclient.R;
import p42.androidbooksclient.view.Book.BookListAdapter;
import p42.androidbooksclient.viewmodel.AuthorViewModel;
import p42.androidbooksclient.viewmodel.BookViewModel;

public class AuthorDescription extends Fragment implements BookListAdapter.OnNoteListener{

    public AuthorDescription() {
        super(R.layout.fragment_author_description);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        AuthorViewModel authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);
        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        RecyclerView recycler = view.findViewById(R.id.authorBookList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        int authorId = getArguments().getInt("authorId");

        view.findViewById(R.id.deleteAuthorButton).setOnClickListener(v -> {
            authorViewModel.deleteAuthor(String.valueOf(authorId));
            Navigation.findNavController(view).navigateUp();
        });

        authorViewModel.fetchOneAuthor(String.valueOf(authorId));
        authorViewModel.getAuthor().observe(getViewLifecycleOwner(), author -> {
            if (author != null) {
                ((TextView) view.findViewById(R.id.authorName)).setText(
                        author.getFirstname() + " " + author.getLastname()
                );
            }
        });

        bookViewModel.fetchBooksOfAuthor(String.valueOf(authorId));
        bookViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                BookListAdapter adapter = new BookListAdapter(books, this);
                recycler.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onNoteClick(int bookId) {
        Bundle bundle = new Bundle();
        bundle.putInt("bookId",bookId);
        Navigation.findNavController(getView()).navigate(R.id.action_authorDescription_to_bookDescription,bundle);
    }

}