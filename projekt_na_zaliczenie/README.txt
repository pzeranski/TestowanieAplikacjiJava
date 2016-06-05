Czynności wymagane do uruchomienia projektu:

1. Uruchomić trzy okna terminala i w każdym z nich przejść do katalogu projektu i już go nie zmieniać (bardzo ważne)!

2. Terminal1: Uruchomienie serwera bazy danych
 > ./scripts/runHSQLDBServer.sh

3. Terminal2: Uruchomienie klienta bazy danych
 > ./scripts/runHSQLDBClient.sh

4. Terminal3: Uruchomienie serwera webowego z usługą RESTową
 >  mvn jetty:run

5. Uruchomić przeglądarkę (lub innego klienta http) i sprawdzić czy serwis działa:
http://localhost:8080/restservicedemo/api/tdriver/test
http://localhost:8080/restservicedemo/api/train/test
Spodziewany rezultat: 
"REST Service is running"

6. Odpalamy testy :-)