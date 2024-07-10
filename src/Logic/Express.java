package Logic;

public interface Express {
    public  abstract boolean evaluate();

    public abstract Express and(Express expression);
    public abstract Express or(Express expression);
    public abstract Express not();
    public abstract Express not(Express express);
    public abstract Express equate(Express expression);
    public abstract Express imply(Express expression);
    public abstract Express iff(Express expression);

    public static Express thereExist(Express... expressions) {
        return new ThereExist(expressions);
    }

    public static Express forAll(Express... expressions) {
        return new ForAll(expressions);
    }
}
