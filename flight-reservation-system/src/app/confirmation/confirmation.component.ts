import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import * as saveAs from 'file-saver';
import { environment } from 'src/environments/environment';
import { Endpoints } from '../model/Endpoints';
import { ReservationService } from '../services/reservation.service';


@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {



  reservationResponse: any;
  downloadPdfUrl = `${environment.api}${Endpoints.downloadPdf}`;
  downloadFilename: any;
  constructor(private reservationService: ReservationService
    ) { }

  ngOnInit(): void {
    this.reservationResponse = this.reservationService.getReservationResponse();
    this.downloadFilename = `${this.downloadPdfUrl}/`+ this.reservationResponse.fileName;
  }

  downloadFile(): void {
    console.log('Request for downloading ...')
    this.reservationService
      .downloadTicket(this.reservationResponse.fileName)
      .subscribe(blob => saveAs(blob, this.reservationResponse.fileName));
  }

  // download() {
  //   console.log(this.reservationResponse);
  //   this.reservationService.downloadTicket(this.reservationResponse.fileName).subscribe((res: any) => {
  //     console.log();
  //   });
  // }

}



