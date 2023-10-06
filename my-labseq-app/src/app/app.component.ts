import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  result: String = '';
  n: number = 0;
  constructor(private apiService: ApiService) {}

  getN() {
    this.apiService.getSequence(this.n).subscribe({
      next: (data: any ) => {
        console.log(BigInt(data.value));
        if (data.value===Infinity) {
          this.result = BigInt(data.value).toString();
        }
        else
        {
          this.result = BigInt(data.value).toString();
        }
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  ngOnInit() {

}



}
