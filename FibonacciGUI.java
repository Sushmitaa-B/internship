import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 *🧮SS AI Lab's Mathematical Assistant
 * Combined Java Swing application for arithmetic operations and Fibonacci curve visualization
 */
public class FibonacciGUI extends JFrame {

    // === Global Styling ===
    private static final Font APP_FONT_PLAIN = new Font("Poppins", Font.PLAIN, 16);
    private static final Font APP_FONT_BOLD = new Font("Poppins", Font.BOLD, 18);
    private static final Font APP_FONT_TITLE = new Font("Poppins", Font.BOLD, 26);

    // === Pastel Color Palette ===
    private static final Color BACKGROUND_MAIN = new Color(245, 243, 255); // soft lavender
    private static final Color PANEL_BACKGROUND = new Color(255, 255, 255); // white
    private static final Color TITLE_COLOR = new Color(180, 140, 255); // pastel violet
    private static final Color BUTTON_COLOR = new Color(140, 180, 255); // pastel blue
    private static final Color TEXT_COLOR = new Color(60, 60, 70); // soft grayish text
    private static final Color SUCCESS_COLOR = new Color(100, 170, 140); // pastel green

    private JComboBox<String> operationCombo;
    private JTextField num1Field, num2Field, nField;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JPanel inputPanel, displayPanel;
    private FibonacciPlotPanel plotPanel;

    public FibonacciGUI() {
        setTitle("🧮 SS AI Lab's Mathematical Assistant");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BACKGROUND_MAIN);

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(TITLE_COLOR);
        JLabel titleLabel = new JLabel("🧮 SS AI Lab's Mathematical Assistant");
        titleLabel.setFont(APP_FONT_TITLE);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_MAIN);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Control Panel (Left)
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(PANEL_BACKGROUND);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlPanel.setPreferredSize(new Dimension(350, 600));

        JLabel opLabel = new JLabel("Choose the operation:");
        opLabel.setFont(APP_FONT_BOLD);
        opLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(opLabel);
        controlPanel.add(Box.createVerticalStrut(10));

        String[] operations = {"Addition", "Subtraction", "Multiplication", "Division", "Fibonacci Curve"};
        operationCombo = new JComboBox<>(operations);
        operationCombo.setMaximumSize(new Dimension(300, 30));
        operationCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        operationCombo.setFont(APP_FONT_PLAIN);
        operationCombo.addActionListener(e -> updateInputFields());
        controlPanel.add(operationCombo);
        controlPanel.add(Box.createVerticalStrut(20));

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(PANEL_BACKGROUND);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(inputPanel);

        controlPanel.add(Box.createVerticalStrut(20));
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(APP_FONT_BOLD);
        calculateButton.setBackground(BUTTON_COLOR);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setMaximumSize(new Dimension(300, 40));
        calculateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        calculateButton.addActionListener(e -> performCalculation());
        controlPanel.add(calculateButton);

        controlPanel.add(Box.createVerticalStrut(20));
        resultLabel = new JLabel("");
        resultLabel.setFont(APP_FONT_BOLD);
        resultLabel.setForeground(SUCCESS_COLOR);
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(resultLabel);

        mainPanel.add(controlPanel, BorderLayout.WEST);

        // Display Panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBackground(PANEL_BACKGROUND);
        displayPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        plotPanel = new FibonacciPlotPanel();
        displayPanel.add(plotPanel, BorderLayout.CENTER);
        mainPanel.add(displayPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        updateInputFields();
    }

    private void updateInputFields() {
        inputPanel.removeAll();
        String operation = (String) operationCombo.getSelectedItem();

        if (operation.equals("Fibonacci Curve")) {
            JLabel nLabel = new JLabel("Enter the number of terms (N):");
            nLabel.setFont(APP_FONT_PLAIN);
            nLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputPanel.add(nLabel);
            inputPanel.add(Box.createVerticalStrut(5));

            nField = new JTextField("10");
            nField.setMaximumSize(new Dimension(300, 30));
            nField.setFont(APP_FONT_PLAIN);
            nField.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputPanel.add(nField);
        } else {
            JLabel num1Label = new JLabel("Enter the Value of A:");
            num1Label.setFont(APP_FONT_PLAIN);
            num1Label.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputPanel.add(num1Label);
            inputPanel.add(Box.createVerticalStrut(5));

            num1Field = new JTextField("0.0");
            num1Field.setMaximumSize(new Dimension(300, 30));
            num1Field.setFont(APP_FONT_PLAIN);
            num1Field.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputPanel.add(num1Field);
            inputPanel.add(Box.createVerticalStrut(10));

            JLabel num2Label = new JLabel("Enter the Value of B:");
            num2Label.setFont(APP_FONT_PLAIN);
            num2Label.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputPanel.add(num2Label);
            inputPanel.add(Box.createVerticalStrut(5));

            num2Field = new JTextField("0.0");
            num2Field.setMaximumSize(new Dimension(300, 30));
            num2Field.setFont(APP_FONT_PLAIN);
            num2Field.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputPanel.add(num2Field);
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void performCalculation() {
        String operation = (String) operationCombo.getSelectedItem();

        try {
            if (operation.equals("Fibonacci Curve")) {
                int N = Integer.parseInt(nField.getText());
                if (N < 1 || N > 100) {
                    JOptionPane.showMessageDialog(this, "N must be between 1 and 100",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<FibonacciCalculator.CurveSegment> segments =
                        FibonacciCalculator.calculateIndividualCurves(N);

                double totalLength = 0;
                for (FibonacciCalculator.CurveSegment segment : segments) {
                    totalLength += Math.PI * (segment.radius / 2.0);
                }

                plotPanel.plotIndividualCurves(segments);
                resultLabel.setText(String.format("Fibonacci Curve Length: %.2f units", totalLength));

            } else {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                String result = "";

                switch (operation) {
                    case "Addition":
                        result = String.format("Result: %.2f", FibonacciCalculator.add(num1, num2));
                        break;
                    case "Subtraction":
                        result = String.format("Result: %.2f", FibonacciCalculator.subtract(num1, num2));
                        break;
                    case "Multiplication":
                        result = String.format("Result: %.2f", FibonacciCalculator.multiply(num1, num2));
                        break;
                    case "Division":
                        result = "Result: " + FibonacciCalculator.divide(num1, num2);
                        break;
                }

                resultLabel.setText(result);
                plotPanel.clear();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Plot Panel ---
    class FibonacciPlotPanel extends JPanel {
        private List<FibonacciCalculator.CurveSegment> segments;
        private boolean showPlot = false;

        public FibonacciPlotPanel() {
            setBackground(PANEL_BACKGROUND);
            setPreferredSize(new Dimension(600, 600));
        }

        public void plotIndividualCurves(List<FibonacciCalculator.CurveSegment> segments) {
            this.segments = segments;
            this.showPlot = true;
            repaint();
        }

        public void clear() {
            this.showPlot = false;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (!showPlot || segments == null || segments.isEmpty()) {
                g.setColor(TEXT_COLOR);
                g.setFont(APP_FONT_PLAIN);
                String msg = "Fibonacci Curve will appear here";
                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(msg)) / 2;
                int y = getHeight() / 2;
                g.drawString(msg, x, y);
                return;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE;
            double minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;

            for (FibonacciCalculator.CurveSegment segment : segments) {
                for (double x : segment.X) {
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                }
                for (double y : segment.Y) {
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }

            double rangeX = maxX - minX;
            double rangeY = maxY - minY;
            double padding = 0.1;
            minX -= rangeX * padding;
            maxX += rangeX * padding;
            minY -= rangeY * padding;
            maxY += rangeY * padding;

            int plotWidth = getWidth() - 60;
            int plotHeight = getHeight() - 60;
            double scaleX = plotWidth / (maxX - minX);
            double scaleY = plotHeight / (maxY - minY);
            double scale = Math.min(scaleX, scaleY);
            int offsetX = 30;
            int offsetY = 30;

            // Grid
            g2d.setColor(new Color(230, 230, 240));
            for (int i = 0; i <= 10; i++) {
                int x = offsetX + (int) (i * plotWidth / 10.0);
                int y = offsetY + (int) (i * plotHeight / 10.0);
                g2d.drawLine(x, offsetY, x, offsetY + plotHeight);
                g2d.drawLine(offsetX, y, offsetX + plotWidth, y);
            }

            // Axes
            g2d.setColor(TEXT_COLOR);
            g2d.setStroke(new BasicStroke(2));
            int zeroX = offsetX + (int) ((-minX) * scale);
            int zeroY = offsetY + plotHeight - (int) ((-minY) * scale);
            g2d.drawLine(offsetX, zeroY, offsetX + plotWidth, zeroY);
            g2d.drawLine(zeroX, offsetY, zeroX, offsetY + plotHeight);

            // Draw curves
            Color[] colors = {
                new Color(170, 200, 255),
                new Color(255, 190, 200),
                new Color(190, 255, 210),
                new Color(255, 230, 170),
                new Color(210, 190, 255)
            };
            g2d.setStroke(new BasicStroke(2.5f));

            for (int idx = 0; idx < segments.size(); idx++) {
                FibonacciCalculator.CurveSegment segment = segments.get(idx);
                g2d.setColor(colors[idx % colors.length]);
                for (int i = 0; i < segment.X.size() - 1; i++) {
                    int x1 = offsetX + (int) ((segment.X.get(i) - minX) * scale);
                    int y1 = offsetY + plotHeight - (int) ((segment.Y.get(i) - minY) * scale);
                    int x2 = offsetX + (int) ((segment.X.get(i + 1) - minX) * scale);
                    int y2 = offsetY + plotHeight - (int) ((segment.Y.get(i + 1) - minY) * scale);
                    g2d.drawLine(x1, y1, x2, y2);
                }
            }

            // Title
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(APP_FONT_BOLD);
            String title = "Fibonacci Curve (Individual Quadrants)";
            FontMetrics fm = g2d.getFontMetrics();
            int titleX = (getWidth() - fm.stringWidth(title)) / 2;
            g2d.drawString(title, titleX, 20);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.put("Label.font", APP_FONT_PLAIN);
                UIManager.put("Button.font", APP_FONT_BOLD);
                UIManager.put("ComboBox.font", APP_FONT_PLAIN);
                UIManager.put("TextField.font", APP_FONT_PLAIN);
                UIManager.put("TitledBorder.font", APP_FONT_BOLD);
                UIManager.put("Panel.background", PANEL_BACKGROUND);
            } catch (Exception ignored) {}
            new FibonacciGUI();
        });
    }
}

// ======================================================================
// Helper logic class (non-public)
// ======================================================================
class FibonacciCalculator {
    public static class CurveSegment {
        public List<Double> X;
        public List<Double> Y;
        public double radius;
        public int index;

        public CurveSegment(List<Double> X, List<Double> Y, double radius, int index) {
            this.X = X;
            this.Y = Y;
            this.radius = radius;
            this.index = index;
        }
    }

    public static List<CurveSegment> calculateIndividualCurves(int N) {
        List<CurveSegment> segments = new ArrayList<>();
        List<Integer> F = new ArrayList<>(Arrays.asList(0, 1));

        for (int i = 0; i < N; i++) F.add(F.get(F.size() - 1) + F.get(F.size() - 2));

        List<double[]> I = new ArrayList<>(Arrays.asList(
            new double[]{0, 0},
            new double[]{0, 0},
            new double[]{0, 0}
        ));

        for (int j = 3; j < N; j++) {
            int pivot = (j - 2) % 4;
            boolean X_flag = (pivot == 1 || pivot == 3);
            boolean Sign_Flag = (pivot == 1 || pivot == 2);

            double x_temp = I.get(j - 1)[0];
            double y_temp = I.get(j - 1)[1];
            if (Sign_Flag && X_flag) x_temp += F.get(j - 2);
            else if (Sign_Flag) y_temp += F.get(j - 2);
            else if (X_flag) x_temp -= F.get(j - 2);
            else y_temp -= F.get(j - 2);
            I.add(new double[]{x_temp, y_temp});
        }

        F.remove(0);
        I.remove(0);

        int[][] Angle = {{1, 91}, {91, 181}, {181, 271}, {271, 361}};
        for (int i = 0; i < I.size(); i++) {
            List<Double> segmentX = new ArrayList<>();
            List<Double> segmentY = new ArrayList<>();
            int[] AnR = Angle[i % 4];
            double r = F.get(i);
            double x_align = I.get(i)[0];
            double y_align = I.get(i)[1];
            for (int j = AnR[0]; j < AnR[1]; j++) {
                double x_temp = r * Math.cos(Math.toRadians(j));
                double y_temp = r * Math.sin(Math.toRadians(j));
                segmentX.add(x_temp + x_align);
                segmentY.add(y_temp + y_align);
            }
            segments.add(new CurveSegment(segmentX, segmentY, r, i));
        }
        return segments;
    }

    // Basic arithmetic operations
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static String divide(double a, double b) {
        return (b == 0) ? "Cannot divide by zero" : String.format("%.2f", a / b);
    }
}
