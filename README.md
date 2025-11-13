Radili:Emin Veispahić,
Eldan Šehagić,
Kemal Hasanspahić

🧩 Novi model – Battle

Predstavlja pojedinačnu borbu između karaktera i nasumičnog protivnika.

Svaka borba čuva:

Ime igrača (playerName)

Ime protivnika (opponentName)

Snagu igrača (playerPower)

Snagu protivnika (opponentPower)

Ishod borbe (result – "POBJEDA", "PORAZ", "NERIJEŠENO")

Vrijeme borbe (battleTime)

Relacija sa Character modelom:

Jedan karakter može imati više borbi (One-to-Many).

Borba se ne povezuje direktno sa drugim karakterima osim nasumično generisanog protivnika.

🏗️ Logika dodavanja i relacije

Novi objekt Battle kreira se pri završetku borbe kroz logiku simulacije u BattleService.

Borba se odmah spremi u bazu (BattleRepository.save(battle)).

Rezultat borbe (ishod, osvojeni bodovi, novi level) se vraća view-u za prikaz korisniku.

⚙️ Funkcionalnosti controllera
1️⃣ CharacterController (obični controller – MVC)

Ruta /battle: prikaz svih karaktera i izbor karaktera za borbu.

Ruta /battle/start: pokreće simulaciju borbe za izabranog karaktera; kreira Battle objekt i sprema ga u bazu.

Ruta /history: prikazuje sve odigrane borbe (istorija borbi) sa detaljima.

2️⃣ BattleRestController (REST API)

GET /api/battles: vraća listu svih borbi u JSON formatu.

REST controller omogućava programatski pristup podacima borbi, dok MVC controller omogućava interaktivni web prikaz i vođenje borbi.

🕹️ Glavne funkcionalnosti vezane za Battle

Simulacija borbe za izabranog karaktera

Automatsko računanje ishoda borbe (pobjeda/poraz/neriješeno)

Ažuriranje bodova, levela i života karaktera nakon borbe

Pregled ihstorije svih borbi
