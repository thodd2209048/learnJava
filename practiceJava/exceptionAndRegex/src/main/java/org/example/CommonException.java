package org.example;

public class CommonException extends RuntimeException {
    private int errorCode;
    protected String extraInfo;

    public CommonException(int errorCode, String extraInfo) {
        this.errorCode = errorCode;
        this.extraInfo = extraInfo;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    @Override
    public String toString() {
        return
                "Error: " + errorCode +
                ", moreInfo: " + extraInfo;
    }
}