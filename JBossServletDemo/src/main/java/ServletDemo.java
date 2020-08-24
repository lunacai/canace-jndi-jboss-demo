import com.pamirs.pradar.Pradar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ServletDemo extends HttpServlet {
    private Connection conn = null;
    private Statement stmt = null;
    private DBConnection connection = new DBConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
//        if(index == null){
//            index = "select";
//        }
        if (index.equals("select")) {
            String sql = "SELECT * FROM user limit 1";
            Map<String, String> map = select(sql);
            resp.getWriter().write("select>>>>>>>>>>" + map.toString());
        } else if (index.equals("add")) {
            String names = req.getParameter("username");
            String content = req.getParameter("content");
            String sql = "insert into user(`name`,`content`) value('" + names + "','" + content + "');";
            System.out.println("sql>>>>>>>>>>>>>"+sql);
//            System.out.println("Pradar>>>>>>>>>>>>>>" + Pradar.isClusterTest());
            String result = add(sql);
            resp.getWriter().write("add User>>>>>>>>>>" + result);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String index = req.getParameter("index");

        if (index.equals("add")) {
            String names = req.getParameter("username");
            String content = req.getParameter("content");
            String sql = "insert into user(`name`,`content`) value('" + names + "','" + content + "');";
            String result = add(sql);
            resp.getWriter().write("add User>>>>>>>>>>" + result);
        }

    }


    public Map<String, String> select(String sql) {
        Map<String, String> map = new HashMap<String, String>();
        ResultSet rs = null;
        try {
            conn = connection.getConnecting();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String content = rs.getString("content");
                System.out.print("id: " + id);
                System.out.println(", name: " + name);
                map.put("id", String.valueOf(id));
                map.put("name", name);
                map.put("content", content);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String add(String sql) {
        try {
            conn = connection.getConnecting();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
