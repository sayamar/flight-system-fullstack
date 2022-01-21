package com.jpmc.test_interview.data.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@Entity
@Table(name = "passengers")
@Getter
@Setter
@Builder( toBuilder = true )
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passengerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String email;
    private String phone;

}
