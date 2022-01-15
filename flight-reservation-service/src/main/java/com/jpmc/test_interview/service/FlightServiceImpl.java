package com.jpmc.test_interview.service;

import com.jpmc.test_interview.data.FlightRepository;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.dto.FlightDto;
import com.jpmc.test_interview.service.mapper.FlightMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
  //  private final DateUtil dateUtil;

    @Override
    public List<FlightDto> findFlights(String departureCity, String arrivalCity, String departureTime) {
        log.info("Finding the flights  from  " + departureCity +" To " + arrivalCity );
        List<Flight> listOfFlights = flightRepository
                .findAll()
                .stream()
                .filter(eachFlight -> eachFlight.getDepartureCity().equalsIgnoreCase(departureCity) &&
                        eachFlight.getArrivalCity().equalsIgnoreCase(arrivalCity) &&
                        this.convertTimestampToDate(eachFlight.getDepartureTime()).equals(departureTime))
                .collect(Collectors.toList());
        return flightMapper.ToFlightDtoList(listOfFlights);
    }

    public String convertTimestampToDate(Timestamp timestamp){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp.getTime()));
    }


}

