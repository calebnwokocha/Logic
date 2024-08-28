import Logic.*;

public class Example {
    public static void main(String[] args) {
        Axiom ax_b = new Axiom(true, "a");
        Axiom ax_a = new Axiom(false, ax_b, 6);
        Axiom ax_c = new Axiom(true, ax_b, 8);

        // Basic Operations
        Express exp_1 = ax_a.and(ax_b);
        System.out.println("Result 1: " + exp_1.verify() + "\n");

        Express exp_2 = ax_a.or(ax_b);
        System.out.println("Result 2: " + exp_2.verify() + "\n");

        Express exp_3 = ax_a.not(ax_a);
        System.out.println("Result 3: " + exp_3.verify() + "\n");

        Express exp_4 = ax_a.equate(ax_b);
        System.out.println("Result 4: " + exp_4.verify() + "\n");

        Express exp_5 = ax_a.imply(ax_b);
        System.out.println("Result 5: " + exp_5.verify() + "\n");

        Express exp6 = ax_a.iff(ax_b);
        System.out.println("Result 6: " + exp6.verify() + "\n");

        // Quantifiers
        Express exp_7 = ax_a.there_exist(ax_a, ax_b);
        System.out.println("\nResult 7: " + exp_7.verify() + "\n");

        Express exp_8 = ax_a.for_all(ax_a, ax_b);
        System.out.println("Result 8: " + exp_8.verify() + "\n");

        // Complex Expressions
        Express exp_9 = ax_a.and(ax_b).or(ax_a.not(ax_a));
        System.out.println("Result 9: " + exp_9.verify() + "\n");

        Express exp_10 = ax_a.or(ax_b).and(ax_a.imply(ax_b));
        System.out.println("Result 10: " + exp_10.verify() + "\n");

        Express exp_11 = ax_a.and(ax_b).there_exist(ax_a.or(ax_b));
        System.out.println("Result 11: " + exp_11.verify() + "\n");

        Express exp_12 = ax_a.and(ax_b).for_all(ax_a.or(ax_b));
        System.out.println("Result 12: " + exp_12.verify() + "\n");

        // Nested Quantifiers
        Express exp_13 = ax_a.and(ax_b).for_all(ax_a).there_exist(ax_b);
        System.out.println("Result 13: " + exp_13.verify() + "\n");

        Express exp_14 = ax_a.or(ax_b).there_exist(ax_a).for_all(ax_b);
        System.out.println("Result 14: " + exp_14.verify() + "\n");

        // More Complex and Nested Expressions
        Express exp_15 = ax_a.or(ax_b).for_all(ax_a.or(ax_b)).there_exist(ax_c.and(ax_b));
        System.out.println("Result 15: " + exp_15.verify() + "\n");

        Express exp_16 = ax_a.and(ax_c).there_exist(ax_a.and(ax_c)).for_all(ax_b.or(ax_c));
        System.out.println("Result 16: " + exp_16.verify() + "\n");

        Express exp_17 = ax_a.and(ax_b).for_all(ax_a).there_exist(ax_b.or(ax_c)).and(ax_a.imply(ax_c));
        System.out.println("Result 17: " + exp_17.verify() + "\n");

        Express exp_18 = ax_a.or(ax_b).there_exist(ax_a.and(ax_b)).for_all(ax_c.not(ax_c)).or(ax_b);
        System.out.println("Result 18: " + exp_18.verify() + "\n");

        Express exp_19 = ax_a.not(ax_a).for_all(ax_a.not(ax_a)).there_exist(ax_b.and(ax_c)).imply(ax_a.or(ax_c));
        System.out.println("Result 19: " + exp_19.verify() + "\n");

        Express exp_20 = ax_a.and(ax_b).there_exist(ax_a).for_all(ax_b).and(ax_a.for_all(ax_c).there_exist(ax_a.not(ax_a)));
        System.out.println("Result 20: " + exp_20.verify() + "\n");

        Express exp_21 = ax_a.or(ax_a).and(ax_c).the(ax_b);
        System.out.println("Result 21: " + exp_21.verify() + "\n");
    }
}
