package ru.netology.transfer_card_spring.utils;


import ru.netology.transfer_card_spring.exception.ConfirmationException;
import ru.netology.transfer_card_spring.exception.InputException;
import ru.netology.transfer_card_spring.exception.TransferException;
import ru.netology.transfer_card_spring.model.*;

import static ru.netology.transfer_card_spring.utils.Commission.COMMISSION_VALUE;

public class Validator {

    public static void validationCards(Card cardFrom, Card cardTo, TransferRequest request,
                                 String cardNumFromRequest, String cardNumToRequest) {
        if (cardFrom == null) {
            throwInputException(Card.builder().cardNumber(cardNumFromRequest).build(),
                    cardTo, request.getAmount(),
                    "Номер карты отправителя не верный.");
            return;
        }
        if (cardTo == null) {
            throwInputException(cardFrom, Card.builder().cardNumber(cardNumToRequest).build(),
                    request.getAmount(),
                    "Номер карты получателя не верный.");
            return;
        }
        if (cardNumFromRequest.equals(cardNumToRequest)) {
            throwInputException(cardFrom, cardTo, request.getAmount(),
                    "Номера карты отправителя и номер карты получателя равны.");
        }
        if (!cardFrom.getValidTill().equals(request.getCardFromValidTill())) {
            throwInputException(cardFrom, cardTo, request.getAmount(),
                    "Срок действия карты отправителя введен неверно.");
        }
        if (!cardFrom.getCvv().equals(request.getCardFromCVV())) {
            throwInputException(cardFrom, cardTo, request.getAmount(),
                    "CVV код карты отправителя введен неверно.");
        }
        if (cardFrom.getAmountCard().getValue() < (request.getAmount().getValue() + (request.getAmount().getValue() * COMMISSION_VALUE))) {
            throwTransferException(cardFrom, cardTo, request.getAmount(),
                    "Недостаточно средств для перевода.");
        }
    }
    public static void validationOperation(TransferOperation transferOperation, String code) {
        if (transferOperation == null) {
            throwInputDataException("Операция не найдена.");
            return;
        }
        if (!transferOperation.getCode().equals(code)) {
            throwConfirmationException(transferOperation.getCardFrom(), transferOperation.getCardTo(), transferOperation.getAmount(),
                    "Код подтверждения операции неверен.");
        }
    }

        private static void throwConfirmationException(Card cardFrom, Card cardTo, Amount amount, String error) {
           WriteLog.writeFailToLog(cardFrom, cardTo, amount, error);
            throw new ConfirmationException(error);
        }

        private static void throwTransferException(Card cardFrom, Card cardTo, Amount amount, String error) {
            WriteLog.writeFailToLog(cardFrom, cardTo, amount, error);
            throw new TransferException(error);
        }

        private static void throwInputException(Card cardFrom, Card cardTo, Amount amount, String error) {
            WriteLog.writeFailToLog(cardFrom, cardTo, amount, error);
            throw new InputException(error);
        }

        private static void throwInputDataException(String error) {
            WriteLog.writeFailToLog(error);
            throw new InputException(error);
        }
    }
