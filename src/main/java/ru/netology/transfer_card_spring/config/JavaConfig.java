package ru.netology.transfer_card_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.transfer_card_spring.controller.TransferController;
import ru.netology.transfer_card_spring.repository.CardRepositoryImp;
import ru.netology.transfer_card_spring.repository.TransferOperationRepositoryImp;
import ru.netology.transfer_card_spring.service.TransferService;

@Configuration
public class JavaConfig {
    @Bean
    public TransferController transferController() {
        return new TransferController(transferService());
    }

    @Bean
    public TransferService transferService() {
        return new TransferService(cardRepositoryImp(), transferOperationRepositoryImp());
    }

    @Bean
    public CardRepositoryImp cardRepositoryImp() {
        return new CardRepositoryImp();
    }

    @Bean
    public TransferOperationRepositoryImp transferOperationRepositoryImp() {
        return new TransferOperationRepositoryImp();
    }
}
