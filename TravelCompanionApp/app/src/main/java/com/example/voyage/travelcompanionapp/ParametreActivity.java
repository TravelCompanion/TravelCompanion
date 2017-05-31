package com.example.voyage.travelcompanionapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ParametreActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text_email;
    TextView text_nom;
    TextView text_mdp;

    EditText edm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        text_email = (TextView) findViewById(R.id.email_edit_user);
        text_nom= (TextView) findViewById(R.id.nom_edit_user);

        text_email.setOnClickListener(ParametreActivity.this);
    }

    @Override
    public void onClick(View v) {
        if (v == text_email) {
            View pmail_view=getLayoutInflater().inflate(R.layout.parametre_popup_email, null);
            int pmail_select=R.id.email_popup;
            String title_text_edit= R.string.title_popup_edit +" l'email";
            CreateAleterDialog(title_text_edit,edm,text_email,pmail_view,pmail_select);



        }
        if(v==text_nom)
        {
            View pnom_view=getLayoutInflater().inflate(R.layout.parametre_popup_nom, null);
            int pnom_select=R.id.nom_popup;
            String title_text_edit= R.string.title_popup_edit +" le nom";
            CreateAleterDialog(title_text_edit,edm,text_nom,pnom_view,pnom_select);
        }
    }


    public void addListenerPopup(EditText popup, TextView mail_edit) {
        String mail_m = popup.getText().toString();
        mail_edit.setText(mail_m);
        Log.d("user_mail_modif", String.valueOf(mail_m));

    }

    public void CreateAleterDialog(String title, EditText edt, final TextView t, View popup_view, int selec_popup) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ParametreActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        alertDialogBuilder.setTitle(title);
        //View mView = getLayoutInflater().inflate(R.layout.parametre_popup_email, null);
        edt = (EditText) popup_view.findViewById(selec_popup);
        alertDialogBuilder
                .setCancelable(false)
                .setView(popup_view);

        final EditText finalEdt = edt;
        alertDialogBuilder.setPositiveButton(R.string.modifier, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                addListenerPopup(finalEdt,t);

                // if this button is clicked, close
                // current activity
                //Toast.makeText(MonumentActivity.this, "Note: "+String.valueOf(rating), Toast.LENGTH_SHORT).show();

                //ParametreActivity.this.finish();
                dialog.cancel();

            }
        })
                .setNegativeButton(R.string.fermer, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

}
