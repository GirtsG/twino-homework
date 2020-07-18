package eu.twino.homework.domain.repository;

import eu.twino.homework.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPersonalId(String personalId);
}
