package View;

import javax.swing.*;

public class AnalysisView {
    private static AnalysisView totalUsersView = null;
    private static AnalysisView totalGroupsView = null;
    private static AnalysisView totalMessagesView = null;

    private static JFrame[] analysisWindows = new JFrame[3];
    private static JPanel[] analysisPanels = new JPanel[3];
    private static JLabel[] titleFields = new JLabel[3];
    private static JLabel[] dataFields = new JLabel[3];

    private AnalysisView(String title, int data, int i, boolean display) {
        analysisWindows[i] = new JFrame();

        analysisPanels[i] = new JPanel();
        titleFields[i] = new JLabel(title, JLabel.CENTER);
        analysisPanels[i].add(titleFields[i]);
        dataFields[i] = new JLabel();
        dataFields[i].setText(new Integer(data).toString());
        analysisPanels[i].add(dataFields[i]);

        analysisWindows[i].add(analysisPanels[i]);
        analysisWindows[i].setVisible(display);
        analysisWindows[i].setSize(200, 100);
    }

    public static void getTotalUsersViewInstance(String title, int data, boolean display){
        if(totalUsersView == null){
            totalUsersView = new AnalysisView(title, data, 0, display);
        }
        else{
            totalUsersView.dataFields[0].setText(new Integer(data).toString());
            if(analysisWindows[0].isVisible() || display == true) {
                analysisWindows[0].setVisible(true);
            }
            else{
                analysisWindows[0].setVisible(false);
            }
        }
    }

    public static void getTotalGroupsViewInstance(String title, int data, boolean display){
        if(totalGroupsView == null){
            totalGroupsView = new AnalysisView(title, data, 1, display);
        }
        else{
            totalGroupsView.dataFields[1].setText(new Integer(data).toString());
            if(analysisWindows[1].isVisible() || display == true) {
                analysisWindows[1].setVisible(true);
            }
            else{
                analysisWindows[1].setVisible(false);
            }
        }
    }

    public static void getTotalMessagesViewInstance(String title, int data, boolean display){
        if(totalMessagesView == null){
            totalMessagesView = new AnalysisView(title, data, 2, display);
        }
        else{
            totalMessagesView.dataFields[2].setText(new Integer(data).toString());
            if(analysisWindows[2].isVisible() || display == true) {
                analysisWindows[2].setVisible(true);
            }
            else{
                analysisWindows[2].setVisible(false);
            }
        }
    }

}
