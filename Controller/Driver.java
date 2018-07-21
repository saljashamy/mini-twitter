package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;

public class Driver {

    // reference: https://stackoverflow.com/questions/8210630/how-to-search-a-particular-node-in-jtree-and-make-that-node-expanded
    private static TreePath find(DefaultMutableTreeNode root, String s) {
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

        // Add Groups
        JTree tree = AdminView.getTree();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();

        TreePath tPath = find(root, "root");
        tree.setSelectionPath(tPath);
        controller.addUser("root", "root", "user1");

        tPath = find(root, "root");
        tree.setSelectionPath(tPath);
        controller.addGroupUser("root", "group1", "user2");

        tPath = find(root, "group1");
        tree.setSelectionPath(tPath);
        controller.addGroupUser("group1", "group2", "user3");

        tPath = find(root, "group2");
        tree.setSelectionPath(tPath);
        controller.addGroupUser("group2", "group3", "user4");

        tPath = find(root, "group1");
        tree.setSelectionPath(tPath);
        controller.addUser("group1", "group1", "user5");

        tPath = find(root, "group1");
        tree.setSelectionPath(tPath);
        controller.addUser("group1", "group1", "user6");

        tPath = find(root, "group1");
        tree.setSelectionPath(tPath);
        controller.addUser("group1", "group1", "user7");

        tPath = find(root, "group2");
        tree.setSelectionPath(tPath);
        controller.addUser("group2", "group2", "user8");

        tPath = find(root, "root");
        tree.setSelectionPath(tPath);
        controller.addUser("root", "group4", "user9");

        tPath = find(root, "group4");
        tree.setSelectionPath(tPath);
        controller.addUser("group4", "group4", "user10");

        tPath = find(root, "group4");
        tree.setSelectionPath(tPath);
        controller.addUser("group4", "group4", "user11");

        tPath = find(root, "group1");
        tree.setSelectionPath(tPath);
        controller.addUser("group1", "group1", "user12");

        tPath = find(root, "group2");
        tree.setSelectionPath(tPath);
        controller.addUser("group2", "group2", "user13");

        tPath = find(root, "group3");
        tree.setSelectionPath(tPath);
        controller.addUser("group3", "group3", "user14");

        tPath = find(root, "root");
        tree.setSelectionPath(tPath);
        controller.addUser("root", "root", "user15");

        // Add Users
        Controller.requestUserView("user1");
        Controller.requestUserView("user2");
        Controller.requestUserView("user3");
        Controller.requestUserView("user4");
        Controller.requestUserView("user5");

        User user1 = Group.getUser(Group.getRoot(), "user1");
        User user2 = Group.getUser(Group.getRoot(), "user2");
        User user3 = Group.getUser(Group.getRoot(), "user3");
        User user4 = Group.getUser(Group.getRoot(), "user4");
        User user5 = Group.getUser(Group.getRoot(), "user5");

        // Add followers
        Controller.followUser(user1,"user2");
        Controller.followUser(user1,"user3");
        Controller.followUser(user1,"user4");
        Controller.followUser(user1,"user5");

        Controller.followUser(user2,"user1");
        Controller.followUser(user2,"user3");
        Controller.followUser(user2,"user4");

        Controller.followUser(user3,"user1");
        Controller.followUser(user3,"user2");

        Controller.followUser(user4,"user1");

        Controller.followUser(user5,"user4");

        Controller.setUserViewsInvisble();

    }
}
