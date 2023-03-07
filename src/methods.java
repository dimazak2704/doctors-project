import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class methods {

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

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
