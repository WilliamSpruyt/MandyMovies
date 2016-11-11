package tk.qwerky.mandymovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class ReviewPage extends AppCompatActivity {
    public Flick theFlick;
    public String idno = "207932";
    public ReviewAdapter adapter;
    Reviews rev1 = new Reviews("self regarding tosh self " +
            "regarding tosh self regarding tosh self regarding tosh self regarding tosh " +
            "self regarding tosh self regarding tosh  ", "A WAnker");
    Reviews rev2 = new Reviews("self regarding tosh self " +
            "regarding tosh self regarding tosh self regarding tosh self regarding tosh " +
            "self regarding tosh self regarding tosh  ", "A WAnker");
    Reviews rev3 = new Reviews("self regarding tosh self " +
            "regarding tosh self regarding tosh self regarding tosh self regarding tosh " +
            "self regarding tosh self regarding tosh  ", "A WAnker");
    Reviews[] reviewSet = {rev1, rev2, rev3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        ListView listView = (ListView) findViewById(R.id.list);
        Intent intent = getIntent();
        theFlick = (Flick) intent.getSerializableExtra("MyClass");

        idno = theFlick.getId_no();
        RocketAsyncTask sinker = new RocketAsyncTask();
        final ArrayList<Reviews> reviewArrayList = new ArrayList<Reviews>(Arrays.asList(reviewSet));
        sinker.execute();
        adapter = new ReviewAdapter(this, reviewArrayList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView.setAdapter(adapter);

    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        theFlick = (Flick) savedInstanceState.getSerializable("flick");

    }

    public void toaster(String message) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent =
                        new Intent(ReviewPage.this, MovieDetail.class);
                intent.putExtra("MyClass", theFlick);
                startActivity(intent);
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class RocketAsyncTask extends AsyncTask<URL, Void, ArrayList<Reviews>> {

        @Override
        protected ArrayList<Reviews> doInBackground(URL... urls) {


            URL url = FlicksUtil.createUrl(FlickContract.MOVIE_TRAILERa + idno + FlickContract.MOVIE_TRAILERr);


            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = FlicksUtil.makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Reviews> flicks = FlicksUtil.extractReviews(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}

            return flicks;
        }

        /**

         */

        @Override
        protected void onPostExecute(ArrayList<Reviews> flicks) {
            if (adapter != null) {
                adapter.clear();
                if (flicks.isEmpty()) {
                    Reviews empty = new Reviews("WE HAVE NO REVIEWS", "FOR THIS FILM");
                    flicks.add(empty);
                }

                adapter.addAll(flicks);

            }

            super.onPostExecute(flicks);
        }


    }
}

