package com.jpmc.test_interview.service;

import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.data.FlightRepository;
import com.jpmc.test_interview.dto.FlightDto;
import com.jpmc.test_interview.service.mapper.FlightMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

    @InjectMocks
    private FlightServiceImpl flightServiceImpl;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;


    @Test
    public void testShouldSuccessfullyReturnsFlightsWithGivenParameters() {

        // Given
        String dCity = "Singapore";
        String aCity = "London";
        String departureDate = FlightTestDataProvider.getTestDate();
        when(flightRepository.findAll()).thenReturn(FlightTestDataProvider.getFlightList());
        when(flightMapper.ToFlightDtoList(any())).thenReturn(FlightTestDataProvider.getFlightDtoList());

        //When
        List<FlightDto> flightDtoList = flightServiceImpl.findFlights(dCity, aCity, departureDate);
        FlightDto flightDto = flightDtoList.get(0);
        //Then
        assertNotNull(flightDtoList);
        assertEquals(flightDto.getFlightNo(), "SQ100");
        assertEquals(flightDto.getId(), 2L);
        assertEquals(flightDto.getArrivalCity(), "London");
        assertEquals(flightDto.getDepartureCity(), "Singapore");
        assertEquals(flightDto.getAirLines(), "SG Airlines");
        assertNotNull(flightDto.getArrivalTime());
        assertNotNull(flightDto.getDepartureTime());

        // Verification
        verify(flightRepository,times(1)).findAll();
        verify(flightMapper,times(1)).ToFlightDtoList(any());

    }

    @Test
    public void testShouldNotReturnFlightsWithGivenParameters() {

        // Given
        String dCity = "China";
        String aCity = "Sweden";
        String departureDate = FlightTestDataProvider.getTestDate();
        when(flightRepository.findAll()).thenReturn(FlightTestDataProvider.returnsEmptyFlightList());
        when(flightMapper.ToFlightDtoList(any())).thenReturn(FlightTestDataProvider.returnsEmptyFlightDtoList());

        //When
        List<FlightDto> flightDtoList = flightServiceImpl.findFlights(dCity, aCity, departureDate);

        //Then
        assertNotNull(flightDtoList);
        assertEquals(flightDtoList.size(), 0);

        // Verification
        verify(flightRepository,times(1)).findAll();
        verify(flightMapper,times(1)).ToFlightDtoList(any());

    }

}
