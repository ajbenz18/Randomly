package edu.msu.bensmana.randomly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import edu.msu.bensmana.randomly.R;
import edu.msu.bensmana.randomly.Snippets.ContentRetriever;

public class ContentActivity extends AppCompatActivity {

    // instance of ContentRetriever that the activity will use to get snippets
    private ContentRetriever retriever;

    // The snippet being displayed in the textview
    private String snippetShown;

    // The type of snippets this activity is displaying (joke, advice, etc)
    private String identifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        TextView title = findViewById(R.id.title);
        Intent i = getIntent();
        title.setText((CharSequence) i.getExtras().getSerializable("TYPE"));
        identifier = (String) i.getExtras().getSerializable("ID");
        retriever = new ContentRetriever();
        if (savedInstanceState == null){
            onAskForSnippet(findViewById(R.id.textContent));
        }
        else{
            snippetShown = savedInstanceState.getString("snippet");
            UpdateUi();
        }
    }

    // use the content retriever to ask for a new snippet
    // triggered by button
    public void onAskForSnippet(View view){
        new Thread(new Runnable(){
            @Override
            public void run(){
                String snippet = retriever.getSnippet(identifier);

                boolean fail = snippet == null;
                if (!fail){
                    snippetShown = snippet;
                }
                final boolean fail1 = fail;

                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if (fail1){
                            Toast.makeText(view.getContext(), "Failed to load", Toast.LENGTH_SHORT).show();
                        }else{
                            UpdateUi();
                            }
                        }
                });
            }
        }).start();
    }

    // update the textview with the new snippet
    private void UpdateUi(){
        TextView t = (TextView)findViewById(R.id.textContent);
        t.setText(snippetShown);

        // method for auto-resizing text is different for API levels less than 26. Resizing for levels >=26 handled in xml
        if(Build.VERSION.SDK_INT < 26) {
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    t, 12, 50, 2, TypedValue.COMPLEX_UNIT_DIP);

        }


        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("snippet", snippetShown);
    }

    // triggered when user clicks share button
    public void onShare(View v){
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> numbers = new ArrayList<String>();
        Cursor c =  getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
        while(c.moveToNext()){
            int nameIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name = c.getString(nameIndex);

            int numberIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = c.getString
                    (numberIndex);
            names.add(name);
            numbers.add(number);
        }
        c.close();

        ArrayList<Contact> contacts = new ArrayList<>();
        // combine names and numbers in contacts objects
        for (int i = 0; i < names.size(); i++ ){
            String name = names.get(i);
            if (name.charAt(0) == '(' || name.charAt(0) == '1'){
                continue;
            }
            contacts.add(new Contact(numbers.get(i), name));
        }

        Collections.sort(contacts);

        ArrayList<String> namesSorted = new ArrayList<String>();
        ArrayList<String> numbersSorted = new ArrayList<String>();
        for (Contact con:contacts){
            namesSorted.add(con.getName());
            numbersSorted.add(con.getNumber());
        }

        // create dialog box to allow user to choose which of their contacts to send to
        LoadContacts dlg = new LoadContacts();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("NUMBERS", numbersSorted);
        bundle.putStringArrayList("NAMES", namesSorted);
        bundle.putString("SNIPPET", snippetShown);
        dlg.setArguments(bundle);
        dlg.show(getSupportFragmentManager(), "load");
    }

    public void onBack(View v){
        finish();
    }
}