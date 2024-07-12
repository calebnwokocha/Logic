package Logic;

class Or extends Operate {
    public Or(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return left.verify() || right.verify();
    }

    @Override
    public String toString() {
        return "_" + left + "_or_" + right + "_";
    }
}
