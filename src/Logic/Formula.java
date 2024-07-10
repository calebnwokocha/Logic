package Logic;

public class Formula implements Express {
    private final Express expression;

    public Formula() {
        this.expression = null;
    }

    public Formula(Express expression) {
        this.expression = expression;
    }

    @Override
    public boolean verify() {
        return expression != null && expression.verify();
    }

    @Override
    public Express and(Express expression) {
        return new Formula(new And(this.expression, expression));
    }

    @Override
    public Express or(Express expression) {
        return new Formula(new Or(this.expression, expression));
    }

    @Override
    public Express not() {
        return new Formula(new Not(this.expression));
    }

    @Override
    public Express not(Express expression) {
        return new Formula(new Not(expression));
    }

    @Override
    public Express equate(Express expression) {
        return new Formula(new Equate(this.expression, expression));
    }

    @Override
    public Express imply(Express expression) {
        return new Formula(new Imply(this.expression, expression));
    }

    @Override
    public Express iff(Express expression) {
        return new Formula(new Iff(this.expression, expression));
    }

    public static Formula thereExist(Express... expressions) {
        return new Formula(new ThereExist(expressions));
    }

    public static Formula forAll(Express... expressions) {
        return new Formula(new ForAll(expressions));
    }
}
