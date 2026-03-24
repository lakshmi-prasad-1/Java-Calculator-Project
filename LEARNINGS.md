                                  Learnings from Calculator Project

This project helped me understand many important concepts in Java.

## Things I understood

* Difference between double and BigDecimal
  → double gives wrong precision sometimes
  → BigDecimal gives exact values

* Exception handling
  → program should not crash if user gives wrong input

* Methods and modular code
  → dividing logic into functions makes code cleaner

* GUI basics
  → how buttons work
  → how events are handled

---

## Problems I faced

1. Invalid input crashing program
   → solved using try-catch

2. Division by zero
   → added check before dividing

3. BigDecimal confusion
   → learned how to use add(), subtract(), multiply(), divide()



## Hardest Bug I Faced

One of the hardest issues I faced was handling invalid user input.
If the user entered text instead of numbers, the program was crashing.

## How I Fixed It

I fixed this by using try-catch blocks and handling InputMismatchException.
I also cleared the input buffer using scanner.nextLine() to prevent repeated errors.

---

## What I improved

* Added scientific calculations
* Added conversion features
* Built GUI version of calculator

---
## Overall

This project gave me confidence in Java basics and helped me understand how real applications handle user input and calculations.