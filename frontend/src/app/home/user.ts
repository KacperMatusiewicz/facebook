export class User {
  name: string;
  lastName: string;
  email: string;
  gender: string;
  password: string;

  constructor(name: string, lastName: string, email: string, gender : string, password: string) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.password = password;
  }
}
