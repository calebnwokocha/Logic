package Logic;

class Or extends Operate {
    public Or(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        boolean result = left.verify() || right.verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        return " (" + left + " ∨ " + right + ") ";
    }

}
