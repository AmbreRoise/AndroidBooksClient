package p42.androidbooksclient.view.Book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import p42.androidbooksclient.R;
import p42.androidbooksclient.model.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListViewHolder> {
    private List<Book> _books;
    private final OnNoteListener _onNoteListener;
    private final int _navigationAction;


    public BookListAdapter(List<Book> data, OnNoteListener onNoteListener, int navigationAction){
        _books = data;
        _onNoteListener = onNoteListener;
        _navigationAction = navigationAction;
    }

    @NonNull
    @Override
    public  BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booklist_view_holder, parent, false);
        return new BookListViewHolder(view, this._onNoteListener, this._navigationAction);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position){
        Book book = _books.get(position);
        holder.setBookName(book.getTitle());
        holder.setBookId(book.getID());
    }

    @Override
    public int getItemCount(){
        return _books.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int bookId);
    }

    public void updateData(List<Book> newBooks){
        _books.clear();
        _books.addAll(newBooks);
        notifyDataSetChanged();
    }

}
