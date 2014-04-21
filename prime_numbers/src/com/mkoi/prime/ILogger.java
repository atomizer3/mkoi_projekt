package com.mkoi.prime;

/**
 * Created by Tomasz Nowak on 2014-04-18.
 */

/**
 * Interface for basic logging information.
 */
public interface ILogger {
    /**
     * Writes message to provided output.
     * @param message message to write.
     */
    void log(String message);

    /**
     * Sets the header for current outputs.
     * @param stepName the header text.
     */
    void setStepName(String stepName);
}
