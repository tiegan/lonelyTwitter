package ca.ualberta.cs.lonelytwitter;

//Fix 3 (removing unused imports)
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * All fixes:
 * Fix 1: Removed "Public" from getMessage and getDate in interface Tweetable
 * Why? Because they were redundant.
 *
 * Fix 2: Removed "saveInFile" and "loadFromFile" from LonelyTwitterActivity
 * Why? Because they were no longer being used.
 *
 * Fix 3: Removed unused imports
 * Why? Because they weren't being used.
 *
 * Fix 4: Removed all default file templates from ElasticsearchTweetController, TweetList and LonelyTwitterActivityTest
 * Why? Because there's no reason to have the default file templates in there.
 *
 * Fix 5: Added "final" modifiers to LonelyTwitterActivity activity and TweetList tweets.
 * Why? Because it suggested they could be made final.
 *
 * Fix 6: Changed ElasticsearchTweetController method verifySettings() to private.
 * Why? ElasticsearchTweetController should be the only class calling verifySettings().
 */

public class LonelyTwitterActivity extends Activity {

	private final Activity activity = this; // Fix 5

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<NormalTweet> tweetList = new ArrayList<NormalTweet>();
	private ArrayAdapter<NormalTweet> adapter;

	public ListView getOldTweetsList(){
		return oldTweetsList;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button searchButton = (Button) findViewById(R.id.search);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				NormalTweet newTweet = new NormalTweet(text);
				tweetList.add(newTweet);
				adapter.notifyDataSetChanged();

				//Lab 7.
				// saveInFile(); // TODO replace this with elastic search
				ElasticsearchTweetController.AddTweetsTask addTweetsTasks =
						new ElasticsearchTweetController.AddTweetsTask();
				addTweetsTasks.execute(newTweet);
			}
		});

		// Newly implemented button, borrows the first half of what it does from
		// the save button (with GetTweetsTask substituted for AddTweetsTask).
		searchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();

				ElasticsearchTweetController.GetTweetsTask getTweetsTask =
						new ElasticsearchTweetController.GetTweetsTask();
				getTweetsTask.execute(text);

				// Turns out just setting tweetList = getTweetsTask.get() just makes
				// the adapter hate you and stop actually looking at tweetList, requiring
				// the need for clearing the tweetList then adding in each NormalTweet
				// object to keep the adapter happy. Who knew? (I do now!)
				try {
					ArrayList<NormalTweet> temp = getTweetsTask.get();
					tweetList.clear();
					for(int i = 0; i < temp.size(); ++i) {
						tweetList.add(temp.get(i));
					}
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					Log.i("Error", "The request for tweets failed in Search Button click.");
				}
			}
		});

		oldTweetsList.setOnItemClickListener(new
				AdapterView.OnItemClickListener(){
					public void onItemClick(AdapterView<?> parent, View view,
											int position ,long id){
						Intent intent = new Intent(activity, EditTweetActivity.class);
						startActivity(intent);
					}

				});


	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		//loadFromFile(); // TODO replace this with elastic search
		ElasticsearchTweetController.GetTweetsTask getTweetsTask =
				new ElasticsearchTweetController.GetTweetsTask();
		getTweetsTask.execute("");

		try {
			tweetList = getTweetsTask.get();
		} catch (Exception e) {
			Log.i("Error", "The request for tweets failed in onStart.");
		}

		adapter = new ArrayAdapter<NormalTweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	// Fix 2 (saveInFile and loadFromFile were here)
}