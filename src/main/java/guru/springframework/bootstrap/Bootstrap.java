package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data loaded = " + categoryRepository.count());

        Customer customer1 = new Customer();
        customer1.setFirstname("Phil");
        customer1.setLastname("Fernandez");

        Customer customer2 = new Customer();
        customer2.setFirstname("Isabella");
        customer2.setLastname("Fernandez");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Kratos");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Comcast");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);

        System.out.println("Vendor Data loaded = " + vendorRepository.count());
        System.out.println("Vendor 1: " + vendorRepository.findById(1L));

    }
}
