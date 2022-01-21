import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PassengerService } from '../services/passenger.service';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {

  cardDetailsForm : FormGroup = this.fb.group({
    cardHolderName : new FormControl('', Validators.required),
    cardNumber : new FormControl('', Validators.required),
    expiryDate : new FormControl('', Validators.required)
  });

  flightReservationResponse : any;

  constructor(private fb:FormBuilder, 
    private passengerService: PassengerService, 
    private router: Router, 
    private reservationService: ReservationService,
    private location: Location) { }

  submitCardDetails() {
    const passengerDetails = this.passengerService.getPassengerDetails();
    const data = {
      ...passengerDetails,
      ...this.cardDetailsForm.value
    };
    this.reservationService.bookFlight(data).subscribe((res: any) => {
      if ( res != null ) {
        this.reservationService.setReservationResponse(res);
        this.router.navigate(['/confirmation']);
      }
    });
  }

  back() {
    this.location.back();
  }

  ngOnInit(): void {
  }

}
