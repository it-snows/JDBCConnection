import com.company.data.DataLoader;
import com.company.data.DatabaseManager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        var dm = new DatabaseManager();
        for (var region:
             dm.getRegions()) {
            System.out.println(region.getName());
        }

        var dl = new DataLoader();
        dl.loadCSV();

    }
}
