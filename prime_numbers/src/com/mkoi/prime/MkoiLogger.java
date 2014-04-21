package com.mkoi.prime;

import javax.swing.*;
import java.util.Date;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */

/**
 * Basic logging class.
 */
public class MkoiLogger implements ILogger {

    /**
     * Constructor for UI TextArea
     * @param textArea area to fulfill with text.
     */
    public MkoiLogger(JTextArea textArea) {
        this.textArea = textArea;
    }

    private JTextArea textArea;
    private String stepName;

    /**
     * Sets the header for current outputs.
     * @param stepName the header text.
     */
    @Override
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

        /**
         * Writes message to provided output.
         * @param message message to write.
         */
    @Override
    public void log(String message) {
        Date date = new Date();
        textArea.setText(textArea.getText() +
                date.toString().substring(11, 20) +
                stepName + " " + message + '\n');
    }
}
