package com.example.chhatiaw_mybookwishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_view, parent, false);
        }

        TextView bookTitle = convertView.findViewById(R.id.book_title);
        TextView authorName = convertView.findViewById(R.id.book_author);
        TextView genreName = convertView.findViewById(R.id.book_genre);
        TextView year = convertView.findViewById(R.id.book_year);
        ImageView statusIndicator = convertView.findViewById(R.id.status_indicator);


        bookTitle.setText(book.getBookName());
        authorName.setText(book.getAuthorName());
        genreName.setText(book.getGenre());
        year.setText(book.getYear().toString());


        if (book.getReadingStatus()) {
            statusIndicator.setImageResource(R.drawable.green_circle); //IF STATUS IS TRUE, THEN WE CALL THE GREEN CIRCLE PICTURE
        } else {
            statusIndicator.setImageResource(R.drawable.red_circle); // IF FALSE, WE CALL THE RED CIRCLE SQUARE
        }


        return convertView;
    }


}
