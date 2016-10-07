package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * It's a test. I guess it makes sure the activity works. idk I never actually
 * touched this before.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

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
}