Stwórz aplikację w technologii Java Spring Boot , która przy pomocy REST API wystawi 2
endpointy (endpointy wg twojego uznania):

1. wykonujące rozliczenie szkoły z podanym id dla zadanego miesiąca.
2. wykonujące rozliczenie danego rodzica w szkole w zadanym miesiącu.

Opis domeny:
School (id, name, hour_price)
Child (id, firstname, lastname, parent_id, school_id)
Parent (id, firstname, lastname)
Attendance (id, child_id, entry_date, exit_date)

Można założyć, że żadne dane w tabelach nie są nullami.
School:
name - nazwa,
hour_price - stawka godzinowa za pobyt, z dokładnością do 2 liczb po przecinku,

Child:
firstname - imię dziecka
lastname - nazwisko dziecka,
parent_id - id rodzica,
school_id - id szkoły, do której chodzi dziecko

Parent:
firstname - imię rodzica,
lastname - nazwisko rodzica

Attendance:
child_id - id dziecka, którego dotyczy wpis
entry_date - datatime określający czas wejścia do przedszkola
exit_date - datetime określający czas wyjścia z przedszkola
(Możemy założyć, że entry_date >= exit_date)

Oplaty naliczane sa za kazda rozpoczeta godzinę .
Darmowy okres przebywania dziecka w przedszkolu to 7:00 - 12:00
Czyli przyprowadzajac dziecko o 6:59 a odbierajac je 12:01 nalezy naliczyc 2 platne
godziny.

Rozliczenia:

- mają być zwracane w postaci JSON
- muszą zawierać dane rodzica(ów), sumę naliczonych opłat dla ich dzieci, a także składowe
  opłaty naliczone na poszczególne dzieci wraz z czasem jaki spędziły w szkole.