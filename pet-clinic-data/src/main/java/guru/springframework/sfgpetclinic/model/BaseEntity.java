package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

// Serializable - convert state of an obj into a byte stream
public class BaseEntity implements Serializable {
    private Long id; // Long is better than long, recommended by Hibernate b/c long can't be null

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
