import java.util.List;

public interface Observable {
    public void addFollower(User user);
    public void tweet(String message, List<UserView> userViews);
}
