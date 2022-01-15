package com.jpmc.test_interview.data.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Entity
@Table(name = "reservations")
@Getter
@Setter
@Builder( toBuilder = true )
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long bookingConfirmationNumber;
    private String status;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="passengerId")
    private Passenger passenger;
    private String flightNo;
    private LocalDateTime bookingDateTime;

}
