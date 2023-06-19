import { Component, Input, OnInit } from '@angular/core';
import { ActivateAccountService } from '../services/activate-account.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {
  public email: String | undefined;
  public form: FormGroup = new FormGroup({})
  public formForEmailChanging: FormGroup = new FormGroup({})

  successResponse!: string;

  constructor(private activateAccountService: ActivateAccountService,
              private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.checkConditions();
    this.form = new FormGroup({    
      pin: new FormControl('', Validators.required),
    })
    this.formForEmailChanging = new FormGroup({
      newEmail: new FormControl('')
    })
    this.email = this.loginService.getLoggedInUser()?.email;
  }
  
  
  
  submit() {
    if(!this.form.valid) {
      this.error = "Popunite polja vazecim vrijednostima"
    } else {
      this.checkConditions();
      this.activateAccountService.acctivateAccountSubmit(this.form.value.pin).subscribe(data => {
        if(data!=null) {
          if(data===true) {
            this.loginService.setIsActivatedOnTrue();
            this.loginService.setIsLoggedIn(true);
            this.error = "Uspjesno";
            this.router.navigate(["/home"]);
          } else {
            this.error = "Unijeli ste pogresan pin";
          }
        }
      })      
    }
  }   
  

  sendPinAgain() {
    this.checkConditions();
    this.activateAccountService.sendPinAgain().subscribe();
  }

  changeEmail() {
    this.checkConditions();
    if(this.formForEmailChanging.value.newEmail=="") {
      this.successResponse = "Morate unijeti email."
    } else {
      this.activateAccountService.changeEmail(this.formForEmailChanging.value.newEmail).subscribe(data => {
        if(data!=null) {
          if(data===true) {
            this.successResponse = "Uspjesno promjenjen email";
            this.email = this.formForEmailChanging.value.newEmail;
          } else {
            this.successResponse = "Email nije promjenjen.";
          }
        }
      })
    }
  }

  private checkConditions() {
    if(this.loginService.getLoggedInUser()===null) {
      this.router.navigate(["/clients/login"])
    }
    if(this.loginService.isLoggedIn() && this.loginService.getLoggedInUser().isActivated) {
      this.router.navigate(["/home"])
    }
  }

  @Input() error: string | null | undefined
}
