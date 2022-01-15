import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ColDef } from 'ag-grid-community';
import { Observable, of } from 'rxjs';
import { shareReplay } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Endpoints } from '../model/Endpoints';

@Component({
  selector: 'app-flight-booking',
  templateUrl: './flight-booking.component.html',
  styleUrls: ['./flight-booking.component.scss']
})
export class FlightBookingComponent implements OnInit {

  environment: any = environment;
  private findFlightsUrl = `${environment.api}${Endpoints.findFlights}`;


  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { }

  flightSearchForm: FormGroup = this.fb.group({
    departureCity: new FormControl('', Validators.required),
    arrivalCity: new FormControl('', Validators.required),
    departureDate: new FormControl('', Validators.required)
  });

  flights: Observable<any> = of(null);
  
  columnDefs: ColDef[] = [
    { field: '', checkboxSelection: true, width: 60 },
    { field: 'airLines' },
    { field: 'flightNo' },
    { field: 'departureCity' },
    { field: 'arrivalCity'},
    { field: 'departureTime'},
    { field: 'arrivalTime'}
];
selectedFlightId: any;

flightSelected({ data: { id } }: any) {
 this.selectedFlightId = id;
}
  
nextClicked() {
  this.router.navigate(['/passenger-details', this.selectedFlightId]);
}

  findFlights() {
    this.flights = this.http.get(this.findFlightsUrl, {params:  {...this.flightSearchForm.value}}).pipe(shareReplay());
  }

  ngOnInit(): void {
  }
}
