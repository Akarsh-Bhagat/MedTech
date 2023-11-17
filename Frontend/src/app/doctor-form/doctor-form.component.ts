import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.css']
})
export class DoctorFormComponent implements OnInit {
  doctorForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.doctorForm = this.fb.group({
      services: this.fb.array([]),
      experiences: this.fb.array([]),
      awards: this.fb.array([]),
      education: this.fb.array([]),
      specializations: this.fb.array([]),
      memberships: this.fb.array([])
    });
  }

  getFormArrayControls(formArrayName: string): AbstractControl[] {
    return (this.doctorForm.get(formArrayName) as FormArray).controls;
  }

  addInput(formArrayName: string) {
    const formArray = this.doctorForm.get(formArrayName) as FormArray;
    formArray.push(this.fb.control(''));
  }

  removeInput(formArrayName: string, index: number) {
    const formArray = this.doctorForm.get(formArrayName) as FormArray;
    formArray.removeAt(index);
  }
}
