package Logic;

abstract class Operate implements Express {
    protected boolean value;
    protected Express left;
    protected Express right;
    protected Express[] rights;

    public Operate(boolean value) {
        this.value = value;
    }

    public Operate(Express right) {
        this.right = right;
    }
    public Operate(Express left, Express right) {
        this.left = left;
        this.right = right;
    }

    public Operate(Express left, Express... rights) {
        this.left = left;
        this.rights = rights;
    }

    @Override
    public abstract boolean verify();

    @Override
    public abstract String toString();

    @Override
    public Express and(Express expression) {
        return new And(this, expression);
    }

    @Override
    public Express or(Express expression) {
        return new Or(this, expression);
    }

    @Override
    public Express not(Express expression) {
        return new Not(this, expression);
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
    public Express there_exist(Express... expressions) {
        return new ThereExist(this, expressions);
    }

    @Override
    public Express for_all(Express... expressions) {
        return new ForAll(this, expressions);
    }
}
