# FamilyApp
-------------------------------------------------------------------
### INSTRUKCJA ###

1. Informacje techniczne :
- docker
- baza danych PostgreSQL 9.6
- java 11

2. Budowa i uruchomienie obrazów docker:
    1. Dla każdego pobranego repozytorium aplikacji (FamilyApp, FamilyMemberApp, FamilyDatabase) uruchom w terminalu
    polecenie : mvn clean install

    2. Dla poszegolnych repozytoriow (w scieżce repozytorium) wykonaj polecenia budowy:
    /FamilyMemberApp:
          docker build . -t family-member-app
     /FamilyDatabase:
          docker build . -t family-database
     3. Uruchom terminal w /FamilyApp i wykonaj:
          docker compose up
     4. Przetestuj działanie aplikacji poniższymi przykładami

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