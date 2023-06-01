package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//import my packages
import vehicle.*;

public class MainFrame extends JFrame implements ActionListener {

    //data members
    static ArrayList<Vehicle> vehicleList; //np modifier (only for package gui)
    public static void main(String[] args) {

        //create new array list
        //create new object
        vehicleList = new ArrayList<Vehicle>();
        MainFrame frame = new MainFrame();

    }

    //data members

    //all Buttons
    private final JButton buttonJeep = new JButton();
    private final JButton buttonFrigate = new JButton();
    private final JButton buttonGamePlane = new JButton();
    private final JButton buttonSpyPlane = new JButton();
    private final JButton buttonBicycle = new JButton();
    private final JButton buttonCruise = new JButton();
    private final JButton buttonAmphibious = new JButton();
    private final JButton buttonElectricBicycle = new JButton();
    private final JButton buttonHybridPlane = new JButton();
    private final JButton buttonShowAllVehicle = new JButton();


    //constructor
    public MainFrame() {
        super("Car Agency");
        this.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 5));
        getContentPane().add(panel);
        //all images icons
        ImageIcon iconJeep = new ImageIcon("src/ImgSource/jeep3.png");
        ImageIcon iconFrigate = new ImageIcon("src/ImgSource/frigate.png");
        ImageIcon iconGameGlider = new ImageIcon("src/ImgSource/gameGlider.png");
        ImageIcon iconSpyGlider = new ImageIcon("src/ImgSource/spyGlider.png");
        ImageIcon iconBicycle = new ImageIcon("src/ImgSource/bicycle1.png");
        ImageIcon iconCruise = new ImageIcon("src/ImgSource/cruise.png");
        ImageIcon iconAmphibious = new ImageIcon("src/ImgSource/amphibious.png");
        ImageIcon iconElectricBicycle = new ImageIcon("src/ImgSource/electricBike.png");
        ImageIcon iconHybridPlane = new ImageIcon("");
        ImageIcon iconMenu = new ImageIcon("");
        //all labels buttons
        JLabel labelJeep = new JLabel("Jeep");
        JLabel labelFrigate = new JLabel("Frigate");
        JLabel labelGamePlane = new JLabel("Game Plane");
        JLabel labelSpyPlane = new JLabel("Spy Plane");
        JLabel labelBicycle = new JLabel("Bicycle");
        JLabel labelCruise = new JLabel("Cruise");
        JLabel labelAmphibious = new JLabel("Amphibious");
        JLabel labelElectricBicycle = new JLabel("Electric Bicycle");
        JLabel labelHybridPlane = new JLabel("Hybrid Plane");
        JLabel labelMenu = new JLabel("Go to Menu");



        //add all panels
        panel.add(newPanel(iconJeep, labelJeep, buttonJeep));
        panel.add(newPanel(iconFrigate, labelFrigate, buttonFrigate));
        panel.add(newPanel(iconGameGlider, labelGamePlane, buttonGamePlane));
        panel.add(newPanel(iconSpyGlider, labelSpyPlane, buttonSpyPlane));
        panel.add(newPanel(iconBicycle, labelBicycle, buttonBicycle));
        panel.add(newPanel(iconCruise, labelCruise, buttonCruise));
        panel.add(newPanel(iconAmphibious, labelAmphibious, buttonAmphibious));
        panel.add(newPanel(iconElectricBicycle, labelElectricBicycle, buttonElectricBicycle));
        panel.add(newPanel(iconHybridPlane, labelHybridPlane, buttonHybridPlane));
        panel.add(newPanel(iconMenu, labelMenu, buttonShowAllVehicle));

        //action listeners
        buttonShowAllVehicle.addActionListener(this);
        buttonJeep.addActionListener(this);
        buttonFrigate.addActionListener(this);
        buttonGamePlane.addActionListener(this);
        buttonSpyPlane.addActionListener(this);
        buttonBicycle.addActionListener(this);
        buttonCruise.addActionListener(this);
        buttonAmphibious.addActionListener(this);
        buttonElectricBicycle.addActionListener(this);
        buttonHybridPlane.addActionListener(this);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);
    }

    public JPanel newPanel(ImageIcon icon, JLabel label, JButton button){
        JPanel panel = new JPanel();
//        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(500, 500));
//        label.setForeground(Color.WHITE);
        panel.add(button);
        panel.add(label);
        return panel;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonShowAllVehicle) {
            // Open new frame for menu

//            OnlySeeAllVehicle onlySeeAllVehicle = new OnlySeeAllVehicle();
            if (MainFrame.vehicleList.size() == 0) {
                JOptionPane.showMessageDialog(null, "There is no vehicle in the agency!");
            } else {
//                OnlySeeAllVehicle onlySeeAllVehicle = new OnlySeeAllVehicle();
                MenuFrame menuFrame = MenuFrame.getInstance();
                this.dispose(); // Close current frame
            }

        }
        if (e.getSource() == buttonJeep) {
            // Open new frame for menu
            CreateVehicleFrame detailsForJeep = new CreateVehicleFrame("Jeep");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonFrigate) {
            // Open new frame for menu
            CreateVehicleFrame detailsForFrigate = new CreateVehicleFrame("Frigate");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonGamePlane) {
            // Open new frame for menu
            CreateVehicleFrame detailsForGamePlane = new CreateVehicleFrame("GamePlane");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonSpyPlane) {
            // Open new frame for menu
            CreateVehicleFrame detailsForSpyPlane = new CreateVehicleFrame("SpyPlane");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonBicycle) {
            // Open new frame for menu
            CreateVehicleFrame detailsForBicycle = new CreateVehicleFrame("Bicycle");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonCruise) {
            // Open new frame for menu
            CreateVehicleFrame detailsForCruise = new CreateVehicleFrame("CruiseShip");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonAmphibious) {
            // Open new frame for menu
            CreateVehicleFrame detailsForAmphibious = new CreateVehicleFrame("Amphibious");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonElectricBicycle) {
            // Open new frame for menu
            CreateVehicleFrame detailsForElectricBicycle = new CreateVehicleFrame("ElectricBicycle");
            this.dispose(); // Close current frame
        }
        if (e.getSource() == buttonHybridPlane) {
            // Open new frame for menu
            CreateVehicleFrame detailsForHybridPlane = new CreateVehicleFrame("HybridPlane");
            this.dispose(); // Close current frame
        }

    }
}


