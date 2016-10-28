package com.mansha99.contactapp.services;
import com.mansha99.contactapp.models.Contact;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface ContactService extends JpaRepository<Contact, Long> {
  public Contact findByEmail(String email);

}