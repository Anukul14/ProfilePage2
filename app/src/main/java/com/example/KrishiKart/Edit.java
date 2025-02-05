package com.example.KrishiKart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit extends AppCompatActivity {

    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE=1;
    private Bitmap bitmap;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ProfileImage=(CircleImageView)findViewById(R.id.profile_image);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery1=new Intent();
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"select Picture"),PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE&&resultCode==RESULT_OK);
        imageUri=data.getData();
        try {

            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
            ProfileImage.setImageBitmap(bitmap);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
