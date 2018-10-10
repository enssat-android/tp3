package fr.enssat.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fr.enssat.firstapp.counter.Counter;

public class MainActivity extends AppCompatActivity {

    private static final String TOTAL_COUNT = "total_count";

    private Counter counter = new Counter();
    private Counter savedCounter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu: {
                saveCounter();
                return true;
            }
            case R.id.restore_menu: {
                restoreCounter();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveCounter() {
        savedCounter = counter;
    }

    public void restoreCounter() {
        if (savedCounter != null) {
            counter = savedCounter;
        }
        updateUI();
    }
    /**
     * reset the counter
     * @param view -- the view that is clicked
     */
    public void resetMe(View view){
        if (counter.isInit()) {
            Snackbar.make(view, "It's already done !!!", Snackbar.LENGTH_SHORT).show();
        } else {
            Counter saved = counter;
            counter = new Counter();
            updateUI();
            Snackbar.make(view, "Reset done", Snackbar.LENGTH_LONG)
                    .setAction("Undo", (v) -> {counter = saved; updateUI();})
                    .show();
        }
    }

    private void updateUI() {
        // Get the text view.
        TextView showCountTextView = (TextView)
                findViewById(R.id.textView);

        // Display the new value in the text view.
        showCountTextView.setText(Integer.toString(counter.get()));
    }

    public void countMe(View view) {
        // increment Counter
        counter.inc();

        // update the UI
        updateUI();
    }

    public void randomMe(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, SecondActivity.class);

        // Add the count to the extras for the Intent.
        randomIntent.putExtra(TOTAL_COUNT, counter.get());

        // Start the new activity.
        startActivity(randomIntent);
    }
}
