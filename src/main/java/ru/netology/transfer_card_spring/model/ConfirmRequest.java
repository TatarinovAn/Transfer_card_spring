package ru.netology.transfer_card_spring.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmRequest {
    private String code;

    private int operationId;
}
