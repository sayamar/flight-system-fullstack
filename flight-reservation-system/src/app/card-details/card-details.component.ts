import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
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
    private reservationService: ReservationService) { }

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

  ngOnInit(): void {
  }

}
