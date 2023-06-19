package Graphics;

import vehicle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryFrame {
    private static InventoryFrame instance;
    //data members
    private JFrame frame = new JFrame("Inventory");
    private JButton buttonMenu = new JButton();

    private ImageIcon imageVehicle;

    //constructor
    private InventoryFrame() {

        int numberOfColumns = (int) Math.ceil(Math.sqrt(VehicleMenuFrame.vehicleList.size()));
        int numberOfRows = (int) Math.ceil((double) VehicleMenuFrame.vehicleList.size() / numberOfColumns);
        if(numberOfRows == 0 && numberOfColumns == 0){
            numberOfRows = 1;
            numberOfColumns = 1;
        }
        frame.getContentPane().setLayout(new GridLayout(numberOfRows,numberOfColumns));
        frame.setBounds(0, 0, 800, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        buttonMenu.setPreferredSize(new Dimension(50, 50));

        ArrayList<JLabel> labels = new ArrayList<>();
        for (Vehicle i : VehicleMenuFrame.vehicleList) {
            JLabel label = VehiclePanels(i);
            frame.add(label);
            labels.add(label);
        }
        buttonMenu.setText("close");
        buttonMenu.setMnemonic(KeyEvent.VK_I);
        buttonMenu.setPreferredSize(new Dimension(100, 50));

        JPanel PanelButton = new JPanel();
        PanelButton.add(buttonMenu, BorderLayout.SOUTH);
        frame.add(PanelButton);

        buttonMenu.addActionListener(e -> {
            frame.setVisible(false);

        });

        frame.setVisible(true);
    }

    public static InventoryFrame getInstance() {
        if (instance == null) {
            instance = new InventoryFrame();
        }
        return instance;
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
    public static void resetInstance() {
        instance.frame.dispose();
        instance = null;
    }
    public JLabel VehiclePanels(Vehicle vehicle) {
        JLabel panel = new JLabel();
        panel.setLayout(new GridLayout(1, 1));

        // Add image
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(400, 200));
        imageVehicle = new ImageIcon(vehicle.getImage().getImage());
        Image image = imageVehicle.getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        this.imageVehicle = new ImageIcon(image);
        Color borderColor = getColorFromString(vehicle.getColor());
        imageLabel.setBorder(BorderFactory.createLineBorder(borderColor, 6));
        imageLabel.setIcon(imageVehicle);
        panel.add(imageLabel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JComponent componentToString = (JComponent)e.getSource();
                componentToString.setToolTipText("<html><span style='font-size:12px'>" + vehicle.toString().replace(
                        "\n", "<br>") + "</span></html>");
            }
        });
        return panel;
    }
}
