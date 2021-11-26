package com.example.firebasephoneauthentication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView txt_alertMain;
    CountryCodePicker countryCodePicker;
    EditText edtMobileNo;
    Button btnGenerate;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    //String FullNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_alertMain = (TextView) findViewById(R.id.alertMain);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.CountryCode);
        edtMobileNo = (EditText) findViewById(R.id.inputNumber);

        countryCodePicker.registerCarrierNumberEditText(edtMobileNo);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);
        txt_alertMain.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String PhoneNumber =edtMobileNo.getText().toString();
                countryCodePicker.registerCarrierNumberEditText(edtMobileNo);

                if (PhoneNumber.length() == 0)
                {
                    Toast.makeText(MainActivity.this,"Please Enter Correct Number",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Intent intent=new Intent(MainActivity.this,OTPActivity.class);
                    intent.putExtra("mobile",countryCodePicker.getFullNumberWithPlus().replace(" ",""));
                    startActivity(intent);
                }
            }
        });

    }
}



