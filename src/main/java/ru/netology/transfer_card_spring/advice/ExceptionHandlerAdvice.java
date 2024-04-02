package ru.netology.transfer_card_spring.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.transfer_card_spring.exception.ConfirmationException;
import ru.netology.transfer_card_spring.exception.InputException;
import ru.netology.transfer_card_spring.exception.TransferException;
import ru.netology.transfer_card_spring.model.ErrorResponse;

import java.util.concurrent.atomic.AtomicInteger;

public class ExceptionHandlerAdvice {
    private final AtomicInteger id = new AtomicInteger(0);

    @ExceptionHandler(InputException.class)
    public ResponseEntity<ErrorResponse> handleInputData(InputException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleTransferError(TransferException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }

    @ExceptionHandler(ConfirmationException.class)
    public ResponseEntity<ErrorResponse> handleConfirmationError(ConfirmationException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }

}
