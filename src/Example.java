import Logic.*;

public class Example {
    public static void main(String[] args) {
        Variable varA = new Variable(true);
        Variable varB = new Variable(true);

        // Example: variable.and.variable
        Express exp1 = varA.and(varB).imply(varA).and(varB);
        System.out.println("varA AND varB: " + exp1.verify()); // Output: true

        // Example: variable.and.formula
        Formula formula1 = new Formula(varA);
        Express exp2 = formula1.and(varB);
        System.out.println("varA AND formula(varB): " + exp2.verify()); // Output: true

        // Example: variable.or.formula
        Express exp3 = varB.iff(varB);
        System.out.println("varA OR formula(varB): " + exp3.verify()); // Output: true

        // Example: variable.or.variable
        Express exp4 = varA.or(varB);
        System.out.println("varA OR varB: " + exp4.verify()); // Output: true

        // Example: variable.not
        Express exp5 = varA.not();
        System.out.println("NOT varA: " + exp5.verify()); // Output: false

        // Example: variable.equate.variable
        Express exp6 = varA.equate(varB).and(varA).equate(exp5);
        System.out.println("varA EQUALS varB: " + exp6.verify()); // Output: false

        // Example: there exists (varA, varB)
        Express exp7 = Formula.thereExist(varA, varB);
        System.out.println("There exists (varA, varB): " + exp7.verify()); // Output: true

        // Example: for all (varA, varB)
        Express exp8 = Formula.forAll(varA, varB);
        System.out.println("For all (varA, varB): " + exp8.verify()); // Output: true

        // Example: variable.imply.variable
        Express exp9 = varA.imply(varB);
        System.out.println("varA IMPLIES varB: " + exp9.verify()); // Output: true

        // Example: formula.imply.variable
        Express exp10 = formula1.imply(varB);
        System.out.println("formula(varA) IMPLIES varB: " + exp10.verify()); // Output: true

        // Example: forAll and thereExist chaining
        Express exp11 = Formula.forAll(varA).thereExist(varA);
        System.out.println("For all (varA) there exists (varA): " + exp11.verify()); // Output: true
    }
}
