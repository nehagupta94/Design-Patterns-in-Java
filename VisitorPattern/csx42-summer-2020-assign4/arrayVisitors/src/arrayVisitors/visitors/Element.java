package arrayVisitors.visitors;

/**
 *
 * @param <Visitor>
 *     This method is used to accept and takes <Visitor> as a parameter.
 */

public interface Element<Visitor> {
    void accept(Visitor visitor);
}
