package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * An interface implemented in Tweet, makes sure the class has a method to get
 * the message and a method to get the date.
 */
public interface Tweetable {
    public String getMessage();
    public Date getDate();

}
