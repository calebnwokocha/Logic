package Logic;

class And extends Operate {
    public And(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return left.verify() && right.verify();
    }

    @Override
    public String toString() {
        return "(" + left + " AND " + right + ")";
    }
}
