import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserView {
    private User user = null;
    private JFrame userWindow;

    private JTextField userID;
    private JButton followUser;
    private JTextArea following;
    private JTextField tweet;
    private JButton postTweet;
    private JTextArea feed;
    private JPanel userPanel;
    private JPanel followPanel;
    private JPanel followingPanel;
    private JPanel tweetPanel;
    private JPanel feedPanel;

    public UserView(User user){
        this.user = user;

        /*
            User Window
        */
        userWindow = new JFrame();

        /*
            User Panel
        */
        userPanel = new JPanel();
        userPanel.setLayout(new GridBagLayout());
        followPanel = new JPanel();
        followPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        userPanel.add(followPanel, gbc);

        /*
           Follow User Panel
        */
        // User ID Field
        userID = new JTextField();
        userID.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        followPanel.add(userID, gbc);

        // Follow User Button
        followUser = new JButton();
        followUser.setText("Follow User");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        followPanel.add(followUser, gbc);

        /*
           Following Panel
        */
        followingPanel = new JPanel();
        followingPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        userPanel.add(followingPanel, gbc);

        // Following Text Area
        List<User> followingList = user.getFollowers();
        String followingText = "Following:\n";
        for(User u : followingList){
            followingText += "- " + u.getID();
        }
        following = new JTextArea();
        following.setText(followingText);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        followingPanel.add(following, gbc);

        /*
           Tweet Panel
        */
        tweetPanel = new JPanel();
        tweetPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        userPanel.add(tweetPanel, gbc);

        // Tweet Text Field
        tweet = new JTextField();
        tweet.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tweetPanel.add(tweet, gbc);

        // Post Tweet Button
        postTweet = new JButton();
        postTweet.setText("Post Tweet");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tweetPanel.add(postTweet, gbc);

        /*
           Feed Panel
        */
        feedPanel = new JPanel();
        feedPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        userPanel.add(feedPanel, gbc);

        // Feed Text Area
        List<String> feedList = user.getFeed();
        String feedText = "Feed:\n";
        for(String f : feedList){
            feedText += "- " + f;
        }
        feed = new JTextArea();
        feed.setText(feedText);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        feedPanel.add(feed, gbc);

        setListeners();

        userWindow.add(userPanel);
        userWindow.setVisible(true);
        userWindow.setSize(600, 400);
    }

    private void setListeners() {
        followUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.followUser(user, userID.getText());
            }
        });
    }

    public void updateFollowers(){
        List<User> followingList = user.getFollowers();
        String followingText = "Following:\n";
        for(User u : followingList){
            followingText += "- " + u.getID();
        }
        System.out.println(followingText);
        following.setText(followingText);
        userWindow.revalidate();
    }

}
