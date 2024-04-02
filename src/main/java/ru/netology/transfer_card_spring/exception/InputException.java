package ru.netology.transfer_card_spring.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InputException extends RuntimeException {
    public InputException(String message) {
        super(message);
    }
}
