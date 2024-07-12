import Logic.*;

public class Example {
    public static void main(String[] args) {
        Variable varA = new Variable(false);
        Variable varB = new Variable(true);
        Variable varC = new Variable(true);

        // Basic Operations
        Express exp1 = varA.and(varB);
        System.out.println("Result 1: " + exp1.verify() + "\n");

        Express exp2 = varA.or(varB);
        System.out.println("Result 2: " + exp2.verify() + "\n");

        Express exp3 = varA.not(varA);
        System.out.println("Result 3: " + exp3.verify() + "\n");

        Express exp4 = varA.equate(varB);
        System.out.println("Result 4: " + exp4.verify() + "\n");

        Express exp5 = varA.imply(varB);
        System.out.println("Result 5: " + exp5.verify() + "\n");

        Express exp6 = varA.iff(varB);
        System.out.println("Result 6: " + exp6.verify() + "\n");

        // Quantifiers
        Express exp7 = varA.there_exist(varA, varB);
        System.out.println("\nResult 7: " + exp7.verify() + "\n");

        Express exp8 = varA.for_all(varA, varB);
        System.out.println("Result 8: " + exp8.verify() + "\n");

        // Complex Expressions
        Express exp9 = varA.and(varB).or(varA.not(varA));
        System.out.println("Result 9: " + exp9.verify() + "\n");

        Express exp10 = varA.or(varB).and(varA.imply(varB));
        System.out.println("Result 10: " + exp10.verify() + "\n");

        Express exp11 = varA.and(varB).there_exist(varA.or(varB));
        System.out.println("Result 11: " + exp11.verify() + "\n");

        Express exp12 = varA.and(varB).for_all(varA.or(varB));
        System.out.println("Result 12: " + exp12.verify() + "\n");

        // Nested Quantifiers
        Express exp13 = varA.and(varB).for_all(varA).there_exist(varB);
        System.out.println("Result 13: " + exp13.verify() + "\n");

        Express exp14 = varA.or(varB).there_exist(varA).for_all(varB);
        System.out.println("Result 14: " + exp14.verify() + "\n");

        // More Complex and Nested Expressions
        Express exp15 = varA.or(varB).for_all(varA.or(varB)).there_exist(varC.and(varB));
        System.out.println("Result 15: " + exp15.verify() + "\n");

        Express exp16 = varA.and(varC).there_exist(varA.and(varC)).for_all(varB.or(varC));
        System.out.println("Result 16: " + exp16.verify() + "\n");

        Express exp17 = varA.and(varB).for_all(varA).there_exist(varB.or(varC)).and(varA.imply(varC));
        System.out.println("Result 17: " + exp17.verify() + "\n");

        Express exp18 = varA.or(varB).there_exist(varA.and(varB)).for_all(varC.not(varC)).or(varB);
        System.out.println("Result 18: " + exp18.verify() + "\n");

        Express exp19 = varA.not(varA).for_all(varA.not(varA)).there_exist(varB.and(varC)).imply(varA.or(varC));
        System.out.println("Result 19: " + exp19.verify() + "\n");

        Express exp20 = varA.and(varB).there_exist(varA).for_all(varB).and(varA.for_all(varC).there_exist(varA.not(varA)));
        System.out.println("Result 20: " + exp20.verify() + "\n");
    }
}
