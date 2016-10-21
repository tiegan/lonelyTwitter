package ca.ualberta.cs.lonelytwitter;

public class NormalTweet extends Tweet {

    /**
     * Instantiates a new Normal tweet.
     *
     * @param message the message
     */
    public NormalTweet(String message) {
        super(message);
    }

    /**
     * Returns false for isImportant check.
     *
     * @return Boolean.FALSE
     */
    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
