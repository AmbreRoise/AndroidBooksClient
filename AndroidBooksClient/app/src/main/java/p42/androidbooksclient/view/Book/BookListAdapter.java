package p42.androidbooksclient.view.Book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import p42.androidbooksclient.R;

public class BookListAdapter extends RecyclerView.Adapter<BookListViewHolder> {
    private final JSONArray _books;
    private final OnNoteListener _onNoteListener;


    public BookListAdapter(JSONArray data, OnNoteListener onNoteListener){
        _books = data;
        _onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public  BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booklist_view_holder, parent, false);
        return new BookListViewHolder(view, this._onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position){
        try {
            String title = _books.getJSONObject(position).getString("title");
            holder.setBookName(title);
        } catch (JSONException e) {
            e.printStackTrace();
            holder.setBookName("Erreur de titre");
        }
    }

    @Override
    public int getItemCount(){
        return _books.length();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
