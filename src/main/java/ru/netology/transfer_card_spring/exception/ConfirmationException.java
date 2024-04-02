package ru.netology.transfer_card_spring.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfirmationException extends RuntimeException {
    public ConfirmationException(String message) {
        super(message);
    }
}
