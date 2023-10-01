package ebenezr.dev.roomsoftspring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String code;
    private String description;
    private int maxOccupants;
    private int maxChild;
    private int maxAdult;
    private boolean availabilityStatus;
    private boolean vacant;
    private float rate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomtypeId", referencedColumnName = "id")
    private RoomType roomtype;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "floorId", referencedColumnName = "id")
    private Floor floor;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
