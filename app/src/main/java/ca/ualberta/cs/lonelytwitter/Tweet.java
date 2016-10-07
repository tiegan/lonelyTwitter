package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Main class of LonelyTwitter that holds the info about the tweet objects.
 */
public abstract class Tweet implements Tweetable {
    private String message;
    private Date date;

    /**
     * Instantiates a new Tweet (when not given date).
     *
     * @param message the message
     */
    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    /**
     * Instantiates a new Tweet (when given date).
     *
     * @param message the message
     * @param date    the date
     */
    public Tweet(String message, Date date){
        this.message = message;
        this.date = date;
    }

    /**
     * Returns the string in message.
     *
     * @return message
     */
    @Override
    public String toString() {
        return message;
    }

    /**
     * Abstract method used in sub-classes to check if they're important.
     *
     * @return the boolean
     */
    public abstract Boolean isImportant();

    /**
     * Sets the string in message.
     *
     * @param message the message
     * @throws TweetTooLongException the tweet too long exception
     */
    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 140){
            //Do Something!
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    /**
     * Sets the date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the stored string in message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the date in message.
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
