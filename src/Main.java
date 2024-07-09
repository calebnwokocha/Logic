import Logic.*;

public class Main {
    public static void main(String[] args) {
        Variable varA = new Variable(true);
        Variable varB = new Variable(false);

        // Example: variable.and.variable
        Express exp1 = varA.and(varB);
        System.out.println("varA AND varB: " + exp1.evaluate()); // Output: false

        // Example: variable.and.formula
        Formula formula1 = new Formula(varA);
        Express exp2 = formula1.and(varB);
        System.out.println("varA AND formula(varB): " + exp2.evaluate()); // Output: false

        // Example: variable.or.formula
        Express exp3 = varA.not(varB);
        System.out.println("varA OR formula(varB): " + exp3.evaluate()); // Output: true

        // Example: variable.or.variable
        Express exp4 = varA.or(varB);
        System.out.println("varA OR varB: " + exp4.evaluate()); // Output: true

        // Example: variable.not
        Express exp5 = varA.not();
        System.out.println("NOT varA: " + exp5.evaluate()); // Output: false

        // Example: variable.equate.variable
        Express exp6 = varA.equate(varB).and(varA).equate(exp5);
        System.out.println("varA EQUALS varB: " + exp6.evaluate()); // Output: false

        // Example: there exists (varA, varB)
        Express exp7 = Formula.forAll(varA, varB);
        System.out.println("There exists (varA, varB): " + exp7.evaluate()); // Output: true

        Express.forAll(varA);
        // Example: for all (varA, varB)
        Formula.thereExist(varA).or(Formula.thereExist(varB));
        Express exp8 = Express.forAll(varA, varA);
        System.out.println("For all (varA, varB): " + exp8.evaluate()); // Output: false

        // Example: variable.imply.variable
        Express exp9 = varA.imply(varB);
        System.out.println("varA IMPLIES varB: " + exp9.evaluate()); // Output: false

        // Example: formula.imply.variable
        Express exp10 = formula1.imply(varB);
        System.out.println("formula(varA) IMPLIES varB: " + exp10.evaluate()); // Output: false

        // New examples
        Express exp11 = exp7.or(varA);
        System.out.println("exp7 OR varA: " + exp11.evaluate()); // Output: true

        Express exp12 = exp7.and(varA);
        System.out.println("exp7 AND varA: " + exp12.evaluate()); // Output: true

        Express exp13 = exp7.or(formula1);
        System.out.println("exp7 OR formula1: " + exp13.evaluate()); // Output: true

        Express exp14 = exp7.and(formula1);
        System.out.println("exp7 AND formula1: " + exp14.evaluate()); // Output: true

        Express exp15 = varA.or(exp7);
        System.out.println("varA OR exp7: " + exp15.evaluate()); // Output: true

        //Formula.forAll(exp3).f

        Express exp16 = exp7.and(exp7);
        System.out.println("exp7 AND exp7: " + exp16.evaluate()); // Output: true

        Express exp17 = formula1.imply(formula1);
        System.out.println("formula1 IMPLIES formula1: " + exp17.evaluate()); // Output: true
    }
}
