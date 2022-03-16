package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    Long id;
    String firstname;
    String lastname;

    @JsonProperty("customer_url")
    String customerUrl;

}
