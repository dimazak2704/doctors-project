import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class test extends JFrame {

    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = screenSize.width;
    static int height = screenSize.height;
    static boolean but = false;

    public static String [] arr = {"09.00", "09.30", "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30", "17.00", "17.30"};
    public static String [] v = {"09.00", "14.30"};
    public static String[] removeElements(String[] arr, String[] values) {
        ArrayList<String> result = new ArrayList<>();

        for (String s : arr) {
            boolean shouldRemove = false;

            for (String value : values) {
                if (s.equals(value)) {
                    shouldRemove = true;
                    break;
                }
            }

            if (!shouldRemove) {
                result.add(s);
            }
        }

        return result.toArray(new String[0]);
    }



    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("doctor's appointment");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setBounds(width/2-600,height/2-360,1200,720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JLabel label = new JLabel("Фамілія Імя По батькові");
        label.setBounds(350,20,500,40);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));


        JLabel label1 = new JLabel("Виберіть час запису");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label1.setBounds(450,100,300,40);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);


        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(500,200,200,40);
        comboBox.setFont(new Font("Times New Roman", Font.BOLD, 30));

        JButton button = new JButton("Підтвердити");
        button.setBounds(400,500,400,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Завдання виконано успішно!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(label);frame.add(label1);frame.add(comboBox);frame.add(button);
        frame.setVisible(true);
    }
}