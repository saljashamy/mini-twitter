import java.awt.*;

public class Controller {
    private AdminView view;
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
        }
        else{
            if(Group.groupIDs.contains(groupInput)){
                System.out.println("Group already exists");
                return;
            }
            addGroupUser(groupNode, groupInput, userInput);
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
    }

    public static void requestUserView(String userInput){
        if(!User.userIDs.contains(userInput)) {
            System.out.println("User does not exist");
        }

    }

}
