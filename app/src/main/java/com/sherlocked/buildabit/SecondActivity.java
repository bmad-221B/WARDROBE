package com.sherlocked.buildabit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

public class SecondActivity extends AppCompatActivity {
    Switch aSwitch ;
    EditText occupationEditText ;
    EditText ageEditText ;
    TextView bodyTextView ;
    String gender,bodyType ;
    FirebaseDatabase database ;
    DatabaseReference myRef = database.getReference() ;
    public void nextActivity(android.view.View view) {
        if(aSwitch.isChecked()) {
            gender = "Female" ;
        }
        myRef.child("Information").child("Occupation").push().setValue(occupationEditText.getText()) ;
        myRef.child("Information").child("Gender").push().setValue(gender) ;
        myRef.child("Information").child("Body Type").push().setValue(occupationEditText.getText()) ;
        if(aSwitch.isChecked()) {
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        }

    }
    public void endo(android.view.View view){
        bodyType = "Endomorph" ;
    }
    public void ecto(android.view.View view){
        bodyType = "Ectomorph" ;
    }
    public void meso(android.view.View view){
        bodyType = "Mesomorph" ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        aSwitch = findViewById(R.id.switch1);
        occupationEditText = findViewById(R.id.workEditText) ;
        ageEditText = findViewById(R.id.ageEditText) ;
        bodyTextView = findViewById(R.id.bodyTextView) ;
        gender = "Male" ;
    }
}
