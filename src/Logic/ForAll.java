package Logic;

class ForAll extends Operate {
    public ForAll(Express left, Express... rights) {
        super(left, rights);
    }


    @Override
    public boolean verify() {
        for (Express express : rights) {
            if (new Not(express, express).verify()) {
                return false;
            }
        }
        boolean result = left.verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [").append(left).append(" ∀ ");
        for (int i = 0; i < rights.length; i++) {
            sb.append(rights[i]);
            if (i < rights.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("] ");
        return sb.toString();
    }
}
