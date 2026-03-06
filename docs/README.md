# Snoopy User Guide
<img width="996" height="560" alt="image (5)" src="https://github.com/user-attachments/assets/def16a3f-3cb1-47dd-bb9e-3608fcf8e011" />
Snoopy is a desktop app for managing your personal tasks and schedules, optimized for use via a Command Line Interface (CLI). If you can type fast, Snoopy can get your task management done faster than traditional GUI apps.

# Quick Start
1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest `.jar` file from [here](https://github.com/omcodedthis/ip/releases).
3. Copy the file to a new folder as new folder containing the save data of the program is created in the current working directory of the file.
4. Start using the command `java -jar <insert release-name>.jar` in the terminal.

# Features
- Words in UPPER_CASE are the parameters to be supplied by the user (e.g. in todo TASK_NAME, TASK_NAME is a parameter which can be used as todo read book).
- Parameters are expected in the order specified in the command format.

## Adding a ToDo : `todo`
Adds a simple task without any date or time attached to it.

Format: `todo TASK_NAME`

Examples:
* `todo borrow book`
* `todo finish CS2113 assignment`

**Expected output for `todo`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
No problemo. I have added this ToDo to the list:
[T][ ] borrow book
Now you have 1 task(s) in the list.
_______________________________________________________________________________________________
```

## Adding a Deadline : `deadline`
Adds a task that needs to be done before a specific date/time.

Format: `deadline TASK_NAME /by DEADLINE`

Examples:
* `deadline return book /by Sunday`
* `deadline submit report /by 2026-02-28 1800`

**Expected output for `deadline`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
No problemo. I have added this Deadline to the list:
[D][ ] return book (by: Sunday)
Now you have 1 task(s) in the list.
_______________________________________________________________________________________________
```

## Adding an Event : `event`
Adds a task that starts at a specific time and ends at a specific time.

Format: `event TASK_NAME /from START_TIME /to END_TIME`

Examples:
* `event project meeting /from Mon 2pm /to 4pm`
* `event career fair /from 2026-03-01 1000 /to 2026-03-01 1800`

**Expected output for `event`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
No problemo. I have added this Event to the list:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 1 task(s) in the list.
_______________________________________________________________________________________________
```

## Listing all tasks : `list`
Shows a list of all tasks currently being tracked.

Format: `list`

Examples:
* `list`

**Expected output for `list`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
Here is everything I am tracking dawg:
     1. [T][ ] borrow book
     2. [D][ ] return book (by: Sunday)
     3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
_______________________________________________________________________________________________
```

## Marking a task as done or not done : `mark` / `unmark`
Marks an existing task in the list as completed or incomplete.

Format: 
* `mark INDEX`
* `unmark INDEX`

Examples:
* `mark 1`
* `unmark 1`

**Expected output for `mark`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
Marked this as done dawg:
[T][X] borrow book
_______________________________________________________________________________________________
```
**Expected output for `unmark`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
Marked this not done dawg, you gotta double up:
[T][ ] borrow book
_______________________________________________________________________________________________
```

## Locating tasks by name: `find`
Finds tasks whose description contains the given keyword. 

Format: `find KEYWORD`

Examples:
* `find book`
* `find meeting`

**Expected output for `find`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
Here is everything I found dawg:
     1. [T][ ] borrow book
     2. [D][ ] return book (by: Sunday)
_______________________________________________________________________________________________
```

## Deleting a task : `delete`
Deletes the specified task from the list.

Format: `delete INDEX`

Examples:
* `delete 1`

**Expected output for `delete`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
No problemo. I will delete this Deadline from the list:
[D][ ] return book
Now you have 0 task(s) in the list.
_______________________________________________________________________________________________
```

## Exiting the program : `bye`
Exits the program and saves your tasks.

Format: `bye`

Examples:
* `bye`

**Expected output for `bye`:**
```text
_______________________________________________________________________________________________
(Snoopy Says)
Ciao! See ya later.
_______________________________________________________________________________________________
```

