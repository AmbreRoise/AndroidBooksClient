package p42.androidbooksclient.view.Book;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.EditText;

import androidx.navigation.Navigation;

import p42.androidbooksclient.R;
import p42.androidbooksclient.viewmodel.TagViewModel;

public class TagCreate extends Fragment {
    public TagCreate() {
        super(R.layout.fragment_tag_create);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TagViewModel tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);

        tagViewModel.getTag().observe(getViewLifecycleOwner(), tag -> {
            if(tag != null){
                Navigation.findNavController(view).navigateUp();
            }
        });

        view.findViewById(R.id.confirmCreateBook).setOnClickListener(v -> {
            EditText inputTag = view.findViewById(R.id.inputTag);
            String tagStr = inputTag.getText().toString().trim();

            inputTag.setError(null);

            if(tagStr.isEmpty()){
                inputTag.setError("The Tag must have a name");
            }

            tagViewModel.createTag(tagStr);
        });

    }

    }