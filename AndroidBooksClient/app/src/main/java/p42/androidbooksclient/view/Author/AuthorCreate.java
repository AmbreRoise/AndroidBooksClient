package p42.androidbooksclient.view.Author;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import p42.androidbooksclient.R;
import p42.androidbooksclient.viewmodel.AuthorViewModel;

public class AuthorCreate extends Fragment {

    public AuthorCreate() {
        super(R.layout.fragment_author_create);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AuthorViewModel authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);

        authorViewModel.getAuthor().observe(getViewLifecycleOwner(), author -> {
            if(author != null){
                Navigation.findNavController(view).navigateUp();
            }
        });

        view.findViewById(R.id.confirmCreateAuthor).setOnClickListener(v -> {
            EditText firstnameInput = view.findViewById(R.id.inputFirstname);
            EditText lastnameInput = view.findViewById(R.id.inputLastname);

            String firstname = firstnameInput.getText().toString().trim();
            String lastname = lastnameInput.getText().toString().trim();

            firstnameInput.setError(null);
            lastnameInput.setError(null);

            if(firstname.isEmpty()){
                firstnameInput.setError("Firstname is required");
                return;
            }

            if(lastname.isEmpty()){
                lastnameInput.setError("Lastname is required");
                return;
            }


            authorViewModel.createAuthor(firstname, lastname);
        });
    }
}