export class SearchedUserResult {
  id: number;
  name: string;
  lastName: string;

  constructor(id: number, name: string, lastName: string) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
  }
}
