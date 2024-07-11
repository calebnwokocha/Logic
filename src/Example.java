import Logic.*;

public class Example {
    public static void main(String[] args) {
        Variable varA = new Variable(false);
        Variable varB = new Variable(true);
        Variable varC = new Variable(true);

        // Basic Operations
        Express exp1 = varA.and(varB);
        System.out.println("varA AND varB: " + exp1.verify());

        Express exp2 = varA.or(varB);
        System.out.println("varA OR varB: " + exp2.verify());

        Express exp3 = varA.not();
        System.out.println("NOT varA: " + exp3.verify());

        Express exp4 = varA.equate(varB);
        System.out.println("varA EQUALS varB: " + exp4.verify());

        Express exp5 = varA.imply(varB);
        System.out.println("varA IMPLIES varB: " + exp5.verify());

        Express exp6 = varA.iff(varB);
        System.out.println("varA IFF varB: " + exp6.verify());

        // Quantifiers
        Express exp7 = varA.thereExist(varA, varB);
        System.out.println("There exists (varA, varB): " + exp7.verify());

        Express exp8 = varA.forAll(varA, varB);
        System.out.println("For all (varA, varB): " + exp8.verify());

        // Complex Expressions
        Express exp9 = varA.and(varB).or(varA.not());
        System.out.println("(varA AND varB) OR (NOT varA): " + exp9.verify());

        Express exp10 = varA.or(varB).and(varA.imply(varB));
        System.out.println("(varA OR varB) AND (varA IMPLIES varB): " + exp10.verify());

        Express exp11 = varA.and(varB).thereExist(varA.or(varB));
        System.out.println("(varA AND varB) there exists (varA OR varB): " + exp11.verify());

        Express exp12 = varA.and(varB).forAll(varA.or(varB));
        System.out.println("(varA AND varB) for all (varA OR varB): " + exp12.verify());

        // Nested Quantifiers
        Express exp13 = varA.and(varB).forAll(varA).thereExist(varB);
        System.out.println("(varA AND varB) for all varA, there exists varB: " + exp13.verify());

        Express exp14 = varA.or(varB).thereExist(varA).forAll(varB);
        System.out.println("(varA OR varB) there exists varA, for all varB: " + exp14.verify());

        // More Complex and Nested Expressions
        Express exp15 = varA.or(varB).forAll(varA.or(varB)).thereExist(varC.and(varB));
        System.out.println("(varA OR varB) for all (varA OR varB), there exists (varC AND varB): " + exp15.verify());

        Express exp16 = varA.and(varC).thereExist(varA.and(varC)).forAll(varB.or(varC));
        System.out.println("(varA AND varC) there exists (varA AND varC), for all (varB OR varC): " + exp16.verify());

        Express exp17 = varA.and(varB).forAll(varA).thereExist(varB.or(varC)).and(varA.imply(varC));
        System.out.println("(varA AND varB) for all varA, there exists (varB OR varC) AND (varA IMPLIES varC): " + exp17.verify());

        Express exp18 = varA.or(varB).thereExist(varA.and(varB)).forAll(varC.not()).or(varB);
        System.out.println("(varA OR varB) there exists (varA AND varB), for all (NOT varC) OR varB: " + exp18.verify());

        Express exp19 = varA.not().forAll(varA.not()).thereExist(varB.and(varC)).imply(varA.or(varC));
        System.out.println("(NOT varA) for all (NOT varA), there exists (varB AND varC) IMPLIES (varA OR varC): " + exp19.verify());

        Express exp20 = varA.and(varB).thereExist(varA).forAll(varB).and(varA.forAll(varC).thereExist(varA.not()));
        System.out.println("(varA AND varB) there exists varA, for all varB AND for all varC, there exists (NOT varA): " + exp20.verify());
    }
}
