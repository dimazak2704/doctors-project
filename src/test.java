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
        frame.setBounds(width / 2 - 600, height / 2 - 360, 1200, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


//        JRadioButton tomato = new JRadioButton("Tomato");
//        JRadioButton barbeque = new JRadioButton("Barbeque");
//        ButtonGroup group = new ButtonGroup();
//        group.add(tomato);
//        group.add(barbeque);
//        JPanel radiopanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        radiopanel.add(tomato);
//        radiopanel.add(barbeque);
//        frame.getContentPane().add(radiopanel);
//        radiopanel.setBounds(240,330,110,70);
//        radiopanel.setOpaque(false);
//        tomato.setForeground(Color.white);
//        barbeque.setForeground(Color.white);
//
//
//
//        frame.setLayout(null);
//        frame.setSize(600, 700);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);



        JPanel panel = new JPanel(new GridLayout(0,1));
        JRadioButton myRadio;
        ButtonGroup group = new ButtonGroup();
        for(int i = 0; i<100; i++){
            myRadio = new JRadioButton("text" + i);
            group.add(myRadio);
            panel.add(myRadio);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVisible(false);
        scrollPane.setBounds(200,200,200,200);
        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);

        Thread.sleep(1000);
        scrollPane.setVisible(true);
        Thread.sleep(1000);
        panel.removeAll();
    }
}
