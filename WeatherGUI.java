import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class WeatherGUI extends JFrame {

    private JTextField cityField;
    private JButton submitButton;

    private JLabel cityValue;
    private JLabel tempValue;
    private JLabel humidityValue;
    private JLabel windValue;
    private JLabel conditionValue;

    private WeatherService service = new WeatherService();

    public WeatherGUI() {

        setTitle("Real-Time Weather Forecasting System");
        setSize(700, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel main = new JPanel();
        main.setBackground(new Color(240,248,255));
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // ================= HEADER =================

        JLabel icon = new JLabel("⛅");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 90));
        icon.setForeground(new Color(255,193,7));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("REAL-TIME WEATHER FORECASTING");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(25,118,210));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Get Real-Time Weather Information");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(Box.createVerticalStrut(20));
        main.add(icon);
        main.add(Box.createVerticalStrut(10));
        main.add(title);
        main.add(Box.createVerticalStrut(8));
        main.add(subtitle);
        main.add(Box.createVerticalStrut(30));

        // ================= SEARCH PANEL =================

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setMaximumSize(new Dimension(560,180));
        searchPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(220,220,220),2,true),
                new EmptyBorder(20,25,20,25)));

        searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.Y_AXIS));

        JLabel cityLabel = new JLabel("📍 ENTER CITY NAME");
        cityLabel.setFont(new Font("Arial", Font.BOLD,20));
        cityLabel.setForeground(new Color(33,150,243));
        cityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cityField = new JTextField();
        cityField.setMaximumSize(new Dimension(420,45));
        cityField.setPreferredSize(new Dimension(420,45));
        cityField.setHorizontalAlignment(JTextField.CENTER);
        cityField.setFont(new Font("Arial", Font.BOLD,18));

        submitButton = new JButton("SUBMIT");
        submitButton.setBackground(new Color(46,204,113));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Arial", Font.BOLD,20));
        submitButton.setPreferredSize(new Dimension(180,45));
        submitButton.setMaximumSize(new Dimension(180,45));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchPanel.add(cityLabel);
        searchPanel.add(Box.createVerticalStrut(15));
        searchPanel.add(cityField);
        searchPanel.add(Box.createVerticalStrut(20));
        searchPanel.add(submitButton);

        main.add(searchPanel);
        main.add(Box.createVerticalStrut(30));

        // ================= RESULT PANEL =================

        JPanel result = new JPanel();
        result.setBackground(Color.WHITE);
        result.setMaximumSize(new Dimension(560,330));
        result.setBorder(new CompoundBorder(
                new LineBorder(new Color(220,220,220),2,true),
                new EmptyBorder(25,25,25,25)));

        result.setLayout(new GridLayout(5,2,15,20));

        Font labelFont = new Font("Segoe UI", Font.BOLD,20);
        Font valueFont = new Font("Segoe UI", Font.BOLD,19);

        result.add(createLabel("📍 City :",labelFont));
        cityValue = new JLabel("-");
        cityValue.setFont(valueFont);
        result.add(cityValue);

        result.add(createLabel("🌡 Temperature :",labelFont));
        tempValue = new JLabel("-");
        tempValue.setFont(valueFont);
        result.add(tempValue);

        result.add(createLabel("💧 Humidity :",labelFont));
        humidityValue = new JLabel("-");
        humidityValue.setFont(valueFont);
        result.add(humidityValue);

        result.add(createLabel("💨 Wind Speed :",labelFont));
        windValue = new JLabel("-");
        windValue.setFont(valueFont);
        result.add(windValue);

        result.add(createLabel("⛅ Weather :",labelFont));
        conditionValue = new JLabel("-");
        conditionValue.setFont(valueFont);
        result.add(conditionValue);

        main.add(result);
        main.add(Box.createVerticalStrut(25));
                // ================= BUTTON ACTION =================

        submitButton.addActionListener(e -> {

            String city = cityField.getText().trim();

            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a city name.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            WeatherData data = service.getWeather(city);

            if (data == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Unable to fetch weather information.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            cityValue.setText(data.getCity());
            tempValue.setText(String.format("%.1f °C", data.getTemperature()));
            humidityValue.setText(data.getHumidity() + " %");
            windValue.setText(data.getWindSpeed() + " m/s");
            conditionValue.setText(data.getCondition());

        });

        // ================= FOOTER =================

        JLabel footer = new JLabel("Weather API");
        footer.setFont(new Font("Arial", Font.ITALIC, 14));
        footer.setForeground(Color.GRAY);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(footer);
        main.add(Box.createVerticalStrut(20));

        add(main);
        setVisible(true);

    }

    // ================= HELPER METHOD =================

    private JLabel createLabel(String text, Font font) {

        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(new Color(33, 33, 33));

        return label;
    }

}