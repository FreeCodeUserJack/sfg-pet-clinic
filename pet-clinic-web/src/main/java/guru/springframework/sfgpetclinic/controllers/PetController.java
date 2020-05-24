package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEW_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types") // for select dropdown boxes
    public Collection<PetType> populatePetType() {
        return petTypeService.findAll(); // get all petTypes and bind to "types" attribute in model
    }

    @ModelAttribute("owner") // owner bound to model
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new") // can also use ModelMap model and then model.put("pet", pet)
    public String initCreationPetForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        populatePetType(); // explicitly call method to add petTypes to model else error in selectField fragment
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationPetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }
//        pet.setOwner(owner); // not need? or redundant code?
        petService.save(pet); // i'm guessing here that saving pet will also update owner?
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdatePetForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));

        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }
        owner.getPets().add(pet);
        petService.save(pet); // saving pet will also update owner?

        return "redirect:/owners/" + owner.getId();
    }
}
