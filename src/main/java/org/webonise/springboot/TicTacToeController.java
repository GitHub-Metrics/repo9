package org.webonise.springboot;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TicTacToeController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponceContent response(Message message) throws Exception {
        Thread.sleep(1000);
        String messageContent = message.getName();
        String messageSend = TicTacToeCalculator.getResponse(messageContent);
        return new ResponceContent("Hello, " + messageSend + "!");
    }

}