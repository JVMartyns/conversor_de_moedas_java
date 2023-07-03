package conversor_de_moedas_java;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class CurrencyConverterMainFrame extends JFrame {
    private static CurrencyConverterMainFrame instance;

    public CurrencyConverterMainFrame() {
        Map<String, String> currencies = new HashMap<>();
        currencies.put("Real", "BRL");
        currencies.put("Dollar", "USD");
        currencies.put("Euro", "EUR");
        currencies.put("Libras Esterlinas", "GBP");
        currencies.put("Peso Argentino", "ARS");
        currencies.put("Peso Chileno", "CLP");

        JTextField input = new JTextField();
        JComboBox<String> menu1 = new JComboBox<>(currencies.keySet().toArray(new String[0]));
        JComboBox<String> menu2 = new JComboBox<>(currencies.keySet().toArray(new String[0]));
        JButton buttonOK = new JButton("CONVERTER");
        JButton buttonCancel = new JButton("CANCELAR");
        JLabel resultLabel = new JLabel("");

        buttonOK.addActionListener(e -> {
            Double value = 0.0;
            try {
                value = parseDouble(input.getText());
            } catch (Exception ex) {
                return;
            }

            String optionMenu1 = menu1.getSelectedItem().toString();
            String optionMenu2 = menu2.getSelectedItem().toString();
            value = parseDouble(input.getText());
            String from = currencies.get(optionMenu1);
            String to = currencies.get(optionMenu2);
            if (optionMenu1 != optionMenu2) {
                try {
                    Double quotation = QuotationApi.getQuotation(from, to);
                    resultLabel.setText(to + ": " + String.format("%.2f", quotation * value));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                resultLabel.setText(to + ": " + String.format("%.2f", value));
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

        this.setTitle("Conversor de Moedas");
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

    public static CurrencyConverterMainFrame getInstance() {
        if (instance == null) {
            instance = new CurrencyConverterMainFrame();
        }
        return instance;
    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 10, 10, 10);
    }

    private Double parseDouble(String s) {
        double value = Double.parseDouble(s.replace(",", "."));
        return Double.parseDouble(String.format("%.2f", value).replace(",", "."));
    }
}
