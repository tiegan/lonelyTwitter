/*
 * Copyright (C) 2016 Team 20, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, copy or distribute this code under terms and conditions of University of Alberta
 * and Code of Student Behavior.
 * Please contact a@lonelybot.ca for more details or questions.
 * (I should probably change that joke e-mail but I don't really care so I'm leaving it.)
 */
package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class is the main view class in lonelyTwitter class.
 * It deals with user inputs, saves/loads them into/out of the file FILE_NAME (file.sav).
 * <p> You can access this file from Android Device Monitor. </p>
 * <pre> Pre-formatted text. </pre>
 * <code> Pseudo-code that is used for this class is as follows: <br>
 *   Step 1: <br>
 *   Step 2: <br>
 * </code>
 * <ol>
 * <li>Item 1</li>
 * <li>Item 2</li>
 * <li>Item 3</li>
 * <li>Item 4</li>
 * </ol>
 * @author tiegan
 * @since 1.4
 * @see NormalTweet
 * @see java.io.BufferedReader
 * @see TweetList
 */
public class LonelyTwitterActivity extends Activity {

	private Activity activity = this;

	/**
	 * This is the name of the file that is saved in your virtual device (or real device).
	 * You can access it using Android Device Monitor by selecting your app,
	 * then data -> data -> file.sav.
	 * @see NormalTweet
	 */
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	// It returns oldTweetsList. Like, really.
	public ListView getOldTweetsList() {
		return oldTweetsList;
	}
	
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				Tweet newTweet = new NormalTweet(text);
				tweetList.add(newTweet);
				adapter.notifyDataSetChanged();
				saveInFile();
			}
		});

		// Clear button is set and actually works now.
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Clears list that appears on screen by just calling clear on adapter.
				adapter.clear();

				// Clears list saved in file by just opening the file then closing it again.
				// If the program is cleared then closed, it actually crashes the program if
				// IOException isn't set to make a new tweetList. How fun.
				try {
					FileOutputStream fileStream = openFileOutput(FILENAME, 0);
					fileStream.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					tweetList = new ArrayList<Tweet>();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					tweetList = new ArrayList<Tweet>();
				}
			}
		});

		// Goes to the tweet clicked on.
		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				intent.putExtra("givenTweet", position);
				startActivity(intent);
			}
		});
	}

	/**
	 * This method defines what happens at the start when the activity is called.
	 * Loads tweets from json file to an array list then to adapter.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 *
	 * @param inputString1 This is prefix
	 * @param inputString2 This is date
	 * @param inputString3 This is candy
	 * @param inputString4 This is nuts
     * @return
	 * CTRL+Shift+/
	 */
	private String buildTweetString(String inputString1, String inputString2,
									String inputString3, String inputString4) {
		String outputString = "The completed tweet string is: " + inputString1
				+ ", " + inputString2 + ", " + inputString3 + ", " + inputString4;
		return outputString;
	}

	/**
	 * This method loads the json file and generates the tweets from its contents.
	 * @exception FileNotFoundException
	 */
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

	/**
	 * This method takes the tweets and saves them in the json file.
	 * @throws RuntimeException
	 * @exception FileNotFoundException
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(tweetList, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}