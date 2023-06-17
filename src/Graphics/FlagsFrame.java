package Graphics;

import vehicle.Amphibious;
import vehicle.ISeaTransportation;
import vehicle.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class FlagsFrame extends JFrame implements ActionListener {
    private static FlagsFrame instance;

        private JButton[] flagButtons;
        private String[] flagNames = {"Israel", "USA", "german", "Greece", "somalia", "pirate", "italy"};
        private ImageIcon[] flagImages;

        private FlagsFrame() {
            super("Choose a Flag");
            setSize(1000, 600);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel panel = new JPanel();
            getContentPane().add(panel);
            panel.setLayout(new GridLayout(2, 4));

            // Load the flag images
            flagImages = new ImageIcon[7];
            for (int i = 0; i < 7; i++) {
                Image img = new ImageIcon("src/ImgSource/" + flagNames[i] + ".png").getImage();
                // Resize the image to your desired dimensions
                Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                flagImages[i] = new ImageIcon(resizedImg);
            }

            // Create the buttons
            flagButtons = new JButton[7];
            for (int i = 0; i < 7; i++) {
                flagButtons[i] = new JButton(flagImages[i]);
                flagButtons[i].setActionCommand(flagNames[i]);
                flagButtons[i].setBackground(Color.darkGray);
                flagButtons[i].addActionListener(this);
            }

            // Create the layout
            for (int i = 0; i < 7; i++) {
                panel.add(flagButtons[i]);
            }
            // Add the panel to the frame
            setContentPane(panel);
            setVisible(true);
        }
    public static FlagsFrame getInstance() {
        if (instance == null) {
            instance = new FlagsFrame();
        }
        return instance;
    }
    public static void resetInstance() {
        instance.dispose();
        instance = null;
    }
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            // Get the name of the selected flag
            String flagName = e.getActionCommand();
            // Do something with the flagName
            JOptionPane.showMessageDialog(this, "You selected the " + flagName + " flag.");
            if (source == flagButtons[0]){
                changeFlag("Israel");
            } //Israel
            if (source == flagButtons[1]){
                changeFlag("USA");
            } //UnitedState
            if (source == flagButtons[2]){
                changeFlag("German");
            } //Germany
            if (source == flagButtons[3]){
                changeFlag("Greece");
            } //Greece
            if (source == flagButtons[4]){
                changeFlag("Somalia");
            } //Somalia
            if (source == flagButtons[5]){
                changeFlag("Pirate");
            } //JollyRoger
            if (source == flagButtons[6]){
                changeFlag("Italy");
            } //Italia

            this.dispose();
            MenuFrame frame = MenuFrame.getInstance();
        }

            public void changeFlag(String flagName) {
                Thread t = new Thread(() -> {
                    try {
                        synchronized (VehicleMenuFrame.vehicleList) {
                            Random rand = new Random();
                            int randomNum;
                            randomNum = 3000 + rand.nextInt((8000 - 3000) + 1);
                            LoadingDBFrame loadingDBFrame = new LoadingDBFrame("Updating Database...");
                            for (Vehicle i : VehicleMenuFrame.vehicleList) {
                                if (i instanceof ISeaTransportation)
                                    ((ISeaTransportation) i).setCountryFlag(flagName);
                                else if (i instanceof Amphibious)
                                    ((Amphibious) i).setCountryFlag(flagName);
                            }
                            Thread.sleep(randomNum);
                            loadingDBFrame.setText("Update Done!");
                            Thread.sleep(700);
                            loadingDBFrame.terminate();
                        }
                    } catch (InterruptedException e) {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                });
                t.start();
            }
    }
