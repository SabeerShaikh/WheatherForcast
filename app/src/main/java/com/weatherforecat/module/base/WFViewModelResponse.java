package com.weatherforecat.module.base;

import androidx.lifecycle.LiveData;

public class WFViewModelResponse<T> extends LiveData {

    public final T response;
    /**
     * always check this status when the object is received
     **/
    public final boolean success;
    /**
     * set with the reason for the failure
     **/
    public final String failureMessage;

    /**
     * error code from api layer
     */
    public final int code;

    private WFViewModelResponse(T response, boolean status, String failureMessage, int code) {
        this.response = response;
        this.success = status;
        this.failureMessage = failureMessage;
        this.code = code;
    }

    /**
     * Called in successful completion of use case
     *
     * @param response
     */
    public WFViewModelResponse(T response) {
        this(response, true, "", 0);
    }

    /**
     * Called in a failure situation
     *
     * @param message
     */
    public WFViewModelResponse(String message, int code) {
        this(null, false, message, code);
    }

    /**
     * Called in a failure situation
     *
     * @param message
     */
    public WFViewModelResponse(String message) {
        this(null, false, message, -1);
    }
}
