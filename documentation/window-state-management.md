```ts

export class WindowModel {
    id: number;
    windowType: WindowType;
    isFocused: boolean;
    isMaximized: boolean;
    isMinimized: boolean;
    isResizable: boolean;
    isDraggable: boolean;
    content: object;
    
    
}

enum WindowType {
    MessageBox,
    UserPage,
}

@Injectable
export class WindowManagementService {

    currentZIndex: number;
    windowList:  Map<Number, HTMLElement>;
    taskBarList: Map<Number, HTMLElement>;
    idCounter: number;
    currentFocusedElement: HTMLElement;
    
    constructor(
        private windowContainerRef: ContainerRef,
        private taskbarContainerRef: ContainerRef
    ) {
        this.idCounter = 0;
    }
    
    
    openWindow(window: WindowModel) {
        switch (window.windowType) {
            case WindowType.UserPage:
                let newDesktopUserPage = new UserPageComponent(this.idCounter);
                this.windowContainerRef.append(newDesktopUserPage);
                this.taskbarContainerRef.append(new TaskBarItemComponent(this.idCounter++, newDesktopUserPage.getIcon()));
                
        }
    }
    closeWindow(windowId: number) {
        // usuwanie - potrzebne jest id 
    }
    minimizeWindow(windowId: number) {
        // minimimalizacja - id, zmiana visibility + style w taksbarze + 
    }
    maximizeWindow(windowId: number) {
        // potrzebne jest id + zmiana kolejnosci wszystkich zindexow i zmiana stylu elementu o najwiekszym z indexie
        // zmiana stylu
    }
    focusWindow(windowId: number) {
        
    }
}
//potrzebny jest stan zindexów w formie kolejki elemementów

export interface TaskBarItem {
    setIcon(icon: string | null);
    maximize(windowId: number);
    minimize(windowId: number);
}


export interface DesktopWindow {
    getIcon() : string | null;
    close(windowId: number);
    focus(windowId: number);
    maximize(windowId: number);
    minimize(windowId: number);
}
```
```html
    <window #ww onClose="this.close()">
    
</window>
```

```ts
export class UserProfileComponent implements DesktopWindow{
    constructor(service: WindowManagementService, windowId: number) {
    }
    
    close() {
        this.service.closeWindow(this.windowId);
    }
    
}

export class TaskBarItem {
    icon: string;
}

export class HomePageComponent {
    constructor(service: WindowManagementService) {
    }

}
```


```json


```