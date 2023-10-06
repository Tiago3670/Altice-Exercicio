import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getSequence(_n: any) {
      return this.http.get(`${this.baseUrl}/labseq/${_n}`);
      // return this.http.get(`${this.baseUrl}`);
  }

}
