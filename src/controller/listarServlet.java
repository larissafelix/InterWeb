package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Regiao;
import servico.RegiaoServico;
import servico.ServicoFactory;

@WebServlet("/listarServlet")
public class listarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegiaoServico regiaoServico = ServicoFactory.criarRegiaoServico();
		List<Regiao> lista = regiaoServico.buscarTodos();
		request.setAttribute("lista", lista);
		RequestDispatcher rd = request.getRequestDispatcher("regiao.jsp");
		rd.forward(request, response);}
}
