import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { AppMaterialModule } from '../app-material/app-material.module';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UploadImageComponent } from './upload-image/upload-image.component';
import { ShowClientComponent } from './show-client/show-client.component';
import { SendMailComponent } from './send-mail/send-mail.component';
import { HomeModule } from '../home/home.module';


@NgModule({
  declarations: [
    ActivateAccountComponent,
    LoginComponent,
    RegistrationComponent,
    UploadImageComponent,
    ShowClientComponent,
    SendMailComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    AppMaterialModule,
    MatCardModule,
    ReactiveFormsModule,
    HomeModule
  ]
})
export class ClientModule { }
