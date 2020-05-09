package guru.springframework.sfgpetclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
// Serializable - convert state of an obj into a byte stream
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long is better than long, recommended by Hibernate b/c long can't be null

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
