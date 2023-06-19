import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { LoginResponse } from 'src/app/model/client/login-response.model';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public form: FormGroup = new FormGroup({})
  hide = true;

  constructor(private loginService: LoginService,
              private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({    
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    })
  }

  public submit() {
    if(!this.form.valid) {
      this.error="Popunite sva polja vazecim vrijednostima"
    } else {
      this.loginService.login(this.form.value.username, this.form.value.password).subscribe(data => {
        if(data!=null) {
          this.loginService.setLoggedInUser(new LoginResponse(data.id, data.name, data.surname, 
            data.username, data.city, data.email, data.avatar, data.isActivated));
          if(data.isActivated) {
            this.loginService.setIsLoggedIn(true); //tek i nakon aktivacije naloga se moze staviti true jer ne mora aktivirati nalog i onda nije prijavljen
            this.router.navigate(["/home"])
          } else {
            this.router.navigate(["/clients/activate-account"])
          }
        } else {
          this.error = "Nepravilan unos!"
        }
      })
    }
          
  }

  @Input() error: string | null | undefined
}
