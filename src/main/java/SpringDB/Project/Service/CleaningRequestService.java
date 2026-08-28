package SpringDB.Project.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringDB.Project.Repository.AddressRepository;
import SpringDB.Project.Repository.AppUserRepository;
import SpringDB.Project.Repository.CleaningRequestRepository;
import SpringDB.Project.model.Address;
import SpringDB.Project.model.AppUser;
import SpringDB.Project.model.CleaningRequest;
import SpringDB.Project.model.enums.RequestStatus;

@Service
public class CleaningRequestService {
    @Autowired
    private CleaningRequestRepository requestRepository;
    @Autowired
    private final AppUserRepository userRepository;
    @Autowired
    private final AddressRepository addressRepository;

    public CleaningRequestService(CleaningRequestRepository requestRepository, AppUserRepository userRepository, AddressRepository addressRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public CleaningRequest createCleaningRequest(Integer customerId, Integer addressId, CleaningRequest request) {
        AppUser customer = userRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer does not exist!"));

        Address address = addressRepository.findById(addressId).orElseThrow(() -> new NoSuchElementException("Address does not exist!"));

        if (!address.getCustomer().getId().equals(customerId)) {
            throw new IllegalArgumentException("This address does not belong to this customer!");
        }

        request.setCustomer(customer);
        request.setAddress(address);

        return requestRepository.save(request);
    }

    public List<CleaningRequest> getCleaningRequestByUser(Integer customerId) {
        return requestRepository.findByCustomer_Id(customerId);
    }

    // admin use only 
    public List<CleaningRequest> getAllCleaningRequests() {
        return requestRepository.findAll();
    }

    public CleaningRequest updateRequestStatus(Integer requestId, RequestStatus status) {
        CleaningRequest request = requestRepository.findById(requestId).orElseThrow(() -> new NoSuchElementException("This request does not exist in our database!"));

        request.setStatus(status);

        return requestRepository.save(request);
    }

    public CleaningRequest cancelCleaningRequest(Integer requestId) {
        return updateRequestStatus(requestId, RequestStatus.CANCELLED);
    }
}
