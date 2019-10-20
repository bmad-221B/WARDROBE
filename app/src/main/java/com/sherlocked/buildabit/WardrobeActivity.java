package com.sherlocked.buildabit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WardrobeActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference() ;
    ListView listView ;
    ArrayList<String>myList = new ArrayList<String>();
    ArrayAdapter<String>arrayAdapter ;
    String str,str1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        //listView = findViewById(R.id.wardrobeListView);
        setTitle(FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "'s Wardrobe");
        str = getIntent().getStringExtra("str");
        str1 = getIntent().getStringExtra("str1");
        Adapter adapter=new Adapter(myList);
        RecyclerView recyclerView=findViewById(R.id.rv_wardrobe);
        recyclerView.setAdapter(adapter);

//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
           if(str.equals("T-Shirt")){
            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).child("Wardrobe").child("T-Shirt").push().setValue(str1);
            }else if(str.equals("Shirt")){
                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).child("Wardrobe").child("Shirt").push().setValue(str1);
            }else if(str.equals("Jeans")){
                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).child("Wardrobe").child("Jeans").push().setValue(str1);
            }else if(str.equals("Trousers")){
                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).child("Wardrobe").child("Trousers").push().setValue(str1);
            }else if(str.equals("FootWear")){
                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).child("Wardrobe").child("FootWear").push().setValue(str1);
            }
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("Le lo",dataSnapshot.getKey());
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.i("Le lo1",dataSnapshot1.getKey());
                    for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
                        Log.i("Le lo2",dataSnapshot2.getKey());
                        for(DataSnapshot dataSnapshot3:dataSnapshot2.getChildren()){
                            myList.add(dataSnapshot2.getKey() + "(" + dataSnapshot3.getValue() + ")");
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //listView.setAdapter(arrayAdapter);
    }
}
