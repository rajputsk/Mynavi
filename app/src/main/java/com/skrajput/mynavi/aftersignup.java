package com.skrajput.mynavi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by S K RAJPUT on 6/6/2017.
 */

public class aftersignup extends AppCompatActivity{
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aftersignup);

        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void logout(View v) {


        firebaseAuth.signOut();
        finish();
        Intent i = new Intent(aftersignup.this,MainActivity.class);
        startActivity(i);
    }
}