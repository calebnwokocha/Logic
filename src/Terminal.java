import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Logic.*;

public class Terminal {
    private static final Map<String, Express> expressions = new HashMap<>();

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
            } catch (Exception e) {
                System.out.println("Error0: " + e.getMessage() + ". Type 'HELP' for help.");
            }
        }
        scanner.close();
    }

    private static void handleCommand(String command) {
        if (command.equalsIgnoreCase("HELP")) {
            printHelp();
        } else if (command.startsWith("Variable")) {
            handleVariable(command);
        } else if (command.startsWith("Express")) {
            handleExpression(command);
        } else if (command.contains(".verify()")) {
            handleVerifyExpression(command);
        } else {
            System.out.println("Syntax " + command + " is invalid.");
        }
    }

    private static void handleVariable(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid variable definition syntax.");
        }
        String name = parts[0].trim().split(" ")[1];
        String valueString = parts[1].trim().replace("new Variable(", "").replace(")", "");
        boolean value = Boolean.parseBoolean(valueString);
        expressions.put(name, new Variable(value));
        System.out.println("Defined variable " + name + " with value " + value);
    }

    private static void handleExpression(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid expression definition syntax.");
        }
        String name = parts[0].trim().split(" ")[1];
        String[] exprParts = parts[1].trim().replace(";", "").split("\\.");
        if (exprParts.length < 2) {
            throw new IllegalArgumentException("Invalid expression syntax.");
        }
        Express result = expressions.get(exprParts[0].trim());
        if (result == null) {
            throw new IllegalArgumentException("Expression " + exprParts[0] + " not found.");
        }

        for (int i = 1; i < exprParts.length; i++) {
            String[] subParts = exprParts[i].split("\\(");
            String operator = subParts[0].trim();
            String argumentPart = subParts[1].replace(")", "").trim();

            if (operator.equals("thereExist") || operator.equals("forAll")) {
                String[] args = argumentPart.split(",");
                Express[] expressionsArgs = new Express[args.length];
                for (int j = 0; j < args.length; j++) {
                    expressionsArgs[j] = expressions.get(args[j].trim());
                    if (expressionsArgs[j] == null) {
                        throw new IllegalArgumentException("Expression " + args[j].trim() + " not found.");
                    }
                }
                if (operator.equals("thereExist")) {
                    result = result.thereExist(expressionsArgs);
                } else {
                    result = result.forAll(expressionsArgs);
                }
            } else {
                Express rightExpression = null;
                if (!argumentPart.isEmpty()) {
                    rightExpression = expressions.get(argumentPart);
                    if (rightExpression == null) {
                        throw new IllegalArgumentException("Expression " + argumentPart + " not found.");
                    }
                }

                result = switch (operator) {
                    case "and" -> result.and(rightExpression);
                    case "or" -> result.or(rightExpression);
                    case "not" -> result.not();
                    case "equate" -> result.equate(rightExpression);
                    case "imply" -> result.imply(rightExpression);
                    case "iff" -> result.iff(rightExpression);
                    default -> throw new IllegalArgumentException("Unknown operator: " + operator + ".");
                };
            }
        }
        expressions.put(name, result);
        System.out.println("Defined expression " + name);
    }

    private static void handleVerifyExpression(String command) {
        String[] parts = command.split("\\.");
        if (parts.length != 2 || !parts[1].equals("verify()")) {
            throw new IllegalArgumentException("Invalid verify syntax.");
        }
        Express result = expressions.get(parts[0]);
        if (result == null) {
            throw new IllegalArgumentException("Expression " + parts[0] + " not found.");
        }
        System.out.println("Result: " + result.verify());
    }

    private static void printHelp() {
        System.out.println("Logic Terminal Commands:");
        System.out.println("1. Define a variable: Variable varName = new Variable(true|false)");
        System.out.println("   Example: Variable varA = new Variable(true)");
        System.out.println("2. Define an expression: Express expName = varName.operator(varName|formulaName|expression)");
        System.out.println("   Operators: and, or, not, equate, imply, iff, thereExist, forAll");
        System.out.println("   Example: Express exp1 = varA.and(varB).imply(varA).and(varB)");
        System.out.println("3. Verify an expression: expression.verify();");
        System.out.println("   Example: exp1.verify()");
        System.out.println("5. Exit the Terminal: EXIT");
        System.out.println("Software Author: Caleb Princewill N. (calebnwokocha@gmail.com)");
    }
}
