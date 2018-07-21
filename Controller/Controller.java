package Controller;

import Model.*;
import Model.Composite.Component;
import Model.Analysis;
import Model.Visitor.NodeVisitor;
import View.AdminView;
import View.AnalysisView;
import View.UserView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Controller {
    private AdminView view;
    private static List<UserView> userViews = new ArrayList<>();
    public Controller(){
        view = AdminView.getInstance();
    }

    public static void addUser(String groupNode, String groupInput, String userInput){
        if(groupNode.equals("")){
            System.out.println("Select group first");
            return;
        }
        else if(groupNode.equals(groupInput)){
            Group.userChange(groupNode, userInput);
            requestTotalUsers(false);
        }
        else{
            if(Group.groupIDs.contains(groupInput)){
                System.out.println("Group already exists");
                return;
            }
            addGroupUser(groupNode, groupInput, userInput);
            requestTotalUsers(false);
            requestTotalGroups(false);
        }
    }

    public static void addGroupUser(String groupNode, String groupInput, String userInput){
        if(userInput.equals("")){
            System.out.println("Select user first");
            return;
        }
        else if (Group.groupIDs.contains(groupInput)){
            System.out.println("Group already exists");
            return;
        }
        Group.groupUserChange(groupNode, groupInput, userInput);
        requestTotalUsers(false);
        requestTotalGroups(false);
    }

    public static void requestUserView(String userInput){
        if(!User.userIDs.contains(userInput)) {
            System.out.println("User does not exist");
        }
        else{
            User user = Group.getUser(Group.getRoot(), userInput);
            UserView userview = getUserView(userInput);
            if(userview == null) {
                userview = new UserView(user);
                userViews.add(userview);
            }
            else{
                userview.setVisible();
            }
        }
    }

    public static void followUser(User user, String otherUserID){
        UserView userView = getUserView(user.getID());
        User otherUser = Group.getUser(Group.getRoot(), otherUserID);
        user.addFollowing(otherUser, userView);
    }

    public static void postTweet(User user, String tweet){
        user.tweet(tweet, userViews);
        requestTotalMessages(false);
    }

    public static void requestTotalUsers(boolean display){
        NodeVisitor vistor = new Analysis();
        Group root = Group.getRoot();
        int totalUsers = 0;
        totalUsers = getTotalUsers(vistor, root, totalUsers);
        AnalysisView.getTotalUsersViewInstance("Total Users:", totalUsers, display);
    }

    private static int getTotalUsers(NodeVisitor visitor, Group parent, int count) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Group) {
                count += getTotalUsers(visitor, (Group) component, 0);
            } else if (component instanceof User) {
                ((User) component).accept(visitor);
                count++;
            }
        }
        return count;
    }

    public static void requestTotalGroups(boolean display){
        NodeVisitor vistor = new Analysis();
        Group root = Group.getRoot();
        int totalGroups = 1;
        totalGroups = getTotalGroups(vistor, root, totalGroups);
        AnalysisView.getTotalGroupsViewInstance("Total Groups:", totalGroups, display);
    }

    private static int getTotalGroups(NodeVisitor visitor, Group parent, int count) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Group) {
                count++;
                count += getTotalGroups(visitor, (Group) component, 0);
            }
        }
        return count;
    }

    public static void requestTotalMessages(boolean display){
        NodeVisitor vistor = new Analysis();
        Group root = Group.getRoot();
        int totalMessages = 0;
        totalMessages = getTotalMessages(vistor, root, totalMessages);
        AnalysisView.getTotalMessagesViewInstance("Total Messages:", totalMessages, display);
    }

    private static int getTotalMessages(NodeVisitor visitor, Group parent, int count) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Group) {
                count += getTotalMessages(visitor, (Group) component, 0);
            } else if (component instanceof User) {
                count += ((User) component).accept(visitor);

            }
        }
        return count;
    }

    public static void requestValidateIDs(){
        NodeVisitor visitor = new Analysis();
        HashSet<String> IDs = new HashSet<>();
        Group root = Group.getRoot();
        boolean valid = checkIDs(visitor, root, IDs);
        if(valid){
            AdminView.setValidationField("IDs are Valid");
        }
        else{
            AdminView.setValidationField("IDs are Invalid");
        }
    }

    private static boolean checkIDs(NodeVisitor visitor, Group parent, HashSet<String> IDs){
        for(Component component : parent.getComponents()) {
            String ID = component.getID();
            if(ID.matches(".*\\s+.*")){
                return false;
            }
            if (component instanceof Group) {
                if (IDs.contains(((Group) component).acceptIDChecker(visitor))) {
                    return false;
                }
                IDs.add(ID);
                checkIDs(visitor, (Group) component, IDs);
            } else if (component instanceof User) {
                if (IDs.contains(((User) component).acceptIDChecker(visitor))) {
                    return false;
                }
                IDs.add(ID);
            }
        }
        return true;
    }

    public static void requestLastUpdate(){
        NodeVisitor visitor = new Analysis();
        HashSet<String> IDs = new HashSet<>();
        Group root = Group.getRoot();
        long time = 0;
        time = getTimeUpdate(visitor, root, time);
        AdminView.setLastUpdateInfo(time);
    }

    private static long getTimeUpdate(NodeVisitor visitor, Group parent, long time){
        for(Component component : parent.getComponents()) {
            if (component instanceof Group) {
                long current = getTimeUpdate(visitor, (Group) component, time);
                if(current > time){
                    time = current;
                }
            } else if (component instanceof User) {
                long current = ((User) component).getTimeUpdated();
                if(current > time){
                    time = current;
                }
            }
        }
        return time;
    }

    public static UserView getUserView(String userID){
        for(UserView uv: userViews){
            if(uv.getUser().getID().equals(userID)){
                return uv;
            }
        }
        return null;
    }

    public static void setUserViewsInvisble(){
        for(UserView uv: userViews){
            uv.setInvisible();
        }
    }

}
