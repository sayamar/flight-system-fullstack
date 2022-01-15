package com.jpmc.test_interview.api.controller;

import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.dto.FlightDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    FlightController flightController;


    @Test
    public void testShouldFindAndReturnsFlights() {

        // Given
        String findFLightsUrl = "http://localhost:" + port + "/api/v1/flights";
        when(flightController.findFlights(any(),any(),any())).thenReturn(FlightTestDataProvider.getResponseEntityWithFlightDtoList());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(findFLightsUrl)
                .queryParam("departureCity", "Singapore")
                .queryParam("arrivalCity", "London")
                .queryParam("departureDate", "202-03-17");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        //When
        ResponseEntity<List<FlightDto>> flightResponseEntity =
                this.restTemplate.exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity, new ParameterizedTypeReference<List<FlightDto>>() {}
                );
        //Then
        assertThat(flightResponseEntity).isNotNull();
        assertThat(flightResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(flightResponseEntity.getBody().get(0).getFlightNo()).isEqualTo("SQ100");

        //Verification
        verify(flightController,times(1)).findFlights(any(),any(),any());

    }

    @Test
    public void testShouldNotReturnFlightsForGivenParameters() {

        // Given
        String findFLightsUrl = "http://localhost:" + port + "/api/v1/flights";
        when(flightController.findFlights(any(),any(),any())).thenReturn(FlightTestDataProvider.getResponseEntityWithNoFlights());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(findFLightsUrl)
                .queryParam("departureCity", "Singapore")
                .queryParam("arrivalCity", "Macau")
                .queryParam("departureDate", "202-03-17");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        //When
        ResponseEntity<List<FlightDto>> flightResponseEntity =
                this.restTemplate.exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity, new ParameterizedTypeReference<List<FlightDto>>() {}
                );
        //Then
        assertThat(flightResponseEntity).isNotNull();
        assertThat(flightResponseEntity.getBody().size()).isEqualTo(0);
        assertThat(flightResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        //Verification
        verify(flightController,times(1)).findFlights(any(),any(),any());

    }



}
