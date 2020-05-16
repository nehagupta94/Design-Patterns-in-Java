package elements;

public interface Element<Visitor> {
    void accept(Visitor visitor);
}
