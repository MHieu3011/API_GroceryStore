package com.vcc.apigrocerystore.exception;

import com.google.gson.Gson;
import com.vcc.apigrocerystore.builder.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    @Qualifier("gsonCustom")
    private Gson gson;

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("method argument not valid")
                .build();
        return buildResponseEntity(response);
    }

    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("missing request parameters")
                .build();
        return buildResponseEntity(response);
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
                                                                         HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("method not supported")
                .build();
        return buildResponseEntity(response);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("http message not readable")
                .build();
        return buildResponseEntity(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
                                                                     HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .buildMessage("unsupported media type")
                .build();
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(Response response) {

        return new ResponseEntity<>(gson.toJson(response), HttpStatus.BAD_REQUEST);
    }
}
