package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

// Since I more or less did the same thing for my assignment, I just copied the relevant
// code from there and threw it into here. Isn't that great?
public class EditTweetActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private TextView tweetView;
    private int index;
    private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();

    // Sets index and tweetView on create.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        Intent intent = getIntent();
        index = intent.getIntExtra("givenTweet", 0);

        tweetView = (TextView) findViewById(R.id.Tweet);
    }

    // Loads from file, gets the tweet, then sets the TextView tweetView to the
    // tweet's message.
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        Tweet tweet = tweetList.get(index);
        tweetView.setText(tweet.getMessage());
    }

    // Loads from the file as in LonelyTwitterActivity. Nothing new.
    private void loadFromFile() {
        ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // The line below is stolen. Copyright me do not steal.
            // http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
            tweetList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            tweetList = new ArrayList<Tweet>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            tweetList = new ArrayList<Tweet>();
        }
    }
}
