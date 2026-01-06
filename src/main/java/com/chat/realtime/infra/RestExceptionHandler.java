package com.chat.realtime.infra;

import com.chat.realtime.exceptions.ChatRoomNotFoundException;
import com.chat.realtime.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(404).body(threatResponse);
    }

    @ExceptionHandler(ChatRoomNotFoundException.class)
    private ResponseEntity<RestErrorMessage> chatRoomNotFoundHandler(ChatRoomNotFoundException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(404).body(threatResponse);
    }

}
