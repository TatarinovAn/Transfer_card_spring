package ru.netology.transfer_card_spring.repository;

import ru.netology.transfer_card_spring.model.Card;
import ru.netology.transfer_card_spring.model.Amount;
import ru.netology.transfer_card_spring.model.TransferOperation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransferOperationRepositoryImp implements TransferOperationRepository {
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    private final Map<Integer, TransferOperation> transferOperationMap = new ConcurrentHashMap<>();

    @Override
    public int createTransferOperation(Card cardFrom, Card cardTo, Amount amount) {
        amount.setValue(amount.getValue() / 100);
        int id = atomicCounter.incrementAndGet();
        transferOperationMap.put(id, TransferOperation.builder()
                .id(id)
                .cardFrom(cardFrom)
                .cardTo(cardTo)
                .amount(amount)
                .code("0000")
                .build());
        return id;
    }

    @Override
    public TransferOperation getTransferOperationById(int id) {
        return transferOperationMap.get(id);
    }

    @Override
    public void removeTransferOperationById(int id) {
        transferOperationMap.remove(id);
    }
}
