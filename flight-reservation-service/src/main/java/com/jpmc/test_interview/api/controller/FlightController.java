package com.jpmc.test_interview.api.controller;

import com.jpmc.test_interview.dto.FlightDto;
import com.jpmc.test_interview.exception.DownloadTicketException;
import com.jpmc.test_interview.service.FileStorageService;
import com.jpmc.test_interview.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
public class FlightController {

    private final FlightService flightService;
    private final FileStorageService fileStorageService;

    @GetMapping(value = "/flights", produces = "application/json")
    public ResponseEntity<List<FlightDto>> findFlights(
            @RequestParam @NotNull String departureCity,
            @RequestParam @NotNull String arrivalCity,
            @RequestParam @NotNull String departureDate) {
        log.info("Request for finding flights ...D  " + departureCity + " A " + arrivalCity + " Date " + departureDate);
        return new ResponseEntity<>(this.flightService.findFlights(departureCity, arrivalCity, departureDate), HttpStatus.OK);

    }

    @ExceptionHandler(DownloadTicketException.class)
    @GetMapping(value = "/downloadFile/{fileName:.+}", produces = "application/octet-stream")
    public ResponseEntity<Resource> downLoadItinerary(
            @PathVariable @NotNull String fileName) {
        log.info("Downloading the file...{} ", fileName);
        Resource resource = this.fileStorageService.getFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment: filename=\"" + resource.getFilename() + "\"").body(resource);
    }


}
