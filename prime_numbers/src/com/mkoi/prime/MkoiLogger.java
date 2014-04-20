package com.mkoi.prime;

import javax.swing.*;
import java.util.Date;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public class MkoiLogger implements ILogger {

    public MkoiLogger(JTextArea textArea) {
        this.textArea = textArea;
    }

    private JTextArea textArea;
    private String stepName;

    @Override
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    @Override
    public void log(String message) {
        Date date = new Date();
        textArea.setText(textArea.getText() +
                date.toString().substring(11, 20) +
                stepName + " " + message + '\n');
    }
}
