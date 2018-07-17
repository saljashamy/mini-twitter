import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class User implements Component, Observable, Observer, VisitableNode {
    public static HashSet<String> userIDs = new HashSet<String>();

    private String id;
    private List<User> following = new ArrayList<User>();
    private List<User> followers = new ArrayList<User>();
    private List<String[]> feed = new ArrayList<>();
    private String currentTweet;

    public User(String id){
        if(userIDs.contains(id)){
            throw new RuntimeException("User already exists");
        }
        else{
            setID(id);
            userIDs.add(id);
        }
    }

    public void addFollowing(User user, UserView userView){
        if(!following.contains(user) && this.getID() != user.getID()) {
            following.add(user);
            user.addFollower(this);
            userView.notifyFollowingChange();
        }
    }

    public List<User> getFollowing() {
        return following;
    }

    @Override
    public void addFollower(User user){
        followers.add(user);
    }

    public List<User> getFollowers() {
        return followers;
    }

    @Override
    public void tweet(String tweet, List<UserView> userViews){
        currentTweet = tweet;
        feed.add(new String[]{getID(),tweet});
        for(User user:  getFollowers()){
            user.update(this);
        }
        for(UserView userView: userViews){
            userView.notifyTweetChange();
        }
    }
    public String getLatestTweet(){
        return currentTweet;
    }

    public  List<String[]> getFeed(){
        return feed;
    }

    @Override
    public void update(User user){
        feed.add(new String[]{user.getID(), user.getLatestTweet()});
    }

    public void setID(String id){
        this.id = id;
    }

    @Override
    public String getID(){
        return id;
    }

    @Override
    public int accept(NodeVistor visitor){
        return visitor.visit(this);
    }
}
