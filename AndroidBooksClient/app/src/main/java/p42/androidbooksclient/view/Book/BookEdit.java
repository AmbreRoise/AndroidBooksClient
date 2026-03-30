package p42.androidbooksclient.view.Book;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Book;
import p42.androidbooksclient.model.Tag;
import p42.androidbooksclient.viewmodel.BookViewModel;
import p42.androidbooksclient.viewmodel.TagViewModel;

public class BookEdit extends Fragment {

    private Book _currentBook = null;
    private List<Tag> _allTags = new ArrayList<>();

    public BookEdit() {
        super(R.layout.fragment_book_edit);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        TagViewModel tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);

        EditText editTitle = view.findViewById(R.id.editTitle);
        EditText editYear = view.findViewById(R.id.editYear);
        LinearLayout tagsContainer = view.findViewById(R.id.editTagsContainer);

        int bookId = getArguments().getInt("bookId");
        bookViewModel.fetchOneBook(String.valueOf(bookId));

        bookViewModel.getBook().observe(getViewLifecycleOwner(), book -> {
            if(book != null && _currentBook == null){
                _currentBook = book;
                editTitle.setText(book.getTitle());
                if(book.getPublicationYear() != null){
                    editYear.setText(String.valueOf(book.getPublicationYear()));
                }
                updateCheckboxes(tagsContainer, book);
            }
        });

        tagViewModel.fetchAllTags();
        tagViewModel.getTags().observe(getViewLifecycleOwner(), tags -> {
            if(tags != null){
                _allTags = tags;
                tagsContainer.removeAllViews();
                for(Tag tag : tags){
                    CheckBox cb = new CheckBox(requireContext());
                    cb.setText(tag.getName());
                    cb.setTag(tag.getID());
                    if(_currentBook != null && _currentBook.getTags() != null){
                        for(Tag bookTag : _currentBook.getTags()){
                            if(bookTag.getID().equals(tag.getID())){
                                cb.setChecked(true);
                                break;
                            }
                        }
                    }
                    tagsContainer.addView(cb);
                }
            }
        });

        view.findViewById(R.id.confirmEditBook).setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String yearStr = editYear.getText().toString().trim();

            if(title.isEmpty()){
                editTitle.setError("The book must have a title");
                return;
            }

            Integer year = null;
            if(!yearStr.isEmpty()){
                try {
                    year = Integer.parseInt(yearStr);
                } catch(NumberFormatException e){
                    editYear.setError("The publication year must be a valid number");
                    return;
                }
            }
            Log.d("BookEdit", "updateBook called with title=" + title + " year=" + year);
            bookViewModel.updateBook(String.valueOf(bookId), title, year);

            List<Tag> currentTags = _currentBook != null && _currentBook.getTags() != null
                    ? _currentBook.getTags() : new ArrayList<>();

            for(int i = 0; i < tagsContainer.getChildCount(); i++){
                CheckBox cb = (CheckBox) tagsContainer.getChildAt(i);
                int tagId = (int) cb.getTag();
                boolean wasChecked = false;

                for(Tag t : currentTags){
                    if(t.getID() == tagId){ wasChecked = true; break; }
                }

                if(cb.isChecked() && !wasChecked){
                    bookViewModel.associateTag(String.valueOf(bookId), String.valueOf(tagId));
                } else if(!cb.isChecked() && wasChecked){
                    bookViewModel.dissociateTag(String.valueOf(bookId), String.valueOf(tagId));
                }
            }

            Navigation.findNavController(view).navigateUp();
        });
    }

    private void updateCheckboxes(LinearLayout tagsContainer, Book book){
        for(int i = 0; i < tagsContainer.getChildCount(); i++){
            CheckBox cb = (CheckBox) tagsContainer.getChildAt(i);
            int tagId = (int) cb.getTag();
            if(book.getTags() != null){
                for(Tag t : book.getTags()){
                    if(t.getID() == tagId){ cb.setChecked(true); break; }
                }
            }
        }
    }
}