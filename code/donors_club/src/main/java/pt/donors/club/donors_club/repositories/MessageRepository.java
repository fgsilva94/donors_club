package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Message;
import pt.donors.club.donors_club.models.views.MessageSimpleView;

public interface MessageRepository extends CrudRepository<Message, Integer> {
  @Query(value = "SELECT m.msg_id as id, m.msg_text as text, m.msg_time as time, m.msg_sender_id as senderId, "
      + "u.usr_name as senderName " + "FROM messages m " + "INNER JOIN users u ON m.msg_sender_id = u.usr_id "
      + "WHERE msg_chat_id = :id", nativeQuery = true)
  Iterable<MessageSimpleView> findMessagesByChatId(@Param("id") int id);
}
