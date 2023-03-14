import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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



    public static void main(String[] args) throws InterruptedException, SQLException {


        JFrame frame = new JFrame("doctor's appointment");                                  //Frame
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setBounds(width/2-600,height/2-360,1200,720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.getContentPane().setBackground(new Color(255,240,225));



        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctors_appointment", "test", "root");
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select client_name, client_birth, time from records where doctor_id = 4 and month_id = 3 and day_id = 1");



        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        resultSet.last();
        int rowCount = resultSet.getRow();
        resultSet.beforeFirst();

        Object[][] data = new Object[rowCount][columnCount];

        int row = 0;
        while (resultSet.next()) {
            for (int col = 0; col < columnCount; col++) {
                data[row][col] = resultSet.getObject(col+1);
            }
            row++;
        }



        String[] columnNames = { "Ім'я", "Рік народження", "Час запису" };



//        JTable client_tabl=new JTable(data,columnNames){
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        client_tabl.setBounds(100, 75, 1000, 495);
//        client_tabl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//        client_tabl.setRowHeight(25);
//        JTableHeader header = client_tabl.getTableHeader();
//        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        client_tabl.setBounds(100,75,1000,495);
//        JScrollPane sp=new JScrollPane(client_tabl);
//        sp.setBounds(100, 75, 1000, 495);
//        frame.add(sp);




//        for(int i=0; i<3; i++){
//            model.addColumn(columnNames[i]);
//        }
//        for(int i=0; i< data.length; i++){
//            model.addRow(data[i]);
//        }


        // Створити об'єкт моделі таблиці зі стовпцями та їх назвами
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Додати рядки до моделі таблиці
        Object[] rowData1 = {1, "Іван", "Петров"};
        Object[] rowData2 = {2, "Марія", "Сидорова"};
        for(int i=0; i< data.length; i++){
            model.addRow(data[i]);
        }

        // Створити JTable з моделлю таблиці
        JTable table = new JTable(model);

        // Встановити координати та розміри для таблиці
        table.setBounds(100, 75, 1000, 495);

        // Створити JScrollPane для JTable
        JScrollPane scrollPane = new JScrollPane(table);

        // Встановити координати та розміри для JScrollPane
        scrollPane.setBounds(100, 75, 1000, 495);

        // Створити JFrame і додати JScrollPane
        JFrame frame1 = new JFrame("Приклад таблиці");
        frame1.setLayout(null);
        frame1.add(scrollPane);

        // Встановити координати та розміри для JFrame
        frame1.setSize(1200, 720);
        frame1.setVisible(true);
//        frame.add(table);frame.add(scrollPane);
//
//        frame.setVisible(true);


    }
}
