package Logic;

class Imply extends Operate {
    public Imply(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        boolean result = new Or(new Not(left, left), right).verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        return "_" + left + "_imply_" + right + "_";
    }

    @Override
    public Object[] getProperties() {
        return properties;
    }
}
