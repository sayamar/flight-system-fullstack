import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Endpoints } from '../model/Endpoints';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  environment: any = environment;
  private bookingFlightUrl = `${environment.api}${Endpoints.bookFlight}`;
  private downloadPdfUrl = `${environment.api}${Endpoints.downloadPdf}`;

  reservationResponse: any;

  constructor(private httpClient : HttpClient) { }

  getReservationResponse() {
    return {...this.reservationResponse};
  }

  setReservationResponse(reservationResponse: any) {
    this.reservationResponse = {...reservationResponse};
  }

  bookFlight(data: any){
    console.log('Booking flight.....>')
    return this.httpClient.post(this.bookingFlightUrl, data, httpOptions);
  }

  downloadTicket(file: string | undefined): Observable<Blob> {
    const pathUrlToDownload = `${this.downloadPdfUrl}/`+ file;
    return this.httpClient.get(pathUrlToDownload, {
      responseType: 'blob'
    });
  }
   
}
