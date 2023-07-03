package conversor_de_moedas_java;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemperatureConverterMainFrame extends JFrame {
    private static TemperatureConverterMainFrame instance;

    public TemperatureConverterMainFrame() {
        String temperatures[] = {
                "Celsius", "Kelvin", "Fahrenheit"
        };

        JTextField input = new JTextField();
        JComboBox<String> menu1 = new JComboBox<>(temperatures);
        JComboBox<String> menu2 = new JComboBox<>(temperatures);

        JButton buttonOK = new JButton("CONVERTER");
        JButton buttonCancel = new JButton("CANCELAR");
        JLabel resultLabel = new JLabel("");

        buttonOK.addActionListener(e -> {
            double value = 0.0;
            try {
                value = parseDouble(input.getText());
            } catch (Exception ex) {
                return;
            }

            String optionMenu1 = menu1.getSelectedItem().toString();
            String optionMenu2 = menu2.getSelectedItem().toString();
            value = parseDouble(input.getText());

            if (optionMenu1 != optionMenu2) {
                try {
                    TemperatureConverter temperatureConverter = new TemperatureConverter();
                    double convertedTemperature = temperatureConverter.convert(optionMenu1, optionMenu2, value);
                    resultLabel.setText(optionMenu2 + ": " + convertedTemperature);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                resultLabel.setText(optionMenu2 + ": " + value);
            }
        });

        buttonCancel.addActionListener(e -> {
            dispose();
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.add(new JLabel("Valor:"));
        contentPanel.add(input);
        contentPanel.add(new JLabel("DE:"));
        contentPanel.add(menu1);
        contentPanel.add(new JLabel("PARA:"));
        contentPanel.add(menu2);
        contentPanel.add(buttonOK);
        contentPanel.add(buttonCancel);
        contentPanel.add(new JLabel("Resultado:"));
        contentPanel.add(resultLabel);

        this.setTitle("Conversor de Temperatura");
        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(contentPanel);

        // Centralizar a janela na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        int xPos = (screenWidth - windowWidth) / 2;
        int yPos = (screenHeight - windowHeight) / 2;
        this.setLocation(xPos, yPos);
    }

    public static TemperatureConverterMainFrame getInstance() {
        if (instance == null) {
            instance = new TemperatureConverterMainFrame();
        }
        return instance;
    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 10, 10, 10);
    }

    private double parseDouble(String s) {
        double value = Double.parseDouble(s.replace(",", "."));
        return Double.parseDouble(String.format("%.2f", value).replace(",", "."));
    }
}
