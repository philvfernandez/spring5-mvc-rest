package guru.springframework.services;



import guru.springframework.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customer);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
