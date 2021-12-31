package guru.springframework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

    Long id;
    String firstname;
    String lastname;
    String customerUrl;
}
