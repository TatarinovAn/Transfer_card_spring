package ru.netology.transfer_card_spring;


import ru.netology.transfer_card_spring.model.Amount;
import ru.netology.transfer_card_spring.model.ConfirmRequest;
import ru.netology.transfer_card_spring.model.TransferRequest;

public class TestDatabase {
    public static TransferRequest getSuccessTransferRequest() {
        return TransferRequest.builder()
                .cardToNumber("123456780123456")
                .cardFromNumber("0987654321098765")
                .cardFromCVV("321")
                .cardFromValidTill("03/30")
                .amount(Amount.builder()
                        .value(10)
                        .currency("RUR")
                        .build())
                .build();
    }

    public static ConfirmRequest getConfirmRequest() {
        return ConfirmRequest.builder()
                .code("0000")
                .operationId(1)
                .build();
    }

    public static TransferRequest getFailedAmountTransferRequest() {
        return TransferRequest.builder()
                .cardToNumber("123456780123456")
                .cardFromNumber("0987654321098765")
                .cardFromCVV("321")
                .cardFromValidTill("03/30")
                .amount(Amount.builder()
                        .value(30000)
                        .currency("RUR")
                        .build())
                .build();
    }

    public static TransferRequest getFailedCardFromTransferRequest() {
        return TransferRequest.builder()
                .cardToNumber("123456780123456")
                .cardFromNumber("1111111111111111")
                .cardFromCVV("123")
                .cardFromValidTill("03/30")
                .amount(Amount.builder()
                        .value(1000)
                        .currency("RUR")
                        .build())
                .build();
    }

    public static TransferRequest getFailedCVVTransferRequest() {
        return TransferRequest.builder()
                .cardToNumber("123456780123456")
                .cardFromNumber("0987654321098765")
                .cardFromCVV("121")
                .cardFromValidTill("03/30")
                .amount(Amount.builder()
                        .value(1000)
                        .currency("RUR")
                        .build())
                .build();
    }

    public static ConfirmRequest getSuccessConfirmRequest() {
        return ConfirmRequest.builder()
                .code("0000")
                .operationId(1)
                .build();
    }


}
