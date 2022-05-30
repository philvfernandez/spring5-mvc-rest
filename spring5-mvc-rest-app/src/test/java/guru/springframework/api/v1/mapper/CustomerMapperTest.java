package guru.springframework.api.v1.mapper;

import guru.springframework.domain.Customer;
import guru.springframework.model.CustomerDTO;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static Long ID = 1l;
    public static String FIRSTNAME = "Phil";
    public static String LASTNAME = "Fernandez";
    public static String URL = "ATestURL";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        customer.setCustomerUrl(URL);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(Long.valueOf(ID), customer.getId());

    }
}
