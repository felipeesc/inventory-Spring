package com.microservico.estoque.configuration;

import com.microservico.estoque.common.RabbitmqConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange exchange) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, exchange.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue queueCategory = this.fila(RabbitmqConstantes.QUEUE_CATEGORY);
        Queue queueCity = this.fila(RabbitmqConstantes.QUEUE_CITY);
        Queue queueInput = this.fila(RabbitmqConstantes.QUEUE_INPUT);
        Queue queueInputItem = this.fila(RabbitmqConstantes.QUEUE_INPUT_ITEM);
        Queue queueOutput = this.fila(RabbitmqConstantes.QUEUE_OUTPUT);
        Queue queueOutputItem = this.fila(RabbitmqConstantes.QUEUE_OUTPUT_ITEM);
        Queue queueProduct = this.fila(RabbitmqConstantes.QUEUE_PRODUCT);
        Queue queueProvider = this.fila(RabbitmqConstantes.QUEUE_PROVIDER);
        Queue queueStore = this.fila(RabbitmqConstantes.QUEUE_STORE);
        Queue queueToken = this.fila(RabbitmqConstantes.QUEUE_TOKEN);
        Queue queueTransport = this.fila(RabbitmqConstantes.QUEUE_TRANSPORT);
        Queue queueUser = this.fila(RabbitmqConstantes.QUEUE_USER);


        DirectExchange trocaDeFila = this.trocaDireta();

        Binding callCategory = this.relacionamento(queueCategory, trocaDeFila);
        Binding callCity = this.relacionamento(queueCity, trocaDeFila);
        Binding callInput = this.relacionamento(queueInput, trocaDeFila);
        Binding callInputItem = this.relacionamento(queueInputItem, trocaDeFila);
        Binding callOutput = this.relacionamento(queueOutput, trocaDeFila);
        Binding callOutputItem = this.relacionamento(queueOutputItem, trocaDeFila);
        Binding callProduct = this.relacionamento(queueProduct, trocaDeFila);
        Binding callProvider = this.relacionamento(queueProvider, trocaDeFila);
        Binding callStore = this.relacionamento(queueStore, trocaDeFila);
        Binding callToken = this.relacionamento(queueToken, trocaDeFila);
        Binding callTransport = this.relacionamento(queueTransport, trocaDeFila);
        Binding callUser = this.relacionamento(queueUser, trocaDeFila);


        this.amqpAdmin.declareQueue(queueCategory);
        this.amqpAdmin.declareQueue(queueCity);
        this.amqpAdmin.declareQueue(queueInput);
        this.amqpAdmin.declareQueue(queueInputItem);
        this.amqpAdmin.declareQueue(queueOutput);
        this.amqpAdmin.declareQueue(queueOutputItem);
        this.amqpAdmin.declareQueue(queueProduct);
        this.amqpAdmin.declareQueue(queueProvider);
        this.amqpAdmin.declareQueue(queueStore);
        this.amqpAdmin.declareQueue(queueToken);
        this.amqpAdmin.declareQueue(queueTransport);
        this.amqpAdmin.declareQueue(queueUser);


        this.amqpAdmin.declareExchange(trocaDeFila);

        this.amqpAdmin.declareBinding(callCategory);
        this.amqpAdmin.declareBinding(callCity);
        this.amqpAdmin.declareBinding(callInput);
        this.amqpAdmin.declareBinding(callInputItem);
        this.amqpAdmin.declareBinding(callOutput);
        this.amqpAdmin.declareBinding(callOutputItem);
        this.amqpAdmin.declareBinding(callProduct);
        this.amqpAdmin.declareBinding(callProvider);
        this.amqpAdmin.declareBinding(callStore);
        this.amqpAdmin.declareBinding(callToken);
        this.amqpAdmin.declareBinding(callTransport);
        this.amqpAdmin.declareBinding(callUser);

    }
}
