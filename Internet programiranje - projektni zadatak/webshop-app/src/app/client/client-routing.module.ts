import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UploadImageComponent } from './upload-image/upload-image.component';
import { ShowClientComponent } from './show-client/show-client.component';
import { SendMailComponent } from './send-mail/send-mail.component';
import { WebshopToolbarComponent } from '../home/webshop-toolbar/webshop-toolbar.component';

const routes: Routes = [{
  path: 'login',
  component: LoginComponent
}, 
{
  path: '',
  component: RegistrationComponent,
  children: [
    { path: 'registration', component: RegistrationComponent },
    { path: 'update-account', component: RegistrationComponent }
  ]
},
{
  path: 'upload-image',
  component: UploadImageComponent
},
{
  path: 'activate-account',
  component: ActivateAccountComponent
},
{
  path: 'show',
  component: ShowClientComponent
},
{
  path: 'send-mail',
  component: SendMailComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
