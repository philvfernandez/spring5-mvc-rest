package guru.springframework.controllers.v1;

import guru.springframework.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.model.CustomerDTO;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);
        customer1.setCustomerUrl(CUSTOMER_URL);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname(FIRSTNAME2);
        customer2.setLastname(LASTNAME2);
        customer2.setCustomerUrl(CUSTOMER_URL2);

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1,customer2));


        /* For Debugging response */
        /* ResultActions result = mockMvc.perform(get("/api/v1/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.customers", hasSize(2)));
        String response1 = result.andReturn().getResponse().getContentAsString();
        System.out.println("********************Customer Response:************************** " + response1); */

        mockMvc.perform(get("/api/v1/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
     }

     @Test
    public void getCustomerById() throws Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRSTNAME);
        customer1.setLastname(LASTNAME);
        customer1.setCustomerUrl(CUSTOMER_URL);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));
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

         when(customerService.createNewCustomer(any())).thenReturn(returnDTO);

         //when/then
         mockMvc.perform(post("/api/v1/customers/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                    .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
     }

     @Test
    public void testUpdateCustomer() throws Exception {

        //given
         CustomerDTO customer = new CustomerDTO();
         customer.setFirstname("Phil");
         customer.setLastname("Fernandez");

         CustomerDTO returnDTO = new CustomerDTO();
         returnDTO.setFirstname(customer.getFirstname());
         returnDTO.setLastname(customer.getLastname());
         returnDTO.setCustomerUrl("/api/v1/customers/1");

         when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

         //when/then
         mockMvc.perform(put("/api/v1/customers/1")
                 .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(asJsonString(customer)))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.firstname", equalTo("Phil")))
                 .andExpect(jsonPath("$.lastname", equalTo("Fernandez")))
                 .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
     }

     @Test
    public void testPatchCustomer() throws Exception {

        //given
         CustomerDTO customerDTO = new CustomerDTO();
         customerDTO.setFirstname("Phil");

         CustomerDTO returnDTO = new CustomerDTO();
         returnDTO.setFirstname(customerDTO.getFirstname());
         returnDTO.setLastname("Fernandez");
         returnDTO.setCustomerUrl("/api/v1/customers/1");

         when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

         mockMvc.perform(patch("/api/v1/customers/1")
                 .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(asJsonString(customerDTO)))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.firstname", equalTo("Phil")))
                 .andExpect(jsonPath("$.lastname", equalTo("Fernandez")))
                 .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
     }

     @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
     }

     @Test
    public void testNotFoundException() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

         mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
     }

}
