import java.util.ArrayList;
import java.util.Scanner;

public class TaskListApp {
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to Task List Application!");
        System.out.println("Available commands: add, remove, list, exit");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("add")) {
                addTask(scanner);
            } else if (command.equalsIgnoreCase("remove")) {
                removeTask(scanner);
            } else if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added.");
    }

    private static void removeTask(Scanner scanner) {
        System.out.print("Enter the task number to remove: ");
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(scanner.nextLine());
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                tasks.remove(taskNumber - 1);
                System.out.println("Task removed.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task number.");
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Task List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
