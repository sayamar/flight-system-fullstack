import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardDetailsComponent } from './card-details/card-details.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { FlightBookingComponent } from './flight-booking/flight-booking.component';
import { PassengerDetailsComponent } from './passenger-details/passenger-details.component';


const routes: Routes = [
  {path: '', redirectTo: "booking", pathMatch: 'full'},
  {path: 'booking', component: FlightBookingComponent},
  {path: 'passenger-details/:id', component: PassengerDetailsComponent},
  {path: 'card-details', component: CardDetailsComponent},
  {path: 'confirmation', component: ConfirmationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
