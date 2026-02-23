# Snoopy User Guide
<img width="1359" height="560" alt="image" src="https://github.com/user-attachments/assets/b345b675-351f-4217-8388-83d780c296e6" />
Snoopy is a desktop app for managing your personal tasks and schedules, optimized for use via a Command Line Interface (CLI). If you can type fast, Snoopy can get your task management done faster than traditional GUI apps.

# Features
- Words in UPPER_CASE are the parameters to be supplied by the user (e.g. in todo TASK_NAME, TASK_NAME is a parameter which can be used as todo read book).
- Parameters are expected in the order specified in the command format.

## Adding a ToDo : `todo`
Adds a simple task without any date or time attached to it.

Format: `todo TASK_NAME`

Examples:
* `todo borrow book`
* `todo finish CS2113 assignment`

Adds the specified ToDo task to the tracking list and displays a confirmation message with the current total number of tasks.

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

Adds the specified Deadline task to the list, formats the deadline time, and displays a confirmation message with the current total number of tasks.

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

Adds the specified Event task to the list, formats the start and end times, and displays a confirmation message with the current total number of tasks.

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

Iterates through your saved tasks and prints them out in a numbered list, including their type, completion status, and attached dates.

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

Updates the status icon of the task at the specified index to an `[X]` (for completed) or `[ ]` (for incomplete) and prints a confirmation message. The index must be a valid positive integer based on the `list` output.

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
Finds tasks whose descriptions contain the given keyword. 

Format: `find KEYWORD`

Examples:
* `find book`
* `find meeting`

Searches through all tracked task descriptions for the specified keyword (case-insensitive) and returns a numbered list of all matching tasks.

```text
_______________________________________________________________________________________________
(Snoopy Says)
Here is everything I found dawg:
     1. [T][ ] borrow book
     2. [D][ ] return book (by: Sunday)
_______________________________________________________________________________________________
```
