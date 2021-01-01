package pt.donors.club.donors_club.repositories;

import org.springframework.data.repository.CrudRepository;

import pt.donors.club.donors_club.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}
