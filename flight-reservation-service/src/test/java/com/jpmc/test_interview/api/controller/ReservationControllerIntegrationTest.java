package com.jpmc.test_interview.api.controller;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.FlightReservationResponse;
import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.util.FlightConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ReservationController reservationController;

    @Test
    public void test_ShouldSuccessfullyBookTheFlightAndReturnsConfirmation() {

        // Given
        String bookFlightUrl = "http://localhost:" + port + "/api/v1/bookFlight";

        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        when(reservationController.bookFlight(any())).thenReturn(FlightTestDataProvider.getFlightReservationResponse());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<FlightReservationResponse> flightResponseEntity = this.restTemplate.postForEntity(
                bookFlightUrl, flightReservationRequest, FlightReservationResponse.class);

        assertThat(flightResponseEntity).isNotNull();
        assertThat(flightResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(flightResponseEntity.getBody().getBookingConfirmationNumber()).isEqualTo("10");
        assertThat(flightResponseEntity.getBody().getStatus()).isEqualTo(FlightConstants.BOOKING_CONFIRMED);
        assertThat(flightResponseEntity.getBody().getFileName()).isEqualTo("C:/Users/Maruthi/data");

    }

    @Test
    public void test_ShouldReturnBadRequest404IfFlightRequestDataIsNull() {

        // Given
        String bookFlightUrl = "http://localhost:" + port + "/api/v1/bookFlight";

        FlightReservationRequest flightReservationRequest = null;
        when(reservationController.bookFlight(any())).thenReturn(FlightTestDataProvider.getFlightReservationResponse());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FlightReservationRequest> entity = new  HttpEntity<FlightReservationRequest>(flightReservationRequest,headers);

        ResponseEntity<FlightReservationResponse> flightResponseEntity = this.restTemplate.postForEntity(
                bookFlightUrl, entity, FlightReservationResponse.class);

        assertThat(flightResponseEntity).isNotNull();
        assertThat(flightResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);

    }


}
