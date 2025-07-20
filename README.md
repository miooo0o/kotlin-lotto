# kotlin-lotto

## [step 1] Features

### Lotto Class

- [x] 6 unique numbers
- [x] in range 1 - 45
- [x] Lotto instance has toString

### WinningNumbers Class

- [x] valid lotto tickets inside winning numbers class
- [x] valid 1 bonus unique number inside winning numbers class

### Rank

- [x] return valid Rank depend on match count, bonus number and if required
- [x] implement valueOf

### Controller

- [x] Add the controller where happen the flow of the app
- [x] Use View classes for input and output
- [x] Create a retry help functions to validate the input and in case of error, retry it.
- [x] Create a method or service to create the Lotto tickets
- [x] Create retry helper function to get user input
- [x] Add a generateLottoTickets service

### Services

- [x] add Lotto service
- [x] add Validator for the purchase amount of user input
- [x] Add a service to generate the lotto tickets from the amount passed
- [x] minimum ticket: 1000 KRW

### InputView

- [x] Get input
    - [x] Please enter the purchase amount.
    - [x] Please enter last week’s winning numbers.
    - [x] Please enter the bonus number.

### OutputView

- [x] Print out the purchased tickets
- [x] lotto statistics
    - [x] lotto statistics with prices
    - [x] print lotto statistics

### refactors

- [x] create Const value, no magic number
- [x] customize exception for DRY
- [x] add documentation
- [x] separate controller logics
- [x] refactor methods

---


---

## [step 2] Features

### Feedback

#### Application
- [x] separate `println()` into `OutputView`
- [x] refactor if needed

### Output View
- [x] collect `const` message and print logic

### Input View 
- [x] collect read and retry logic

#### Utils
- [x] merge into domain
  - [x] move to domain logic

#### Refactor
- [x] fix and clarify condition: `(size == 0 || size == 1)`

### LottoNumber
- [x] wrap `Int` to `LottoNumber`
- [x] validate itself
  - [x] require: in range (1 ~ 45)
  - [x] require: no duplicated numbers
- [x] override `equals`, `hashCode`
- [x] implement `from` function
- [x] override `toString`

### LottoTicket
- [x] wrap `HashSet<LottoNumber>` to `LottoTicket`
- [x] require: sufficient size, 6
- [x] override `toString`

### IssuedTickets
- [x] wrap `List<LottoTicket>` to `IssuedTickets`

### TicketIssuer
- [x] implement a flow to issue ticket 
  - get PendingTickets(List<Set<Int>>) → create LottoTickets → return IssuedTickets

### LottoTicketFactory
- [x] implement feature to issue a ticket

### Test
- [ ] add **E2E test**

### todo
- [x] issue 2 different types:
  - [x] manual
  - [x] random