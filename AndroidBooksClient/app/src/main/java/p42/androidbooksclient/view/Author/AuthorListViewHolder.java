package p42.androidbooksclient.view.Author;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import p42.androidbooksclient.R;
import p42.androidbooksclient.view.Book.BookListAdapter;

public class AuthorListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private  final TextView textView;
    private  int _authorId;
    AuthorListAdapter.OnNoteListener onNoteListener;

    public AuthorListViewHolder(@NonNull View itemView, AuthorListAdapter.OnNoteListener onNoteListener){
        super(itemView);
        textView = itemView.findViewById(R.id.AuthorName);
        itemView.setOnClickListener(this);
        this.onNoteListener = onNoteListener;
    }

    @Override
    public void onClick(View v){
        this.onNoteListener.onNoteClick(_authorId);
    }

    public void setAuthorName(String firstname, String lastname){
        textView.setText(firstname + " " + lastname);
    }

    public void setAuthorId(int id){
        _authorId = id;
    }
}
