import java.util.ArrayList;
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
            UserView userview = new UserView(user);
            userViews.add(userview);
        }
    }

    public static void followUser(User user, String otherUserID, UserView userView){
        User otherUser = Group.getUser(Group.getRoot(), otherUserID);
        user.addFollowing(otherUser, userView);
    }

    public static void postTweet(User user, String tweet){
        user.tweet(tweet, userViews);
        requestTotalMessages(false);
    }

    public static void requestTotalUsers(boolean display){
        NodeVistor vistor = new Analysis();
        Group root = Group.getRoot();
        int totalUsers = 0;
        totalUsers = getTotalUsers(vistor, root, totalUsers);
        AnalysisView.getTotalUsersViewInstance("Total Users:", totalUsers, display);
    }

    private static int getTotalUsers(NodeVistor visitor, Group parent, int count) {
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
        NodeVistor vistor = new Analysis();
        Group root = Group.getRoot();
        int totalGroups = 1;
        totalGroups = getTotalGroups(vistor, root, totalGroups);
        AnalysisView.getTotalGroupsViewInstance("Total Groups:", totalGroups, display);
    }

    private static int getTotalGroups(NodeVistor visitor, Group parent, int count) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Group) {
                count++;
                count += getTotalGroups(visitor, (Group) component, 0);
            }
        }
        return count;
    }

    public static void requestTotalMessages(boolean display){
        NodeVistor vistor = new Analysis();
        Group root = Group.getRoot();
        int totalMessages = 0;
        totalMessages = getTotalMessages(vistor, root, totalMessages);
        AnalysisView.getTotalMessagesViewInstance("Total Messages:", totalMessages, display);
    }

    private static int getTotalMessages(NodeVistor visitor, Group parent, int count) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Group) {
                count += getTotalUsers(visitor, (Group) component, 0);
            } else if (component instanceof User) {
                count += ((User) component).accept(visitor);

            }
        }
        return count;
    }
}
