package vlad.springframework.jms.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vlad.springframework.jms.config.JmsConfig;
import vlad.springframework.jms.model.HelloWorldMessage;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloWorldReceiver {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listenMessage(
            @Payload HelloWorldMessage helloWorldMessage,
            @Headers MessageHeaders headers,
            Message message
    ) {
//        System.out.println("I got a Message!");

//        System.out.println(helloWorldMessage);
    }
    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(
            @Payload HelloWorldMessage helloWorldMessage,
            @Headers MessageHeaders headers,
            Message message
    ) throws JMSException {
        HelloWorldMessage payloadMessage = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
    }
}
