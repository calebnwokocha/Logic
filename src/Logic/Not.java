package Logic;

class Not extends Operate {
    public Not(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        boolean result = left.verify() && !right.verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        return "_" + left + "_not_" + right + "_";
    }

    @Override
    public Object[] getProperties() {
        return properties;
    }
}
