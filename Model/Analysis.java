package Model;

import Model.Visitor.NodeVisitor;

import java.util.List;

public class Analysis implements NodeVisitor {

    public Analysis(){}

    @Override
    public int visit(User user) { return user.getFeed().size(); }

    @Override
    public int visit(Group group) {
        return 1;
    }

    @Override
    public String visitID(User user){ return user.getID(); }

    @Override
    public String visitID(Group group){ return group.getID(); }

    @Override
    public long visitUpdatedTime(User user){ return user.getTimeUpdated();}

    @Override
    public long visitUpdatedTime(Group group){ return 0; }

}
