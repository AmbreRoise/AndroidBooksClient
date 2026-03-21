package p42.androidbooksclient.view.Book;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import p42.androidbooksclient.R;

public class BookList extends Fragment implements BookListAdapter.OnNoteListener{

    public BookList() {
        super(R.layout.fragment_book_list);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.bookList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        JSONArray data = loadJSONFromAsset();

        BookListAdapter adapter = new BookListAdapter(data,this);
        recycler.setAdapter(adapter);

    }
    public void onNoteClick(int position){
    }

    private JSONArray loadJSONFromAsset() {
        try {
            java.io.InputStream is = requireContext().getAssets().open("books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return new JSONArray(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}