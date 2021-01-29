package pt.donors.club.donors_club.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
  @Query(value = "SELECT * FROM chats WHERE chat_usr_id = :id", nativeQuery = true)
  Iterable<Chat> findChatsByUser(@Param("id") int id);

  @Query(value = "SELECT * FROM chats WHERE chat_usr_id = :userId AND chat_ad_id = :adId", nativeQuery = true)
  Optional<Chat> findChatByAdPostAndUser(@Param("userId") int userId, @Param("adId") int adId);
}
