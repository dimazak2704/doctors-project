import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.*;
import java.util.ArrayList;

public class swing {
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = screenSize.width;
    static int height = screenSize.height;
    Font label_font = new Font("Times New Roman", Font.BOLD, 30);
    Font combobox_font = new Font("Times New Roman", Font.PLAIN, 25);
    ImageIcon searchImg = new ImageIcon("search-icon.png");
    ImageIcon backImg = new ImageIcon("back-icon.png");
    static boolean CBdoctor_by_specialty_visible = false;
    static boolean CBdoctor_by_lastname_visible = false;
    static String doctor_id;
    static String[] str;
    static int id;

    static String [] time_array = {"09.00", "09.30", "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30", "17.00", "17.30"};
    static ArrayList<String> busy_time_list = new ArrayList<>();
    public static String[] removeElements(String[] arr, ArrayList<String> frst) {
        ArrayList<String> result = new ArrayList<>();
        String[] values = frst.toArray(new String[0]);

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


    swing () throws SQLException, InterruptedException {

        JFrame frame = new JFrame("doctor's appointment");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setBounds(width/2-600,height/2-360,1200,720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255,240,225));

        JRadioButton specialty = new JRadioButton("Пошук лікаря за спеціальністю");
        specialty.setFont(label_font);
        specialty.setBounds(10,20,450,40);
        specialty.setBackground(new Color(255,240,225));

        JComboBox CBspecialty = new JComboBox();
        CBspecialty.setBounds(30,100,400,40);
        CBspecialty.setFont(combobox_font);
        CBspecialty.setSelectedItem(null);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctors_appointment", "test", "root");
        Statement statement = con.createStatement();
        final ResultSet[] resultSet = {statement.executeQuery("select specialty_name from specialty")};
        while(resultSet[0].next()){CBspecialty.addItem(resultSet[0].getString(1));}


        JComboBox CBdoctor_by_specialty = new JComboBox();
        CBdoctor_by_specialty.setBounds(30,200,400,40);
        CBdoctor_by_specialty.setFont(combobox_font);
        CBdoctor_by_specialty.setVisible(false);

        CBspecialty.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                CBdoctor_by_specialty.removeAllItems();
                try {
                    resultSet[0] = statement.executeQuery("select doctor.last_name, doctor.first_name, doctor.middle_name from doctor inner join specialty on specialty.specialty_id=doctor.specialty_id where specialty.specialty_name = '"+CBspecialty.getSelectedItem().toString()+"'");
                    while(resultSet[0].next()){
                        System.out.println(resultSet[0].getString(1) + " "+ resultSet[0].getString(2) + " "+ resultSet[0].getString(3));
                        CBdoctor_by_specialty.addItem(resultSet[0].getString(1) + " "+ resultSet[0].getString(2) + " "+ resultSet[0].getString(3));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                CBdoctor_by_specialty.setVisible(true);
                CBdoctor_by_specialty_visible = true;
            }

        });


        JRadioButton searchLB = new JRadioButton("Пошук за прізвищем");
        searchLB.setFont(label_font);
        searchLB.setBounds(530,20,350,40);
        searchLB.setBackground(new Color(255,240,225));

        JTextField searchFld = new JTextField();
        searchFld.setFont(combobox_font);
        searchFld.setBounds(500,100,350,40);

        JButton search_button = new JButton(searchImg);
        search_button.setBounds(850,100,40,39);


        JComboBox CBdoctor_by_lastname = new JComboBox();
        CBdoctor_by_lastname.setBounds(500,200,390,40);
        CBdoctor_by_lastname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        CBdoctor_by_lastname.setVisible(false);

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CBdoctor_by_lastname.removeAllItems();
                try {
                    resultSet[0] = statement.executeQuery("select doctor.first_name, doctor.last_name, doctor.middle_name, specialty.specialty_name from doctor inner join specialty on specialty.specialty_id=doctor.doctor_id where doctor.last_name='"+ searchFld.getText() +"'");
                    while (resultSet[0].next()){
                        System.out.println(resultSet[0].getString(2)+ " "+ resultSet[0].getString(1) + " "+ resultSet[0].getString(3)+ " "+ resultSet[0].getString(4));
                        CBdoctor_by_lastname.addItem(resultSet[0].getString(2)+ " "+ resultSet[0].getString(1) + " "+ resultSet[0].getString(3)+ " "+ resultSet[0].getString(4));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                CBdoctor_by_lastname.setVisible(true);
                CBdoctor_by_lastname_visible = true;
            }
        });



        JLabel date_label = new JLabel("Виберіть дату запису");
        date_label.setFont(label_font);
        date_label.setBounds(890,20,550,40);

        JMonthChooser monthChooser = new JMonthChooser();
        monthChooser.setBounds(970,100,400,40);
        monthChooser.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JDayChooser dayChooser = new JDayChooser();
        dayChooser.setDecorationBordersVisible(false);
        dayChooser.setDecorationBackgroundColor(new Color(255,178,102));
        dayChooser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dayChooser.setBounds(950,150,200,200);
        dayChooser.setMonth(monthChooser.getMonth());


        monthChooser.addPropertyChangeListener("month", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                dayChooser.setMonth(monthChooser.getMonth());
            }
        });

        ButtonGroup choise_group = new ButtonGroup();
        choise_group.add(specialty);choise_group.add(searchLB);
        specialty.setSelected(true);


        JButton next = new JButton("Далі");
        next.setFont(label_font);
        next.setBounds(400,500,400,100);



        JLabel doctor_name = new JLabel();
        doctor_name.setBounds(350,20,500,40);
        doctor_name.setHorizontalTextPosition(JLabel.CENTER);
        doctor_name.setHorizontalAlignment(JLabel.CENTER);
        doctor_name.setFont(label_font);
        doctor_name.setVisible(false);

        JLabel choose_time_label = new JLabel("Виберіть час запису");
        choose_time_label.setFont(label_font);
        choose_time_label.setBounds(450,100,300,40);
        choose_time_label.setHorizontalTextPosition(JLabel.CENTER);
        choose_time_label.setHorizontalAlignment(JLabel.CENTER);
        choose_time_label.setVisible(false);

        JComboBox CbTime = new JComboBox();
        CbTime.setBounds(500,200,200,40);
        CbTime.setVisible(false);
        CbTime.setFont(label_font);

        JButton complete_button = new JButton("Підтвердити");
        complete_button.setBounds(400,500,400,100);
        complete_button.setFont(label_font);
        complete_button.setVisible(false);

        JButton back_button = new JButton(backImg);
        back_button.setBounds(0,0,40,40);
        back_button.setVisible(false);


        frame.add(specialty);frame.add(CBspecialty);frame.add(dayChooser);frame.add(monthChooser);frame.add(searchLB);frame.add(searchFld);frame.add(date_label);frame.add(CBdoctor_by_specialty);frame.add(CBdoctor_by_lastname);frame.add(next);frame.add(search_button);frame.add(CbTime);frame.add(back_button);frame.add(doctor_name);frame.add(choose_time_label);frame.add(complete_button);
        frame.setVisible(true);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CbTime.removeAllItems();
                busy_time_list.clear();

                if(choise_group.isSelected(specialty.getModel())){
                    doctor_id = CBdoctor_by_specialty.getSelectedItem().toString();
                    str = doctor_id.split(" ");
                    try {
                        resultSet[0] = statement.executeQuery("select doctor_id from doctor where first_name = '"+str[1]+"' and last_name = '"+str[0]+"' and middle_name='"+str[2]+"';");
                        while(resultSet[0].next()){
                            id = resultSet[0].getInt(1);
                        }
                        System.out.println(id);
                    } catch (SQLException ex) {throw new RuntimeException(ex);}
                }
                else {
                    doctor_id = CBdoctor_by_lastname.getSelectedItem().toString();
                    System.out.println(doctor_id);
                    str = doctor_id.split(" ");
                    try {
                        resultSet[0] = statement.executeQuery("select doctor_id from doctor where first_name = '"+str[1]+"' and last_name = '"+str[0]+"' and middle_name='"+str[2]+"';");
                        while(resultSet[0].next()){
                            id = resultSet[0].getInt(1);
                        }
                        System.out.println(id);
                    } catch (SQLException ex) {throw new RuntimeException(ex);}
                }

                try {
                    resultSet[0] = statement.executeQuery("select time from records where doctor_id = "+ id+ " and day_id = "+dayChooser.getDay()+" and month_id = "+ monthChooser.getMonth());
                    while(resultSet[0].next()){
                        busy_time_list.add(resultSet[0].getString(1));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String[] avaible_time = removeElements(time_array,busy_time_list);
                for (int i = 0; i<avaible_time.length; i++){
                    CbTime.addItem(avaible_time[i]);
                }

                try {
                    resultSet[0] = statement.executeQuery("select last_name, first_name, middle_name from doctor where doctor_id="+id);
                    while (resultSet[0].next()){
                        doctor_name.setText(resultSet[0].getString(1)+" "+resultSet[0].getString(2)+" "+resultSet[0].getString(3));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                specialty.setVisible(false);CBspecialty.setVisible(false);dayChooser.setVisible(false);monthChooser.setVisible(false);searchLB.setVisible(false);searchFld.setVisible(false);search_button.setVisible(false);date_label.setVisible(false);CBdoctor_by_specialty.setVisible(false);CBdoctor_by_lastname.setVisible(false);next.setVisible(false);
                CbTime.setVisible(true);back_button.setVisible(true);doctor_name.setVisible(true);choose_time_label.setVisible(true);complete_button.setVisible(true);


            }
        });


        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialty.setVisible(true);CBspecialty.setVisible(true);dayChooser.setVisible(true);monthChooser.setVisible(true);searchLB.setVisible(true);searchFld.setVisible(true);search_button.setVisible(true);date_label.setVisible(true);CBdoctor_by_specialty.setVisible(CBdoctor_by_specialty_visible);CBdoctor_by_lastname.setVisible(CBdoctor_by_lastname_visible);next.setVisible(true);
                CbTime.setVisible(false);back_button.setVisible(false);doctor_name.setVisible(false);choose_time_label.setVisible(false);complete_button.setVisible(false);


            }
        });

        complete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = statement.executeUpdate("insert into records (doctor_id, month_id, day_id, time) values ("+id+","+monthChooser.getMonth()+","+dayChooser.getDay()+","+CbTime.getSelectedItem()+")");
                    JOptionPane.showMessageDialog(null, "Завдання виконано успішно!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}