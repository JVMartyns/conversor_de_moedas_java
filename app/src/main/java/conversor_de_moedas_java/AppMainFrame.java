package conversor_de_moedas_java;

import java.awt.*;
import javax.swing.*;

public class AppMainFrame extends JFrame {

    public AppMainFrame() {
        JButton currencyConverterButton = new JButton("Conversor de Moedas");
        JButton temperatureConverterButton = new JButton("Converter Temperatura");
        JButton exitButton = new JButton("Sair");

        currencyConverterButton.addActionListener(e -> {
            CurrencyConverterMainFrame app = CurrencyConverterMainFrame.getInstance();
            app.setVisible(true);
        });

        temperatureConverterButton.addActionListener(e -> {
            TemperatureConverterMainFrame app = TemperatureConverterMainFrame.getInstance();
            app.setVisible(true);
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.add(currencyConverterButton);
        contentPanel.add(temperatureConverterButton);
        contentPanel.add(exitButton);

        this.setTitle("Menu");
        this.setSize(300, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    @Override
    public Insets getInsets() {
        return new Insets(10, 10, 10, 10);
    }
}
