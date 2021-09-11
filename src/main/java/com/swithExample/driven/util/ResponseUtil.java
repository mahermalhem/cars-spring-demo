package com.swithExample.driven.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swithExample.driven.common.enums.APIStatus;
import com.swithExample.driven.controller.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Phil Conal
 */
@Component
public class ResponseUtil {
    private ObjectMapper mapper;

    @Autowired
    public ResponseUtil(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Create HTTP Response
     * @param apiStatus
     * @param data
     * @return
     */
    private RestApiResponse _createResponse(APIStatus apiStatus, Object data) {
        return new RestApiResponse(apiStatus, data);
    }

    /**
     * Create HTTP Response with description
     * @param apiStatus
     * @param data
     * @return
     */
    private RestApiResponse _createResponse(APIStatus apiStatus, Object data, String description) {
        return new RestApiResponse(apiStatus, data, description);
    }

    /**
     * Build HTTP Response
     * @param apiStatus
     * @param data
     * @param httpStatus
     * @return
     */
    public ResponseEntity<RestApiResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus) {
        return new ResponseEntity(_createResponse(apiStatus, data), httpStatus);
    }

    /**
     * Build HTTP Response with description
     * @param apiStatus
     * @param data
     * @param description
     * @param httpStatus
     * @return
     */
    public ResponseEntity<RestApiResponse> buildResponse(APIStatus apiStatus, Object data, String description, HttpStatus httpStatus) {
        return new ResponseEntity(_createResponse(apiStatus, data, description), httpStatus);
    }

    /**
     * Return success HTTP Request
     * @param data
     * @return
     */
    public ResponseEntity<RestApiResponse> successResponse(Object data) {
        return buildResponse(APIStatus.OK, data, HttpStatus.OK);
    }

    /**
     * Return success HTTP Request with description
     * @param data
     * @param description
     * @return
     */
    public ResponseEntity<RestApiResponse> successResponse(Object data, String description) {
        return buildResponse(APIStatus.OK, data, description, HttpStatus.OK);
    }
}
