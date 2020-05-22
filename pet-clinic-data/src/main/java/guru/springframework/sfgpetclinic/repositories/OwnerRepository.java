package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName); // not sure how this works

    List<Owner> findAllByLastNameLike(String lastName); // literally key words, Spring Data JPA can parse it
}
