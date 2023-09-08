package com.example.cqrs_demo.command.interceptors;

import com.example.cqrs_demo.command.commands.CreateTransactionCommand;
import com.example.cqrs_demo.core.entities.TransactionLookup;
import com.example.cqrs_demo.core.repository.TransactionLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateTransactionCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final TransactionLookupRepository transactionLookupRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull final List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            // narrow action to the CreateTransactionCommand
            if (command.getPayloadType().equals(CreateTransactionCommand.class)) {
                log.info("Intercepted {}", command.getPayloadType().getSimpleName());
                CreateTransactionCommand createTransactionCommand = (CreateTransactionCommand) command.getPayload();
                final Optional<TransactionLookup> optional = transactionLookupRepository.findById(createTransactionCommand.getId());
                if(optional.isPresent()){
                    throw new IllegalArgumentException(String.format("Transaction with id %s already exists",
                            createTransactionCommand.getId()));
                }

            }
            return command;
        };
    }


}
