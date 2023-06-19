package Graphics;
import vehicle.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

public class BuyingVehicleFrame {
    private static BuyingVehicleFrame instance;
    private ImageIcon imageVehicle;
    private final JFrame frame;


    private BuyingVehicleFrame() {

        frame = new JFrame("Buy Vehicle");
        frame.setBounds(100, 100, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 3, 5, 5)); // 3 columns

        ArrayList<JLabel> labels = new ArrayList<>();
        for (Vehicle i : VehicleMenuFrame.vehicleList) {
            JLabel label = VehiclePanels(i);
            frame.getContentPane().add(label);
            labels.add(label);

        }
        frame.setVisible(true);

    }
    public static BuyingVehicleFrame getInstance() {
        if (instance == null) {
            instance = new BuyingVehicleFrame();
        }
        return instance;
    }
    public static void resetInstance() {
        instance.frame.dispose();
        instance = null;
    }
    private Color getColorFromString(String colorString) {
        // Define color mappings
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("red", Color.RED);
        colorMap.put("green", Color.GREEN);
        colorMap.put("blue", Color.BLUE);
        // Add more color mappings as needed

        // Get the corresponding Color object for the color string
        Color color = colorMap.get(colorString.toLowerCase());
        if (color == null) {
            // Default color if mapping is not found
            color = Color.BLACK;
        }
        return color;
    }
    public JLabel VehiclePanels(Vehicle vehicle) {
        JLabel panel = new JLabel();
        panel.setLayout(new GridLayout(2, 1));

        // Add image
        JLabel imageLabel = new JLabel();
//        imageLabel.setPreferredSize(new Dimension(10, 10));
        imageVehicle = new ImageIcon(vehicle.getImage().getImage());
        Image image = imageVehicle.getImage(); // transform it
        image = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        this.imageVehicle = new ImageIcon(image);
        Color borderColor = getColorFromString(vehicle.getColor());
        imageLabel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        imageLabel.setIcon(imageVehicle);
        panel.add(imageLabel);

        // Add details
        JTextArea detailsArea = new JTextArea(vehicle.toString());
        detailsArea.setBackground(Color.darkGray);
        detailsArea.setForeground(Color.white);
        detailsArea.setEditable(false);
        panel.add(detailsArea);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int delay = new Random().nextInt(6) + 5;
                try{
                    Thread.sleep(delay * 1000);
                    new LoadingDBFrame("Charging");
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                if (!TestManager.isVehicleInTest(vehicle)) {
                    int result = JOptionPane.showConfirmDialog(frame, "Do you want to buy this vehicle?", "Confirmation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You are successfully bought this vehicle", "Congratulations", JOptionPane.INFORMATION_MESSAGE);

                        update(vehicle);
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You can't buy this vehicle because it is already in test\n", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return panel;
    }

    public void update(Vehicle vehicle) {
        Thread t = new Thread(() -> {
            try {
                synchronized (VehicleMenuFrame.vehicleList) {
                    Random rand = new Random();
                    int randomNum;
                    randomNum = 3000 + rand.nextInt((8000 - 3000) + 1);
                    LoadingDBFrame loadingDBFrame = new LoadingDBFrame("Updating Database...");
                    VehicleMenuFrame.vehicleList.remove(vehicle);
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

    public void update() {
        Thread t = new Thread(() -> {
            try {
                synchronized (VehicleMenuFrame.vehicleList) {
                    Random rand = new Random();
                    int randomNum;
                    randomNum = 3000 + rand.nextInt((10000 - 5000) + 1);
                    LoadingDBFrame loadingDBFrame = new LoadingDBFrame("Charging Database");
                    Thread.sleep(randomNum);
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


