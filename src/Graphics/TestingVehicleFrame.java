package Graphics;

import vehicle.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TestingVehicleFrame {
    private static TestingVehicleFrame instance;
    private JFrame frame;
    private ImageIcon imageVehicle;
    private JComboBox<Double> comboBox;

    private TestingVehicleFrame() {
        frame = new JFrame("Take vehicle for test");
        frame.setBounds(100, 100, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 3, 10, 10));
        frame.getContentPane().setBackground(Color.darkGray);

        comboBox = new JComboBox<>();
        for (double i = 0; i < 200000; i += 5) {
            comboBox.addItem(i);
        }
        comboBox.setSelectedItem("choose a distance");

        ArrayList<JLabel> labels = new ArrayList<>();
        for (Vehicle vehicle : MainFrame.vehicleList) {
            JLabel label = createVehiclePanel(vehicle);
            frame.getContentPane().add(label);
            labels.add(label);
        }
        frame.setVisible(true);
    }
    public static TestingVehicleFrame getInstance() {
        if (instance == null) {
            instance = new TestingVehicleFrame();
        }
        return instance;
    }
    public static void resetInstance() {
        instance.frame.dispose();
        instance = null;
    }
    private JLabel createVehiclePanel(Vehicle vehicle) {
        JLabel panel = new JLabel();
        panel.setLayout(new GridLayout(2, 1));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 200));

        imageVehicle = new ImageIcon(vehicle.getImage().getImage());
        Image image = imageVehicle.getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);

        this.imageVehicle = new ImageIcon(image);
        imageLabel.setIcon(imageVehicle);
        panel.add(imageLabel);

        JTextArea detailsArea = new JTextArea(vehicle.toString());
        detailsArea.setEditable(false);
        panel.add(detailsArea);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                synchronized (TestingVehicleFrame.this) {
                    if (!TestManager.isVehicleInTest(vehicle)) {
                        int distance;
                        try {
                            distance = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "Please enter the distance you would like to drive."));
                        } catch (NumberFormatException exp) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input. Distance must be an Integer.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (distance <= 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input. Distance cannot be Zero or Negative number",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (vehicle.getDistanceTraveled() + distance < 200000) {
                            try {

                                vehicle.move(distance);
                                TestManager.startTest(vehicle, distance);
                                JOptionPane.showMessageDialog(null, "The vehicle is now being tested.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (IllegalStateException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You can't travel more than 200000 km", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This vehicle is already being tested.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        return panel;
    }
}
