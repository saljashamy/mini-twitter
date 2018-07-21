package Model.Visitor;

import Model.Group;
import Model.User;

import java.util.List;

public interface NodeVisitor {
    public int visit(User user);
    public int visit(Group user);
    public String visitID(User user);
    public String visitID(Group group);
    public long visitUpdatedTime(User user);
    public long visitUpdatedTime(Group group);
}
