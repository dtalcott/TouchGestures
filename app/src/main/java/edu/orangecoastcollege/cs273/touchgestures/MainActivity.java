package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * This activity analyzes the user's gestures and adds it to the tally of that gesture.
 *
 * @author Devon Tallcott
 * @version 1.0
 *
 * created on 11/2/17
 */
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener

{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    //member variable  to detect gestures
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /**
     * Sends the touch event to all the children in the view group:
     * ex. scroll group to the TextView
     * @param ev the motion event triggering the touch
     * @return True if event was handled; false otherwise
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * Handles a single tap gesture
     * @param motionEvent the motion event triggering the touch
     * @return True if event was handled; false otherwise
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent)
    {
        //displays the message and increments the count
        gesturesLogTextView.append("\nonSingleTap gesture occurred");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /**
     *Handles a double tap gesture
     * @param motionEvent motionEvent the motion event triggering the touch
     * @return True if event was handled; false otherwise
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent)
    {
        gesturesLogTextView.append("\nonDoubleTap gesture occurred");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * During a double tap, another event occurs (including move)
     * @param motionEvent motionEvent the motion event triggering the touch
     * @return True if event was handled; false otherwise
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent)
    {
        return false;
    }

    /**
     * User made initial contact with the device.
     * Every gesture begins with onDown
     * @param motionEvent motionEvent the motion event triggering the touch
     * @return True if event was handled; false otherwise
     */
    @Override
    public boolean onDown(MotionEvent motionEvent)
    {
        gesturesLogTextView.append("\nonDown gesture occurred");
        return true;
    }

    /**
     * Down event where the user does not let go, short duration
     * @param motionEvent motionEvent the motion event triggering the touch
     */
    @Override
    public void onShowPress(MotionEvent motionEvent)
    {

    }

    /**
     *  User releases touch.
     * @param motionEvent motionEvent the motion event triggering the touch
     * @return True if event was handled; false otherwise
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent)
    {
        gesturesLogTextView.append("\nonSingleTapUp gesture occurred");
        return true;
    }

    /**
     * A down event followed by a press and a lateral movement without letting go.
     * @param motionEvent where scroll originated
     * @param motionEvent1 where scroll stopped
     * @param distanceX distance x direction (pixels)
     * @param distanceY distance y direction (pixels)
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY)
    {
        gesturesLogTextView.append("\nonScroll: distanceX = " + distanceX + " distanceY = " + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * Down hold for a long duration
     * @param motionEvent motionEvent the motion event triggering the touch
     */
    @Override
    public void onLongPress(MotionEvent motionEvent)
    {
        gesturesLogTextView.append("\nonLongPress gesture occurred");
        longPressTextView.setText(String.valueOf(++longPresses));

    }

    /**
     * Similar to a scroll with a faster velocity and releases
     * @param motionEvent where fling started
     * @param motionEvent1 where fling stopped
     * @param v initial velocity (pixels/second)
     * @param v1 terminal velocity
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1)
    {
        gesturesLogTextView.append("\nonFling initial V = " + v + " terminal V = " + v1);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    /**
     * Resets all the member variables and empties the textViews
     * @param v
     */
    public void clearAll(View v)
    {
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
        gesturesLogTextView.setText("");
        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        longPressTextView.setText("0");
        scrollTextView.setText("0");
        flingTextView.setText("0");
    }
    {}

}
