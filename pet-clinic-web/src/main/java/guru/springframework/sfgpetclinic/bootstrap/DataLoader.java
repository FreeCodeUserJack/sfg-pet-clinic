package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    // change to DI autowiring
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog); // sets ID and returns it back, really need this?

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        owner1.setAddress("123 Sesame Street");
        owner1.setCity("Make Believe");
        owner1.setTelephone("1234567890");

        Pet johnsPet = new Pet();
        johnsPet.setPetType(savedDogPetType);
        johnsPet.setName("Fido");
        johnsPet.setBirthDate(LocalDate.now());
        johnsPet.setOwner(owner1);
        owner1.getPets().add(johnsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jane");
        owner2.setLastName("Fonda");
        owner2.setAddress("555 Baker's Street");
        owner2.setCity("Jonestown");
        owner2.setTelephone("5555555555");

        Pet janesPet = new Pet();
        janesPet.setPetType(savedCatPetType);
        janesPet.setName("Furball");
        janesPet.setBirthDate(LocalDate.now());
        janesPet.setOwner(owner2);
        owner2.getPets().add(janesPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Smith");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Jane");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
