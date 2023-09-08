package com.example.cqrs_demo.core.error_handling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceExceptionHandler {

    private final MessageSource messageSource;


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest req) {
        String path = ((ServletWebRequest) req).getRequest().getRequestURI();
        var parsedErrors = parseErrors(ex.getBindingResult().getAllErrors());
        ErrorMessage errorMessage =
                new ErrorMessage(
                        new Date(), parsedErrors.toString());

        System.out.println(errorMessage);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalArgumentException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorMessage> handleException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // This handles all exceptions thrown in the @CommandHandler
    @ExceptionHandler(value = {CommandExecutionException.class})
    ResponseEntity<ErrorMessage> handleCommandExecutionException(CommandExecutionException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    private List<String> parseErrors(List<ObjectError> allErrors) {
        return allErrors.stream().map(this::localizeErrorMessage).collect(Collectors.toList());
    }


    private String localizeErrorMessage(ObjectError objectError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        log.info(localizedErrorMessage);
        return localizedErrorMessage;
    }
}
