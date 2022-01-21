package com.jpmc.test_interview.util;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.config.FlightConfig;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.data.entity.Reservation;
import com.jpmc.test_interview.exception.FlightNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PDFGeneratorTest {

    @InjectMocks
    private PDFGenerator pdfGenerator;

    @Mock
    private FlightConfig flightConfig;

    @Test
    public void test_ShouldGenerateThePDFAndReturnsFilePath() {

        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        Reservation reservation = FlightTestDataProvider.getReservationDetails();
        Flight flight = FlightTestDataProvider.getFlight();
        when(flightConfig.getPath()).thenReturn("C:/Users/Maruthi/Data");

        // When
        String path = pdfGenerator.generatePdf(flightReservationRequest, reservation, flight);

        // Then
        assertNotNull(path);
        assertEquals("200_Sankar.pdf", path);
        // Verification
        verify(flightConfig, times(1)).getPath();
    }

    @Test
    public void test_ShouldThrowFileNotFoundExceptionIfPathIsWrong() {

        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        Reservation reservation = FlightTestDataProvider.getReservationDetails();
        Flight flight = FlightTestDataProvider.getFlight();
        when(flightConfig.getPath()).thenReturn(null);

        //when
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            pdfGenerator.generatePdf(flightReservationRequest, reservation, flight);
        });

        // Verification
        verify(flightConfig, times(1)).getPath();

    }


}
