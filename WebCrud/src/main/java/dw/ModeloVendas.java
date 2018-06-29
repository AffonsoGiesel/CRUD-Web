package dw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloVendas {
  private String code;
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }

  private String product;
  public String getProduct() {
    return product;
  }
  public void setProduct(String product) {
    this.product = product;
  }

  private Integer quantity;
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  private static Connection adquirirConexao() throws SQLException {
    //Estabelecer uma conexão com o banco de dados.
    String url = "jdbc:derby://localhost:1527/vendadb;create=true";
    String user = "app";
    String password = "app";
    return DriverManager.getConnection(url, user, password);
  }

  public void include() throws SQLException {
    Connection conn = adquirirConexao();
    
    //Obter sentença SQL.
    String sql = "insert into sale (code, product, quantity) values (?, ?, ?)";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, code);
    pstmt.setString(2, product);
    pstmt.setInt(3, quantity);
    pstmt.execute();
  }

  public void save() throws SQLException {
    Connection conn = adquirirConexao();

    //Obter sentença SQL.
    String sql = "update sale set product = ?, quantity = ? where code = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, product);
    pstmt.setInt(2, quantity);
    pstmt.setString(3, code);
    pstmt.execute();
  }

  public void delete() throws SQLException {
    Connection conn = adquirirConexao();
    
    //Obter sentença SQL.
    String sql = "delete from sale where code = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, code);
    pstmt.execute();
  }

  public static List<ModeloVendas> list() throws SQLException {
    Connection conn = adquirirConexao();
    
    Statement stmt = conn.createStatement();
    String sql = "select code, product, quantity from sale order by code";
    ResultSet rs = stmt.executeQuery(sql);
  
    List<ModeloVendas> listaDeVendas = new ArrayList<ModeloVendas>();
    while (rs.next()) {
      // Cria um sale para o registro.
      ModeloVendas sale = new ModeloVendas();
      sale.setCode(rs.getString("code"));
      sale.setProduct(rs.getString("product"));
      sale.setQuantity(rs.getInt("quantity"));
      // Adiciona o sale na lista de vendas.
      listaDeVendas.add(sale);
    }
    return listaDeVendas;
  }
}
