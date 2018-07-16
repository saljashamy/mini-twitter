import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class User implements Component, Observable, Observer {
    public static HashSet<String> userIDs = new HashSet<String>();
    private static int userCount = 0;
    private static int messageCount = 0;

    private User user = null;
    private String id;
    private List<User> following = new ArrayList<User>();
    private List<User> followers = new ArrayList<User>();
    private List<String> feed = new ArrayList<String>();
    private String currentTweet;

    public User(String id){
        if(userIDs.contains(id)){
            throw new RuntimeException("User already exists");
        }
        else{
            setID(id);
            userIDs.add(id);
            this.userCount++;
        }
    }

    public void addFollowing(User user){
        following.add(user);
        user.addFollower(this);
    }

    @Override
    public void addFollower(User user){
        followers.add(user);
    }

    @Override
    public void tweet(String tweet){
        currentTweet = tweet;
        feed.add(tweet);
        messageCount++;
        for(User user:  followers){
            user.update(this);
            messageCount++;
        }
    }

    public List<String> getFeed(){
        return feed;
    }

    public List<User> getFollowers() {
        return followers;
    }

    @Override
    public void update(User user){
        feed.add(user.getLatestTweet());
    }

    public String getLatestTweet(){
        return currentTweet;
    }

    public void setID(String id){
        this.id = id;
    }

    @Override
    public String getID(){
        return id;
    }
}
