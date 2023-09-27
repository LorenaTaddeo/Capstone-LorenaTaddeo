import { Component, OnInit } from '@angular/core';
import { Scooter } from './scooter';
import { HttpClient } from '@angular/common/http';
import { switchMap, tap } from 'rxjs';
import { NgbCalendar, NgbDateStruct, NgbDatepickerNavigateEvent } from '@ng-bootstrap/ng-bootstrap';

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
  constructor(
    private http: HttpClient,
    private calendar: NgbCalendar
  ) { }
  ngOnInit(){
    const userData= localStorage.getItem('user');
    if(userData){
      this.userId=JSON.parse(userData).id;
    }
    
    this.model = this.calendar.getToday();
    this._findAvailableScooters();
   
    }

_findAvailableScooters(): void{
  this._searchingDate= this.model.year+'-'+this.model.month.toString().padStart(2, '0')+'-'+this.model.day.toString().padStart(2, '0')
  this.http.get<Scooter[]>('http://127.0.0.1:8080/api/scooter').pipe(
    tap((response:Scooter[])=>{this.scooters = response}),
    switchMap(()=>this.http.get<any[]>('http://127.0.0.1:8080/api/reservation/bookingDay?bookingDay='+this._searchingDate))
    ).subscribe((reservations:any[]) => {
      this._setScootersStatus(reservations)
      });
}



    setDate(){
      this._findAvailableScooters();
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
 
    this.http.post('http://127.0.0.1:8080/api/reservation',body,{responseType: 'text'}).pipe(switchMap(()=>this.http.get<any[]>('http://127.0.0.1:8080/api/reservation/bookingDay?bookingDay='+this._searchingDate))).subscribe((reservations:any[])=>{  this._setScootersStatus(reservations)});

  }


}
