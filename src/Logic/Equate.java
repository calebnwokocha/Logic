package Logic;

class Equate extends Operate {
    public Equate(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return left.verify() == right.verify();
    }

    @Override
    public String toString() {
        return "(" + left + " EQUALS " + right + ")";
    }
}
