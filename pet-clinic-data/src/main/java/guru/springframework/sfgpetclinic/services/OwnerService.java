package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

// CrudService is generic interface so to extend we need to provide the specific type
public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
