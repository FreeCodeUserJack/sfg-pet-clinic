package sfgpetclinic;

import guru.springframework.sfgpetclinic.SfgPetClinicApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// registers test extention with JUnit 5 test
//@ExtendWith(SpringExtension.class) // no explicit need as @SpringBootTest already is annotated with it
// classes attribute specifies the annotated classes to use for loading an ApplicationContext
@SpringBootTest(classes = SfgPetClinicApplication.class)
class SfgPetClinicApplicationTests {

    @Test
    void contextLoads() {
    }

}
