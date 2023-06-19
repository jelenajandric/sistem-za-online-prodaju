import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SendMailService } from '../services/send-mail.service';
import { LoginService } from '../services/login.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-send-mail',
  templateUrl: './send-mail.component.html',
  styleUrls: ['./send-mail.component.css']
})
export class SendMailComponent implements OnInit{

  public form: FormGroup = new FormGroup({})
  @Input() error: string | null | undefined

  constructor(private sendMailService: SendMailService,
              private loginService: LoginService,
              private router: Router) {}

  ngOnInit(): void {
    if(!this.loginService.isLoggedIn()) {
      this.router.navigate(["/clients/login"]);
    }
    this.form = new FormGroup({    
      content: new FormControl('')
    })
  }

  sendMail() {
    if(!this.loginService.isLoggedIn()) {
      this.router.navigate(["/clients/login"]);
    }
    if(this.form.value.content) {
      this.sendMailService.sendMailToTechnicalSupport(this.form.value.content).subscribe(data=> {
        this.router.navigate(['home']);
      })
    } else {
      this.error = "Morate unijeti tekst mejla"
    }
  }


}
