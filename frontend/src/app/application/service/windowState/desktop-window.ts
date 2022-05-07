export interface DesktopWindow {
  getIcon() : string | null;
  close(): void;
  focus(): void;
  maximize(): void;
  minimize(): void;
  setId(windowId: number): void;
}
