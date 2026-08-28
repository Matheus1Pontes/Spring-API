package SpringDB.Project.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SpringDB.Project.Repository.AddressRepository;
import SpringDB.Project.Repository.AppUserRepository;
import SpringDB.Project.model.Address;
import SpringDB.Project.model.AppUser;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AppUserRepository userRepository;

    public AddressService(AddressRepository addressRepository, AppUserRepository userRepository) {
        this.addressRepository =addressRepository;
        this.userRepository = userRepository;
    }

    public Address addAddressToCustomer(Integer customerId, Address address) {
        AppUser customer = userRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("That customer does not exist!"));

        address.setCustomer(customer);

        return addressRepository.save(address);
    }

    public List<Address> getAddressByCustomerId(Integer customerId) {
        return addressRepository.findByCustomer_Id(customerId);
    }

    public Address updateAddress(Integer addressId, Address updatedAddress) {
        if (addressRepository.existsById(addressId)) {
            updatedAddress.setId(addressId);
            return addressRepository.save(updatedAddress);
        } else {
            throw new NoSuchElementException("This address does not exist in our database!");
        }
    }

    public void deleteAddress(Integer addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new NoSuchElementException("This address does not exist in our database!");
        }
    }
}
