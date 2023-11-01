import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      firstname: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      lastname: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      address: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      dob: [Validators.required],
      email: ['',[Validators.required, Validators.email]],
      specialisation: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
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

  getErrorMessage(controlName: string) {
    const control = this.editForm.get(controlName);
    if (control?.hasError('required')) {
      return 'Field is required';
    } else if (control?.hasError('pattern')) {
      return 'Invalid input';
    } else if (control?.hasError('email')) {
      return 'Invalid email';
    } else if (control?.hasError('invalidDOB')) {
      return 'Please provide a valid year after 1900';
    }
    return '';
  }

 


  onSubmit(){
    if(this.editForm.valid){
      const userData = this.editForm.value;
      console.log(userData);
      
      // navigating to home page
      this.userService.updateData(userData,this.doctor.doctorId)
        .subscribe(res => {
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/home']);});
        })
    }
  }
}
