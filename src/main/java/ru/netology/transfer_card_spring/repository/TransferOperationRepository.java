package ru.netology.transfer_card_spring.repository;

import ru.netology.transfer_card_spring.model.Card;
import ru.netology.transfer_card_spring.model.Amount;
import ru.netology.transfer_card_spring.model.TransferOperation;

public interface TransferOperationRepository {
    int createTransferOperation(Card cardFrom, Card cardTo, Amount amount);

    TransferOperation getTransferOperationById(int id);

    void removeTransferOperationById(int id);
}
