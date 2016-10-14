package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;
import java.util.ArrayList;

/**
 * It tests the TweetList to make sure everything works the way it should.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

    /**
     * Instantiates a new Tweet list test.
     */
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    /**
     * Test to make sure adding a tweet works.
     */
    public void testAddTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Adding tweet");
        tweets.addTweet(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    /**
     * Test to make sure it can check that a tweet exists.
     */
    public void testHasTweet() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("Test");
        list.addTweet(tweet);
        assertTrue(list.hasTweet(tweet));
    }

    /**
     * Test to make sure it can delete tweets.
     */
    public void testDelete() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("Deleting tweet");
        list.addTweet(tweet);
        list.delete(tweet);
        assertFalse(list.hasTweet(tweet));
    }

    /**
     * Test to make sure it can retrieve a tweet.
     */
    public void testGetTweet() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("Getting tweet");
        list.addTweet(tweet);
        Tweet returnedTweet = list.getTweet(0);
        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
    }

    /**
     * Test to get all the tweets in the ArrayList.
     */
    public void testGetAllTweets() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("First tweet");
        list.addTweet(tweet);
        Tweet tweet2 = new NormalTweet("Second tweet");
        list.addTweet(tweet2);
        ArrayList<Tweet> list2 = list.getTweets();
        assertEquals(list.getTweet(0), list2.get(0));
        assertEquals(list.getTweet(1), list2.get(1));
    }

    /**
     * Test to get the total count of tweets in the list.
     */
    public void testGetCount() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("First tweet");
        list.addTweet(tweet);
        Tweet tweet2 = new NormalTweet("Second tweet");
        list.addTweet(tweet2);
        assertEquals(list.getCount(), 2);
    }

    /**
     * Test that adds a duplicate tweet. It'll always fail since it's not allowed.
     */
    public void testAddDuplicateTweet() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("Test");
        list.addTweet(tweet);
        list.addTweet(tweet);
        assertTrue(list.hasTweet(tweet));
    }
}
