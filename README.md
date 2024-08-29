# School Billing Application

Create an application using Java Spring Boot that exposes two endpoints via REST API.

## Task

The application should expose the following two endpoints:
1. Perform a billing for a school with a given ID for a specified month.
2. Perform a billing for a specific parent in a school for a specified month.

## Domain Description

The application will work with the following entities:

- **School** (`id`, `name`, `hour_price`)
- **Child** (`id`, `firstname`, `lastname`, `parent_id`, `school_id`)
- **Parent** (`id`, `firstname`, `lastname`)
- **Attendance** (`id`, `child_id`, `entry_date`, `exit_date`)

### Data Constraints

- You can assume that no data in the tables is null.
- **School**:
  - `name` - name of the school
  - `hour_price` - hourly rate for attendance, accurate to 2 decimal places
- **Child**:
  - `firstname` - child’s first name
  - `lastname` - child’s last name
  - `parent_id` - ID of the parent
  - `school_id` - ID of the school the child attends
- **Parent**:
  - `firstname` - parent's first name
  - `lastname` - parent's last name
- **Attendance**:
  - `child_id` - ID of the child the record pertains to
  - `entry_date` - datetime marking the child's entry to the school
  - `exit_date` - datetime marking the child's exit from the school
    - *(You can assume that `entry_date <= exit_date`)*

## Billing Rules

- Charges are applied for every started hour.
- The free period for the child’s stay at school is from 7:00 AM to 12:00 PM.
- Example: If a child is brought in at 6:59 AM and picked up at 12:01 PM, 2 chargeable hours should be billed.

## Output

The application should return the billing details in JSON format, including:
- Parent details.
- The total charges calculated for their children.
- A breakdown of the charges per child, including the time they spent at school.

---
# Original task description in Polish:

Stwórz aplikację w technologii Java Spring Boot, która przy pomocy REST API wystawi 2 endpointy (endpointy wg twojego uznania):

1. wykonujące rozliczenie szkoły z podanym id dla zadanego miesiąca.
2. wykonujące rozliczenie danego rodzica w szkole w zadanym miesiącu.

## Opis domeny:

- **School** (`id`, `name`, `hour_price`)
- **Child** (`id`, `firstname`, `lastname`, `parent_id`, `school_id`)
- **Parent** (`id`, `firstname`, `lastname`)
- **Attendance** (`id`, `child_id`, `entry_date`, `exit_date`)

Można założyć, że żadne dane w tabelach nie są nullami.

- **School**:
  - `name` - nazwa
  - `hour_price` - stawka godzinowa za pobyt, z dokładnością do 2 liczb po przecinku
- **Child**:
  - `firstname` - imię dziecka
  - `lastname` - nazwisko dziecka
  - `parent_id` - id rodzica
  - `school_id` - id szkoły, do której chodzi dziecko
- **Parent**:
  - `firstname` - imię rodzica
  - `lastname` - nazwisko rodzica
- **Attendance**:
  - `child_id` - id dziecka, którego dotyczy wpis
  - `entry_date` - datetime określający czas wejścia do przedszkola
  - `exit_date` - datetime określający czas wyjścia z przedszkola
    - *(Możemy założyć, że entry_date >= exit_date)*

## Oplaty naliczane sa za kazda rozpoczeta godzinę:

- Darmowy okres przebywania dziecka w przedszkolu to 7:00 - 12:00.
- Czyli przyprowadzajac dziecko o 6:59 a odbierajac je 12:01 nalezy naliczyc 2 platne godziny.

## Rozliczenia:

- Mają być zwracane w postaci JSON.
- Muszą zawierać dane rodzica(ów), sumę naliczonych opłat dla ich dzieci, a także składowe opłaty naliczone na poszczególne dzieci wraz z czasem, jaki spędziły w szkole.
