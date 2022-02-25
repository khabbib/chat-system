package view;

import controller.Controller;
import view.panel.CenterLPanel;
import view.panel.CenterRPanel;
import view.panel.NorthPanel;
import view.panel.SouthPanel;

import javax.swing.*;

public class MainPanel extends JPanel {
    private NorthPanel nPanel;
    private CenterLPanel cLPanel;
    private CenterRPanel cRPanel;
    private SouthPanel sPanel;

    public MainPanel(int width, int height, Controller controller) {
        super(null);
        this.setSize(width, height);

        nPanel = new NorthPanel(width, height / 8, controller);
        add(nPanel);

        cLPanel = new CenterLPanel(width - (width / 3), height - (height / 2) + 30, controller);
        add(cLPanel);

        cRPanel = new CenterRPanel((width / 5) + 20, height - (height / 2) + 30, controller);
        add(cRPanel);

        sPanel = new SouthPanel(width, height - (height / 8), controller);
        add(sPanel);
    }

}
