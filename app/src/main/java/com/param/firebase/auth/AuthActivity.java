package com.param.firebase.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.param.firebase.MainActivity;
import com.param.firebase.R;

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AUTH";
    private static final int RC_SIGN_IN = 1001;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        validateLogin();
        ///googleLogin();
        // facebookLogin();
       // emailPasswordLogin();
    }

    private void emailPasswordLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(AuthUI.EMAIL_PROVIDER)
                        .build(), RC_SIGN_IN);
    }

    private void facebookLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(AuthUI.FACEBOOK_PROVIDER)
                        .setTheme(R.style.GreenTheme)
                        .build(), RC_SIGN_IN);
    }

    private void googleLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(AuthUI.GOOGLE_PROVIDER)
                        .setTheme(R.style.AppTheme)
                        .build(), RC_SIGN_IN);
    }




    private void validateLogin() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            startActivity(new Intent(this, MainActivity.class));
            finish();
            Toast.makeText(AuthActivity.this, "User logged in", Toast.LENGTH_SHORT).show();
        } else {
            // not signed in
            Toast.makeText(AuthActivity.this, "User not logged in.", Toast.LENGTH_SHORT).show();
           // emailPasswordLogin();
            facebookLogin();
          //  googleLogin();
        }

       /* mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
                Toast.makeText(AuthActivity.this, "Sign in not successful.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
