package com.jpmc.test_interview.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.config.FlightConfig;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.data.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

@Component
@Slf4j
@AllArgsConstructor
public class PDFGenerator {

    private final FlightConfig flightConfig;

    @SneakyThrows
    public String generatePdf(FlightReservationRequest reservationRequest, Reservation reservation, Flight flight)  {

        String filePath = flightConfig.getPath() + "/" + reservation.getBookingConfirmationNumber() + "_"
                + reservationRequest.getLastName() + ".pdf";

        this.generateItinerary(reservationRequest, filePath, flight, reservation);
        log.info("Generating the itinerary for {} ", filePath);
        return new File(filePath).getName();
    }

    private void generateItinerary(FlightReservationRequest reservation, String filePath, Flight flight, Reservation reser) throws Exception {
            Document pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(filePath));
            pdfDocument.open();
            pdfDocument.add(tableFormation(reservation, flight, reser));
            pdfDocument.close();
    }

    private PdfPTable tableFormation(FlightReservationRequest reservation, Flight flight, Reservation reser) {
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Flight Itinerary"));
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Flight Details"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("Departure city");
        table.addCell(flight.getDepartureCity());
        table.addCell("Arrival city");
        table.addCell(flight.getArrivalCity());
        table.addCell("Flight number ");
        table.addCell(flight.getFlightNo());
        table.addCell("Departure Time");
        table.addCell(flight.getDepartureTime().toString());
        table.addCell("Arrival Time");
        table.addCell(flight.getArrivalTime().toString());


        cell = new PdfPCell(new Phrase("Passenger Details"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("First Name");
        table.addCell(reservation.getFirstName());
        table.addCell("Last Name");
        table.addCell(reservation.getLastName());
        table.addCell("Email");
        table.addCell(reservation.getEmail());
        table.addCell("Phone");
        table.addCell(reservation.getPhone());

        cell = new PdfPCell(new Phrase("Booking status"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("Booking confirmation number");
        table.addCell(reser.getBookingConfirmationNumber().toString());
        table.addCell("Booking status");
        table.addCell(reser.getStatus());

        return table;

    }
}
