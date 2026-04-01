package p42.androidbooksclient.view.Book;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.model.Tag;
import p42.androidbooksclient.viewmodel.AuthorViewModel;
import p42.androidbooksclient.viewmodel.BookViewModel;
import p42.androidbooksclient.viewmodel.TagViewModel;

public class BookCreate extends Fragment {

    private List<Author> _authorList = new ArrayList<>();
    private List<Tag> _tagList = new ArrayList<>();

    public BookCreate() {
        super(R.layout.fragment_book_create);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        AuthorViewModel authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);
        TagViewModel tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);

        Spinner spinnerAuthor = view.findViewById(R.id.spinnerAuthor);
        LinearLayout tagsContainer = view.findViewById(R.id.tagsContainer);

        view.findViewById(R.id.createTag).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_bookCreate_to_tagCreate);
        });

        authorViewModel.fetchAllAuthors();
        authorViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
            if(authors != null){
                _authorList = authors;
                List<String> authorNames = new ArrayList<>();
                for(Author a : authors){
                    authorNames.add(a.getFirstname() + " " + a.getLastname());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        authorNames
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerAuthor.setAdapter(adapter);
            }
        });

        tagViewModel.fetchAllTags();
        tagViewModel.getTags().observe(getViewLifecycleOwner(), tags -> {
            if(tags != null){
                _tagList = tags;
                tagsContainer.removeAllViews();
                for(Tag tag : tags){
                    CheckBox checkBox = new CheckBox(requireContext());
                    checkBox.setText(tag.getName());
                    checkBox.setTag(tag.getID());
                    tagsContainer.addView(checkBox);
                }
            }
        });

        bookViewModel.getBook().observe(getViewLifecycleOwner(), book -> {
            if(book != null){
                Navigation.findNavController(view).navigateUp();
            }
        });

        view.findViewById(R.id.confirmCreateBook).setOnClickListener(v -> {
            EditText titleInput = view.findViewById(R.id.inputTitle);
            EditText yearInput = view.findViewById(R.id.inputYear);

            String title = titleInput.getText().toString().trim();
            String yearStr = yearInput.getText().toString().trim();

            titleInput.setError(null);
            yearInput.setError(null);

            if(title.isEmpty()){
                titleInput.setError("Title is required");
                return;
            }

            if(_authorList.isEmpty()){
                Toast.makeText(requireContext(), "No authors available", Toast.LENGTH_SHORT).show();
                return;
            }

            Integer year = null;
            if(!yearStr.isEmpty()){
                try {
                    year = Integer.parseInt(yearStr);
                } catch(NumberFormatException e){
                    yearInput.setError("The publication year must be a valid number");
                    return;
                }
            }


            int selectedPos = spinnerAuthor.getSelectedItemPosition();
            String authorId = String.valueOf(_authorList.get(selectedPos).getID());

            List<String> selectedTagIds = new ArrayList<>();
            for(int i = 0; i < tagsContainer.getChildCount(); i++){
                CheckBox cb = (CheckBox) tagsContainer.getChildAt(i);
                if(cb.isChecked()){
                    selectedTagIds.add(String.valueOf(cb.getTag()));
                }
            }

            bookViewModel.createBookOfAuthor(authorId, title, year, selectedTagIds);
        });
    }
}