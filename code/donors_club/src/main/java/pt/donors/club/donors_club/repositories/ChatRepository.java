package pt.donors.club.donors_club.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Chat;
import pt.donors.club.donors_club.models.views.ChatSimpleView;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
  String queryChatSimpleView = "SELECT c.chat_id as id, ad.ad_title as adTitle, ow.usr_name as user, "
      + "(SELECT MAX(msg_time) FROM messages WHERE msg_chat_id = c.chat_id) as updated " + "FROM chats c "
      + "INNER JOIN adposts ad ON c.chat_ad_id = ad.ad_id " + "INNER JOIN users u ON c.chat_usr_id = u.usr_id "
      + "INNER JOIN users ow ON ad.ad_owner_id = ow.usr_id " + "WHERE c.chat_usr_id = :id OR ad.ad_owner_id = :id";

  @Query(value = queryChatSimpleView, nativeQuery = true)
  Iterable<ChatSimpleView> findChatsByUser(@Param("id") int id);

  @Query(value = "SELECT * FROM chats WHERE chat_usr_id = :userId AND chat_ad_id = :adId", nativeQuery = true)
  Optional<Chat> findChatByAdPostAndUser(@Param("userId") int userId, @Param("adId") int adId);
}
