package pt.donors.club.donors_club.controllers;

import java.util.Optional;
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

import pt.donors.club.donors_club.models.AdPost;
import pt.donors.club.donors_club.models.Chat;
import pt.donors.club.donors_club.models.exceptions.NotAcceptableException;
import pt.donors.club.donors_club.models.exceptions.NotFoundException;
import pt.donors.club.donors_club.models.views.ChatSimpleView;
import pt.donors.club.donors_club.models.views.MessageSimpleView;
import pt.donors.club.donors_club.repositories.AdPostRepository;
import pt.donors.club.donors_club.repositories.ChatRepository;
import pt.donors.club.donors_club.repositories.MessageRepository;

@RestController
@RequestMapping(path = "/api/chats")
public class ChatController {
    private Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private AdPostRepository adRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public int initChat(@RequestBody Chat chat) {
        logger.info("Trying to initialize new chat");

        int adId = chat.getAd().getId();
        int userId = chat.getUser().getId();

        Optional<ChatSimpleView> _chat = chatRepository.findChatByAdPostAndUser(adId, userId);

        if (_chat.isEmpty()) {
            Optional<AdPost> _ad = adRepository.findById(adId);

            if (!_ad.isEmpty()) {
                if (_ad.get().getOwner().getId() != userId) {
                    return chatRepository.save(chat).getId();
                } else {
                    throw new NotAcceptableException("(" + adId + ", " + userId + ")", "Chat", "(adId, userId)");
                }
            } else {
                throw new NotFoundException("" + adId, "AdPost", "adId");
            }
        } else {
            return _chat.get().getId();
        }
    }

    @GetMapping(path = "/{chatId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<MessageSimpleView> getMessages(@PathVariable int chatId) {
        logger.info("Sending all messages by chat id " + chatId);

        return messageRepository.findMessagesByChatId(chatId);
    }
}
