package Logic;

class Or extends Operate {
    public Or(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        return left.verify() || right.verify();
    }
}
