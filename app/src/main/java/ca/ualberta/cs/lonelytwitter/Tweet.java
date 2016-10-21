package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

import io.searchbox.annotations.JestId;

public abstract class Tweet implements Tweetable {
    private String message;
    private Date date;
    @JestId
    private String id;

    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
    }

    public Tweet(String message, Date date) {
        this.message = message;
        this.date = date;
    }

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


    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 140) {
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

    //Lab 7.
    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
