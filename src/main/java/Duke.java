import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        partition();

        // name of the robot in ASCII art
        String name = " _____ _                       _     _       _         \n" +
                "/  __ \\ |                     | |   | |     | |        \n" +
                "| /  \\/ |__   ___  ___ _______| |__ | | ___ | | __ ____\n" +
                "| |   | '_ \\ / _ \\/ _ \\_  / _ \\ '_ \\| |/ _ \\| |/ /|_  /\n" +
                "| \\__/\\ | | |  __/  __// /  __/ |_) | | (_) |   <  / / \n" +
                " \\____/_| |_|\\___|\\___/___\\___|_.__/|_|\\___/|_|\\_\\/___|";

        // introduction and initial prompt for user input
        System.out.print("Hello! My name is:\n" + name + "\n\n" +
                "What can I do for you today? :)\n" + ">>");

        // Scanner object for getting user input from the terminal
        Scanner scanner = new Scanner(System.in);

        // string to monitor current user input
        String userInput = scanner.nextLine();
        String[] userWords = userInput.split(" ");
        String userCommand = userWords[0];

        // Task array for storing all user inputted tasks, and integer indexer to monitor size of array,
        // assume number of tasks do not exceed 100
        Task[] tasks = new Task[100];
        int tasksIndex = 0;

        // if 'bye' command is given exit program, else keep prompting for user input
        while (!userCommand.equals("bye")) {
            // if 'list' command is given, list out all tasks
            if (userCommand.equals("list")) {
                // if list is empty, print 'no item' message instead of tasks
                if (tasksIndex == 0) {
                    System.out.println("No item stored in your list! :o");
                } else {
                    System.out.println("Here are the item(s) in your list. :)");
                    // print out tasks and number each task
                    for (int i = 0; i < tasksIndex; i++) {
                        System.out.println(i + 1 + ". " + tasks[i]);
                    }
                }
            }
            // if "mark" command is given, mark the corresponding task in tasks
            else if (userCommand.equals("mark")) {
                // split userInput into command and integer
                try {
                    int selectedItem = Integer.parseInt(userWords[1]);
                    // check if integer given is in range of number of tasks
                    tasks[selectedItem - 1].setMarked(true);
                    System.out.println("Task " + selectedItem + " marked!\n" +
                            tasks[selectedItem - 1]);
                }catch (Exception e) {
                    System.out.println("Invalid integer input! :(");
                }
            }
            // if "unmark" command is given, unmark the corresponding task in tasks
            else if (userCommand.equals("unmark")) {
                // split userInput into command and integer
                try {
                    int selectedItem = Integer.parseInt(userWords[1]);
                    // check if integer given is in range of number of tasks
                    tasks[selectedItem - 1].setMarked(false); // unmark task
                    System.out.println("Task " + selectedItem + " unmarked!\n" +
                            tasks[selectedItem - 1]);
                }catch (Exception e) {
                    System.out.println("Invalid integer input! :(");
                }
            }
            // if "todo" command is given
            else if (userCommand.equals("todo")) {
                // store information of todo: Name
                String[] todoNameWords = Arrays.copyOfRange(userWords, 1, userWords.length);
                String todoName = String.join(" ", todoNameWords);
                Todo todo = new Todo(todoName);
                tasks[tasksIndex] = todo; // Store user input into array
                tasksIndex++; // Increase String array index
                System.out.println("I've added this task to your list:");
                System.out.println(todo);
                System.out.println("You now have " + tasksIndex + " task(s) in your list. :]");
            }
            // if "event" command is given
            else if (userCommand.equals("event")) {
                // find the index of "/from" and "/to" to separate the information
                int fromSplitIndex = 0;
                int toSplitIndex = 0;
                for(int i = 1; i < userWords.length; i++) {
                    if (userWords[i].equals("/from")) {
                        fromSplitIndex = i;
                    }
                    else if (userWords[i].equals("/to")) {
                        toSplitIndex = i;
                        break;
                    }
                }
                // store information of event: Name, From and To
                String[] eventNameWords = Arrays.copyOfRange(userWords, 1, fromSplitIndex);
                String[] eventFromWords = Arrays.copyOfRange(userWords, fromSplitIndex + 1, toSplitIndex);
                String[] eventToWords = Arrays.copyOfRange(userWords, toSplitIndex + 1, userWords.length);
                String eventName = String.join(" ", eventNameWords);
                String eventFrom = String.join(" ", eventFromWords);
                String eventTo = String.join(" ", eventToWords);
                Event event = new Event(eventName, eventFrom, eventTo); // New Event object
                tasks[tasksIndex] = event; // Store user input into array
                tasksIndex++; // Increase String array index
                System.out.println("I've added this task to your list:");
                System.out.println(event);
                System.out.println("You now have " + tasksIndex + " task(s) in your list. :]");
            }
            // if "deadline" command is given
            else if (userCommand.equals("deadline")) {
                // find the index of "/by" to separate the information
                int splitIndex = 0;
                for(int i = 1; i < userWords.length; i++) {
                    if (userWords[i].equals("/by")) {
                        splitIndex = i;
                        break;
                    }
                }
                // store information of deadline: Name and By
                String[] deadlineNameWords = Arrays.copyOfRange(userWords, 1, splitIndex);
                String[] deadlineByWords = Arrays.copyOfRange(userWords, splitIndex + 1, userWords.length);
                String deadlineName = String.join(" ", deadlineNameWords);
                String deadlineBy = String.join(" ", deadlineByWords);
                Deadline deadline = new Deadline(deadlineName, deadlineBy); // New Deadline object
                tasks[tasksIndex] = deadline; // Store user input into array
                tasksIndex++; // Increase String array index
                System.out.println("I've added this task to your list:");
                System.out.println(deadline);
                System.out.println("You now have " + tasksIndex + " task(s) in your list. :]");
            }
            // if not unique command, prompt user again
            else {
                System.out.println("Invalid command! :/");
            }

            partition();

            // prompt user for input and store it
            System.out.print("What do you want to do next? :o\n" + ">>");
            userInput = scanner.nextLine();
            userWords = userInput.split(" ");
            userCommand = userWords[0];
        }

        // program exit statement
        System.out.println(" Bye. Hope to see you again soon! :D");

        partition();
        System.out.println("                          -END-                             ");
    }

    // private function to print a stream of underscores for partitioning robot conversation
    private static void partition() {
        System.out.println("____________________________________________________________");
    }
}