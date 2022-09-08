# FamilyApp
-------------------------------------------------------------------
### INSTRUKCJA ###

1. Informacje techniczne :
- docker
- baza danych PostgreSQL 9.6
- java 11

2. Budowa i uruchomienie obrazów docker:
    1. Dla każdego pobranego repozytorium aplikacji (FamilyApp, FamilyMemberApp, FamilyDatabase) uruchom w terminalu
    polecenie :  mvn clean install
    2. Baza danych POSTGRES - docker image
        Pobierz kontener docker z postgresem v 9.6:
        $ docker pull postgres:9.6
    3. Dla każdego pobranego repozytorium (w scieżce repozytorium) wykonaj polecenia budowy image i uruchomienia
     poszczególnych kontenerów dockera :
    /FamilyApp:
        $ docker build . -t family-app
        $ docker run -p 8020:8020 family-app
    /FamilyMemberApp:
         $ docker build . -t family-member-app
         $ docker run -p 8021:8021 family-member-app
     /FamilyDatabase:
          $ docker compose up

UWAGI: Jeśli podczas uruchomienia usługi createFamily wystąpi, błąd proszę
       uruchomić aplikacje (FamilyApp, FamilyMemberApp) w IDE z uruchomionym kontenerem postgres:9.6.

---------------------------------------------------------------------

### PRZYKLADOWE WYWOALANIA USLUG DLA POSTMAN ####
------------------------------
POST -  createFamily
------------------------------
 http://localhost:8020/createFamily

 w body proszę dodać poniższe wartości JSON:

 {
    "familyName": "GAJOWY",
    "nrOfAdults": 1,
    "nrOfChildren": 0,
    "nrOfinfants": 1,
    "familyMemberList" : [
        {"familyName": "GAJOWY",
        "givenName": "Michal",
        "age": 30
        },
        {"familyName": "GAJOWY",
        "givenName": "Adam",
        "age": 2
        }
    ]
}
 lub
{
    "familyName": "KOWALSKI",
    "nrOfAdults": 2,
    "nrOfChildren": 0,
    "nrOfinfants": 1,
    "familyMemberList" : [
        {"familyName": "KOWALSKI",
        "givenName": "Piotr",
        "age": 30
        },
        {"familyName": "KOWALSKI",
        "givenName": "Krzysztof",
        "age": 2
        },
        {"familyName": "KOWALSKA",
        "givenName": "Anna",
        "age": 17
        }
    ]
}
lub
{
    "familyName": "NOWAK",
    "nrOfAdults": 1,
    "nrOfChildren": 1,
    "nrOfinfants": 2,
    "familyMemberList" : [
        {"familyName": "NOWAK",
        "givenName": "Ksawery",
        "age": 30
        },
        {"familyName": "NOWAK",
        "givenName": "Ewa",
        "age": 2
        },
        {"familyName": "NOWAK",
        "givenName": "Krystyna",
        "age": 15
        },
        {"familyName": "NOWAK",
        "givenName": "Katarzyna",
        "age": 3
        }
    ]
}

-----------------------------------------
GET - getFamily
-----------------------------------------
http://localhost:8020/getFamily/{numer rodziny}