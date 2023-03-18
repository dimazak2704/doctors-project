import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.*;

public class prog_for_doctor {

    static Font label_font = new Font("Times New Roman", Font.BOLD, 30);
    static Font combobox_font = new Font("Times New Roman", Font.PLAIN, 25);
    static ImageIcon searchImg = new ImageIcon("search-icon.png");
    static ImageIcon backImg = new ImageIcon("back-icon.png");
    static boolean CBdoctor_by_specialty_visible = false;
    static boolean CBdoctor_by_lastname_visible = false;
    static String doctor_id;
    static String[] str;
    static int id;

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("doctor's appointment");                                  //Frame
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setBounds(swing.width/2-600, swing.height/2-360,1200,720);
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
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctors_appointment", "acc_name", "password");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final ResultSet[] resultSet = {statement.executeQuery("select specialty_name from specialty")};
        while(resultSet[0].next()){CBspecialty.addItem(resultSet[0].getString(1));}
        CBspecialty.setSelectedItem(null);


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
        CBdoctor_by_specialty.setSelectedItem(null);






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
                    resultSet[0] = statement.executeQuery("select doctor.first_name, doctor.last_name, doctor.middle_name, specialty.specialty_name from doctor inner join specialty on doctor.specialty_id=specialty.specialty_id where doctor.last_name='"+ searchFld.getText() +"'");
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
        CBdoctor_by_lastname.setSelectedItem(null);




//                                               DATE CHOOSER
        JLabel date_label = new JLabel("Виберіть дату запису");
        date_label.setFont(label_font);
        date_label.setBounds(890,20,550,40);

        JMonthChooser monthChooser = new JMonthChooser();                                       // MONTH CHOOSER
        monthChooser.setBounds(970,100,400,40);
        monthChooser.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JDayChooser dayChooser = new JDayChooser();                                                //DAY CHOOSER
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



        String[] columnNames = { "Ім'я", "Рік народження", "Час запису" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable client_tabl = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        client_tabl.setBounds(100, 75, 1000, 495);
        client_tabl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        client_tabl.setRowHeight(30);
        JTableHeader header = client_tabl.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 30));
        client_tabl.setBounds(100, 75, 1000, 495);
        JScrollPane scrollPane = new JScrollPane(client_tabl);
        scrollPane.setBounds(100, 75, 1000, 495);
        scrollPane.setVisible(false);


        JButton back_button = new JButton(backImg);
        back_button.setBounds(0,0,40,40);
        back_button.setVisible(false);


        frame.add(specialty);frame.add(CBspecialty);frame.add(dayChooser);frame.add(monthChooser);frame.add(searchLB);frame.add(searchFld);frame.add(CBdoctor_by_specialty);frame.add(CBdoctor_by_lastname);frame.add(next);frame.add(search_button);frame.add(date_label);frame.add(next);frame.add(scrollPane);frame.add(back_button);
        frame.setVisible(true);


        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    model.setRowCount(0);
                    resultSet[0] = statement.executeQuery("select client_name, client_birth, time from records where doctor_id = "+id+" and month_id = "+monthChooser.getMonth()+" and day_id = "+dayChooser.getDay()+" order by time ASC");
                    ResultSetMetaData metaData = resultSet[0].getMetaData();
                    int columnCount = metaData.getColumnCount();
                    resultSet[0].last();
                    int rowCount = resultSet[0].getRow();
                    resultSet[0].beforeFirst();

                    Object[][] data = new Object[rowCount][columnCount];

                    int row = 0;
                    while (resultSet[0].next()) {
                        for (int col = 0; col < columnCount; col++) {
                            data[row][col] = resultSet[0].getObject(col+1);
                        }
                        row++;
                    }

                    for(int i=0; i< data.length; i++){
                        model.addRow(data[i]);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                scrollPane.setVisible(true);back_button.setVisible(true);
                specialty.setVisible(false);CBspecialty.setVisible(false);dayChooser.setVisible(false);monthChooser.setVisible(false);searchLB.setVisible(false);searchFld.setVisible(false);search_button.setVisible(false);
                CBdoctor_by_specialty.setVisible(false);CBdoctor_by_lastname.setVisible(false);next.setVisible(false);date_label.setVisible(false);

            }
        });


        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialty.setVisible(true);CBspecialty.setVisible(true);dayChooser.setVisible(true);monthChooser.setVisible(true);searchLB.setVisible(true);searchFld.setVisible(true);search_button.setVisible(true);
                CBdoctor_by_specialty.setVisible(CBdoctor_by_specialty_visible);CBdoctor_by_lastname.setVisible(CBdoctor_by_lastname_visible);next.setVisible(true);date_label.setVisible(true);
                scrollPane.setVisible(false);back_button.setVisible(false);
            }
        });
    }
}










//select client_name, client_birth, time from records where doctor_id = 4 and month_id = 3 and day_id = 1;
