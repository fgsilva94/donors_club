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
import pt.donors.club.donors_club.models.Chat;
import pt.donors.club.donors_club.models.exceptions.NotAcceptableException;
import pt.donors.club.donors_club.models.exceptions.NotFoundException;
import pt.donors.club.donors_club.models.views.AdPostSimpleView;
import pt.donors.club.donors_club.models.views.ChatSimpleView;
import pt.donors.club.donors_club.repositories.AdPostRepository;
import pt.donors.club.donors_club.repositories.ChatRepository;

@RestController
@RequestMapping(path = "/api/chats")
public class ChatController {
    private Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private AdPostRepository adRepository;

    @GetMapping(path = "/{userId}/{adId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatSimpleView getChat(@PathVariable int userId, @PathVariable int adId) {
        logger.info("Sending chat simple view with user id "+userId+" and ad id "+adId);

        Optional<ChatSimpleView> _chat = chatRepository.findChatByAdPostAndUser(userId, adId);

        if (_chat.isEmpty())
            throw new NotFoundException("(" + userId + ", " + adId + ")", "Chat", "userId, adId");
        else
            return _chat.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public int initChat(@RequestBody Chat chat) {
        logger.info("Try initialize new chat with id " + chat.getId());

        int adId = chat.getUser().getId();
        int userId = chat.getUser().getId();
        
        Optional<ChatSimpleView> _chat = chatRepository.findChatByAdPostAndUser(adId, userId);

        if (_chat.isEmpty()) {
            Optional<AdPostSimpleView> _ad = adRepository.findSimpleViewByIdAndByOwner(userId, adId);

            if (_ad.isEmpty())
                return chatRepository.save(chat).getId();
            else
                throw new NotAcceptableException("("+adId+", "+userId+")", "Chat", "(adId, userId)");
        } else {
            return _chat.get().getId();
        }
    }
}
