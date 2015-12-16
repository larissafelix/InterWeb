package controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Regiao;
import servico.RegiaoServico;
import servico.ServicoException;
import servico.ServicoFactory;

@WebServlet("/cliente/regiaoServlet")
public class regiaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERIR_OU_ALTERAR = "/cliente/regiaoForm.jsp";
	private static String CONFIRMACAO = "/cliente/regiaoConfirmacao.jsp";
	private static String LISTAR = "/cliente/regiao.jsp";
	private static String ERRO = "/publico/erro.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegiaoServico regiaoServico = ServicoFactory.criarRegiaoServico();

		String forward = "";
		String cmd = request.getParameter("cmd");
		if (cmd == null || cmd.equalsIgnoreCase("")) {
			cmd = "";
		}
		if ("novo".equalsIgnoreCase(cmd)) {
			try {
				Regiao reg = new Regiao();
				request.setAttribute("reg", reg);
				forward = INSERIR_OU_ALTERAR;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execução: " + e.getMessage());
				forward = ERRO;
			}
		}
		RequestDispatcher rd1 = request.getRequestDispatcher(forward);
		rd1.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegiaoServico regiaoServico = ServicoFactory.criarRegiaoServico();
		String forward = "";
		try {
			Regiao reg = instanciar(request);
			regiaoServico.inserirRegiao(reg);
			request.setAttribute("reg", reg);
			forward = CONFIRMACAO;
		} catch (ServicoException e) {
			request.setAttribute("erro", e.getMessage());
			forward = ERRO;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}

	private Regiao instanciar(HttpServletRequest req) {
		String aux;
		Regiao x = new Regiao();
		aux = req.getParameter("codRegiao");
		if (aux != null && !aux.isEmpty())
			x.setCodRegiao(Integer.parseInt(aux));
		aux = req.getParameter("nome");
		x.setNomeRegiao(aux);
		aux = req.getParameter("valor");
		x.setValor(new BigDecimal(aux));

		return x;
	}

}