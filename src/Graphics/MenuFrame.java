package Graphics;
import vehicle.*;

import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.System.exit;

public class MenuFrame extends JFrame implements ActionListener {
    private static MenuFrame instance;
    private final JButton addVehicleButton = new JButton("Add Vehicle");
    private final JButton buyVehicleButton = new JButton("Buy Vehicle");
    private final JButton takeVehicleButton = new JButton("Test Drive");
    private final JButton resetDistanceButton = new JButton("Reset Distance");
    private final JButton changeFlagButton = new JButton("Change flag");
    private final JButton inventoryButton = new JButton("Inventory");
    private final JButton exitButton = new JButton("Quit");

    private final Object lock = new Object();


    //constructor
    private MenuFrame() {
        super("Vehicle Agency Menu");
        this.setSize(800, 600);

        JPanel panel = new JPanel(new GridLayout(7, 1));
        getContentPane().add(panel);


        //adding buttons to panel
        panel.add(addVehicleButton);
        panel.add(buyVehicleButton);
        panel.add(takeVehicleButton);
        panel.add(resetDistanceButton);
        panel.add(changeFlagButton);
        panel.add(inventoryButton);
        panel.add(exitButton);

        //change size and font of buttons
        addVehicleButton.setFont(new Font("Arial", Font.BOLD, 20));
        buyVehicleButton.setFont(new Font("Arial", Font.BOLD, 20));
        takeVehicleButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetDistanceButton.setFont(new Font("Arial", Font.BOLD, 20));
        changeFlagButton.setFont(new Font("Arial", Font.BOLD, 20));
        inventoryButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));

        //action listeners of buttons
        addVehicleButton.addActionListener(this);
        buyVehicleButton.addActionListener(this);
        takeVehicleButton.addActionListener(this);
        resetDistanceButton.addActionListener(this);
        changeFlagButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        exitButton.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static MenuFrame getInstance() {
        if (instance == null) {
            instance = new MenuFrame();
        }
        return instance;
    }
    //methods
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        boolean flag = false;
        if (source == addVehicleButton) {
            new MainFrame();

        }
        if (source == buyVehicleButton) {
           if (BuyingVehicleFrame.getInstance() == null) {
               BuyingVehicleFrame.getInstance();
            }
           else {
               BuyingVehicleFrame.resetInstance();
               BuyingVehicleFrame.getInstance();
           }
        }
        if (source == takeVehicleButton) {
            if (TestingVehicleFrame.getInstance() == null) {
                TestingVehicleFrame.getInstance();
            }
            else {
                TestingVehicleFrame.resetInstance();
                TestingVehicleFrame.getInstance();
            }
        }
        //this.dispose();
        if (source == resetDistanceButton) {
            Reset();
        }
        if (source == changeFlagButton) {
           for (Object vehicle : MainFrame.vehicleList) {
                if (vehicle instanceof ISeaTransportation) {
                    flag = true;
                    FlagsFrame flagsFrame = new FlagsFrame();
                }
            }
           if (!flag) JOptionPane.showMessageDialog(null, "There is no sea vehicle in the agency!", "Error", JOptionPane.ERROR_MESSAGE);
//            this.dispose();
        }
        if (source == inventoryButton) {
            InventoryFrame allVehicles = new InventoryFrame();
//            this.dispose();
        }
        if (source == exitButton) {
            if(TestManager.isAnyVehicleInTest() || BuyManager.isAnyVehicleInBuyProgress()){
                    JOptionPane.showMessageDialog(null, "You can't exit while vehicle is in test!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Goodbye!","Quit" ,JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        }
    }

    private void Reset() {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wanna RESET? ", "RESET", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        synchronized (MainFrame.vehicleList) {
                            Random rand = new Random();
                            int randomNum;
                            randomNum = 3000 + rand.nextInt((8000 - 3000) + 1);
                            LoadingDBFrame loadingDBFrame = new LoadingDBFrame("Updating Database...");
                            for (Vehicle i : MainFrame.vehicleList) {
                                i.setDistanceTraveled(0);
                            }
                            Thread.sleep(randomNum);
                            loadingDBFrame.setText("Update Done!");
                            Thread.sleep(700);
                            loadingDBFrame.terminate();
                        }
                    } catch (InterruptedException e) {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                }
            });
            t.start();

        } else {
            JOptionPane.showMessageDialog(null, "Reset Canceled");
        }
    }
}
