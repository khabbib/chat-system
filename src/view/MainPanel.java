package view;

import model.User;
import model.client.Client;
import view.panel.CenterLPanel;
import view.panel.CenterRPanel;
import view.panel.NorthPanel;
import view.panel.SouthPanel;

import javax.swing.*;
import java.util.ArrayList;

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

        cRPanel = new CenterRPanel((width / 5) + 20, height - (height / 2) + 30, client);
        add(cRPanel);

        sPanel = new SouthPanel(width, height - (height / 8), client);
        add(sPanel);
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

    public void setList(String userList){
        cRPanel.setUserList(userList);
    }
}
