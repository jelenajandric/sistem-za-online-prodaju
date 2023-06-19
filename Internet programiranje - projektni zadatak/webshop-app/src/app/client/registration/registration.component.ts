import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistrationService } from '../services/registration.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationResponse } from 'src/app/model/client/registration-response.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  public form: FormGroup = new FormGroup({})

  isAddMode?: boolean;
  hide: boolean = true;

  constructor(private registrationService: RegistrationService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.form = new FormGroup({    
      name: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required)
    })

    this.route.firstChild?.paramMap.subscribe(params => {
      const path = this.router.url;
      if(path.includes("update-account")) {
        this.isAddMode = false;
      } else {
        this.isAddMode = true;
      }
      
      if (!this.isAddMode) {
        this.registrationService.getInfoForUpdate()
          .subscribe(x => {
            this.form.patchValue(x)
          });
      }
    });
  }

  public submit() : void {
    if(!this.form.valid) {
      this.error="Popunite sva polja vazecim vrijednostima."
    } else {
      if(this.isAddMode) {
        this.registrationService.isUsernameFree(this.form.value.username).subscribe(data=> {
          if(data!=true) {
            this.error="Neuspjesna registracija. Unijeli ste korisnicko ime koje vec postoji.";
          } else {
            this.registrationService.register(this.form.value.name, this.form.value.surname, this.form.value.username, 
              this.form.value.password, this.form.value.city, this.form.value.email).subscribe(data => {
                if(data!=null) {
                  this.registrationService.setRegisteredUser(new RegistrationResponse(data.id, data.name, data.surname, 
                    data.username, data.city, data.email, data.avatar, data.isActivated))
                }
                this.checkData(data);
              });
          }
        });
      
      } else {
        this.registrationService.updateAccount(this.form.value.name, this.form.value.surname, this.form.value.username, 
        this.form.value.password, this.form.value.city, this.form.value.email).subscribe(data => {
          this.checkData(data);
        })  
      }    
    }
  }

    private checkData(data: any) {
      if(data!=null) {
        this.router.navigate(["clients/upload-image"])
      } else {
        this.error = "Nepravilan unos!"
      }
    }
   

  @Input() error: string | null | undefined
}
