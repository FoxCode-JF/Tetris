package controller;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TimeController extends Timer
{
    private static int DEFAULT_INTERVAL = 500;

    public TimeController(ActionListener listener)
    {
        super(DEFAULT_INTERVAL, listener);
    }

    public void shortenDelay(int timesShorter)
    {
        setDelay(DEFAULT_INTERVAL/timesShorter);
    }

    public void setDefaultDelay(){
        setDelay(DEFAULT_INTERVAL);
    }
}
