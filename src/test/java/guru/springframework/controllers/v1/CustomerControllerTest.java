package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;



public class CustomerControllerTest extends AbstractRestControllerTest{

    public static final long ID = 1l;
    public static final String FIRSTNAME = "Phil";
    public static final String LASTNAME = "Fernandez";
    public static final String CUSTOMER_URL = "Url1";
    public static final long ID2 = 2l;
    public static final String FIRSTNAME2 = "Tawnee";
    public static final String LASTNAME2 = "Fernandez";
    public static final String CUSTOMER_URL2 = "Url2";
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);
        customer1.setCustomerUrl(CUSTOMER_URL);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(ID2);
        customer2.setFirstname(FIRSTNAME2);
        customer2.setLastname(LASTNAME2);
        customer2.setCustomerUrl(CUSTOMER_URL2);

        List<CustomerDTO> customers = Arrays.asList(customer1,customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
     }

     @Test
    public void getCustomerById() throws Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);
        customer1.setCustomerUrl(CUSTOMER_URL);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
     }

     @Test
    public void createNewCustomer() throws Exception {
        //given
         CustomerDTO customer = new CustomerDTO();
         customer.setFirstname("Fred");
         customer.setLastname("Flintstone");

         CustomerDTO returnDTO = new CustomerDTO();
         returnDTO.setFirstname(customer.getFirstname());
         returnDTO.setLastname(customer.getLastname());
         returnDTO.setCustomerUrl("/api/v1/customers/1");

         when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

         //when/then
         mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                    .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
     }

}
