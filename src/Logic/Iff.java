package Logic;

class Iff extends Operate {
    public Iff(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        boolean result = new Equate(left, right).verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        return " (" + left + " ⇔ " + right + ") ";
    }
}
