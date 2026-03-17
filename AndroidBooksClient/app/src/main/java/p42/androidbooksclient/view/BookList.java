package p42.androidbooksclient.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import p42.androidbooksclient.R;

public class BookList extends Fragment {

    public BookList() {
        super(R.layout.fragment_book_list);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

}