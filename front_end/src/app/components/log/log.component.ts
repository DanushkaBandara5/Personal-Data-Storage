import { Component } from '@angular/core';
import {TempService} from "../../service/temp.service";
import {Router} from "@angular/router";
import {User} from "../../classes/user";

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent {
  constructor(public temp:TempService,private router:Router){}

  action(usr: HTMLInputElement, pwd: HTMLInputElement) {
    const user=new User(usr.value,pwd.value);
    this.temp.http.post(`${this.temp.BASE_URL}/api/v1/users/login`,user).subscribe({
      next: data => {
        this.router.navigateByUrl("/user");
      },
      error: error => {
        this.router.navigateByUrl("/user")
        console.error('There was an error!', error);
      }
    })

  }
}
