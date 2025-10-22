package com.sofipguz.Challenge5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura los errores de @Valid (MethodArgumentNotValidException).
     * Devuelve un JSON limpio con todos los campos que fallaron la validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {

        // Crea un mapa para guardar los errores de campo (campo: mensaje)
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(), // <-- ESTE ERA EL ERROR
                        fieldError -> fieldError.getDefaultMessage()
                ));

        // Crea el cuerpo de la respuesta de error
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", new Date());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value()); // 400
        errorBody.put("error", "Validation Failed");
        errorBody.put("messages", fieldErrors); // Aquí van los detalles
        errorBody.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Captura la excepción personalizada OrderNotFoundException.
     * Devuelve un JSON limpio con el mensaje de error.
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {

        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", new Date());
        errorBody.put("status", HttpStatus.NOT_FOUND.value()); // 404
        errorBody.put("error", "Not Found");
        errorBody.put("message", ex.getMessage()); // Mensaje de la excepción
        errorBody.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    /**
     * (Opcional pero recomendado)
     * Captura cualquier otra excepción genérica que no hayas manejado.
     * Esto evita que el usuario vea un 500 feo de Spring.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {

        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", new Date());
        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
        errorBody.put("error", "Internal Server Error");
        errorBody.put("message", "An unexpected error occurred. Please try again later.");
        errorBody.put("path", request.getDescription(false).replace("uri=", ""));

        // ¡Importante! Imprime el error real en tu consola para que puedas depurarlo
        ex.printStackTrace();

        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}