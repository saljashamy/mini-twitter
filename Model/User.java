package Model;

import Model.Composite.Component;
import Model.Observer.Observer;
import Model.Observer.Observable;
import Model.Visitor.NodeVisitor;
import Model.Visitor.VisitableNode;
import View.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class User implements Component, Observable, Observer, VisitableNode {
    public static HashSet<String> userIDs = new HashSet<String>();

    private String id;
    private long timeCreated;
    private long timeUpdated;
    private List<User> following = new ArrayList<User>();
    private List<User> followers = new ArrayList<User>();
    private List<String[]> feed = new ArrayList<>(0);
    private String currentTweet;

    public User(String id){
        if(userIDs.contains(id)){
            throw new RuntimeException("User already exists");
        }
        else{
            setID(id);
            userIDs.add(id);
            setTimeCreated(System.currentTimeMillis());
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
        timeUpdated = System.currentTimeMillis();
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

    private void setID(String id){
        this.id = id;
    }

    @Override
    public String getID(){
        return id;
    }

    private void setTimeCreated(long time){ this.timeCreated = time; }

    public long getTimeCreated(){return timeCreated; }

    private void setTimeUpdated(long time){ this.timeUpdated = time; }

    public long getTimeUpdated(){return timeUpdated; }

    @Override
    public int accept(NodeVisitor visitor){
        return visitor.visit(this);
    }

    @Override
    public String acceptIDChecker(NodeVisitor visitor){ return visitor.visitID(this); }

    @Override
    public long acceptTimeUpdate(NodeVisitor visitor){ return visitor.visitUpdatedTime(this); }
}
