package com.jpmc.test_interview.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "flights")
@Setter
@Getter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "flight_no")
    private String flightNo;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;
    @Column(name = "departure_time")
    private Timestamp departureTime;
    @Column(name = "arrival_time")
    private Timestamp arrivalTime;
    @Column(name = "airline")
    private String airLine;

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNo='" + flightNo + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", airLine='" + airLine + '\'' +
                '}';
    }
}
