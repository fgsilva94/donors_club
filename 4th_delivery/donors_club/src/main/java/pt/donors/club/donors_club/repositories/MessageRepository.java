package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
  @Query(value = "SELECT * FROM messages WHERE msg_chat_id = :id", nativeQuery = true)
  Iterable<Message> findMessagesByChatId(@Param("id") int id);
}
