package com.example.chhatiaw_mybookwishlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddBookFragment extends DialogFragment {

    interface AddBookDialogListener {
        void addBook(Book book);
        void deleteBook(Book book);
    }

    private AddBookDialogListener listener;
    private Book existingBook;

    public static AddBookFragment newInstance(Book book) {
        AddBookFragment fragment = new AddBookFragment();
        Bundle args = new Bundle();
        args.putSerializable("book", book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddBookDialogListener) {
            listener = (AddBookDialogListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AddBookDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_book, null);

        EditText editBookName = view.findViewById(R.id.edit_book_name_text);
        EditText editAuthorName = view.findViewById(R.id.edit_author_name_text);
        EditText editGenreName = view.findViewById(R.id.edit_genre_text);
        EditText editYearName = view.findViewById(R.id.edit_year_text);
        CheckBox checkBoxStatus = view.findViewById(R.id.checkbox_reading_status);


        if (getArguments() != null && getArguments().containsKey("book")) {
            existingBook = (Book) getArguments().getSerializable("book");
            editBookName.setText(existingBook.getBookName());
            editAuthorName.setText(existingBook.getAuthorName());
            editGenreName.setText(existingBook.getGenre());
            editYearName.setText(String.valueOf(existingBook.getYear()));
            checkBoxStatus.setChecked(existingBook.getReadingStatus());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle(existingBook != null ? "Edit Book" : "Add Book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", null);


        if (existingBook != null) {
            builder.setNeutralButton("Delete", (dialogInterface, which) -> listener.deleteBook(existingBook));
        }

        AlertDialog dialog = builder.create();


        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                String bookName = editBookName.getText().toString();
                String authorName = editAuthorName.getText().toString();
                String genreName = editGenreName.getText().toString();
                String yearStr = editYearName.getText().toString();


                if (bookName.length() > 50) {
                    Toast.makeText(getContext(), "Book title should be up to 50 characters.", Toast.LENGTH_SHORT).show();
                } else if (authorName.length() > 30) {
                    Toast.makeText(getContext(), "Author name should be up to 30 characters.", Toast.LENGTH_SHORT).show();
                } else if (yearStr.length() != 4) {
                    Toast.makeText(getContext(), "Publication year should be a four-digit number.", Toast.LENGTH_SHORT).show();
                } else {
                    int year = 0;
                    try {
                        year = Integer.parseInt(yearStr);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Invalid year format.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean status = checkBoxStatus.isChecked();


                    if (existingBook != null) {
                        existingBook.setBookName(bookName);
                        existingBook.setAuthorName(authorName);
                        existingBook.setGenre(genreName);
                        existingBook.setYear(year);
                        existingBook.setReadingStatus(status);
                        listener.addBook(existingBook);
                    } else {
                        listener.addBook(new Book(bookName, authorName, genreName, year, status));
                    }

                    dialog.dismiss();
                }
            });
        });

        return dialog;
    }
}