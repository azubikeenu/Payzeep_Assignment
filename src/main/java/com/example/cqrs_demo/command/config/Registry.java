package com.example.cqrs_demo.command.config;

import com.example.cqrs_demo.command.interceptors.CreateTransactionCommandInterceptor;
import com.example.cqrs_demo.core.error_handling.TransactionServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Registry {

    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext applicationContext, CommandBus commandBus) {
        final CreateTransactionCommandInterceptor commandInterceptor = applicationContext.getBean(CreateTransactionCommandInterceptor.class);
        commandBus.registerDispatchInterceptor(commandInterceptor);
    }


    // register the event error handler
    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler("transaction-group", configuration ->
                new TransactionServiceEventsErrorHandler()
        );

    }

}