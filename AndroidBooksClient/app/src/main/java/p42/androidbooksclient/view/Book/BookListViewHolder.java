package p42.androidbooksclient.view.Book;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import p42.androidbooksclient.R;


public class BookListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private  final TextView textView;
    private int _bookId;
    private final int _navigationAction;
    BookListAdapter.OnNoteListener onNoteListener;

    public BookListViewHolder(@NonNull View itemView, BookListAdapter.OnNoteListener onNoteListener, int navigationAction){
        super(itemView);
        textView = itemView.findViewById(R.id.BookName);
        itemView.setOnClickListener(this);
        this.onNoteListener = onNoteListener;
        this._navigationAction = navigationAction;
    }
    @Override
    public void onClick(View v){
        this.onNoteListener.onNoteClick(_bookId);
        Bundle bundle = new Bundle();
        bundle.putInt("bookId",_bookId);
        Navigation.findNavController(v).navigate(_navigationAction,bundle);
    }

    public void setBookName( String name){
        textView.setText(name);
    }
    public void setBookId(int id){
        _bookId = id;
    }
}
