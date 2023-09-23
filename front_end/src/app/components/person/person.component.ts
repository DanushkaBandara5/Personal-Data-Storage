import { Component } from '@angular/core';
import {TempService} from "../../service/temp.service";
import {Person} from "../../classes/person";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent {
  public personList:Person[]=[];
  constructor(private temp:TempService) {
    this.temp.http.get<Person[]>(`${this.temp.BASE_URL}/api/v1/persons`).subscribe({
      next:personList=>{
        this.personList=personList;
      },
      error:err => {
        console.log("fail to load personlist");
      }

    })

  }

}
