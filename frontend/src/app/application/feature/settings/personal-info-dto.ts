export class PersonalInfoDto {
  private name: string;
  private lastName: string;
  private gender: string;

  constructor(name: string, lastName: string, gender: string) {
    this.name = name;
    this.lastName = lastName;
    this.gender = gender;
  }
}
