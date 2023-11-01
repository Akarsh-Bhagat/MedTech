import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { SharingService } from '../services/sharing.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

doctor: any[]=[];



constructor(private router: Router, private userService :UserService){

  
}
ngOnInit():void{
  this.userService.getPosts().subscribe((response)=>{
    this.doctor=response;
    console.log(response);
  }) 
}

deleteAndReload(doctorId: number) {
  this.userService.deleteData({},doctorId).subscribe(
    () => {
    },
    (error) => {
      window.location.reload();
    }
  );
}

}
