package ru.dinerik.tacocloud;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jms;
    private Destination orderQueue;
    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms ,Destination orderQueue) {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }
/*    @Override
    public void sendOrder(TacoOrder order) {
        jms.send(
                orderQueue,
                session -> session.createObjectMessage(order)
        );
    }*/

    @Override
    public void sendOrder(TacoOrder order) {
        jms.convertAndSend("tacocloud.order.queue", order,
                this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
