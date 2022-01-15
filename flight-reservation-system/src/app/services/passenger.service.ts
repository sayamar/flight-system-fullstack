import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PassengerService {
  passengerDetails: any;
  constructor() { }

  getPassengerDetails() {
    return { ...this.passengerDetails };
  }

  setPassengerDetails(passengerDetails: any) {
    this.passengerDetails = { ...passengerDetails };
  }
}
