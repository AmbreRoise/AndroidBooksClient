package p42.androidbooksclient.view.Author;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.model.Book;
import p42.androidbooksclient.view.Book.BookListAdapter;

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorListViewHolder> {
    private List<Author> _authors;
    private final OnNoteListener _onNoteListener;

    public AuthorListAdapter(List<Author> data, OnNoteListener onNoteListener){
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
        Author author = _authors.get(position);
        holder.setAuthorName(author.getFirstname(), author.getLastname());
        holder.setAuthorId(author.getID());
    }

    @Override
    public int getItemCount(){
        return _authors.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int authorId);
    }

    public void updateData(List<Author> newAuthor){
        _authors.clear();
        _authors.addAll(newAuthor);
        notifyDataSetChanged();
    }
}
