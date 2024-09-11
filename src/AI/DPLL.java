package AI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DPLL {

    // Main function to check satisfiability
    public static boolean isSatisfiable(List<Set<Integer>> clauses, Set<Integer> assignment) {
        clauses = simplify(clauses, assignment);

        // If all clauses are satisfied (empty list of clauses), return true
        if (clauses.isEmpty()) return true;

        // If a clause is empty (unsatisfied clause), return false
        for (Set<Integer> clause : clauses) {
            if (clause.isEmpty()) return false;
        }

        // Unit propagation: If a unit clause (single literal clause) exists, assign its value
        Integer unit = findUnitClause(clauses);
        if (unit != null) {
            Set<Integer> newAssignment = new HashSet<>(assignment);
            newAssignment.add(unit);
            return isSatisfiable(clauses, newAssignment);
        }

        // Pure literal elimination: If a literal appears with only one polarity, assign its value
        Integer pureLiteral = findPureLiteral(clauses);
        if (pureLiteral != null) {
            Set<Integer> newAssignment = new HashSet<>(assignment);
            newAssignment.add(pureLiteral);
            return isSatisfiable(clauses, newAssignment);
        }

        // Choose a literal and recursively check satisfiability
        Integer literal = clauses.get(0).iterator().next();
        Set<Integer> assignTrue = new HashSet<>(assignment);
        Set<Integer> assignFalse = new HashSet<>(assignment);
        assignTrue.add(literal);
        assignFalse.add(-literal);

        return isSatisfiable(clauses, assignTrue) || isSatisfiable(clauses, assignFalse);
    }

    // Simplifies clauses based on the current assignment
    private static List<Set<Integer>> simplify(List<Set<Integer>> clauses, Set<Integer> assignment) {
        List<Set<Integer>> simplifiedClauses = new ArrayList<>();
        for (Set<Integer> clause : clauses) {
            boolean satisfied = false;
            Set<Integer> newClause = new HashSet<>();
            for (Integer literal : clause) {
                if (assignment.contains(literal)) {
                    satisfied = true;
                    break;
                } else if (!assignment.contains(-literal)) {
                    newClause.add(literal);
                }
            }
            if (!satisfied) simplifiedClauses.add(newClause);
        }
        return simplifiedClauses;
    }

    // Finds a unit clause (a clause with only one literal)
    private static Integer findUnitClause(List<Set<Integer>> clauses) {
        for (Set<Integer> clause : clauses) {
            if (clause.size() == 1) {
                return clause.iterator().next();
            }
        }
        return null;
    }

    // Finds a pure literal (a literal that appears with only one polarity)
    private static Integer findPureLiteral(List<Set<Integer>> clauses) {
        Set<Integer> allLiterals = new HashSet<>();
        Set<Integer> negLiterals = new HashSet<>();
        for (Set<Integer> clause : clauses) {
            for (Integer literal : clause) {
                if (literal > 0) {
                    allLiterals.add(literal);
                } else {
                    negLiterals.add(-literal);
                }
            }
        }
        for (Integer literal : allLiterals) {
            if (!negLiterals.contains(literal)) {
                return literal;
            }
        }
        for (Integer literal : negLiterals) {
            if (!allLiterals.contains(literal)) {
                return -literal;
            }
        }
        return null;
    }

    // Testing the DPLL algorithm
    public static void main(String[] args) {
        // Sample CNF: (x1 or x2) and (not x1 or x3) and (x2 or not x3)
        List<Set<Integer>> clauses = new ArrayList<>();
        Set<Integer> clause1 = new HashSet<>();
        clause1.add(-1);  // x1
        clause1.add(-2);  // x2
        clauses.add(clause1);

        Set<Integer> clause2 = new HashSet<>();
        clause2.add(-1); // not x1
        clause2.add(-3);  // x3
        clauses.add(clause2);

        Set<Integer> clause3 = new HashSet<>();
        clause3.add(-2);  // x2
        clause3.add(-3); // not x3
        clauses.add(clause3);

        Set<Integer> assignment = new HashSet<>();

        // Check satisfiability
        boolean result = isSatisfiable(clauses, assignment);
        System.out.println("Satisfiable: " + result);
    }
}
