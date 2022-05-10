export class PostCreationRequest {
  private content: string;
  private visibilityGroupType: string;
  private visibilityUsersId: number[];

  constructor(content: string, groupType: string, visibilityUsersId?: number[]) {
    this.content = content;
    this.visibilityGroupType = groupType;
    if(visibilityUsersId !== undefined){
      this.visibilityUsersId = visibilityUsersId;
    } else {
      this.visibilityUsersId = [];
    }
  }

}
