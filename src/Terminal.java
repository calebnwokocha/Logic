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
                System.out.println("Error: " + e.getMessage() + ". Type 'HELP' for help.");
            }
        }
        scanner.close();
    }

    private static void handleCommand(String command) {
        if (command.equalsIgnoreCase("HELP")) {
            printHelp();
        } else if (command.startsWith("Variable")) {
            handleDefineVariable(command);
        } else if (command.startsWith("Formula")) {
            handleDefineFormula(command);
        } else if (command.startsWith("Express")) {
            handleDefineExpression(command);
        } else if (command.contains(".verify()")) {
            handleVerifyExpression(command);
        } else {
            handleExpression(command);
        }
    }

    private static void handleDefineVariable(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid variable definition syntax. Type 'HELP' for help.");
        }
        String name = parts[0].trim().split(" ")[1];
        String valueString = parts[1].trim().replace("new Variable(", "").replace(")", "");
        boolean value = Boolean.parseBoolean(valueString);
        expressions.put(name, new Variable(value));
        System.out.println("Defined variable " + name + " with value " + value);
    }

    private static void handleDefineFormula(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid formula definition syntax. Type 'HELP' for help.");
        }
        String name = parts[0].trim().split(" ")[1];
        String expressionName = parts[1].trim().replace(";", "")
                .replace("new Formula(", "").replace(")", "");
        Express expression = expressions.get(expressionName);
        if (expression == null) {
            throw new IllegalArgumentException("Expression " + expressionName + " not found. Type 'HELP' for help.");
        }
        expressions.put(name, new Formula(expression));
        System.out.println("Defined formula " + name + " with expression " + expressionName);
    }

    private static void handleDefineExpression(String command) {
        String[] parts = command.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid expression definition syntax. Type 'HELP' for help.");
        }
        String name = parts[0].trim().split(" ")[1];
        String[] exprParts = parts[1].trim().replace(";", "").split("\\.");
        if (exprParts.length < 2) {
            throw new IllegalArgumentException("Invalid expression syntax. Type 'HELP' for help.");
        }
        Express result = expressions.get(exprParts[0]);
        if (result == null) {
            throw new IllegalArgumentException("Expression " + exprParts[0] + " not found. Type 'HELP' for help.");
        }
        for (int i = 1; i < exprParts.length; i++) {
            String[] subParts = exprParts[i].split("\\(");
            String operator = subParts[0];
            String argumentPart = subParts[1].replace(")", "");

            Express rightExpression = null;
            if (!argumentPart.isEmpty()) {
                rightExpression = expressions.get(argumentPart);
                if (rightExpression == null) {
                    throw new IllegalArgumentException("Expression " + argumentPart + " not found. Type 'HELP' for help.");
                }
            }

            result = switch (operator) {
                case "and" -> result.and(rightExpression);
                case "or" -> result.or(rightExpression);
                case "not" -> result.not();
                case "equate" -> result.equate(rightExpression);
                case "imply" -> result.imply(rightExpression);
                case "thereExist" -> Formula.thereExist(rightExpression);
                case "forAll" -> Formula.forAll(rightExpression);
                default -> throw new IllegalArgumentException("Unknown operator: " + operator + ". Type 'HELP' for help.");
            };
        }
        expressions.put(name, result);
        System.out.println("Defined expression " + name);
    }

    private static void handleExpression(String command) {
        String[] parts = command.split("\\.");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid expression syntax. Type 'HELP' for help.");
        }
        Express result = expressions.get(parts[0]);
        if (result == null) {
            throw new IllegalArgumentException("Expression " + parts[0] + " not found. Type 'HELP' for help.");
        }

        for (int i = 1; i < parts.length; i++) {
            String[] subParts = parts[i].split("\\(");
            String operator = subParts[0];
            String argumentPart = subParts[1].replace(")", "");

            Express rightExpression = null;
            if (!argumentPart.isEmpty()) {
                rightExpression = expressions.get(argumentPart);
                if (rightExpression == null) {
                    throw new IllegalArgumentException("Expression " + argumentPart + " not found. Type 'HELP' for help.");
                }
            }

            result = switch (operator) {
                case "and" -> result.and(rightExpression);
                case "or" -> result.or(rightExpression);
                case "not" -> result.not();
                case "equate" -> result.equate(rightExpression);
                case "imply" -> result.imply(rightExpression);
                case "thereExist" -> Formula.thereExist(rightExpression);
                case "forAll" -> Formula.forAll(rightExpression);
                default -> throw new IllegalArgumentException("Unknown operator: " + operator + ". Type 'HELP' for help.");
            };
        }
        String resultName = command.replace(".", "_").replace("(", "_").replace(")", "_");
        expressions.put(resultName, result);
        System.out.println("Result stored in: " + resultName);
    }

    private static void handleVerifyExpression(String command) {
        String[] parts = command.split("\\.");
        if (parts.length != 2 || !parts[1].equals("verify()")) {
            throw new IllegalArgumentException("Invalid verify syntax. Type 'HELP' for help.");
        }
        Express result = expressions.get(parts[0]);
        if (result == null) {
            throw new IllegalArgumentException("Expression " + parts[0] + " not found. Type 'HELP' for help.");
        }
        System.out.println("Result: " + result.verify());
    }

    private static void printHelp() {
        System.out.println("Logic Terminal Commands:");
        System.out.println("1. Define a variable: Variable varName = new Variable(true|false)");
        System.out.println("   Example: Variable varA = new Variable(true)");
        System.out.println("2. Define a formula: Formula formulaName = new Formula(varName)");
        System.out.println("   Example: Formula formula1 = new Formula(varA)");
        System.out.println("3. Define an expression: Express expName = varName.operator(varName|formulaName|expression)");
        System.out.println("   Operators: and, or, not, equate, imply, iff, thereExist, forAll");
        System.out.println("   Example: Express exp1 = varA.and(varB).imply(varA).and(varB)");
        System.out.println("4. Verify an expression: expression.verify();");
        System.out.println("   Example: exp1.verify()");
        System.out.println("5. Exit the Terminal: EXIT");
    }
}
