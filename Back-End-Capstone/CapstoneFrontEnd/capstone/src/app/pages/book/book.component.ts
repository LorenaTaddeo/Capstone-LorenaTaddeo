import { Component, OnInit } from '@angular/core';
import { Scooter } from './scooter';
import { HttpClient } from '@angular/common/http';
import { Observable, switchMap, tap } from 'rxjs';
import { NgbCalendar, NgbDateStruct, NgbDatepickerNavigateEvent } from '@ng-bootstrap/ng-bootstrap';
import { Reservation } from './reservation';

@Component({
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit{

  protected scooters: Scooter[] = [];
  protected userId?:number;
  private _searchingDate?: string;

  model!: NgbDateStruct;
  date!: { year: number; month: number; };
  protected reservationsByDayMap=new Map();

  constructor(
    private http: HttpClient,
    private calendar: NgbCalendar,
  ) { }

  ngOnInit(){
    const accessData= localStorage.getItem('accessData');
    if(accessData){
      this.userId=JSON.parse(accessData).id;
    }
    this.model = this.calendar.getToday();
    this._findAvailableScooters().subscribe();
    this._getUserReservations().subscribe();
    }

  _findAvailableScooters(): Observable<any>{
    this._searchingDate= this.model.year+'-'+this.model.month.toString().padStart(2, '0')+'-'+this.model.day.toString().padStart(2, '0')
    return this.http.get<Scooter[]>('http://127.0.0.1:8080/api/scooter').pipe(
      tap((response:Scooter[])=>{this.scooters = response}),
      switchMap(()=>this.http.get<any[]>('http://127.0.0.1:8080/api/reservation/bookingDay?bookingDay='+this._searchingDate)),
      tap((reservations)=>{ this._setScootersStatus(reservations)})
      )
      
      
  }

  _getUserReservations(): Observable<any>{
    return this.http.get<Reservation[]>('http://127.0.0.1:8080/api/reservation/user?user='+this.userId).pipe(tap((reservations)=>{
     
        reservations.filter((reservation)=>{
          const bookingDay =new Date(reservation.bookingDay).getTime();
          const today= new Date().getTime();
          return bookingDay>today
        });
        this.reservationsByDayMap=new Map();
        reservations.forEach((reservation)=>{
          const scooterWithReservationId= {...reservation.scooter, reservationId: reservation.id};
          const reservationsByDay= this.reservationsByDayMap.get(reservation.bookingDay)
        if(reservationsByDay){

        reservationsByDay.push(scooterWithReservationId)
        }else{
        this.reservationsByDayMap.set(reservation.bookingDay, [scooterWithReservationId])
        }
  
      })
    
console.log(this.reservationsByDayMap)



    }))
  }

  setDate(){
    this._findAvailableScooters().subscribe();
  }

  _setScootersStatus(reservations:any[]): void{
    const reservedIds: number[]=[];
    reservations.forEach((reservation)=>{reservedIds.push(reservation.scooter.id)})
    this.scooters.map((scooter)=>{
      if(reservedIds.indexOf(scooter.id)>=0){
        scooter.scooterstatus="BUSY"
      }else{
        scooter.scooterstatus="FREE"
      }
    })
  }

  changeStatus(id:number){
    const body = {
      user: {
        id: this.userId
      },
      scooter: {
        id: id
      },
      bookingDay: this._searchingDate
    }
    this.http.post('http://127.0.0.1:8080/api/reservation',body,{responseType: 'text'})
    .pipe(
      switchMap(()=>this.http.get<any[]>('http://127.0.0.1:8080/api/reservation/bookingDay?bookingDay='+this._searchingDate)),
      tap((reservations:any[])=>{  this._setScootersStatus(reservations)}),
      switchMap(()=>this._getUserReservations())).subscribe();
  }

deleteReservation(reservationId:number){
  this.http.delete('http://127.0.0.1:8080/api/reservation/'+reservationId).pipe(
switchMap(()=>this._findAvailableScooters()),
switchMap(()=>this._getUserReservations()),
  ).subscribe();

}


}
