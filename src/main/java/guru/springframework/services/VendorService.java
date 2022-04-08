package guru.springframework.services;


import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long vendorId);
}
