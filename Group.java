import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Group implements Component, VisitableNode {

    public static HashSet<String> groupIDs = new HashSet<>();
    private static Group root = new Group("root");

    private String id;
    private List<Component> components = new ArrayList<>();

    public Group(String id){
        if(groupIDs.contains(id)){
            throw new RuntimeException("Group already exists");
        }
        else{
            setID(id);
            groupIDs.add(id);
        }
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public List<Component> getComponents(){
        return components;
    }

    public static void userChange(String groupNode, String userID){
        User user = new User(userID);
        if (groupNode == "root"){
            root.addComponent((Component)user);
        }
        else{
            Group parentGroup = getGroup(root, groupNode);
            parentGroup.addComponent(user);
        }
        AdminView.notifyModelChange();
    }

    public static void groupUserChange(String groupNode, String groupID, String userID){
        User user = new User(userID);
        Group group = new Group(groupID);
        group.addComponent(user);
        if(groupNode.equals("root")){
            root.addComponent(group);
        }
        else{
            Group parentGroup = getGroup(root, groupNode);
            parentGroup.addComponent(group);
        }
        AdminView.notifyModelChange();
    }

    public static Group getGroup(Group parent, String groupID){
        for(Component component : parent.getComponents()){
            if(component instanceof Group && component.getID().equals(groupID)){
                return (Group) component;
            }
            else if(component instanceof Group){
                Group group = getGroup((Group) component, groupID);
                if(group != null){
                    return group;
                }
            }
        }
        return null;
    }

    public static User getUser(Group parent, String userID){
        User user = null;
        for(Component component : parent.getComponents()){
            if(component instanceof Group){
                user = getUser((Group)component, userID);
            }
            else if(component instanceof User && component.getID().equals(userID)){
                user =  (User) component;
            }
        }
        return user;
    }

    public static Group getRoot(){
        return root;
    }

    public void setID(String id){
        this.id = id;
    }

    @Override
    public String getID(){
        return id;
    }

    @Override
    public int accept(NodeVistor visitor) {
        return visitor.visit(this);
    }
}
