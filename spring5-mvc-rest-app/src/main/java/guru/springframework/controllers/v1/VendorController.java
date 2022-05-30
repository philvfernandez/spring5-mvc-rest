package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;
import guru.springframework.services.VendorService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Api(description = "This is my Vendor Controller class.")
@Controller
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // @ApiOperation(value = "This will get a list of all vendors", notes = "Note number 1 for the get all vendors endpoint.")
    @GetMapping
    public ResponseEntity<VendorListDTO> getAllVendors() {
        return new ResponseEntity<VendorListDTO>(
                new VendorListDTO(vendorService.getAllVendors()), HttpStatus.OK);
    }

    //@ApiOperation(value = "This will get a vemdor given an ID.", notes = "Don't forget to include the ID in the endpoint.")
    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id) {
        return new ResponseEntity<VendorDTO>(
                vendorService.getVendorById(id), HttpStatus.OK);
    }

    //@ApiOperation(value = "This will create a new vendor.", notes = "Create JSON block that includes a new vendor.")
    @PostMapping
    public ResponseEntity<VendorDTO> createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(vendorService.createNewVendor(vendorDTO),
                HttpStatus.OK);
    }

    //@ApiOperation(value = "This will delete a vendor given an ID", notes = "Don't forget to include the ID in the endpoint.")
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //@ApiOperation(value = "This will patch a vendor request", notes = "Will add more information later.")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VendorDTO> patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(vendorService.patchVendor(id, vendorDTO),
        HttpStatus.OK);
        //return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    //@ApiOperation(value = "This will update a vendor given an ID and a JSON body.", notes = "Include ID and JSON body element.")
    @PutMapping({"/{id}"})
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(vendorService.saveVendorByDTO(id, vendorDTO),
                HttpStatus.OK);
    }
}
