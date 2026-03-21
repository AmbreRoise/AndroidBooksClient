package p42.androidbooksclient.view.Author;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.view.Book.BookListAdapter;

public class AuthorList extends Fragment implements AuthorListAdapter.OnNoteListener {

    public AuthorList() {
        super(R.layout.fragment_author_list);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.authorList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        JSONArray data = loadJSONFromAsset();

        AuthorListAdapter adapter = new AuthorListAdapter(data,this);
        recycler.setAdapter(adapter);

    }
    public void onNoteClick(int position){
    }

    private JSONArray loadJSONFromAsset() {
        try {
            java.io.InputStream is = requireContext().getAssets().open("authors.json");
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