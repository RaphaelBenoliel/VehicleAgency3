package Graphics;

import vehicle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class CreateVehicleFrame extends JFrame implements ActionListener {

    //data members
    private JTextField modelNameField;

    private JTextField sourceOfEnergyField;
//    private String modelName;


    String vehicleType;
    private JComboBox<Integer> comboBoxPassengers, comboBoxWheels,comboBoxFuel, comboBoxLifeTime, comboBoxSpeed ;
    private JRadioButton withWindButton;
    private JComboBox<String> comboBoxKindOfLand, comboBoxFlag;
    private JButton confirmButton;


    public CreateVehicleFrame(String vehicleType) {
        this.vehicleType = vehicleType;
        setTitle(vehicleType + " Details"); //title frame
        setSize(500, 600); //size frame
        setLocationRelativeTo(null); //center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); //show frame

        //images
        ImageIcon iconJeep = new ImageIcon("src/ImgSource/jeep3.png");
        ImageIcon iconFrigate = new ImageIcon("src/ImgSource/frigate.png");
        ImageIcon iconGameGlider = new ImageIcon("src/ImgSource/gameGlider.png");
        ImageIcon iconSpyGlider = new ImageIcon("src/ImgSource/spyGlider.png");
        ImageIcon iconBicycle = new ImageIcon("src/ImgSource/bicycle1.png");
        ImageIcon iconCruise = new ImageIcon("src/ImgSource/cruise.png");
        ImageIcon iconAmphibious = new ImageIcon("src/ImgSource/amphibious.png");
        ImageIcon iconElectricBicycle = new ImageIcon("src/ImgSource/electricBike.png");
        ImageIcon iconHybridPlane = new ImageIcon("src/ImgSource/hybridPlane.png");

        //create panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        switch (vehicleType) {
            case "Jeep" -> {
                //add text fields for model
                panel.add(new JLabel(iconJeep));
                panel.add(panelModel());
                //combobox for max speed
                panel.add(panelMaxSpeed(1000));
                //combobox for fuel consumption
                panel.add(panelMaxFuel(100));
                //combobox for lifetime
                panel.add(panelLifeTime(100));
            }
            case "Frigate" -> {
                panel.add(new JLabel(iconFrigate));
                //add text fields for model
                panel.add(panelModel());
                //combobox MaxSpeed
                panel.add(panelMaxSpeed(1000));
                //combo box for Maximum passengers
                panel.add(panelMaxPassenger(50000));
                panel.add(panelWithDirection());
            }
            case "GameGlider" -> {
                panel.add(new JLabel(iconGameGlider));
                //no button needed for him
            }
            case "SpyGlider" -> {
                panel.add(new JLabel(iconSpyGlider));
                panel.add(panelSourceOfEnergy());
            }
            case "Bicycle" -> {
                panel.add(new JLabel(iconBicycle));
                panel.add(panelModel());
                panel.add(panelMaxSpeed(30));
                panel.add(panelMaxPassenger(3));
                panel.add(panelKindOfLand());
            }
            case "CruiseShip" -> {
                panel.add(new JLabel(iconCruise));
                //add text fields for model
                panel.add(panelModel());
                //combobox MaxPassenger
                panel.add((panelMaxPassenger(1000)));
                //combobox MaxSpeed
                panel.add(panelMaxSpeed(999999));
                //combobox Flag
                panel.add(panelFlag());

            }
            case "Amphibious", "HybridPlane" -> {
                if (vehicleType.equals("Amphibious")) {
                    panel.add(new JLabel(iconAmphibious));
                } else {
                    panel.add(new JLabel(iconHybridPlane));
                }
                //add text fields for model
                panel.add(panelModel());
                //combobox MaxSpeed
                panel.add(panelMaxSpeed(1000));
                //combobox number of wheels
                panel.add(panelNumberOfWheels());
                //combobox MaxFuel
                panel.add(panelMaxFuel(100));
                //combobox lifetime
                panel.add(panelLifeTime(100));
                //combobox max number of passenger
                panel.add(panelMaxPassenger(1000));
                //combobox with direction
                panel.add(panelWithDirection());
                panel.add(panelFlag());

            }
            case "ElectricBicycle" -> {
                panel.add(new JLabel(iconElectricBicycle));
                //add text fields for model
                panel.add(panelModel());
                //combobox MaxSpeed
                panel.add(panelMaxSpeed(1000));
                panel.add(panelMaxPassenger(3));
                panel.add(panelKindOfLand());
                //combobox lifetime
                panel.add(panelLifeTime(100));
            }
        }
        confirmButton = new JButton("Create Vehicle");
        panel.add(confirmButton, BorderLayout.SOUTH);
        confirmButton.addActionListener(this);
    }

    public JPanel panelModel(){
        JPanel panelModel = new JPanel();
        modelNameField = new JTextField("Enter Model Name", 20);
        panelModel.add(modelNameField);
        modelNameField.addActionListener(this);
        return panelModel;
    }

    public JPanel panelMaxSpeed(int limit){
        JPanel panelMaxSpeed = new JPanel();
        JLabel maxSpeedLabel = new JLabel("Enter Max Speed");
        panelMaxSpeed.add(maxSpeedLabel);
        comboBoxSpeed = new JComboBox<Integer>();
        for (int i = 5; i <= limit; i += 5) {
            comboBoxSpeed.addItem(i-1);
        }
        panelMaxSpeed.add(comboBoxSpeed);
        return panelMaxSpeed;
    }

    public JPanel panelMaxFuel(int limit){
        JPanel panelMaxFuel = new JPanel();
        JLabel maxFuelLabel = new JLabel("Enter Max Fuel");
        panelMaxFuel.add(maxFuelLabel);
        comboBoxFuel = new JComboBox<Integer>();
        for (int i = 1; i <= limit; i++) {
            comboBoxFuel.addItem(i);
        }
        panelMaxFuel.add(comboBoxFuel);
        return panelMaxFuel;
    }

    public JPanel panelLifeTime(int limit){
        JPanel panelLifeTime = new JPanel();
        JLabel lifeTimeLabel = new JLabel("Enter Life Time");
        panelLifeTime.add(lifeTimeLabel);
        comboBoxLifeTime = new JComboBox<Integer>();
        for (int i = 1; i <= limit; i++) {
            comboBoxLifeTime.addItem(i);
        }
        panelLifeTime.add(comboBoxLifeTime);
        return panelLifeTime;
    }

    public JPanel panelMaxPassenger(int limit){
        JPanel panelMaxPassenger = new JPanel();
        JLabel MaxPassengerLabel = new JLabel("Enter Max passenger");
        panelMaxPassenger.add(MaxPassengerLabel);
        comboBoxPassengers = new JComboBox<Integer>();
        for (int i = 1; i <= limit; i++) {
            comboBoxPassengers.addItem(i);
        }
        panelMaxPassenger.add(comboBoxPassengers);
        return panelMaxPassenger;
    }

    public JPanel panelSourceOfEnergy(){
        JPanel panelSourceOfEnergy = new JPanel();
        sourceOfEnergyField = new JTextField("Enter Source of Energy", 25);
        panelSourceOfEnergy.add(sourceOfEnergyField);
        sourceOfEnergyField.addActionListener(this);
        return panelSourceOfEnergy;
    }

    public JPanel panelKindOfLand(){
        JPanel panelKindOfLand = new JPanel();
        JLabel labelKindOfLand = new JLabel("Choose Kind of Land");
        panelKindOfLand.add(labelKindOfLand);
        comboBoxKindOfLand = new JComboBox<String>();
        comboBoxKindOfLand.addItem("Paved");
        comboBoxKindOfLand.addItem("Dirt");
        panelKindOfLand.add(comboBoxKindOfLand);
        return panelKindOfLand;
    }

    public JPanel panelFlag(){
        JPanel PanelFlag = new JPanel();
        JLabel labelFlag = new JLabel("Choose Flag");
        PanelFlag.add(labelFlag);
        comboBoxFlag = new JComboBox<String>();
        comboBoxFlag.addItem("Israel");
        comboBoxFlag.addItem("United States");
        comboBoxFlag.addItem("Greece");
        comboBoxFlag.addItem("Germany");
        comboBoxFlag.addItem("Somalia");
        comboBoxFlag.addItem("Pirate");
        PanelFlag.add(comboBoxFlag);
        return PanelFlag;
    }

    public JPanel panelNumberOfWheels(){
        JPanel panelNumberOfWheels = new JPanel();
        JLabel labelNumberOfWheels = new JLabel("Choose Number Of Wheels");
        panelNumberOfWheels.add(labelNumberOfWheels);
        comboBoxWheels = new JComboBox<Integer>();
        for (int i = 1; i <= 20; i++) {
            comboBoxWheels.addItem(i);
        }
        panelNumberOfWheels.add(comboBoxWheels);
        return panelNumberOfWheels;
    }
    public JPanel panelWithDirection(){
        JPanel panelWithDirection = new JPanel();
        withWindButton = new JRadioButton("With the wind direction", true);
        JRadioButton againstWindButton = new JRadioButton("Against the wind direction");
        ButtonGroup group = new ButtonGroup();
        group.add(withWindButton);
        group.add(againstWindButton);
        panelWithDirection.add(withWindButton);
        panelWithDirection.add(againstWindButton);
        return panelWithDirection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
//        if (source == radio1){
//            imageLabel.setIcon(image1);
//            path = "images/" + vehicleType + "Images/" + vehicleType + "1.jpg";
//        }
//        if (source == radio2){
//            imageLabel.setIcon(image2);
//            path = "images/" + vehicleType + "Images/" + vehicleType + "2.jpg";
//        }
//        if (source == radio3){
//            imageLabel.setIcon(image3);
//            path = "images/" + vehicleType + "Images/" + vehicleType + "3.jpg";
//        }
//        if (source == validateButton){
//            modelName = modelNameField.getText();
//        }
//        if (source == comboBoxSpeed){
//            int selectValue = (Integer) comboBoxSpeed.getSelectedItem();
//        }
        if(source == confirmButton){
            switch (vehicleType) {
                case "Jeep": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/jeep3.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update(new Jeep(modelNameField.getText(), (Integer) comboBoxSpeed.getSelectedItem(), (Integer) comboBoxFuel.getSelectedItem(), (Integer) comboBoxLifeTime.getSelectedItem(), img));
                    break;
                }
                case "Frigate": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/frigate.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update(new Frigate(modelNameField.getText(), (Integer) comboBoxPassengers.getSelectedItem(),(Integer) comboBoxSpeed.getSelectedItem(),withWindButton.isSelected(), img));
                    break;
                }
                case "GameGlider": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/gameGlider.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update((new GameGlider(img)));
                    break;
                }
                case "SpyGlider": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/spyGlider.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update(new SpyGlider(sourceOfEnergyField.getText(), img));
                    break;
                }
                case "Bicycle": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/bicycle1.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update(new Bicycle(modelNameField.getText(), (Integer) comboBoxPassengers.getSelectedItem(),(Integer) comboBoxSpeed.getSelectedItem(),  (String)comboBoxKindOfLand.getSelectedItem() ,img));
                    break;
                }
                case "CruiseShip": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/cruise.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update(new CruiseShip(modelNameField.getText(), (Integer) comboBoxPassengers.getSelectedItem(),(Integer) comboBoxSpeed.getSelectedItem(), (String) comboBoxFlag.getSelectedItem() , img));
                    break;
                }
                case "Amphibious", "HybridPlane": {
                    if (vehicleType.equals("Amphibious")) {
                        ImageIcon img = getImageFromUser();
                        if (img == null) {
                            img = new ImageIcon("src/ImgSource/amphibious.png");
                            img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                        }
                        update(new Amphibious(modelNameField.getText(), (Integer) comboBoxPassengers.getSelectedItem(),
                                (Integer) comboBoxSpeed.getSelectedItem(), (Integer) comboBoxWheels.getSelectedItem(), withWindButton.isSelected(), (String) comboBoxFlag.getSelectedItem(), (Integer) comboBoxFuel.getSelectedItem(),
                                (Integer) comboBoxLifeTime.getSelectedItem(), img));
                    } else {
                        ImageIcon img = getImageFromUser();
                        if (img == null) {
                            img = new ImageIcon("src/ImgSource/hybridPlane.png");
                            img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                        }
                        update(new HybridPlane(modelNameField.getText(), (Integer) comboBoxPassengers.getSelectedItem(),(Integer) comboBoxSpeed.getSelectedItem(), (Integer) comboBoxWheels.getSelectedItem(), withWindButton.isSelected(), (String) comboBoxFlag.getSelectedItem(),(Integer) comboBoxFuel.getSelectedItem(), (Integer) comboBoxLifeTime.getSelectedItem(),  img));
                    }
                    break;
                }
                case "ElectricBicycle": {
                    ImageIcon img = getImageFromUser();
                    if (img == null) {
                        img = new ImageIcon("src/ImgSource/electricBike.png");
                        img = new ImageIcon(img.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
                    }
                    update(new ElectricBicycle(modelNameField.getText(),(Integer) comboBoxPassengers.getSelectedItem(), (Integer) comboBoxSpeed.getSelectedItem(), (String)comboBoxKindOfLand.getSelectedItem(), (Integer) comboBoxLifeTime.getSelectedItem(), img));
                    break;
                }

            }

            // essaie du bouton confirm
            this.dispose();
            MainFrame frameCars = new MainFrame();
        }
    }

    public void update(Vehicle vehicle) {
        Thread t = new Thread(() -> {
            try {
                synchronized (MainFrame.vehicleList) {
                    Random rand = new Random();
                    int randomNum;
                    randomNum = 3000 + rand.nextInt((8000 - 3000) + 1);
                    LoadingDBFrame loadingDBFrame = new LoadingDBFrame("Updating Database...");
                    MainFrame.vehicleList.add(vehicle);
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

    private ImageIcon getImageFromUser() {
        ImageIcon imageIcon = null;
        JFileChooser fileChooser = new JFileChooser();
        String[] button = {"Set by default", "Upload an image"};
        int option = JOptionPane.showOptionDialog(this, "Select Your option please",
                "Set the image for the vehicle", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                null,button, button[0]);
        if (option == 1 ) {
            int result = fileChooser.showOpenDialog(this);
            do {
                if (result == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this,
                            "You must select an image file", "Select Image", JOptionPane.ERROR_MESSAGE);
                    result = fileChooser.showOpenDialog(this);
                }
            } while (result == JFileChooser.CANCEL_OPTION);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    imageIcon = new ImageIcon(Files.readAllBytes(selectedFile.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imageIcon != null) {
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT));
            }
        }
        if(imageIcon == null) JOptionPane.showMessageDialog(this, "The image will be setting by default");
        return imageIcon;
    }
}




