package Logic;

public interface Express {
    boolean verify();
    String toString();
    Object[] getProperties();

    Express and(Express expression);
    Express or(Express expression);
    Express not(Express expression);
    Express equate(Express expression);
    Express imply(Express expression);
    Express iff(Express expression);
    Express there_exist(Express... expressions);
    Express for_all(Express... expressions);
    Express the(Express expression);

}
