package edu.msu.bensmana.randomly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},1);
        }
    }

    public void onStartJoke(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Joke");
        intent.putExtra("ID", "joke");
        startActivity(intent);
    }

    public void onStartFact(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Random Fact");
        intent.putExtra("ID", "fact");
        startActivity(intent);
    }

    public void onStartAdvice(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Random Advice");
        intent.putExtra("ID", "advice");
        startActivity(intent);
    }

    public void onStartKanye(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Random Kanye Quote");
        intent.putExtra("ID", "kanye");
        startActivity(intent);
    }
    public void onStartQuote(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Random Famous Quote");
        intent.putExtra("ID", "quote");
        startActivity(intent);
    }

    public void onStartNonsense(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Corporate Buzzword Nonsense");
        intent.putExtra("ID", "nonsense");
        startActivity(intent);
    }

    public void onStartCatFact(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Random Cat Fact");
        intent.putExtra("ID", "cat");
        startActivity(intent);
    }

    public void onStartDogFact2(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("TYPE", "Random Dog Fact");
        intent.putExtra("ID", "dog2");
        startActivity(intent);
    }
}