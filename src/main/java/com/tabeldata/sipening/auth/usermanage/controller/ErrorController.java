package com.tabeldata.sipening.auth.usermanage.controller;

import com.tabeldata.sipening.auth.usermanage.dto.ErrorDto;
import com.tabeldata.sipening.auth.usermanage.error.NotAllowedException;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> notFoundHandler(NotFoundException notFoundException) {
        ErrorDto.NotFound data = new ErrorDto.NotFound();
        data.setMessage("Terjadi Error Karena " + notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
    }

    @ExceptionHandler({NotAllowedException.class})
    public ResponseEntity<?> noContentHandler(NotAllowedException notAllowedException) {
        ErrorDto.NotAllowed data = new ErrorDto.NotAllowed();
        data.setMessage("Tidak Diizinkan Mengakses Menu " + notAllowedException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
