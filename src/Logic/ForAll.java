package Logic;

class ForAll extends Operate {
    private final Express[] expressions;

    public ForAll(Express... expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean verify() {
        for (Express expression : expressions) {
            if (!expression.verify()) {
                return false;
            }
        }
        return true;
    }
}
