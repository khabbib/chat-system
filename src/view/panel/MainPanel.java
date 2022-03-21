package view.panel;

import model.User;
import controller.Client;

import javax.swing.*;

public class MainPanel extends JPanel {
    private NorthPanel nPanel;
    private CenterLPanel cLPanel;
    private CenterRPanel cRPanel;
    private SouthPanel sPanel;

    public MainPanel(int width, int height, Client client) {
        super(null);
        this.setSize(width, height);

        nPanel = new NorthPanel(width, height / 8, client);
        add(nPanel);

        cLPanel = new CenterLPanel(width - (width / 3), height - (height / 2) + 30, client);
        add(cLPanel);

        sPanel = new SouthPanel(width, height - (height / 8), client);
        add(sPanel);

        cRPanel = new CenterRPanel((width / 5) + 20, height - (height / 2) + 30, sPanel, client);
        add(cRPanel);
    }

    public NorthPanel getnPanel() {
        return nPanel;
    }

    public void setnPanel(NorthPanel nPanel) {
        this.nPanel = nPanel;
    }

    public CenterLPanel getcLPanel() {
        return cLPanel;
    }

    public void setcLPanel(CenterLPanel cLPanel) {
        this.cLPanel = cLPanel;
    }

    public CenterRPanel getcRPanel() {
        return cRPanel;
    }

    public void setcRPanel(CenterRPanel cRPanel) {
        this.cRPanel = cRPanel;
    }

    public SouthPanel getsPanel() {
        return sPanel;
    }

    public void setsPanel(SouthPanel sPanel) {
        this.sPanel = sPanel;
    }

    public void setList(User userList){
        cRPanel.setUserList(userList);
    }
}
