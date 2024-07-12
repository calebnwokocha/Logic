package Logic;

public interface Express {
    boolean verify();
    String toString();

    Express and(Express expression);
    Express or(Express expression);
    Express not(Express expression);
    Express equate(Express expression);
    Express imply(Express expression);
    Express iff(Express expression);
    Express there_exist(Express expression, Express... expressions);
    Express for_all(Express expression, Express... expressions);
}
