import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EMPTY, Observable, of } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getUserList(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/api/v1/user');
  }

  public updateUserList(users: User[]): Observable<User[]> {
    return this.http.put<User[]>('http://localhost:8080/api/v1/user', users);
  }

  public saveUser(user: User): Observable<User> {
    return this.http.post<User>('http://localhost:8080/api/v1/user', user);
  }
}
