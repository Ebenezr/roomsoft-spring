package ebenezr.dev.roomsoftspring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "users")


/*

model User {
  id                   Int           @id @default(autoincrement())
  -role                 Role          @default(USER)
  -name                 String
  -email                String        @unique
  -password             String?
  -phone                String
  Department           Department?   @relation(fields: [departmentId], references: [id], onDelete: Restrict, onUpdate: Cascade)
  departmentId         Int?
  -activeStatus         Boolean       @default(true)
  -createdAt            DateTime      @default(now())
  -updatedAt            DateTime      @updatedAt
  Reservation          Reservation[]
  passwordResetToken   String?       @unique
  passwordResetExpires DateTime?
  -superuser            Boolean       @default(false)

  @@index([departmentId])
}

 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    private String email;
    private String password;
    private String phone;
    private Boolean activeStatus;
    private Role role;
    private Boolean superuser;


    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;



    enum Role {
        USER,
        ADMIN,
        SUPERUSER
    }

}
