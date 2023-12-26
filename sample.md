```mermaid
sequenceDiagram

participant T as TUI
participant CB as Collin Backend
participant OM as  Open-Meteo

T ->> CB: Request Weather
    CB ->> OM: Request Weather
        OM ->> OM: Experience Weather
        OM -->> CB: Send Weather
    CB -->> T: Send Weather
```
