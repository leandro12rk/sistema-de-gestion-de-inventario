package io.github.leandro12rk.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/*
* Escenario,Excepción,Estado HTTP
El ID no existe en la BD,ResourceNotFoundException,404
El usuario envió campos vacíos,InvalidDataException,400
El usuario intenta editar algo ajeno,AccessDeniedException,403
Error inesperado / Se cayó el server,Exception (Genérica),500
*
* */


@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura errores de búsqueda cuando un recurso específico no existe en BD
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFound(ResourceNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                "PRODUCT_NOT_FOUND"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Gestiona errores de validación de negocio antes de procesar la solicitud
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorDetails> handleBadRequest(InvalidDataException ex) {
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "INVALID_REQUEST_DATA"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Punto de entrada para errores no controlados, evita exponer trazas del sistema al cliente
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex) {
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocurrió un error inesperado en el servidor",
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();

        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                "VALIDATION_ERROR"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
