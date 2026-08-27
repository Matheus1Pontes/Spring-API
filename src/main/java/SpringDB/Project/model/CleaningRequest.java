package SpringDB.Project.model;

import java.time.LocalDate;
import SpringDB.Project.model.enums.Frequency;
import SpringDB.Project.model.enums.RequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cleaningRequest")
public class CleaningRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cleaningRequest")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private AppUser customer;
    
    @ManyToOne
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Frequency frequency;
    
    @Column(nullable = false)
    private int numberOfRooms;

    @Column(nullable = false)
    private int numberOfBathrooms;

    @Column(length = 200, nullable = false)
    private String customerMessage;

    @Column(nullable = false)
    private LocalDate preferredDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;
}
