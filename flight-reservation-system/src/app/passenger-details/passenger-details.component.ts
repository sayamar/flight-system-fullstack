import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormControl,  FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PassengerService } from '../services/passenger.service';

@Component({
  selector: 'app-passenger-details',
  templateUrl: './passenger-details.component.html',
  styleUrls: ['./passenger-details.component.scss']
})
export class PassengerDetailsComponent implements OnInit {
  
  passengerForm: FormGroup = this.fb.group({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    middleName: new FormControl(''),
    dateOfBirth: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    phone: new FormControl('', Validators.required),

  });

  constructor(private location: Location, private fb:FormBuilder, private ar: ActivatedRoute, private router:Router, private passengerService: PassengerService) { }

  ngOnInit(): void {
    console.log(this.ar.snapshot.paramMap.get('id'));
  }

  back() {
    this.location.back();
  }

  nextClicked() {
    const flightId = this.ar.snapshot.paramMap.get('id');
    this.passengerService.setPassengerDetails({ ...this.passengerForm.value, flightId });
    this.router.navigate(['/card-details']);
  }

}
