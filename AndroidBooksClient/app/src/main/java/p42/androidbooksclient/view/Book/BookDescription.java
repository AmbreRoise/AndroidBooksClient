package p42.androidbooksclient.view.Book;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import p42.androidbooksclient.R;
import p42.androidbooksclient.view.Author.AuthorListAdapter;

public class BookDescription extends Fragment {

    public BookDescription(){
        super(R.layout.fragment_book_description);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }
}