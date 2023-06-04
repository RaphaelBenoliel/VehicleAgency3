package Graphics;


import vehicle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InventoryFrame {
    private static InventoryFrame instance;
    //data members
    private JFrame frame = new JFrame("Inventory");
    private JButton buttonMenu = new JButton();

    private ImageIcon imageVehicle;

    //constructor
    private InventoryFrame() {

        int numberOfColumns = (int) Math.ceil(Math.sqrt(MainFrame.vehicleList.size()));
        int numberOfRows = (int) Math.ceil((double) MainFrame.vehicleList.size() / numberOfColumns);
        if(numberOfRows == 0 && numberOfColumns == 0){
            numberOfRows = 1;
            numberOfColumns = 1;
        }
        frame.getContentPane().setLayout(new GridLayout(numberOfRows,numberOfColumns));
        frame.setBounds(0, 0, 800, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        buttonMenu.setPreferredSize(new Dimension(50, 50));

        ArrayList<JLabel> labels = new ArrayList<>();
        for (Vehicle i : MainFrame.vehicleList) {
            JLabel label = VehiclePanels(i);
            frame.add(label);
            labels.add(label);
        }
        buttonMenu.setText("close");
//        buttonMenu.setVerticalTextPosition(AbstractButton.CENTER);
//        buttonMenu.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        buttonMenu.setMnemonic(KeyEvent.VK_I);
        buttonMenu.setPreferredSize(new Dimension(100, 50));

        JPanel PanelButton = new JPanel();
        PanelButton.add(buttonMenu, BorderLayout.SOUTH);

        frame.add(PanelButton);
        //PanelButton.add(new ImageIcon("src/Graphics/Icons/Menu.png"));


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
    public static void resetInstance() {
        instance.frame.dispose();
        instance = null;
    }
    public JLabel VehiclePanels(Vehicle vehicle) {
        JLabel panel = new JLabel();
        panel.setLayout(new GridLayout(1, 1)); // 2 rows
        // Add image
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(400, 200));

        imageVehicle = new ImageIcon(vehicle.getImage().getImage());
//        System.out.println(vehicle.getPath());

        Image image = imageVehicle.getImage(); // transform it
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

        this.imageVehicle = new ImageIcon(image);  // transform it back
        imageLabel.setIcon(imageVehicle);
        panel.add(imageLabel);
        // Add details
        JTextArea detailsArea = new JTextArea(vehicle.toString());
        detailsArea.setEditable(false);
        //panel.add(detailsArea);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JComponent componentToString = (JComponent)e.getSource();
                componentToString.setToolTipText("<html><span style='font-size:12px'>" + vehicle.toString().replace("\n", "<br>") + "</span></html>");

            }
        });

        return panel;
    }
}
