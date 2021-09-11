package com.swithExample.driven.common.enums;

/**
 * @author DiGiEx
 */
public enum APIStatus {
    //////////////////
    //   DEFAULT    //
    //////////////////
    OK(200, "OK"),
    NO_RESULT(201, "No more result."),
    FAIL(202, "Fail"),

    //////////////////
    // CLIENT SIDE  //
    //////////////////
    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized or Access Token is expired"),
    FORBIDDEN(403, "Forbidden! Access denied"),
    NOT_FOUND(404, "Not Found"),
    EXISTED(405, "Already existed"),
    BAD_PARAMS(406, "There is some invalid data"),
    EXPIRED(407, "Expired"),

    // Redeem checking status
    REDEEM_REQUIRE_AUTH(600, "QR Code require authentication to redeem"),
    REDEEM_SINGLE_EXISTED(601, "Already redeem single"),
    REDEEM_MULTIPLE_EXISTED(602, "Already redeem multiple"),
    REDEEM_EXPIRED(603, "QR Code had been expired"),
    REDEEM_REACH_MAX(604, "QR Code had been reach max redeem"),

    INTERNAL_SERVER_ERROR (500, "Internal server error");

    private final int code;
    private final String description;

    private APIStatus(int s, String v) {
        code = s;
        description = v;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static APIStatus getEnum(int code) {
        for (APIStatus v : values())
            if (v.getCode() == code) return v;
        throw new IllegalArgumentException();
    }
}
