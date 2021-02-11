package pt.donors.club.donors_club.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.Message;
import pt.donors.club.donors_club.models.results.SimpleResult;
import pt.donors.club.donors_club.models.views.MessageSimpleView;
import pt.donors.club.donors_club.repositories.MessageRepository;

@RestController
@RequestMapping(path = "/api/messages")
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(path = "/{chatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<MessageSimpleView> getMessages(@PathVariable int chatId) {
        logger.info("Sending all messages by chat id " + chatId);

        return messageRepository.findMessagesByChatId(chatId);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleResult sendMessage(@RequestBody Message message) {
        logger.info("Adding new message with id " + message.getId());
        
        messageRepository.save(message);

        return new SimpleResult("Sending message", message);
    }
}
