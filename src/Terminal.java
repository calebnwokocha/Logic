import java.io.*;
import java.util.*;

import Logic.*;

public class Terminal {
    private static final Map<String, Express> map = new HashMap<>();
    private static final List<String> commandHistory = new ArrayList<>();
    private static final String SAVE_FILE = "commands.txt";
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Logic Terminal. Type 'HELP' for help.");
        while (true) {
            System.out.print("Terminal: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("EXIT")) {
                break;
            }
            try {
                handleCommand(command);
                if (!isSystemCommand(command)) {
                    commandHistory.add(command);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Type 'HELP' for help.");
            }
        }
        scanner.close();
    }

    private static boolean isSystemCommand(String command) {
        return command.equalsIgnoreCase("LOAD") || command.equalsIgnoreCase("SAVE") ||
                command.equalsIgnoreCase("HELP") || command.equalsIgnoreCase("EXPLORE") ||
                command.contains(".verify()");
    }

    private static void handleCommand(String command) throws IOException {
        if (command.equalsIgnoreCase("HELP")) {
            printHelp();
        } else if (command.startsWith("Variable")) {
            handleVariable(command);
        } else if (command.startsWith("Express")) {
            handleExpression(command);
        } else if (command.contains(".verify()")) {
            handleVerification(command);
        } else if (command.equalsIgnoreCase("SAVE")) {
            saveCommands();
        } else if (command.equalsIgnoreCase("LOAD")) {
            loadCommands();
        } else if (command.equalsIgnoreCase("EXPLORE")) {
            generateRandomExpression();
        } else {
            throw new IllegalArgumentException("Syntax " + command + " is invalid.");
        }
    }

    private static void handleVariable(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid variable syntax.");
        }
        String name = parts[0].trim().split(" ")[1];
        String valueString = parts[1].trim().replace("new Variable(", "").replace(")", "");
        boolean value = Boolean.parseBoolean(valueString);
        map.put(name, new Variable(value));
        System.out.println("Defined variable " + name + " with value " + value);
    }

    private static void handleExpression(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid expression syntax.");
        }
        String name = parts[0].trim().split(" ")[1];
        String[] exprParts = parts[1].trim().split("\\.");
        if (exprParts.length < 2) {
            throw new IllegalArgumentException("Invalid expression syntax.");
        }
        Express result = map.get(exprParts[0].trim());
        if (result == null) {
            throw new IllegalArgumentException("Expression " + exprParts[0] + " not found.");
        }

        for (int i = 1; i < exprParts.length; i++) {
            String[] subParts = exprParts[i].split("\\(");
            String operator = subParts[0].trim();
            String argumentPart = subParts[1].replace(")", "").trim();

            if (operator.equals("there_exist") || operator.equals("for_all")) {
                handleQuantifiedExpression(result, operator, argumentPart);
            } else {
                result = handleBinaryOrUnaryExpression(result, operator, argumentPart);
            }
        }
        map.put(name, result);
        System.out.println("Defined expression " + name);
    }

    private static void handleQuantifiedExpression(Express result, String operator, String argumentPart) {
        String[] args = argumentPart.split(",");
        Express[] expressionsArgs = new Express[args.length];
        for (int j = 0; j < args.length; j++) {
            expressionsArgs[j] = map.get(args[j].trim());
            if (expressionsArgs[j] == null) {
                throw new IllegalArgumentException("Expression " + args[j].trim() + " not found.");
            }
        }
        if (operator.equals("there_exist")) {
            result.there_exist(expressionsArgs);
        } else {
            result.for_all(expressionsArgs);
        }
    }

    private static Express handleBinaryOrUnaryExpression(Express result, String operator, String argumentPart) {
        Express rightExpression = null;
        if (!argumentPart.isEmpty()) {
            rightExpression = map.get(argumentPart);
            if (rightExpression == null) {
                throw new IllegalArgumentException("Expression " + argumentPart + " not found.");
            }
        }

        return switch (operator) {
            case "and" -> result.and(rightExpression);
            case "or" -> result.or(rightExpression);
            case "not" -> result.not(rightExpression);
            case "equate" -> result.equate(rightExpression);
            case "imply" -> result.imply(rightExpression);
            case "iff" -> result.iff(rightExpression);
            default -> throw new IllegalArgumentException("Unknown operator: " + operator + ".");
        };
    }

    private static void handleVerification(String command) {
        String[] parts = command.split("\\.");
        if (parts.length != 2 || !parts[1].equals("verify()")) {
            throw new IllegalArgumentException("Invalid verification syntax.");
        }
        Express result = map.get(parts[0]);
        if (result == null) {
            throw new IllegalArgumentException("Expression " + parts[0] + " not found.");
        }
        System.out.println("Result: " + result.verify());
    }

    private static void printHelp() {
        System.out.println("Logic Terminal Commands:");
        System.out.println("1. Define a variable: Variable var_name = new Variable(true|false)");
        System.out.println("   Example: Variable varA = new Variable(true)");
        System.out.println("2. Define an expression: Express expName = varName.operator(varName|expression)");
        System.out.println("   Operators: and, or, not, equate, imply, iff, there_exist, for_all");
        System.out.println("   Example: Express exp1 = varA.and(varB).imply(varA).and(varB)");
        System.out.println("3. Verify an expression: expression.verify()");
        System.out.println("   Example: exp1.verify()");
        System.out.println("4. Save commands: SAVE");
        System.out.println("5. Load commands: LOAD");
        System.out.println("6. Generate random expression: EXPLORE");
        System.out.println("7. Exit the Terminal: EXIT");
        System.out.println("Software Author: Caleb Princewill N. (calebnwokocha@gmail.com)");
    }

    private static void saveCommands() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE, true))) {
            for (String command : commandHistory) {
                writer.println(command);
            }
            commandHistory.clear();
            System.out.println("Command(s) saved successfully.");
        }
    }

    private static void loadCommands() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                handleCommand(line);
            }
            System.out.println("Command(s) loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved command(s) found.");
        }
    }

    private static void generateRandomExpression() {
        if (map.size() < 2) {
            System.out.println("Not enough data saved to explore.");
            return;
        }

        List<String> keys = new ArrayList<>(map.keySet());
        String name1 = keys.get(random.nextInt(keys.size()));
        String name2 = keys.get(random.nextInt(keys.size()));
        String operator = getRandomOperator();

        String expressionName = "exp" + (map.size() + 1);
        String randomExpressionCommand;

        if (operator.equals("there_exist") || operator.equals("for_all")) {
            int numArgs = random.nextInt(keys.size()) + 1;
            StringBuilder args = new StringBuilder();
            for (int i = 0; i < numArgs; i++) {
                if (i > 0) {
                    args.append(",");
                }
                args.append(keys.get(random.nextInt(keys.size())));
            }
            randomExpressionCommand = String.format("Express %s = %s.%s(%s)", expressionName, name1, operator, args.toString());
        } else {
            randomExpressionCommand = String.format("Express %s = %s.%s(%s)", expressionName, name1, operator, name2);
        }

        try {
            handleCommand(randomExpressionCommand);
            commandHistory.add(randomExpressionCommand);
        } catch (Exception e) {
            System.out.println("Error exploring: " + e.getMessage());
        }
    }

    private static String getRandomOperator() {
        String[] operators = {"and", "or", "not", "equate", "imply", "iff", "there_exist", "for_all"};
        return operators[random.nextInt(operators.length)];
    }
}
