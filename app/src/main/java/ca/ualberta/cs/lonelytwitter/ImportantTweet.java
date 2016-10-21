package ca.ualberta.cs.lonelytwitter;

public class ImportantTweet extends Tweet{

    /**
     * Instantiates a new Important tweet.
     *
     * @param message the message
     */
    public ImportantTweet(String message){
        super(message);
    }

    /**
     * Returns true for isImportant check.
     *
     * @return Boolean.TRUE
     */
    @Override
    public Boolean isImportant(){
        return Boolean.TRUE;
    }


}
