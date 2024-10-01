package com.example.chhatiaw_mybookwishlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class TransitionIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_intro);

        
        int TRANSITIONLENGTH = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(TransitionIntroActivity.this, MainActivity.class);
                TransitionIntroActivity.this.startActivity(mainIntent);
                TransitionIntroActivity.this.finish();
            }
        }, TRANSITIONLENGTH);
    }
}
