## CLI
CLI står for `command line interface` og er en tekstbaseret brugergrænseflade

I dag arbejder vi videre på projektet restful-dis (som kan findes her: https://github.com/distribuerede-systemer-2019/restful-dis - vær opmærksom på at det er ikke dette repository i skal clone, men dette: ).
Sidst vi arbejdede med dette repo, interagerede vi med det gennem postman (eller anden HTTP afsendelses værktøj). I dag skal vi lave en brugergrænseflade, som kan sende de samme requests.

#### 1. Opgave
Clone og kør dette repository på samme måde, som vi kørte restful-dis. Bemærk at der er kommet en ny klasse, der hedder Client. Denne er per default sat op til at kunne kommunikere med jeres server. Bemærk at jeres base url (linje 14 i Client.java) muligvis skal ændres. Når i kører serveren burde der meget gerne åbne en default adresse op i jeres browser, og det er denne i skal bruge som base.

Vær sikker på at projektet kører ved at åbne din browser og naviger til base-url'en og skriv /users efter. Den burde gerne returnere brugere.


#### 2. Opgave
Kør Client klassen og prøv at hent brugere gennem `client`-klassen, som kan returnere én specifik bruger, hvor der søges med brugerens ID.

#### 3. Opgave
Implementer metoden `getUserById`
Her kan du få inspiration fra `getAllUsers`. Bemærk at metoden kræver, at du kan tage imod endnu et tal gennem brugergrænsefladen, før du kan returnere din specifikke bruger. Hvis du er i tvivl om endpointed, kan du eventuelt kigge på Severes UserEndpoint. 

Håndter også fejl. Hvis du skriver et tal på en bruger, som ikke eksisterer.

#### 4. Opgave
implementer metoden `createUser`. Igen skal du bruge endnu et menupunkt, hvor du skal kunne indtaste en brugers oplysninger en ad gangen. Dette skal du så lave om til et brugerobject, som skal serializes (gennem Json) og sendes til UserTable. Bemærk at jeres UserEndpoint har en metode, der hedder `createUser` som tager imod en Json String.
Det vil sige, at jeres data skal formateres korrekt inden i sender det afsted. Hint: https://www.baeldung.com/httpurlconnection-post OBS: husk at fjern "utf-8".


