package fm.ua.bacs.testtaskrestservice.repository;

import fm.ua.bacs.testtaskrestservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
