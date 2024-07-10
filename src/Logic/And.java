package Logic;

class And extends Operate {
    public And(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        return left.verify() && right.verify();
    }
}
