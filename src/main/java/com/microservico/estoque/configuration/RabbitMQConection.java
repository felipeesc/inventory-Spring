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
    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange exchange){
       return new Binding(fila.getName(), Binding.DestinationType.QUEUE, exchange.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitmqConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitmqConstantes.FILA_PRECO);

        DirectExchange trocaDeFila = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, trocaDeFila);
        Binding ligacaoPreco = this.relacionamento(filaPreco, trocaDeFila);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(trocaDeFila);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
