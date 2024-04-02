package ru.netology.transfer_card_spring.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}
