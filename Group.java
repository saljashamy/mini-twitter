import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Group implements Component {
    public static HashSet<String> groupIDs = new HashSet<>();
    private static Group root = new Group("root");
    private static int groupCount = 0;

    private Group group = null;
    private String id;
    private List<Component> components = new ArrayList<>();

    public Group(String id){
        if(groupIDs.contains(id)){
            throw new RuntimeException("Group already exists");
        }
        else{
            setID(id);
            groupIDs.add(id);
            groupCount++;
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
            for(Component component : root.getComponents()){
                if(component instanceof Group && component.getID().equals(groupNode)){
                    groupCount++;
                    ((Group) component).addComponent((Component)user);
                    break;
                }
            }
        }
        AdminView.notifyModelChange(user.getID(), "");
    }

    public static void groupUserChange(String groupNode, String groupID, String userID){
        User user = new User(userID);
        Group group = new Group(groupID);
        group.addComponent(user);
        for(Component component : root.getComponents()){
            if(component instanceof Group && component.getID().equals(groupNode)){
                groupCount++;
                ((Group) component).addComponent((Component)group);
                break;
            }
        }
        AdminView.notifyModelChange(user.getID(), group.getID());
    }


    public static void groupChange(String groupNode, String groupID){
        Group group = new Group(groupID);
        for(Component component : root.getComponents()){
            if(component instanceof Group && component.getID().equals(groupNode)){
                groupCount++;
                ((Group) component).addComponent((Component)group);
                break;
            }
        }
        AdminView.notifyModelChange("", group.getID());
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
}
