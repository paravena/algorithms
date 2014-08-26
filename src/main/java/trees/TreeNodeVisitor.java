package trees;

public interface TreeNodeVisitor<T> {
    void visit(T element);
}
