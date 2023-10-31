import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  editForm!: FormGroup;
  doctor: any = []

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private fb: FormBuilder) { }
  
  ngOnInit(): void {
  
    // Initializing  form variable
    this.editForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      address: ['', Validators.required],
      dob: ['', Validators.required],
      email: ['', Validators.required],
      specialisation: ['', Validators.required],
    });

      this.route.params.subscribe(params => {
        const doctorId = params['id'];
        this.userService.getDataById(doctorId).subscribe((data: any) => {
          this.doctor=data;
          const {firstname, lastname, address, dob, email, specialisation} = data
          this.editForm.setValue({
            firstname, lastname, address, dob, email, specialisation
          })

          
          console.log(data);
        });
    })
      
  }


  onSubmit(){
    if(this.editForm.valid){
      const userData = this.editForm.value;
      console.log(userData);

      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          // Add any other necessary headers
        })
      };
      
      // navigating to home page
      this.userService.updateData(userData,this.doctor.doctorId)
        .subscribe(res => {
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/home']);});
        })
    }
  }
}
