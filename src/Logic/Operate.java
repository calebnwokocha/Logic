package Logic;

abstract class Operate implements Express {
    protected boolean value;
    protected Object[] properties;
    protected Express left;
    protected Express right;
    protected Express[] rights;

    public Operate(boolean value, Object[] properties) {
        this.value = value;
        this.properties = properties;
    }

    public Operate(Express left, Express right) {
        this.left = left;
        this.right = right;
        this.properties = new Object[left.getProperties().length +
                right.getProperties().length];
        System.arraycopy(left.getProperties(), 0,
                this.properties, 0, left.getProperties().length);
        System.arraycopy(right.getProperties(), 0, this.properties,
                left.getProperties().length, right.getProperties().length);
    }

    public Operate(Express left, Express... rights) {
        this.left = left;
        this.rights = rights;
        int rightPropertyLength = 0;
        for (Express right : rights) { rightPropertyLength += right.getProperties().length;}
        this.properties = new Object[left.getProperties().length + rightPropertyLength];
        System.arraycopy(left.getProperties(), 0,
                this.properties, 0, left.getProperties().length);
        int leftPropertyLength = left.getProperties().length;
        for (Express right : rights) {
            System.arraycopy(right.getProperties(), 0, this.properties,
                    leftPropertyLength, right.getProperties().length);
            leftPropertyLength = right.getProperties().length;
        }
    }

    @Override
    public abstract boolean verify();

    @Override
    public abstract String toString();

    @Override
    public abstract Object[] getProperties();

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

    @Override
    public Express the(Express expression) {
        return new The(this, expression);
    }
}
