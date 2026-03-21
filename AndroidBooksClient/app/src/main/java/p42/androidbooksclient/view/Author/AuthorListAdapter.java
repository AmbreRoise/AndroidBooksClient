package p42.androidbooksclient.view.Author;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import p42.androidbooksclient.R;
import p42.androidbooksclient.view.Book.BookListAdapter;

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorListViewHolder> {
    private final JSONArray _authors;
    private final OnNoteListener _onNoteListener;

    public AuthorListAdapter(JSONArray data, OnNoteListener onNoteListener){
        _authors = data;
        _onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public AuthorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.authorlist_view_holder, parent, false);
        return new AuthorListViewHolder(view, this._onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorListViewHolder holder, int position){
        try {
            String firstname = _authors.getJSONObject(position).getString("firstname");
            String lastname = _authors.getJSONObject(position).getString("lastname");
            holder.setAuthorName(firstname,lastname);
        } catch (JSONException e) {
            e.printStackTrace();
            holder.setAuthorName("Erreur de nom d'auteur","Dommage");
        }
    }

    @Override
    public int getItemCount(){
        return _authors.length();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
