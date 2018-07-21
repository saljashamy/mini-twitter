package Model.Visitor;

public interface VisitableNode {
    public int accept(NodeVisitor visitor);
    public String acceptIDChecker(NodeVisitor visitor);
    public long acceptTimeUpdate(NodeVisitor visitor);
}
