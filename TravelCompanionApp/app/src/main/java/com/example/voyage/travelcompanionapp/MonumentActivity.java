package com.example.voyage.travelcompanionapp;


import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MonumentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PrefDistance", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_monument);
        Toolbar bar_monument_info=(Toolbar)findViewById(R.id.toolbar_monumentActivity);
        setSupportActionBar(bar_monument_info);
        bar_monument_info.setTitle("Monument");
        TextView descMonu=(TextView)findViewById(R.id.textView_descmonu);
        descMonu.setText("Le Lorem Ipsum est simplement du faux texte employé dans la composition " +
                "et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de" +
                " l'imprimerie depuis les années 1500, quand un peintre anonyme assembla ensemble des" +
                " morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait " +
                "que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans" +
                " que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la " +
                "vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par " +
                "son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.");
        TextView distMonu=(TextView)findViewById(R.id.textView_distance);
        String distance=pref.getString("distance", null);

        distMonu.setText("Distance: " + distance + " m");



        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


}
