                                    Java Calculator Project

## About

This is a calculator project I built using Java as part of my internship practice.
It has both a console version and a GUI version using Swing.

The main idea of this project is to understand how calculations work in real applications and how to handle errors properly.

---

## Features

* Basic operations (Add, Subtract, Multiply, Divide)
* Square root and power functions
* Temperature conversion (Celsius ↔ Fahrenheit)
* Currency conversion (USD ↔ INR)
* GUI calculator using buttons (Swing)
* Continuous execution in console
* Proper error handling

---

## What I used

* Core Java
* Swing (for GUI)
* BigDecimal (for accurate calculations)
* Scanner (for input)

---

## How to run (Maven)

This project uses **Maven** for dependencies and standard build architecture.

To compile:
```bash
mvn clean compile
```

Run GUI version:
```bash
mvn exec:java -Dexec.mainClass="com.calculator.SwingCalculator"
```

Run Console version:
```bash
mvn exec:java -Dexec.mainClass="com.calculator.Calculator"
```

---

## Notes

* I used BigDecimal instead of double to avoid precision issues like 0.1 + 0.2
* Square root is calculated using Math.sqrt (so it uses double internally)
* Currency values are fixed (not real-time)

---

## What I learned

* How to handle user input errors
* How to use BigDecimal for precise calculations
* How GUI works using Swing
* How to structure code into methods
* How event handling works (ActionListener)

---

## Future improvements

* Add expression input like "5 + 3 * 2"
* Improve GUI design
* Use real-time currency API
