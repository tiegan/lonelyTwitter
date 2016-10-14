package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * It's a list of tweets. Woo.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    /**
     * Instantiates a new Tweet list.
     */
    public TweetList() {
        //do nothing
    }

    /**
     * Checks if a tweet is in TweetList and returns true or false.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    public boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    /**
     * Adds a tweet to the ArrayList.
     *
     * @param tweet the tweet
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void addTweet(Tweet tweet) throws IllegalArgumentException {
        if (tweets.contains(tweet)) {
            throw new IllegalArgumentException();
        }
        else {
            tweets.add(tweet);
        }
    }

    /**
     * Deletes a tweet from the ArrayList if it exists.
     *
     * @param tweet the tweet
     */
    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    /**
     * Gets the tweet from the ArrayList if it exists.
     *
     * @param index the index
     * @return the tweet
     */
    public Tweet getTweet(int index) {
        return tweets.get(index);
    }

    /**
     * Returns the ArrayList of tweets.
     *
     * @return the tweets
     */
    public ArrayList<Tweet> getTweets() {
        return new ArrayList<Tweet>(tweets);
    }

    /**
     * Gets the total number of tweets.
     *
     * @return the count
     */
    public int getCount() {
        return tweets.size();
    }
}
