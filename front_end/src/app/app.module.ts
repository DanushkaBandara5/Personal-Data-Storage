import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LogComponent } from './components/log/log.component';
import { AdminComponent } from './components/admin/admin.component';
import { PersonComponent } from './components/person/person.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
const routes:Routes=[{
  path:"login",
  component:LogComponent
},
  {
    path:"admin",
    component:AdminComponent
  },
  {
    path:"user",
    component:PersonComponent
  }]
@NgModule({
  declarations: [
    AppComponent,
    LogComponent,
    AdminComponent,
    PersonComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
