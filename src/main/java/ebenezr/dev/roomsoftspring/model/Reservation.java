package ebenezr.dev.roomsoftspring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String code;
    private ReservationStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staffId", referencedColumnName = "id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomtypeId", referencedColumnName = "id")
    private RoomType roomtype;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room room;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guestId", referencedColumnName = "id")
    private Guest guest;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mealplanId", referencedColumnName = "id")
    private MealPlan mealplan;
    private int maxChild;
    private int maxAdult;
    private String taxName;
    private String note;
    private float roomTotal;
    private float serviceTotal;
    private float netTotal;
    private float discount;
    private float taxTotal;
    private float subTotal;
    private float paid;
    private float balance;
    private paidStatus paidStatus;

    private LocalDateTime bookTime;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


    enum ReservationStatus {
        NEW,
        CONFIRMED,
        CHECKIN,
        CANCELED,
        UNCONFIRMED,
        CHECKOUT
    }

    enum paidStatus {
        PAID,
        UNPAID,
        PENDING,
        PARTIAL
    }
}
