package com.skrajput.mynavi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camera extends AppCompatActivity {
    private int PIC_CAPTURED = 1;
    ImageView imageView;
    private File photofile;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.imageView2);
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PIC_CAPTURED);
       // File photostorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
       // photofile = new File(photostorage, (System.currentTimeMillis()) + ".jpg");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PIC_CAPTURED) {

            try {

                photo = (Bitmap) data.getExtras().get("data");
            } catch (NullPointerException e) {
                photo = BitmapFactory.decodeFile(photofile.getAbsolutePath());
            }

            if (photo != null) {
                imageView.setImageBitmap(photo);
            } else {

                Toast.makeText(this, "Oops,can't get the photo from your gallery", Toast.LENGTH_LONG).show();
            }
        }

    }


        public void share(View v) {
    imageView.setDrawingCacheEnabled(true);
    Bitmap bitmap = imageView.getDrawingCache();
            // Save this bitmap to a file.
                File cache = getApplicationContext().getExternalCacheDir();
                File sharefile = new File(cache, "toshare.jpg");
                try {
                    FileOutputStream out = new FileOutputStream(sharefile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();

                } catch (IOException e) {

                }

                // Now send it out to share
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + sharefile));
                startActivity(share);
                startActivity(Intent.createChooser(share, "Send your picture using:"));


}


}
