package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    Long id;
    @ApiModelProperty(value = "This is the first name", required = true)
    String firstname;

    @ApiModelProperty(required = true)
    String lastname;

    @JsonProperty("customer_url")
    String customerUrl;

}
