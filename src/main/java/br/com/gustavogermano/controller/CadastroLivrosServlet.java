package br.com.gustavogermano.controller;

import br.com.gustavogermano.exception.ErroLivroException;
import br.com.gustavogermano.model.Livro;
import br.com.gustavogermano.util.GerarHtmlLivro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "CadastroLivrosServlet", value = "/livros")
public class CadastroLivrosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Armazena os livros cadastrados durante a execução da aplicação
    private List<Livro> acervo;

    @Override
    public void init() throws ServletException {
        acervo = new LinkedList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        exibirLivros(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String acao = req.getParameter("action");

        if ("delete".equals(acao)) {
            removerLivro(req);
            resp.sendRedirect(req.getContextPath() + "/livros");
            return;
        }

        cadastrarLivro(req, resp);
    }

    private void exibirLivros(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String html = GerarHtmlLivro.gerar(req, acervo);
        req.setAttribute("htmlLivros", html);
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }

    private void removerLivro(HttpServletRequest req) {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                acervo.removeIf(l -> l.getId() == id);
            } catch (NumberFormatException ignored) {}
        }
    }

    private void cadastrarLivro(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Livro novoLivro = new Livro();
            novoLivro.setTitulo(req.getParameter("titulo").toUpperCase());
            novoLivro.setAutor(req.getParameter("autor").toUpperCase());
            novoLivro.setAno(Integer.parseInt(req.getParameter("ano")));
            novoLivro.validar();
            acervo.add(novoLivro);
            resp.sendRedirect(req.getContextPath() + "/livros");
        } catch (NumberFormatException e) {
            tratarErro(req, resp, "Ano deve ser um número.");
        } catch (ErroLivroException e) {
            tratarErro(req, resp, e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao cadastrar livro.", e);
        }
    }

    private void tratarErro(HttpServletRequest req, HttpServletResponse resp, String mensagem)
            throws ServletException, IOException {
        req.setAttribute("mensagemErro", mensagem);
        req.setAttribute("htmlLivros", GerarHtmlLivro.gerar(req, acervo));
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }
}



