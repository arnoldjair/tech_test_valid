import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import swal from 'sweetalert2';
import { Error } from '../../core/model/error';
import { User } from '../../core/model/user';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'valid-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit {
  public userForm: FormGroup;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group(
      {
        firstName: [],
        lastName: [],
      },
      { updateOn: 'change' }
    );
  }

  ngOnInit(): void {}

  submit(): void {
    debugger;
    if (
      !this.userForm.controls['firstName'].value ||
      !this.userForm.controls['lastName'].value
    ) {
      swal.fire({
        icon: 'error',
        title: 'Error',
        text: "The first name or last name can't be null or empty",
      });
    } else {
      const user: User = { ...this.userForm.value, processed: false };
      this.userService.saveUser(user).subscribe(
        (save: User) => {
          swal.fire('User saved successfully');
        },
        (error: any) => {
          debugger;
          swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: error.error.message,
          });
        }
      );
    }
  }
}
