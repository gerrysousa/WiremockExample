package br.com.schoolservice.handler;

import br.com.schoolservice.error.ErrorDetails;
import br.com.schoolservice.error.ResourceNotFoundDetails;
import br.com.schoolservice.error.ResourceNotFoundException;
import br.com.schoolservice.error.ValidationErrorDetails;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rfnException){
    ResourceNotFoundDetails rnfDetails =  ResourceNotFoundDetails.Builder
        .newBuilder()
        .timestamp(new Date().getTime())
        .status(HttpStatus.NOT_FOUND.value())
        .title("Resource Not Found")
        .detail(rfnException.getMessage())
        .developerMessage(rfnException.getClass().getName())
        .build();

    return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request) {
    List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
    String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
    String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

    ValidationErrorDetails rnfDetails =  ValidationErrorDetails.Builder
        .newBuilder()
        .timestamp(new Date().getTime())
        .status(HttpStatus.BAD_REQUEST.value())
        .title("Field Validation Error")
        .detail("Field Validation Error")
        .developerMessage(manvException.getClass().getName())
        .field(fields)
        .fieldMessage(fieldMessages)
        .build();

    return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorDetails errorDetails =  ErrorDetails.Builder
        .newBuilder()
        .timestamp(new Date().getTime())
        .status(status.value())
        .title("Internal Exception")
        .detail(ex.getMessage())
        .developerMessage(ex.getClass().getName())
        .build();
    return new ResponseEntity<>(errorDetails, headers, status);
  }

}
