package pt.donors.club.donors_club.repositories;

import org.springframework.data.repository.CrudRepository;

import pt.donors.club.donors_club.models.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
