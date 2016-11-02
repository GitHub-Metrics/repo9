package org.webonise.springboot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.webonise.springboot.entities.Message;
import org.webonise.springboot.entities.ResponceContent;
import org.webonise.springboot.services.TicTacToeCalculator;

@Controller
public class TicTacToeController {

    @Autowired
    @Qualifier("getTicTacToeCalculator")
    private TicTacToeCalculator ticTacToeCalculator;

    @MessageMapping("/tictactoe")
    @SendTo("/topic/nextmoves")
    public ResponceContent response(Message message) throws Exception {
        Thread.sleep(1000);
        String messageContent = message.getName();
        String messageSend = ticTacToeCalculator.getResponse(messageContent);
        return new ResponceContent(messageSend);
    }

    @MessageMapping("/clearBoard")
    public void clearBoard(Message message) {
        ticTacToeCalculator.fillBoardByDash();
    }

}