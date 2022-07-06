package edu.msu.bensmana.randomly;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class LoadContacts extends DialogFragment {

    // Stores the numbers of all the contacts
    private ArrayList<String> numbers;

    // stores the names of all the contacts
    private ArrayList<String> names;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the title
        builder.setTitle("Contacts");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.contacts_dlg, null);
        builder.setView(view);

        // get the names, numbers, and snippet from the bundle
        Bundle bundle = getArguments();
        numbers = bundle.getStringArrayList("NUMBERS");
        names = bundle.getStringArrayList("NAMES");
        String snippet = bundle.getString("SNIPPET");

        // Add a cancel button
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // Cancel just closes the dialog box
            }
        });
        final AlertDialog dlg = builder.create();

        // Find the list view
        ListView list = (ListView)view.findViewById(R.id.listContacts);

        // Create an adapter
        final ContactsAdapter adapter = new ContactsAdapter(list, names, numbers);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Dismiss the dialog box
                dlg.dismiss();

                String num = numbers.get(position);

                // create text intent
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", snippet);
                sendIntent.putExtra("address", num);
                startActivity(sendIntent);
            }

        });

        return dlg;
    }


    /**
     * An adapter so that list boxes can display a list of contacts from device
     */
    public class ContactsAdapter extends BaseAdapter {

        /**
         * The contacts we display in the list. Receive them in constructor
         */
        private ArrayList<Contact> contacts = new ArrayList();

        /**
         * Constructor
         */
        public ContactsAdapter(final View view, ArrayList<String> names, ArrayList<String> numbers) {

            // combine names and numbers in contacts objects
            for (int i = 0; i < names.size(); i++ ){
                contacts.add(new Contact(numbers.get(i), names.get(i)));
            }

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {


                        view.post(new Runnable() {

                            @Override
                            public void run() {
                                // Tell the adapter the data set has been changed
                                notifyDataSetChanged();
                            }

                        });
                    } catch (Exception e) {
                        // Error condition! Something went wrong
                        Log.e("Contacts", "Something went wrong when loading the contacts", e);
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(view.getContext(), "Unable to load contacts", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }

        @Override
        public int getCount() {
            return contacts.size();
        }

        @Override
        public Contact getItem (int position) {
            return contacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item, parent, false);
            }

            TextView tv = (TextView)view.findViewById(R.id.textItem);
            tv.setText(contacts.get(position).getName());


            return view;
        }

    }

}
