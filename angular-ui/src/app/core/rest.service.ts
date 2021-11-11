import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private REST_API_SERVER = "http://localhost:8080/api/";

  constructor(private httpClient: HttpClient) { }

  public get(type: string, id?: number) {
    let url = this.REST_API_SERVER + type;
    if (id) {
      url =  `${url}/${id}`;
    }
    return this.httpClient.get(url).pipe(catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage;
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

}
