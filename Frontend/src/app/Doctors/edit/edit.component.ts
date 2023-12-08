import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  userForm!: FormGroup;
  doctor: any = []

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private fb: FormBuilder) { }
  
  ngOnInit(): void {
  
    // Initializing  form variable
    this.userForm = this.fb.group({
      firstName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      lastName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      address: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      dateOfBirth: [Validators.required],
      email: ['',[Validators.required, Validators.email]],
      specialisation: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
    });

      this.route.params.subscribe(params => {
        const id = params['id'];
        this.userService.getDataById(id).subscribe((data: any) => {
          this.doctor=data;
          const {firstName, lastName, address, dateOfBirth, email, specialisation} = data
          this.userForm.setValue({
            firstName, lastName, address, dateOfBirth, email, specialisation
          })

          
          console.log(data);
        });
    })
      
  }

  getErrorMessage(controlName: string) {
    const control = this.userForm.get(controlName);
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
    if(this.userForm.valid){
      const userData = this.userForm.value;
      console.log(userData);
      
      // navigating to home page
      this.userService.updateData(userData,this.doctor.id)
        .subscribe(res => {
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/home']);});
        })
    }
  }
}
