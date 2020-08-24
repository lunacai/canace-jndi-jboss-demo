import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

class DBConnection{
    private Connection conn = null;

    public Connection getConnecting() {
        //根据资源名称搜索
        Context ctx = null;
        try {
            ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/jbossjndi");
            conn = dataSource.getConnection();
            return conn;
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        System.out.println("111111");
//        DBConnection connection = new DBConnection();
//        System.out.println("111111");
//        connection.select("select * from T_USER limit 1");
//        System.out.println("111111");
//        connection.close();
//        System.out.println("111111");
//    }
//
//    //    驱动类名
//    String driver="com.mysql.jdbc.Driver";
//    //    URL格式,最后为数据库名
//    String url="jdbc:mysql://192.168.8.110:3306/test_web?useUnicode=true&characterEncoding=UTF8";//JavaTest为你的数据库名称
//    String user="root";
//    String password="zdsoft";
//    Connection coon=null;
//    public DBConnection(){
//        try{
////            加载驱动程序
//            Class.forName(driver);
//            coon=(Connection) DriverManager.getConnection(url,user,password);
//            if(!coon.isClosed()){
//                System.out.println("成功连接数据库！");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public void close(){
//        try{
//            this.coon.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    //    增加数据
//    public void add(String sql){
////        String sql="insert into usrInfo(username,gender,age) values(?,?,?)";　　//向usrInfo表中插入数据
//       // String sql="insert into usrInfo(age,gender,username) values('"+age+"','"+gender+"','"+name+"')";
//        try{
//            coon.setAutoCommit(false);
//            PreparedStatement preStmt=(PreparedStatement)this.coon.prepareStatement(sql);
////            preStmt.setString(1, name);
////            preStmt.setInt(3, age);
////            preStmt.setString(2, gender);　　//和上面的注释的一块组成另外一种插入方法
//            preStmt.executeUpdate();
//            coon.commit();
//            System.out.println("插入数据成功！");
//            preStmt.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    //    查询
//    public void select(String sql){
//        //String sql="select * from usrInfo";//查询usrInfo表中的信息
//
//        try{
//            Statement stmt=(Statement)this.coon.createStatement();
//            ResultSet rs=(ResultSet)stmt.executeQuery(sql);//得到的是结果的集合
//            while(rs.next()){
//                String name=rs.getString("USERNAME");
//                System.out.println(name);
//            }
//            stmt.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
////    更改数据
//
//    public void update(String sql){
//        //String sql="update usrInfo set age=? where username=?";//推荐使用这种方式，下面的那种注释方式不知道为啥有时候不好使
////        String sql="update usrInfo set age="+age+" where username='"+name+"'";
//        try{
//            coon.setAutoCommit(false);
//            PreparedStatement prestmt=(PreparedStatement)this.coon.prepareStatement(sql);
//            //prestmt.setInt(1, age);
//            //prestmt.setString(2,name);
//            prestmt.executeUpdate();
//
//            coon.commit();
////            Statement stmt=(Statement)this.coon.createStatement();
////            stmt.executeUpdate(sql);
//            System.out.println("更改数据成功！");
//            prestmt.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    //    删除数据
//    public void del(String sql){
//        //String sql="delete from usrInfo where username=?";
//        try{
//            coon.setAutoCommit(false);
//            PreparedStatement prestmt=(PreparedStatement)this.coon.prepareStatement(sql);
//            //prestmt.setString(1, name);
//            prestmt.executeUpdate();
//            coon.commit();
//            System.out.println("删除数据成功！");
//            prestmt.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}