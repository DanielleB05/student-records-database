A Java application for organizing and managing student academic records. It processes homework, quiz, and exam scores and exports two structured CSV reports summarizing each student’s performance.

## What it does:
-	Computes each student’s totals and averages across homework, quizzes, and exams
-	Calculates an overall final grade per student
-	Exports two output files:
    - summary.csv – one row per student with ID, name, final grade, and category tools (Homework, Quizzes, Exams)
    - details.csv – one row per student with a full breakdown of every individual assignment score (HW1-HW7, Quiz1-Quiz4, E1-E3)
Both outputs are formatted for direct import into Microsoft Excel.

## Example output:
summary.csv
| ID | Name | Final Grade | Homework | Quizzes | Exams |
|---|---|---|---|---|---|
| 15417503 | Jones, Corey | 68.65 | 402.0 | 338.0 | 253.0 |
| 17272764 | Carlson, Berna | 75.93 | 520.0 | 322.0 | 290.0 |

details.csv
| ID | Name | HW1 | HW2 | ... | Quiz1 | ... | E1 | E2 | E3 |
|---|---|---|---|---|---|---|---|---|---|
| 15417503 | Jones, Corey | 65.0 | 48.0 | ... | 82.0 | ... | 65.0 | 73.0 | 115.0 |


## Tech stack:
-	Language: Java
-	Entry point: PA2Main.java
-	Input/Output: CSV file parsing and generation

## Project structure:
| File | Purpose |
|---|---|
| `PA2Main.java` | Entry point; reads input files and triggers output generation |
| `GradebookManager.java` | Reads and parses input CSVs, builds student records |
| `GradebookReader.java` | Lower-level CSV line parsing and validation helpers |
| `StudentRecords.java` | Represents a single student's ID, name, and scores by category |
| `CSVFile.java` | Writes the `details.csv` and `summary.csv` output reports |


## Running the project:
1.	Clone the repository.
2.	Compile all source files: javac *.java
3.	Run the program: java PA2Main
With no arguments, this looks for six input files in a data/ subfolder relative to the working directory: homework_1.csv, homework_2.csv, quizzes_1.csv, quizzes_2.csv, exams_1.csv, exams_2.csv
Alternatively, pass your own input file paths directly: java PA2Main path/to/homework.csv path/to/quiz.csv path/to/exam.csv
4.	The program writes details.csv and summary.csv to the current directory.
