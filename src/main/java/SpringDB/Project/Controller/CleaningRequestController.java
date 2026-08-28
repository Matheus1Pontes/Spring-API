package SpringDB.Project.Controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import SpringDB.Project.Service.CleaningRequestService;
import SpringDB.Project.model.CleaningRequest;
import SpringDB.Project.model.enums.RequestStatus;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cleaning-requests")
public class CleaningRequestController {

     private final CleaningRequestService requestService;

    public CleaningRequestController(CleaningRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CleaningRequest>> findByCustomerId(@PathVariable("customerId") Integer customerId) {
        List<CleaningRequest> requests = requestService.getCleaningRequestByUser(customerId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/customer/{customerId}/address/{addressId}")
    public ResponseEntity<CleaningRequest> create(@PathVariable("customerId") Integer customerId, @PathVariable("addressId") Integer addressId, @RequestBody CleaningRequest request) {
        CleaningRequest createdRequest = requestService.createCleaningRequest(customerId, addressId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/cleaning-requests/{id}").buildAndExpand(createdRequest.getId()).toUri();
        return ResponseEntity.created(location).body(createdRequest);
    }

    @GetMapping
    public ResponseEntity<List<CleaningRequest>> findAll() {
        List<CleaningRequest> requests = requestService.getAllCleaningRequests();
        return ResponseEntity.ok(requests);
    }

    @PatchMapping("/{requestId}/status/{status}")
    public ResponseEntity<CleaningRequest> updateStatus(@PathVariable("requestId") Integer requestId, @PathVariable("status") RequestStatus status) {
        CleaningRequest request = requestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(request);
    }

    @PatchMapping("/{requestId}/cancel")
    public ResponseEntity<CleaningRequest> cancel(@PathVariable("requestId") Integer requestId) {
        CleaningRequest request = requestService.cancelCleaningRequest(requestId);
        return ResponseEntity.ok(request);
    }

}
