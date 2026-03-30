package p42.androidbooksclient.view.Book;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Tag;
import p42.androidbooksclient.viewmodel.AuthorViewModel;
import p42.androidbooksclient.viewmodel.BookViewModel;

public class BookDescription extends Fragment {
    private ActivityResultLauncher<String> _imagePickerLauncher;
    public BookDescription(){
        super(R.layout.fragment_book_description);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if(uri != null){
                        int bookId = getArguments().getInt("bookId");

                        requireContext().getContentResolver().takePersistableUriPermission(
                                uri,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                        );

                        requireContext()
                                .getSharedPreferences("book_covers", Context.MODE_PRIVATE)
                                .edit()
                                .putString("cover_" + bookId, uri.toString())
                                .apply();

                        ImageView cover = (ImageView) requireView().findViewById(R.id.bookCover);
                        cover.setImageURI(uri);
                        cover.setVisibility(View.VISIBLE);                    }
                }
        );
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        AuthorViewModel authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);

        int bookId = getArguments().getInt("bookId");
        bookViewModel.fetchOneBook(String.valueOf(bookId));

        String savedUri = requireContext()
                .getSharedPreferences("book_covers", Context.MODE_PRIVATE)
                .getString("cover_" + bookId, null);

        ImageView bookCover = view.findViewById(R.id.bookCover);

        if(savedUri != null){
            try {
                bookCover.setImageURI(Uri.parse(savedUri));
                bookCover.setVisibility(View.VISIBLE);
            } catch(SecurityException e){
                requireContext()
                        .getSharedPreferences("book_covers", Context.MODE_PRIVATE)
                        .edit()
                        .remove("cover_" + bookId)
                        .apply();
            }
        }

        view.findViewById(R.id.pickImageButton).setOnClickListener(v -> {
            _imagePickerLauncher.launch("image/*");
        });

        view.findViewById(R.id.deleteBookButton).setOnClickListener(v -> {
            bookViewModel.deleteBook(String.valueOf(bookId));
            Navigation.findNavController(view).navigateUp();
        });

        view.findViewById(R.id.editBookButton).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("bookId", bookId);
            Navigation.findNavController(view).navigate(R.id.action_bookDescription_to_bookEdit, bundle);
        });

        bookViewModel.getBook().observe(getViewLifecycleOwner(), book -> {
            if(book != null){
                ((TextView) view.findViewById(R.id.bookTitle)).setText(book.getTitle());
                ((TextView) view.findViewById(R.id.bookYear)).setText(
                        "Année de publication : " + (book.getPublicationYear() != null
                                ? String.valueOf(book.getPublicationYear())
                                : "Année inconnue")
                );
                if(book.getTags() != null && !book.getTags().isEmpty()){
                    StringBuilder tags = new StringBuilder("Tags : ");
                    for(Tag tag : book.getTags()){
                        tags.append(tag.getName()).append(", ");
                    }
                    tags.setLength(tags.length() - 2);
                    ((TextView) view.findViewById(R.id.bookTags)).setText(tags.toString());
                } else {
                    ((TextView) view.findViewById(R.id.bookTags)).setText("Aucun tag");
                }


                authorViewModel.fetchOneAuthor(String.valueOf(book.getAuthorID()));
            }
        });

        authorViewModel.getAuthor().observe(getViewLifecycleOwner(), author -> {
            if(author != null){
                ((TextView) view.findViewById(R.id.bookAuthor)).setText(
                        "Auteur : " + author.getFirstname() + " " + author.getLastname()
                );
            }
        });
    }
}