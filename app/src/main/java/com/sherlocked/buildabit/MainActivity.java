package com.sherlocked.buildabit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.core.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth ;
    TextView textView ;
    Button button ;
    int RC_SIGN_IN = 1 ;
    public void changeText(android.view.View view) {
        if(textView.getText().toString().equals("Log in")){
            textView.setText("Sign Up");
            button.setText("Log in");
        }
        else{
            textView.setText("Log in");
            button.setText("Sign Up");
        }
    }
    public void getStarted(android.view.View view){

        List<AuthUI.IdpConfig>providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build() );
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN) {
            FirebaseUserMetadata metadata = FirebaseAuth.getInstance().getCurrentUser().getMetadata();
            if(metadata.getCreationTimestamp()==metadata.getLastSignInTimestamp()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this,"Welcome " + user.getDisplayName(),Toast.LENGTH_SHORT).show();
            }
            else{
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this,"Welcome Back!!" + user.getDisplayName(),Toast.LENGTH_SHORT).show();
            }
            if(button.getText().toString().equals("Sign Up")) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }

        }
        else{
            Toast.makeText(this,"Something Went Wrong!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance() ;
        button = findViewById(R.id.button) ;
        textView = findViewById(R.id.textView) ;
    }
}
