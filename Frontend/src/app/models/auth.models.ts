export interface RegisterRequest {
  firstname: String;
  lastname: String;
  email: String;
  password: String;
  }
  
  export interface AuthenticationRequest {
    email: String;
    password: String;
  }
  
  export interface AuthenticationResponse {
    token: String;
  }