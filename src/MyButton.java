import javax.swing.*;
import java.awt.*;


 class MyButton extends JButton {

     MyButton (String title, JLabel text, JPanel panel){
        super(title);
        setFont(new Font("Arial", Font.BOLD, 16));
        if(!title.equals("CE")&&!title.equals("C")&&!title.equals("=")){addActionListener(e -> {
{
             if (text.getText().equals("0")||text.getText().contains("=")||text.getText().equals("Деление на ноль!")) {
                 text.setText(getText());
             } else {
                 text.setText(text.getText() + getText());
             }}

         });}
panel.add(this);
}}