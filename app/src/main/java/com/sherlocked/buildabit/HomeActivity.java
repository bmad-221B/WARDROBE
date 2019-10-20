package com.sherlocked.buildabit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class HomeActivity extends AppCompatActivity {
    ImageView cameraImageView ;
    ImageView wardrobeImageView ;
    ImageView shopImageView ;
    ImageView newsImageView ;
    ImageView calendarImageView ;
    EditText descriptionEditText ;
    ImageView gelleryImage ;
    Bitmap bitmap ;
    ListView listView ;
    String str,str1 ;
    Button button ;
    int b ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference() ;
    ArrayAdapter<String>arrayAdapter;
    ArrayList<String> myList = new ArrayList<String>();
    public void openCamera(android.view.View view){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else{
            getPhoto();
        }
        myList.clear();
        myList.add("T-Shirt");
        myList.add("Shirt");
        myList.add("Jeans");
        myList.add("Trousers");
        myList.add("FootWear");
        arrayAdapter.notifyDataSetChanged();
        button.setText("OK");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED);
            {
                getPhoto();
            }
        }
    }
    public void getPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }
    public void openNews(android.view.View view){
        Toast.makeText(HomeActivity.this,"Moving to News:)",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),NewsActivity.class);
        startActivity(intent);
    }
    public void openShop(android.view.View view){
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle("Where to Shop")
                .setMessage("Choose a Shopping Site")
                .setPositiveButton("Myntra!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HomeActivity.this,"Moving to Myntra:)",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
                        intent.putExtra("Site","Myntra") ;
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Jabong!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HomeActivity.this,"Moving to Jaboong:)",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
                        intent.putExtra("Site","Jabong");
                        startActivity(intent);
                    }
                })
                .show();
    }
    public void openWardrobe(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),WardrobeActivity.class);
        intent.putExtra("str","NO");
        intent.putExtra("str1","NO");
        startActivity(intent);
    }
    public void openCalendar(android.view.View view){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
    public void moveActivity(android.view.View view){
        if(button.getText().toString().equals("OK")){
            str = myList.get(b);
            myList.clear();
            myList.add("Red");
            myList.add("Yellow");
            myList.add("Black");
            myList.add("White");
            myList.add("Blue") ;
            arrayAdapter.notifyDataSetChanged();
            button.setText("GO!");
        }
        else {
            str1 =  myList.get(b)  ;
            Log.i("Message",str);


            Intent intent = new Intent(getApplicationContext(),WardrobeActivity.class);
            intent.putExtra("str",str);
            intent.putExtra("str1",str1);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        cameraImageView = findViewById(R.id.cameraImageView) ;
        wardrobeImageView = findViewById(R.id.wardrobeImageView);
        shopImageView = findViewById(R.id.shopImageView) ;
        newsImageView = findViewById(R.id.newsImageView);
        calendarImageView = findViewById(R.id.calendarImageView) ;
        //descriptionEditText = findViewById(R.id.descriptionEditText);
        gelleryImage = findViewById(R.id.imageView3);
        myList.add("T-Shirt");
        myList.add("Shirt");
        myList.add("Jeans");
        myList.add("Trousers");
        myList.add("FootWear");
        button = findViewById(R.id.button2);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,myList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = (CheckedTextView)view ;
                b = i ;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        try{
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
            Log.i("Image Uploaded","Radhasoami Babaji!!");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            gelleryImage.setImageBitmap(bitmap);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
