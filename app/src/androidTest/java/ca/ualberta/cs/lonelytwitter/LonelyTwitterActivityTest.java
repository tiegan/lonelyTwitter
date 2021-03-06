package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * It's a test. I guess it makes sure the activity works. idk I never actually
 * touched this before.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2<LonelyTwitterActivity> {

    private Solo solo;

    /**
     * Instantiates a new Lonely twitter activity test.
     */
    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    /**
     * Test start.
     *
     * @throws Exception the exception
     */
    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    //Robotium set up.
    public void setUp() throws Exception {
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(), getActivity());
    }

    // Tests tweet by inputting test tweet, making sure it's there then clearing it and making
    // sure it's cleared.
    public void testTweet() {
        solo.assertCurrentActivity("Wrong Activity!", LonelyTwitterActivity.class);
        solo.clickOnButton("Clear");

        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");

        solo.clickOnButton("Save");
        solo.clearEditText((EditText) solo.getView(R.id.body));

        assertTrue(solo.waitForText("Test Tweet!"));

        solo.clickOnButton("Clear");
        assertFalse(solo.waitForText("Test Tweet!"));
    }

    // Clicks on a tweet in the tweet list.
    public void testClickTweetList() {
        LonelyTwitterActivity activity = (LonelyTwitterActivity) solo.getCurrentActivity();
        //Copied from testTweet() above.
        solo.assertCurrentActivity("Wrong Activity!", LonelyTwitterActivity.class);
        solo.clickOnButton("Clear");

        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");

        solo.clickOnButton("Save");
        //break
        // Needed, else the test fails. Stuff is slow, y'know?
        solo.waitForText("Test Tweet!");

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("Test Tweet!", tweet.getMessage());

        solo.clickInList(0);

        solo.assertCurrentActivity("Wrong Activity!", EditTweetActivity.class);

        // More or less the same thing from the lab, except just made sure Test Tweet was
        // the same.
        assertTrue(solo.waitForText("Test Tweet!"));

        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity!", LonelyTwitterActivity.class);
    }

    //Robotium tear down.
    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}