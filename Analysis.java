public class Analysis implements NodeVistor {

    public Analysis(){}

    @Override
    public int visit(User user) {
        return user.getFeed().size();
    }

    @Override
    public int visit(Group group) {
        return 1;
    }
}
