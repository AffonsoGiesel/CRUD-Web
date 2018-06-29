package dw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/venda-mvc/venda")
public class ControleVendas extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String op = request.getParameter("op");
    op = (op == null ? "" : op);

    ModeloVendas Sale = new ModeloVendas();
    Sale.setCode(request.getParameter("codigo"));
    Sale.setProduct(request.getParameter("produto"));
    String StringQuantity = request.getParameter("quantidade");
    StringQuantity = (StringQuantity == null ? "0" : StringQuantity);
    Sale.setQuantity(Integer.parseInt(StringQuantity));

    List<ModeloVendas> Sales = null;
    try {
      if (op.equals("incluir")) {
        Sale.include();
      } else if (op.equals("salvar")) {
        Sale.save();
      } else if (op.equals("excluir")) {
        Sale.delete();
      }

      Sales = ModeloVendas.list();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    
    //Adiciona a variável na requisição para o JSP trabalhar.
    request.setAttribute("vendas", Sales);

    //Redireciona requisição para o JSP.
    request.
      getRequestDispatcher("/venda-mvc/venda-view.jsp").
      forward(request, response);
  }
}
