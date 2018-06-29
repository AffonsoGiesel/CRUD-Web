package dw.jpa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jpa/professores")
public class TeacherController extends HttpServlet {
	private String value(HttpServletRequest req, String param, String pattern) {
		String result = req.getParameter(param);
		if (result == null) {
			result = pattern;
		}
		return result;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String msg;
			String op = value(req, "operacao", "");
			String matricula = value(req, "matricula", "");
			String nome = value(req, "nome", "");
			if (op.equals("incluir")) {
				TeacherModel.include(matricula, nome);
				msg = "Inclusão realizada com sucesso.";
			} else if (op.equals("alterar")) {
				TeacherModel.change(matricula, nome);
				msg = "Alteração realizada com sucesso.";
			} else if (op.equals("excluir")) {
				TeacherModel.exclude(matricula);
				msg = "Exclusão realizada com sucesso.";
			} else if (op.equals("")) {
				msg = "";
			} else {
				throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
			}
			req.setAttribute("msg", msg);
			req.setAttribute("professores", TeacherModel.list());
			
			req.getRequestDispatcher("/jpa/professor-view.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
}
