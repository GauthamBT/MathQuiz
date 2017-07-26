package com.example.gauthambt.fragmentpractise;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FragmentContainer extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fragment_container);

        Bundle frameChoice = getIntent().getExtras();

        String choice = frameChoice.getString("fragmentChoice");

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        if (choice.equals("1")) {
            Addition add = new Addition();
            ft.add(R.id.content_fragment_container, add);

        } else if (choice.equals("2")) {
            Subtraction sub = new Subtraction();
            ft.add(R.id.content_fragment_container, sub);

        } else if (choice.equals("3")) {
            Multiply mul = new Multiply();
            ft.add(R.id.content_fragment_container, mul);

        }

        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // do something useful
                onBackPressed();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quit Quiz")
                .setMessage("Confirm to quit the quiz")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        })
                .setNegativeButton("No", null)
                .show();
    }

}
