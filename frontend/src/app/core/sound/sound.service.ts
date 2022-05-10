import { Injectable } from '@angular/core';
import {Sound} from "./sound";

@Injectable({
  providedIn: 'root'
})
export class SoundService {



  soundDir: string = "assets/sounds"
  soundElement: HTMLAudioElement;
  volume: number;

  constructor() {
    this.soundElement = document.createElement("audio");
    this.volume = 0.5;
  }

  playSound(source: Sound){
    this.soundElement.src = `${this.soundDir}/${source}`;
    this.soundElement.volume = this.volume;
    this.soundElement.play();
  }


}
