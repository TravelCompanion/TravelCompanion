package com.example.voyage.travelcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.voyage.travelcompanionapp.callwebservice.RecupUser;
import com.example.voyage.travelcompanionapp.model.ApliUser;


public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {
    EditText iEmailView;
    EditText iPassView;
    EditText iPassViewRep;
    Button ibuttonInsc;
    EditText iUserNameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button bConnexion_ins = (Button) findViewById(R.id.bconnexion_inscription);
        setSupportActionBar(toolbar);
        iUserNameView = (EditText) findViewById(R.id.name_user);

        iEmailView = (EditText) findViewById(R.id.email_inscription_user);
        iPassView = (EditText) findViewById(R.id.inscription_password);
        iPassViewRep = (EditText) findViewById(R.id.inscription_password_rep);
        ibuttonInsc = (Button) findViewById(R.id.email_sign_in_button);


        bConnexion_ins.setOnClickListener(InscriptionActivity.this);
        ibuttonInsc.setOnClickListener(InscriptionActivity.this);


    }


    public boolean verifSamePass(EditText pass1, EditText pass2) {
        boolean result = false;
        if (pass1.getText().toString().trim().equals(pass2.getText().toString().trim())) {
            result = true;
        }
        return result;

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bconnexion_inscription: {
                Intent intent = new Intent(InscriptionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            }

            case R.id.email_sign_in_button: {

                if (iUserNameView.getText().toString().trim().length() > 0
                        && iEmailView.getText().toString().trim().length() > 0 && iPassView.getText().toString().trim().length() > 0 &&
                        iPassViewRep.getText().toString().trim().length() > 0) {
                    RecupUser Ruser = new RecupUser();
                    if(Ruser.validateEmail(iEmailView.getText().toString().trim())) {

                        if (verifSamePass(iPassView, iPassViewRep)) {

                            boolean result = Ruser.testUser(iEmailView.getText().toString(), iPassViewRep.getText().toString());
                            if (result == false) {
                                Session session;
                                session = Session.getSession(getApplicationContext());
                                ApliUser appuser = new ApliUser(iEmailView.getText().toString().trim(), iPassViewRep.getText().toString().trim());
                                session.createLoginSession(iEmailView.getText().toString().trim());
                                session.appuser = appuser;
                                Intent intent = new Intent(InscriptionActivity.this, PreferencesInscriptionActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(InscriptionActivity.this, R.string.mail_exite, Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(InscriptionActivity.this, R.string.mot_de_pass_non_identique, Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(InscriptionActivity.this, R.string.not_email, Toast.LENGTH_SHORT).show();

                    }

                    break;
                }

                //.... etc
            }
        }

    }
}

