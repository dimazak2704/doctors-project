import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class test extends JFrame {

    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = screenSize.width;
    static int height = screenSize.height;
    static boolean but = false;




    static Date mindate = methods.parseDate("2014-02-14");
    static Date today = new Date();
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    static Date maxdate = methods.parseDate(format.format(today));

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
        comboBox.setBounds(500,150,200,40);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        comboBox.addItem("123");
        comboBox.addItem("123");
        comboBox.addItem("123");
        comboBox.setSelectedItem(null);

        JLabel client_name_label = new JLabel("Введіть повне ім'я пацієнта");
        client_name_label.setHorizontalAlignment(JLabel.CENTER);
        client_name_label.setHorizontalTextPosition(JLabel.CENTER);
        client_name_label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        client_name_label.setBounds(350,220,500,40);

        JTextField client_name_field = new JTextField();
        client_name_field.setHorizontalAlignment(JTextField.CENTER);
        client_name_field.setBounds(350,280,500,40);

        JLabel date_label = new JLabel("Вкажіть дату народження пацієнта");
        date_label.setBounds(350,350,500,40);
        date_label.setHorizontalAlignment(JLabel.CENTER);
        date_label.setHorizontalTextPosition(JLabel.CENTER);
        date_label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(525,420,150,50);
        dateChooser.setSelectableDateRange(mindate,maxdate);
        dateChooser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dateChooser.setDateFormatString("yyyy-MM-dd");



        JButton button = new JButton("Підтвердити");
        button.setBounds(400,550,400,100);



        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date dateFromDateChooser = dateChooser.getDate();
                String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);
                System.out.println(dateString);
                dateChooser.setDate(null);


                //JOptionPane.showMessageDialog(null, "Завдання виконано успішно!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(label);frame.add(label1);frame.add(comboBox);frame.add(button);frame.add(client_name_label);frame.add(client_name_field);frame.add(dateChooser);frame.add(date_label);
        frame.setVisible(true);
    }
}