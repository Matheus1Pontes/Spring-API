package SpringDB.Project.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SpringDB.Project.Service.AddressService;
import SpringDB.Project.model.Address;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/addresses")
public class AddressController {

     private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Address>> findById(@PathVariable("customerId") Integer customerId) {
        List<Address> addresses = addressService.getAddressByCustomerId(customerId);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Address> create(@PathVariable("customerId") Integer customerId, @RequestBody Address address) {
        Address createdAddress = addressService.addAddressToCustomer(customerId, address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/addresses/{id}").buildAndExpand(createdAddress.getId()).toUri();
        return ResponseEntity.created(location).body(createdAddress);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> update(@PathVariable("addressId") Integer addressId, @RequestBody Address updatedAddress) {
        Address address = addressService.updateAddress(addressId, updatedAddress);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(@PathVariable("addressId") Integer addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}
