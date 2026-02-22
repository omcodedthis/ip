package snoopy.storage;

import snoopy.task.Deadline;
import snoopy.task.Event;
import snoopy.task.Task;
import snoopy.task.TaskList;
import snoopy.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles reading from and writing to the persistent storage file for Snoopy.
 * Ensures that the task list is saved between sessions.
 */
public class Storage {

    public static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(CURRENT_WORKING_DIRECTORY, "data", "SnoopyData.txt");
    private File snoopyDataFile;

    /**
     * Initializes the storage management.
     * Checks for the existence of the data directory and the storage text file, SnoopyData.txt.
     * If they do not exist, it creates them.
     */
    public Storage() {
        snoopyDataFile = FILE_PATH.toFile();

        try {
            File parentDir = snoopyDataFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!snoopyDataFile.exists()) {
                snoopyDataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Yo dawg, something went wrong when creating the file: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the storage file, parses them, and returns them as a list.
     * Invalid or corrupted lines in the text file are skipped with a warning printed to the console.
     *
     * @return A TaskList object containing the saved Tasks. Returns an empty TaskList if the file is empty or missing.
     */
    public TaskList loadFromFile() {
        TaskList taskList = new TaskList();

        try {
            Scanner scanner = new Scanner(snoopyDataFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s*\\|\\s*");

                if (parts.length < 3) {
                    System.out.println("Snoopy skipped a corrupted line (too short): " + line);
                    continue;
                }

                String type = parts[0];
                String isDoneString = parts[1];

                if (!isDoneString.equals("true") && !isDoneString.equals("false") &&
                        !isDoneString.equals("1") && !isDoneString.equals("0")) {
                    System.out.println("Snoopy skipped a line with invalid status: " + line);
                    continue;
                }

                boolean isDone = isDoneString.equals("true") || isDoneString.equals("1");
                String description = parts[2];
                Task task = null;

                switch (type) {
                case "T":
                    if (parts.length == 3) {
                        task = new ToDo(description);
                    } else {
                        System.out.println("Snoopy skipped a corrupted ToDo: " + line);
                    }
                    break;
                case "D":
                    if (parts.length == 4) {
                        task = new Deadline(description, parts[3]);
                    } else {
                        System.out.println("Snoopy skipped a corrupted Deadline: " + line);
                    }
                    break;
                case "E":
                    if (parts.length == 5) {
                        task = new Event(description, parts[3], parts[4]);
                    } else {
                        System.out.println("Snoopy skipped a corrupted Event: " + line);
                    }
                    break;
                default:
                    System.out.println("Snoopy skipped an unknown task type: " + line);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    taskList.addTasktoList(task);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Yo dawg, no existing data file found. Starting fresh!");
        }

        return taskList;
    }

    /**
     * Writes the current list of tasks to the storage file.
     * Overwrites the existing file content with the updated Tasks.
     *
     * @param taskList The TaskList containing the Task objects to be saved.
     */
    public void saveToFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(snoopyDataFile, false);

            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTaskFromIndex(i);
                writer.write(task.toStringForStorage() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Yo dawg, I could not save your data! " + e.getMessage());
        }
    }
}
