package com.fyaora.security.oauth_service.advice;

import com.fyaora.security.oauth_service.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, WebRequest webRequest) {
        return getMessageDTO(HttpStatus.BAD_REQUEST, ex.getMessage(), webRequest);
    }

    private ResponseEntity<MessageDTO> getMessageDTO(HttpStatus status, String message, WebRequest webRequest) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTimestamp(LocalDateTime.now());
        messageDTO.setStatus(status.value());
        messageDTO.setError(status.getReasonPhrase());
        messageDTO.setMessage(message);
        messageDTO.setPath(webRequest.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(status).body(messageDTO);
    }
}
