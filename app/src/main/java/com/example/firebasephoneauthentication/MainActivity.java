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







   /* private void AttemptAuth(String fullName) {
         FullNumber = fullName;
        progressBar.setVisibility(View.VISIBLE);
        txt_alertMain.setText("Please wait");
        txt_alertMain.setVisibility(View.VISIBLE);

        PhoneAuthProvider.verifyPhoneNumber(
                PhoneAuthOptions
                        .newBuilder(FirebaseAuth.getInstance())
                        .setActivity(this)
                        .setPhoneNumber(fullName)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(mCallbacks)
                        .build());
        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            Log.d("TAG", "onVerificationCompleted:" + credential);

            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Log.w(TAG, "onVerificationFailed", e);

            if(e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(MainActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {

            Intent intent= new Intent(MainActivity.this,OTPActivity.class);
            intent.putExtra("verificationId",verificationId);
            intent.putExtra("number",FullNumber);
            startActivity(intent);


            Log.d("TAG", "onCodeSent:" + verificationId);

        }
    };

    private void sendToHomeActivity() {
        Intent intent= new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            sendToHomeActivity();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });*/


