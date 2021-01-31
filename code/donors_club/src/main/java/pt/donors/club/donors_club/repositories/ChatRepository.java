package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Chat;
import pt.donors.club.donors_club.models.views.ChatSimpleView;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
  @Query(value = "SELECT c.chat_id as id, ad.ad_title as adTitle, ow.usr_name as adOwner " + "from chats c "
      + "INNER JOIN users u ON c.chat_usr_id = u.usr_id " + "INNER JOIN adposts ad ON c.chat_ad_id = ad.ad_id "
      + "INNER JOIN users ow ON ad.ad_owner_id = ow.usr_id "
      + "WHERE c.chat_usr_id = :id OR ad.ad_owner_id = :id", nativeQuery = true)
  Iterable<ChatSimpleView> findChatsByUser(@Param("id") int id);
}
