package Logic;

class The extends Operate {
    public The(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        int matchCount = 0;
        for (Object property : left.getProperties()) {
            // if-then statement is an example of theorem
            if (property == right) {
                matchCount++;
            }
        }
        System.out.println(toString());
        return matchCount == 1;
    }

    @Override
    public String toString() {
        return "_" + left + "_the_" + right + "_";
    }

    @Override
    public Object[] getProperties() {
        return properties;
    }

}