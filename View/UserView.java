package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class UserView {
    private User user = null;
    private JFrame userWindow;

    private JPanel userPanel;
    private JTextField userID;

    private JPanel followPanel;
    private JButton followUser;

    private JPanel followingPanel;
    private JScrollPane scrollFollowing;
    private JTextArea following;

    private JPanel tweetPanel;
    private JTextField tweet;
    private JButton postTweet;

    private JPanel feedPanel;
    private JScrollPane scrollFeed;
    private JTextArea feed;

    private JPanel infoPanel;
    private JLabel timeCreated;
    private JLabel timeUpdated;


    public UserView(User user){
        this.user = user;

        /*
            User Window
        */
        userWindow = new JFrame(user.getID());

        /*
            User Panel
        */
        userPanel = new JPanel();
        userPanel.setLayout(new GridBagLayout());

        /*
           Follow User Panel
        */
        followPanel = new JPanel();
        followPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = .4;
        gbc.fill = GridBagConstraints.BOTH;
        userPanel.add(followPanel, gbc);

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
        List<User> followingList = user.getFollowing();
        String followingText = "Following:\n";
        for(User u : followingList){
            followingText += "- " + u.getID() + "\n";
        }
        following = new JTextArea();
        following.setText(followingText);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        scrollFollowing = new JScrollPane(following);
        followingPanel.add(scrollFollowing, gbc);

        /*
           Tweet Panel
        */
        tweetPanel = new JPanel();
        tweetPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = .4;
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
        List<String[]> tweetFeed = user.getFeed();
        String feedText = "Feed:\n";
        for(String[] nameTweet: tweetFeed){
            feedText += "[" + nameTweet[0] + "] " + nameTweet[1] + "\n";
        }
        feed = new JTextArea();
        feed.setText(feedText);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = .5;
        gbc.weighty = .2;
        gbc.fill = GridBagConstraints.BOTH;
        scrollFeed = new JScrollPane(feed);
        feedPanel.add(scrollFeed, gbc);

        /*
           Info Panel
        */
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = .5;
        gbc.weighty = .2;
        gbc.fill = GridBagConstraints.BOTH;
        userPanel.add(infoPanel, gbc);

        // Feed Text Area
        timeCreated = new JLabel();
        timeCreated.setText("Time User Created: " + new Long(user.getTimeCreated()).toString());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = .5;
        gbc.weighty = .2;
        gbc.fill = GridBagConstraints.VERTICAL;
        infoPanel.add(timeCreated, gbc);

        // Feed Text Area
        timeUpdated = new JLabel();
        timeUpdated.setText("Time Last Updated: " + new Long(user.getTimeUpdated()).toString());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = .5;
        gbc.weighty = .2;
        gbc.fill = GridBagConstraints.VERTICAL;
        infoPanel.add(timeUpdated, gbc);

        setListeners();

        userWindow.add(userPanel);
        userWindow.setVisible(true);
        userWindow.setSize(500, 500);
    }

    private void setListeners() {
        followUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.followUser(user, userID.getText());
            }
        });
        postTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.postTweet(user, tweet.getText());
            }
        });
    }

    public void notifyFollowingChange(){
        List<User> followingList = user.getFollowing();
        String followingText = "Following:\n";
        for(User u : followingList){
            followingText += "- " + u.getID() + "\n";
        }
        following.setText(followingText);
    }

    public void notifyTweetChange(){
        List<String[]> tweetFeed = user.getFeed();
        String feedText = "Feed:\n";
        for(String[] nameTweet: tweetFeed){
            feedText += "[" + nameTweet[0] + "] " + nameTweet[1] + "\n";
        }
        feed.setText(feedText);
        timeUpdated.setText("Time Last Updated: " + new Long(user.getTimeUpdated()).toString());
    }

    public UserView getInstance(){
        return this;
    }

    public User getUser(){ return user; }

    public void setInvisible() { userWindow.setVisible(false);}

    public void setVisible() { userWindow.setVisible(true);}

}
