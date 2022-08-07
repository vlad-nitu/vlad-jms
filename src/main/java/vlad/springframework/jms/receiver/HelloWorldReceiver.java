package vlad.springframework.jms.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vlad.springframework.jms.config.JmsConfig;
import vlad.springframework.jms.model.HelloWorldMessage;

@Component
public class HelloWorldReceiver {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listenMessage(
            @Payload HelloWorldMessage helloWorldMessage,
            @Headers MessageHeaders headers,
            Message message
    ) {
        System.out.println("I got a Message!");

        System.out.println(helloWorldMessage);
    }
}
