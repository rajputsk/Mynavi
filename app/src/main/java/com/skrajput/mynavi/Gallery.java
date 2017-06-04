package com.skrajput.mynavi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Gallery extends AppCompatActivity {
private int PICK_IMAGE_REQUEST=0;
    ImageView imageView;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageView= (ImageView) findViewById(R.id.imageView4);
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {


            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                //Log.d(TAG,String,valueOf(bitmap)


            } catch (IOException e) {
                e.printStackTrace();


            }

            if (bitmap != null) {

                imageView.setImageBitmap(bitmap);


            } else {

                Toast.makeText(this, "Oops,can't get the photo from your gallery", Toast.LENGTH_LONG).show();
            }


        }
    }
}
