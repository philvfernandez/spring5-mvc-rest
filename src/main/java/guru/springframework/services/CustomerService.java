package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customer);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
