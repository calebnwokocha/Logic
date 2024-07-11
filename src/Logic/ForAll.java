package Logic;

class ForAll extends Operate {
    private final Express[] expressions;

    public ForAll(Express... expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        for (Express expression : expressions) {
            if (!expression.verify()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FOR_ALL(");
        for (int i = 0; i < expressions.length; i++) {
            sb.append(expressions[i]);
            if (i < expressions.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
