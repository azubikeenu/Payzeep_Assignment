package com.example.cqrs_demo.core.error_handling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

import javax.annotation.Nonnull;

public class TransactionServiceEventsErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(@Nonnull final Exception e, @Nonnull final EventMessage<?> eventMessage,
                        @Nonnull final EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }
}
