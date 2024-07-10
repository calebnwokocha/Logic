package Logic;

public class Formula implements Express {
    private final Express expression;

    public Formula(Express expression) {
        this.expression = expression;
    }

    @Override
    public boolean evaluate() {
        return expression.evaluate();
    }

    @Override
    public Express and(Express expression) {
        return new And(this.expression, expression);
    }

    @Override
    public Express or(Express expression) {
        return new Or(this.expression, expression);
    }

    @Override
    public Express not() {
        return new Not(this.expression);
    }

    @Override
    public Express not(Express expression) {
        return new Not(expression);
    }

    @Override
    public Express equate(Express expression) {
        return new Equate(this.expression, expression);
    }

    @Override
    public Express imply(Express expression) {
        return new Imply(this.expression, expression);
    }

    @Override
    public Express iff(Express expression) {
        return new Iff(this, expression);
    }

    public static Express thereExist(Express... expressions) {
        return new ThereExist(expressions);
    }

    public static Express forAll(Express... expressions) {
        return new ForAll(expressions);
    }
}
