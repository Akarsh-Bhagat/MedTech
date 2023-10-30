import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
doctor: any[]=[];



constructor( private userService :UserService){

  
}
ngOnInit():void{
  this.userService.getPosts().subscribe((response)=>{
    this.doctor=response;
    console.log(response);
  })
}



}
