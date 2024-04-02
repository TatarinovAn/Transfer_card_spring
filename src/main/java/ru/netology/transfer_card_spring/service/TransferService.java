package ru.netology.transfer_card_spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.transfer_card_spring.model.AmountCard;
import ru.netology.transfer_card_spring.model.ConfirmRequest;
import ru.netology.transfer_card_spring.model.TransferRequest;
import ru.netology.transfer_card_spring.model.TransferResponse;
import ru.netology.transfer_card_spring.repository.CardRepositoryImp;
import ru.netology.transfer_card_spring.repository.TransferOperationRepositoryImp;
import ru.netology.transfer_card_spring.utils.Validator;
import ru.netology.transfer_card_spring.utils.WriteLog;

import static ru.netology.transfer_card_spring.utils.Commission.COMMISSION_VALUE;

@AllArgsConstructor
@Service
public class TransferService {

    private final CardRepositoryImp cardRepositoryImp;
    private final TransferOperationRepositoryImp transferOperationRepositoryImp;


    public TransferResponse transferMoney(TransferRequest request) {


        var cardFrom = cardRepositoryImp.getCardByNumber(request.getCardFromNumber());
        var cardTo = cardRepositoryImp.getCardByNumber(request.getCardToNumber());

        Validator.validationCards(cardFrom, cardTo, request, request.getCardFromNumber(), request.getCardToNumber());

        var id = transferOperationRepositoryImp.createTransferOperation(cardFrom, cardTo, request.getAmount());
        return new TransferResponse(id);
    }

    public TransferResponse confirmOperation(ConfirmRequest request) {
        var operation = transferOperationRepositoryImp.getTransferOperationById(request.getOperationId());

        Validator.validationOperation(operation, request.getCode());
        var cardFrom = operation.getCardFrom();
        var cardTo = operation.getCardTo();
        var transferAmount = operation.getAmount();
        var commission = transferAmount.getValue() * COMMISSION_VALUE;

        cardTo.setAmountCard(AmountCard.builder()
                .currency(transferAmount.getCurrency())
                .value(cardTo.getAmountCard().getValue() + transferAmount.getValue())
                .build());

        cardFrom.setAmountCard(AmountCard.builder()
                .currency(transferAmount.getCurrency())
                .value(cardFrom.getAmountCard().getValue() - transferAmount.getValue() - (int) commission)
                .build());

        cardRepositoryImp.saveCard(cardTo);
        cardRepositoryImp.saveCard(cardFrom);
        transferOperationRepositoryImp.removeTransferOperationById(operation.getId());

        WriteLog.writeSuccessToLog(cardFrom, cardTo, transferAmount);

        return new TransferResponse(operation.getId());
    }

}
