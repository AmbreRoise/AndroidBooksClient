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
    BookListAdapter.OnNoteListener onNoteListener;

    public BookListViewHolder(@NonNull View itemView, BookListAdapter.OnNoteListener onNoteListener){
        super(itemView);
        textView = itemView.findViewById(R.id.BookName);
        itemView.setOnClickListener(this);
        this.onNoteListener = onNoteListener;
    }
    @Override
    public void onClick(View v){
        this.onNoteListener.onNoteClick(getAbsoluteAdapterPosition());
        Navigation.findNavController(v).navigate(R.id.action_bookList_to_bookDescription);
    }

    public void setBookName( String name){
        textView.setText(name);
    }
}
