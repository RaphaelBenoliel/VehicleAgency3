package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import vehicle.*;

public class MainFrame extends JFrame implements ActionListener {

    //data members
    static final ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();


    //data members

    //all Buttons
    private final JButton buttonJeep = new JButton();
    private final JButton buttonFrigate = new JButton();
    private final JButton buttonGameGlider = new JButton();
    private final JButton buttonSpyGlider = new JButton();
    private final JButton buttonBicycle = new JButton();
    private final JButton buttonCruise = new JButton();
    private final JButton buttonAmphibious = new JButton();
    private final JButton buttonElectricBicycle = new JButton();
    private final JButton buttonHybridPlane = new JButton();
    private final JButton buttonClose = new JButton();


    //constructor
    public MainFrame() {
        super("Choose a Vehicle to create");
        this.setSize(800, 600);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 5));
        getContentPane().add(panel);
        //all images icons

        ImageIcon iconJeep = new ImageIcon("src/ImgSource/jeep3.png");
        Image img = iconJeep.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconJeep = new ImageIcon(img);

        ImageIcon iconFrigate = new ImageIcon("src/ImgSource/frigate.png");
        img = iconFrigate.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconFrigate = new ImageIcon(img);

        ImageIcon iconGameGlider = new ImageIcon("src/ImgSource/gameGlider.png");
        img = iconGameGlider.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconGameGlider = new ImageIcon(img);

        ImageIcon iconSpyGlider = new ImageIcon("src/ImgSource/spyGlider.png");
        img = iconSpyGlider.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconSpyGlider = new ImageIcon(img);

        ImageIcon iconBicycle = new ImageIcon("src/ImgSource/bicycle1.png");
        img = iconBicycle.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconBicycle = new ImageIcon(img);

        ImageIcon iconCruise = new ImageIcon("src/ImgSource/cruise.png");
        img = iconCruise.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconCruise = new ImageIcon(img);

        ImageIcon iconAmphibious = new ImageIcon("src/ImgSource/amphibious.png");
        img = iconAmphibious.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconAmphibious = new ImageIcon(img);

        ImageIcon iconElectricBicycle = new ImageIcon("src/ImgSource/electricBike.png");
        img = iconElectricBicycle.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconElectricBicycle = new ImageIcon(img);

        ImageIcon iconHybridPlane = new ImageIcon("src/ImgSource/hybridPlane.png");
        img = iconHybridPlane.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        iconHybridPlane = new ImageIcon(img);

        ImageIcon iconMenu = new ImageIcon("src/ImgSource/menu.png");
        img = iconMenu.getImage().getScaledInstance(160, 250, java.awt.Image.SCALE_SMOOTH);
        iconMenu = new ImageIcon(img);

        //all labels buttons
        JLabel labelJeep = new JLabel("Jeep");
        labelJeep.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
//        labelJeep.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel labelFrigate = new JLabel("Frigate");
        labelFrigate.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelGamePlane = new JLabel("Game Glider");
        labelGamePlane.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelSpyPlane = new JLabel("Spy Glider");
        labelSpyPlane.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelBicycle = new JLabel("Bicycle");
        labelBicycle.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelCruise = new JLabel("Cruise");
        labelCruise.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelAmphibious = new JLabel("Amphibious");
        labelAmphibious.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelElectricBicycle = new JLabel("Electric Bicycle");
        labelElectricBicycle.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelHybridPlane = new JLabel("Hybrid Plane");
        labelHybridPlane.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));
        JLabel labelMenu = new JLabel("Go to Menu");
        labelMenu.setFont(labelJeep.getFont().deriveFont(Font.BOLD, 16));


        //add all panels
        panel.add(newPanel(iconJeep, labelJeep, buttonJeep));
        panel.add(newPanel(iconFrigate, labelFrigate, buttonFrigate));
        panel.add(newPanel(iconGameGlider, labelGamePlane, buttonGameGlider));
        panel.add(newPanel(iconSpyGlider, labelSpyPlane, buttonSpyGlider));
        panel.add(newPanel(iconBicycle, labelBicycle, buttonBicycle));
        panel.add(newPanel(iconCruise, labelCruise, buttonCruise));
        panel.add(newPanel(iconAmphibious, labelAmphibious, buttonAmphibious));
        panel.add(newPanel(iconElectricBicycle, labelElectricBicycle, buttonElectricBicycle));
        panel.add(newPanel(iconHybridPlane, labelHybridPlane, buttonHybridPlane));
        panel.add(newPanel(iconMenu, labelMenu, buttonClose));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (MainFrame.vehicleList.size() == 0) {
                    JOptionPane.showMessageDialog(null, "There is no vehicle in the agency!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //open menu frame and close this frame
                    MenuFrame.getInstance();
                    dispose();
                }
            }
        });
        //action listeners
        buttonClose.addActionListener(this);
        buttonJeep.addActionListener(this);
        buttonFrigate.addActionListener(this);
        buttonGameGlider.addActionListener(this);
        buttonSpyGlider.addActionListener(this);
        buttonBicycle.addActionListener(this);
        buttonCruise.addActionListener(this);
        buttonAmphibious.addActionListener(this);
        buttonElectricBicycle.addActionListener(this);
        buttonHybridPlane.addActionListener(this);
        setVisible(true);
    }

    public JPanel newPanel(ImageIcon icon, JLabel label, JButton button){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(500, 500));
        panel.add(button);
        panel.add(label);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonClose){
            if (MainFrame.vehicleList.size() == 0) {
                JOptionPane.showMessageDialog(null, "There is no vehicle in the agency!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Open new frame for menu singleton
                MenuFrame.getInstance();
                this.dispose();
            }
        }
        if (e.getSource() == buttonJeep) {
            new CreateVehicleFrame("Jeep");
            this.dispose();
        }
        if (e.getSource() == buttonFrigate) {
            new CreateVehicleFrame("Frigate");
            this.dispose();
        }
        if (e.getSource() == buttonGameGlider) {
            new CreateVehicleFrame("GameGlider");
            this.dispose();
        }
        if (e.getSource() == buttonSpyGlider) {
            new CreateVehicleFrame("SpyGlider");
            this.dispose();
        }
        if (e.getSource() == buttonBicycle) {
            new CreateVehicleFrame("Bicycle");
            this.dispose();
        }
        if (e.getSource() == buttonCruise) {
            new CreateVehicleFrame("CruiseShip");
            this.dispose();
        }
        if (e.getSource() == buttonAmphibious) {
            new CreateVehicleFrame("Amphibious");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonElectricBicycle) {
            new CreateVehicleFrame("ElectricBicycle");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonHybridPlane) {
            new CreateVehicleFrame("HybridPlane");
            this.dispose(); // Close current frame
        }
    }
}
