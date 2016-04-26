import javax.swing.*;
import java.awt.*;


class Calculator extends JFrame {
    private JLabel text;
    private final Parser parser = new Parser();

    Calculator() {
        setName("Calculator_SortStation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 200, 400, 200);
        setLayout(new BorderLayout());

        text = new JLabel("0");
        text.setFont(new Font(text.getFont().getName(), Font.BOLD, 16));
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JPanel numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(4, 3, 4, 4));

        String[] numbersButtons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "."};

        for (String numbersButton : numbersButtons) {
            new MyButton(numbersButton, text, numbersPanel);
        }

        MyButton clear = new MyButton("CE", text, numbersPanel);
        clear.addActionListener(e -> text.setText("0"));

        JPanel operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(2, 1, 4, 4));

        String[] operationButtons = {"+", "-", "/", "*"};
        for (String operationButton : operationButtons) {
            new MyButton(operationButton, text, operationsPanel);
        }

        MyButton resultButton = new MyButton("=", text, operationsPanel);
        resultButton.addActionListener(e -> {
            if(text.getText().contains("/0")){text.setText("Деление на ноль!");return;}
            String result = String.valueOf(parser.parse(text.getText()));
            if (result.endsWith(".0")) {
                text.setText("=" + result.substring(0, result.length() - 2));
            } else {
                text.setText("=" + result);
            }
        });

        MyButton clearButton = new MyButton("C", text, operationsPanel);
        clearButton.addActionListener(e -> {
              if (text.getText().length() > 1) {
                text.setText(text.getText().substring(0, text.getText().length() - 1));
            } else {
                text.setText("0");
            }
        });

        add(text, BorderLayout.NORTH);
        add(numbersPanel, BorderLayout.CENTER);
        add(operationsPanel, BorderLayout.EAST);
        setVisible(true);
    }

}