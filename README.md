# 🎮 Battle Arena – Mini Spring Boot Projekt

Ovaj projekat je izrađen u okviru vježbi iz Web programiranja (3. godina, Softversko inženjerstvo - Politehnički fakultet u Zenici)  
i služi kao **primjer MVC aplikacije u Spring Bootu**.  
Tema je jednostavna **tekstualna igra borbe** između karaktera i nasumično generisanog protivnika.


## 🧩 Opis projekta

Aplikacija simulira mini RPG sistem u kojem:
- Svaki **karakter** ima ime, snagu, živote, bodove i nivo (level).
- Karakter može **kupovati alate** koji povećavaju snagu ili živote.
- Tokom borbe se generiše **nasumičan protivnik** sa određenim nivoom snage.
- Ishod može biti **pobjeda, poraz ili neriješeno**.
- Nakon svake borbe, karakter **napreduje na sljedeći level**.

Aplikacija je napravljena u **Spring Bootu** i koristi **Thymeleaf** za prikaz podataka u preglednom web interfejsu.



## 🧱 Struktura projekta

```
battle-arena/
 ├─ src/main/java/unze/ptf/battlearena/
 │   ├─ BattleArenaApplication.java       # Glavna klasa aplikacije (ulazna tačka)
 │   ├─ model/                            # Modeli – predstavljaju objekte u igri
 │   │   ├─ Character.java                # Klasa za karaktere igrača (ime, snaga, životi, bodovi, level)
 │   │   └─ Tool.java                     # Klasa za alate koje karakter može koristiti
 │   ├─ data/
 │   │   └─ GameData.java                 # Privremeno čuva listu karaktera i alata (in-memory baza)
 │   ├─ service/
 │   │   └─ BattleService.java            # Logika borbe (generisanje protivnika, izračun ishoda)
 │   └─ controller/
 │       └─ CharacterController.java      # Definiše rute, kontrolere i povezuje modele sa prikazima
 │
 ├─ src/main/resources/
 │   ├─ templates/                        # Thymeleaf HTML fajlovi (View dio MVC-a)
 │   │   ├─ characters.html               # Prikaz svih karaktera i njihovih atributa
 │   │   ├─ tools.html                    # Prikaz svih alata i kupovina
 │   │   └─ battle.html                   # Simulacija borbe (interaktivna stranica)
 │   └─ application.properties            # Osnovne postavke aplikacije
 │
 └─ pom.xml                               # Maven konfiguracija (zavisnosti i build)
```



## ⚙️ Objašnjenje MVC strukture

| Dio projekta | Uloga | Lokacija |
|---------------|--------|-----------|
| **Model** | Predstavlja podatke aplikacije (Character, Tool). | `src/main/java/unze/ptf/battlearena/model/` |
| **View (pogled)** | Prikazuje sadržaj korisniku kroz HTML stranice uz Thymeleaf. | `src/main/resources/templates/` |
| **Controller** | Prima zahtjeve iz browsera, koristi modele i šalje ih ka View-u. | `src/main/java/unze/ptf/battlearena/controller/` |

Dodatno:
- **`service/`** sadrži poslovnu logiku – u ovom slučaju **BattleService** koji računa tok borbe, bodove i ishod.
- **`data/`** je zamjena za bazu podataka. Tu se privremeno čuvaju likovi i alati pomoću običnih lista.



## 🕹️ Glavne funkcionalnosti

1. **Pregled svih karaktera** – ruta `/characters`
2. **Pregled alata i kupovina** – ruta `/tools`
3. **Simulacija borbe** – ruta `/battle`
4. Automatsko povećanje levela nakon svake borbe
5. Kupovina alata uz ograničenje (maksimalno 3 alata po karakteru)



## 🌱 Kako pokrenuti aplikaciju

1. Otvori projekat u IntelliJ IDEA.
2. Provjeri da imaš instaliran **JDK 17+**.
3. Pokreni projekat (Run → `BattleArenaApplication`).
4. Otvori u browseru:  
   👉 [http://localhost:8080](http://localhost:8080)



## 🔮 Moguća buduća unapređenja

Ovaj projekat može se lako nadograditi za potrebe naprednijih vježbi:

| Unapređenje | Opis |
|--------------|------|
| **Dodavanje baze (JPA + H2/MySQL)** | Umjesto `GameData`, koristiti pravu bazu podataka i CRUD operacije. |
| **Korisnički interfejs (Bootstrap)** | Dodati CSS framework i poboljšati izgled aplikacije. |
| **Historija borbi (Battle model)** | Evidentirati sve borbe sa ishodom i datumom. |
| **Registracija korisnika** | Svaki student/igrač ima svoj nalog i svoje karaktere. |
| **REST API** | Izložiti rute `/api/characters`, `/api/tools` i `/api/battle` kao JSON API za frontend. |
| **Frontend (React/Angular)** | Napraviti poseban frontend koji koristi API aplikacije. |



## 🧠 Cilj vježbe

Projekat “Battle Arena” pomaže studentima da:
- razumiju **princip MVC arhitekture** u Spring Bootu,
- povežu modele, kontrolere i prikaze u jedinstvenu aplikaciju,
- uvježbaju rad s rutama, formama i dinamičkim HTML prikazima,
- kasnije samostalno naprave **svoje mini projekte** po istom principu.



**Autor projekta:** narcisa.hadzajlic@dl.unze.ba – Web programiranje  
**Fakultet:** Politehnički fakultet, Univerzitet u Zenici  
**Godina:** 2025/2026
