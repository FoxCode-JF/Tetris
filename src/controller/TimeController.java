package controller;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Class representing game timer
 */
class TimeController extends Timer
{
    private static int DEFAULT_INTERVAL = 500;

    /**
     * Initializes timer and adds its listener
     * @param listener Timer listener
     */
    TimeController(ActionListener listener)
    {
        super(DEFAULT_INTERVAL, listener);
    }

    /**
     * Shortens timer interval
     * @param timesShorter Value by which interval is divided
     */
    void shortenDelay(int timesShorter)
    {
        setDelay(DEFAULT_INTERVAL/timesShorter);
    }

    /**
     * Sets default timer interval
     */
    void setDefaultDelay(){
        setDelay(DEFAULT_INTERVAL);
    }
}
