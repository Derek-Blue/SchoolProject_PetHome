package com.example.pethome.ui.Members;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.pethome.MainActivity;
import com.example.pethome.R;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Timer;
import java.util.TimerTask;

public class MembersFragment extends Fragment implements View.OnClickListener {

    View view;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;

    public TextView name;
    public ImageView memberImage;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        return view;
    }
    @Override
    public void onStart() {

        name =getView().findViewById(R.id.membername);
        memberImage = getView().findViewById(R.id.imageset);
        getView().findViewById(R.id.signIn).setOnClickListener(this);
        getView().findViewById(R.id.signOut).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(view.getContext(),gso);

        mAuth = FirebaseAuth.getInstance();

        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                updateUI(null);
            }


        }
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("task", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("task", "signInWithCredential:failure", task.getException());

                            Toast.makeText(view.getContext(),"Failed",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        progres();
                        // ...
                    }
                });
    }

    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }
    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    public void updateUI(FirebaseUser user) {
        progres();

        if (user != null) {
            name.setText(user.getDisplayName() + " 您好");
            name.setTextColor(getResources().getColor(R.color.darkorchid));
            memberImage.setImageResource(R.mipmap.petcare);
            memberImage.setBackgroundColor(getResources().getColor(R.color.pink));

            ((MainActivity) getActivity()).memberUser = "OK";

            getView().findViewById(R.id.signIn).setVisibility(View.GONE);
            getView().findViewById(R.id.signOut).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), FoundActivity.class);
                    startActivity(i);
                }
            });
            getView().findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), FavoriteActivity.class);
                    startActivity(i);
                }
            });
        } else {
            name.setText("訪客");
            name.setTextColor(getResources().getColor(R.color.black));
            memberImage.setImageResource(R.mipmap.user);
            memberImage.setBackgroundColor(getResources().getColor(R.color.whitesmoke));

            ((MainActivity) getActivity()).memberUser = "null";
            getView().findViewById(R.id.signIn).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.signOut).setVisibility(View.GONE);

            getView().findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("請登入會員")

                            .setCancelable(true);
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }
            });
            getView().findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("請登入會員")

                            .setCancelable(true);
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.signIn:
                signIn();
                break;
            case R.id.signOut:
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("確認信息")
                        .setMessage("確定要登出嗎?")
                        .setCancelable(true);
                dialog.setPositiveButton("登出", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       signOut();revokeAccess();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {  }
                });
                dialog.show();
                break;
        }
    }

    public void progres(){
        AlertDialog.Builder pgs= new AlertDialog.Builder(view.getContext());
        View item = LayoutInflater.from(view.getContext()).inflate(R.layout.progres, null);
        pgs.setView(item);

        final AlertDialog dlg = pgs.create();
        dlg.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss();
                t.cancel();
            }
        }, 1200);
    }
}