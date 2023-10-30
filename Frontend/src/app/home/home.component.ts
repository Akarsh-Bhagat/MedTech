import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
doctor: any[]=[];



constructor(){
  this.doctor = [

      {id:6,name:'Varsha' ,address: 'Lucknow' ,dob:'2004-07-29',gender: 'Female',role:'C'}
    ];
}



}
