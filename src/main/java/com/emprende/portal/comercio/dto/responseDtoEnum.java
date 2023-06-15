package com.emprende.portal.comercio.dto;

public enum responseDtoEnum {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    private final int code;

    responseDtoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
