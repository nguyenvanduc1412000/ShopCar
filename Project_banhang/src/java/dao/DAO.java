/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Order;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trinh
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Account> getSellAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Accounts\n"
                + "where isSell = 1 and isAdmin = 0 and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Account> getUserAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Accounts\n"
                + "where isSell = 0 and isAdmin = 0 and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByCID(String cid, String index) {
        List<Product> list = new ArrayList<>();
        String query = "with x as (select ROW_NUMBER() over (order by [id] desc) as r,\n"
                + "id,name,[image],price,title,[description] ,cateID from product where cateID = ? and block = 0)\n"
                + "select x.id, x.name, x.[image], x.price, x.title, x.[description] from x where r between ?*6-5 and ?*6";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            ps.setString(2, index);
            ps.setString(3, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where sell_ID = ? and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from product\n"
                + "where id = ? and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getProductBySearch(String searchName) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where [name] like ? and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Account getAccountLogin(String user, String pass) {
        String query = "select * from accounts \n"
                + "where [user] = ? \n"
                + "and pass = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public Account getAccountCreate(String user) {
        String query = "select * from accounts \n"
                + "where [user] = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public Account getEmailCreate(String email) {
        String query = "select * from accounts \n"
                + "where [email] = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteProduct(String pid) {
        String query = "delete from product\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public void insertAccount(String user, String pass, String seller, String email, String phone) {
        String query = "insert Accounts\n"
                + "([user],[pass],[isSell],[isAdmin],[Email],[phone],[block])\n"
                + "values(?,?,?,0,?,?,0)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, seller);
            ps.setString(4, email);
            ps.setString(5, phone);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid) {
        String query = "INSERT [dbo].[product] \n"
                + "([name], [image], [price], [title], [description], [cateID], [sell_ID],[block])\n"
                + "VALUES(?,?,?,?,?,?,?,0)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertOrder(int aid, String pid) {
        String query = "SET IDENTITY_INSERT [dbo].[Order] on\n"
                + "      INSERT INTO [Order]([AccountID],[id],[name],[image],[price])\n"
                + "      SELECT ?,[id],[name],[image],[price]\n"
                + "      FROM [product]\n"
                + "      WHERE [product].id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, aid);
            ps.setString(2, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Order> getAllOrder(int aid) {
        List<Order> list = new ArrayList<>();
        String query = "select id,name,[image],price,sell_ID from [Order] where AccountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, aid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteOrder(String cid) {
        String query = "delete from [Order]\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public void eidtProduct(String name, String image, String price,
            String title, String description, String category, String pid) {
        String query = "update product\n"
                + "set [name] = ?, \n"
                + "[image] = ?, \n"
                + "[price] = ?, \n"
                + "[title] = ?, \n"
                + "[description] = ?, \n"
                + "[cateID] = ? \n"
                + "where id = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Product getLast() {
        String query = "select top 1 * from product where block = 0\n"
                + "order by id desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int count() {
        String query = "select COUNT (*) from product where block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int countByCid(String cid) {
        String query = "select COUNT (*) from product where cateID = ? and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int countBySearchName(String name) {
        String query = "select COUNT (*) from product where [name] like ? and block = 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Product> getProductIndexByID(String index) {
        List<Product> list = new ArrayList<>();
        String query = "with x as (select ROW_NUMBER() over (order by [id] desc) as r,\n"
                + "id,name,[image],price,title,[description] from product where block = 0) \n"
                + "select x.id, x.name, x.[image], x.price, x.title, x.[description] from x where r between ?*6-5 and ?*6";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, index);
            ps.setString(2, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductIndexBySearchName(String name, String index) {
        List<Product> list = new ArrayList<>();
        String query = "with x as (select ROW_NUMBER() over (order by id) as r,\n"
                + "id,name,[image],price,title,[description] from product where [name] like ? and block = 0)\n"
                + "select x.id, x.name, x.[image], x.price, x.title, x.[description] from x where r between ?*6-5 and ?*6";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, index);
            ps.setString(3, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void eidtPassAccount(String pass, int uid) {
        String query = "UPDATE Accounts\n"
                + "SET [pass] = ?\n"
                + "where [uid] = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pass);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void eidtNameAccount(String name, int uid) {
        String query = "UPDATE Accounts\n"
                + "SET [user] = ?\n"
                + "where [uid] = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void eidtPhoneAccount(String phone, int uid) {
        String query = "UPDATE Accounts\n"
                + "SET [phone] = ?\n"
                + "where [uid] = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void eidtEmailAccount(String email, int uid) {
        String query = "UPDATE Accounts\n"
                + "SET [email] = ?\n"
                + "where [uid] = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void blockAccount(String aid) {
        String query = "UPDATE Accounts SET block = 1 WHERE uID = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void unBlockAccount(String aid) {
        String query = "UPDATE Accounts SET block = 0 WHERE uID = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account getAccBlock(String user) {
        String query = "select * \n"
                + "from Accounts\n"
                + "where [USER] like ? and block = 1";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void BlockProduct(String pid) {
        String query = "UPDATE product SET block = 1 WHERE ID = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UnBlockProduct(String pid) {
        String query = "UPDATE product SET block = 0 WHERE ID = ?";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Account> getAllAccBlock() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Accounts\n"
                + "where block = 1";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductBlock() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\n"
                + "where block = 1";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insertCategory(String cname) {
        String query = "INSERT INTO Category\n"
                + "(cname)\n"
                + "VALUES(?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cname);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public void insertBuyer(int uid,String Fname, String Lname, String phone,
            String email, String address, String sellid) {
        String query = "SET IDENTITY_INSERT [dbo].[buyer] ON \n"
                + "INSERT [dbo].[buyer]\n"
                + "([bid],[First Name], [Last Name], [Phone], [Email], [Address], [sellid])\n"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, uid);
            ps.setString(2, Fname);
            ps.setString(3, Lname);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, address);
            ps.setString(7, sellid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.unBlockAccount("49");
//        for (Account o : list) {
//            System.out.println(o);
//            
//        }
    }
}
