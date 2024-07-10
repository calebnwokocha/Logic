package Logic;

abstract class Operate implements Express {
    protected Express left;
    protected Express right;
    private Express[] expressions;

    public Operate(Express left, Express right) {
        this.left = left;
        this.right = right;
    }

    public Operate(Express... expressions) {
        this.expressions = expressions;
    }

    @Override
    public abstract boolean verify();

    @Override
    public Express and(Express expression) {
        return new And(this, expression);
    }

    @Override
    public Express or(Express expression) {
        return new Or(this, expression);
    }

    @Override
    public Express not() {
        return new Not(this);
    }

    @Override
    public Express not(Express expression) {
        return new Not(expression);
    }

    @Override
    public Express equate(Express expression) {
        return new Equate(this, expression);
    }

    @Override
    public Express imply(Express expression) {
        return new Imply(this, expression);
    }

    @Override
    public Express iff(Express expression) {
        return new Iff(this, expression);
    }

    @Override
    public Express thereExist(Express... expression) {
        return new ThereExist(expression);
    }

    @Override
    public Express forAll(Express... expression) {
        return new ForAll(expression);
    }
}
