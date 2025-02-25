Il codice dato puo` causare deadlock perche'
1) Le risorse disponibili sono meno di quelle che possono essere richieste contemporaneamente dagli utenti
2) Sono verificate le condizioni viste a lezione. Il Particolare, gli utenti fanno hold&wait, accedendo alle risorse in ordine qualunque. E` dunque possibile che tutte le risorse di tipo A siano state acquisite da utenti che attendono altrettante risorse di tipo B, mentre tutte le risorse di tipo B sono state acquisite da utenti che attendono altrettante risorse di tipo A.

Soluzione proposta
La soluzione proposta comporta due modifiche al codice dato:
1) le chiamate get del resource manager sono sospesive (e la put risvegli gli eventuali thread utente in attesa).
2) i thread utente acquisiscono sempre le risorse di tipo A prima di quelle di tipo B, quando le hanno bisogno entrambe. A questo scopo, un utente che dopo aver acquisito una risorsa B si rende conto di necessitare anche di una di tipo A, prima rilascia la risorsa B, e poi cerca di ottenerle entrambe richiedendo prima A e poi B.
NB: e` solo il punto 2) che consente di evitare il deadlock. Il punto 1) evita che un thread possa dormire quando la risorsa che sta gli serve e` disponibile.
