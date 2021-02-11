package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Message;
import pt.donors.club.donors_club.models.views.MessageSimpleView;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Query(value = "SELECT msg_id as id, msg_text as text, msg_time as time, msg_sender_id as senderId "
                    + "FROM messages "
                    + "WHERE msg_chat_id = :id", nativeQuery = true)
    Iterable<MessageSimpleView> findMessagesByChatId(@Param("id") int id);
}
