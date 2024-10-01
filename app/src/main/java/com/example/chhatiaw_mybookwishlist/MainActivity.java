package com.example.chhatiaw_mybookwishlist;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
/*
Citing:
OpenAI, ChatGPT3.5, 04/02/2024, Prompt: Write me an update function where I can update my total books count and my total read status code
*Provided my MainActivity code and activity_main.xml structure and it gave me the update function with errors which I ended up fixing myself
*/

public class MainActivity extends AppCompatActivity implements AddBookFragment.AddBookDialogListener {

    ListView bookList;
    BookAdapter bookAdapter;
    ArrayList<Book> dataList;

    private TextView tvBookCount;


    @Override
    public void addBook(Book book) {
        if (book != null) {
            if (dataList.contains(book)) {
                // UPDATING IT
                int index = dataList.indexOf(book);
                dataList.set(index, book);
            } else {

                dataList.add(book);
            }
            bookAdapter.notifyDataSetChanged();
        }

        updateBookCount(); //UPDATING THE COUNTING OF THE TOTAL STUFF
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = findViewById(R.id.book_list);
        dataList = new ArrayList<>();



        bookAdapter = new BookAdapter(this, dataList);
        bookList.setAdapter(bookAdapter);

        bookList.setOnItemClickListener((parent, view, position, id) -> {
            Book selectedBook = dataList.get(position);
            showEditBookDialog(selectedBook);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> showAddBookDialog());

        tvBookCount = findViewById(R.id.tvBookCount);
        updateBookCount();
    }

    @Override
    public void deleteBook(Book book) {
        if (book != null && dataList.contains(book)) {
            dataList.remove(book);
            bookAdapter.notifyDataSetChanged();
        }
        updateBookCount();
    }

    public void showEditBookDialog(Book bookToEdit) {
        AddBookFragment dialog = AddBookFragment.newInstance(bookToEdit);
        dialog.show(getSupportFragmentManager(), "AddBookFragment");
    }

    private void updateBookCount() {
        int totalCount = dataList.size();
        int readCount = 0;
        for (Book book : dataList) {
            if (book.getReadingStatus()) {
                readCount++;
            }
        }
        tvBookCount.setText(String.format(Locale.getDefault(), "Total: %d", totalCount));
        TextView tvReadLabel = findViewById(R.id.tvReadLabel);
        tvReadLabel.setText(String.format(Locale.getDefault(), "Read: %d", readCount));
    }



    public void showAddBookDialog() {
        AddBookFragment dialog = new AddBookFragment();
        dialog.show(getSupportFragmentManager(), "AddBookFragment");
    }


}