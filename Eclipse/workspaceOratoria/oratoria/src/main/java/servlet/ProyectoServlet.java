package servlet;

import dao.FasesporEquipo;
import dao.JDBCDaoCruce;
import dao.JDBCDaoDebate;
import dao.JDBCDaoPuntuacion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

import java.io.IOException;
import java.util.List;

public class ProyectoServlet extends HttpServlet {
	private static final long serialVersionUID = 2051990309999713971L;
	private TemplateEngine templateEngine;
	private JavaxServletWebApplication application;

	private JDBCDaoPuntuacion daoPuntuacion;
	private JDBCDaoDebate daoDebate;
	private JDBCDaoCruce daoCruce;

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		application = JavaxServletWebApplication.buildApplication(servletContext);
		WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html;charset=UTF-8");
	    IServletWebExchange webExchange = application.buildExchange(request, response);
	    WebContext context = new WebContext(webExchange, request.getLocale());

	    String pathInfo = request.getPathInfo();

	    if (pathInfo == null || pathInfo.trim().isEmpty() || pathInfo.trim().equalsIgnoreCase("/indexE")) {
	        templateEngine.process("indexE", context, response.getWriter());
	    } else {
	        String[] partes = pathInfo.substring(1).split("/");
	        String accion = partes[0];
	        String subaccion = partes.length > 1 ? partes[1] : null;

	        switch (accion) {
	            case "PaginaBase":
	                templateEngine.process("PaginaBase", context, response.getWriter());
	                break;
	            case "RegistroPuntuaciones":
	                templateEngine.process("RegistroPuntuaciones", context, response.getWriter());
	                break;
	            case "ResultadoPorFase":
	                if ("mostrar".equalsIgnoreCase(subaccion)) {
	                    mostrarDebatesPorFase(request, response, context);
	                } else {
	                    templateEngine.process("SeleccionarFase", context, response.getWriter());
	                }
	                break;
	            case "ConsultaCruce":
	                if ("mostrar".equalsIgnoreCase(subaccion)) {
	                    mostrarCruce(request, response, context);
	                } else {
	                    templateEngine.process("ConsultaCruce", context, response.getWriter());
	                }
	                break;
	            case "ActualizarCruce":
	                templateEngine.process("ActualizarCruce", context, response.getWriter());
	                break;
	            default:
	                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta no válida: " + pathInfo);
	        }
	    }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    String path = request.getPathInfo();

	    if ("/registrarPuntuacion".equals(path)) {
	        try {
	            int idequipo1 = Integer.parseInt(request.getParameter("idequipo1"));
	            int idequipo2 = Integer.parseInt(request.getParameter("idequipo2"));
	            int idprueba = Integer.parseInt(request.getParameter("idprueba"));
	            int puntuacion1 = Integer.parseInt(request.getParameter("puntuacion1"));
	            int puntuacion2 = Integer.parseInt(request.getParameter("puntuacion2"));
	            String observacion1 = request.getParameter("observacion1");
	            String observacion2 = request.getParameter("observacion2");
	            String penalizacion1 = request.getParameter("penalizacion1");
	            String penalizacion2 = request.getParameter("penalizacion2");
	            String faseStr = request.getParameter("fase");
	            FasesporEquipo fase = FasesporEquipo.valueOf(faseStr);
	            daoPuntuacion = new JDBCDaoPuntuacion();

	            daoPuntuacion.insertaPuntuacion(idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2, fase, idprueba);

	            response.sendRedirect("indexE");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(500, "Error registrando puntuación");
	        }
	    } else if ("/ActualizarCruce".equals(path)) {
	        actualizarCruce(request, response);
	    } else {
	        response.sendError(404);
	    }
	}

	private void mostrarDebatesPorFase(HttpServletRequest request, HttpServletResponse response, WebContext context) throws IOException {
	    try {
	        String faseStr = request.getParameter("fase");
	        if (faseStr != null && !faseStr.isEmpty()) {
	            FasesporEquipo fase = FasesporEquipo.valueOf(faseStr);
	            daoDebate = new JDBCDaoDebate();
	            List<String> debates = daoDebate.listarDebates(fase);
	            context.setVariable("faseSeleccionada", faseStr);
	            context.setVariable("debates", debates);
	        } else {
	            context.setVariable("faseSeleccionada", "No seleccionada");
	            context.setVariable("debates", List.of());
	        }
	        templateEngine.process("ResultadoPorFase", context, response.getWriter());
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(500, "Error obteniendo debates por fase");
	    }
	}

	private void mostrarCruce(HttpServletRequest request, HttpServletResponse response, WebContext context) throws IOException {
	    try {
	        int idcruce = Integer.parseInt(request.getParameter("idcruce"));
	        String dato = request.getParameter("dato");

	        daoCruce = new JDBCDaoCruce();
	        List<String> resultado = daoCruce.consultaCruce(dato, idcruce);

	        context.setVariable("idcruce", idcruce);
	        context.setVariable("dato", dato);
	        if (resultado.isEmpty()) {
	            context.setVariable("resultado", "No se encontró dato.");
	        } else {
	            context.setVariable("resultado", resultado.get(0));
	        }

	        templateEngine.process("MostrarCruce", context, response.getWriter());
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(500, "Error consultando cruce");
	    }
	}

	private void actualizarCruce(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    try {
	        int idcruce = Integer.parseInt(request.getParameter("idcruce"));
	        String dato = request.getParameter("dato");
	        String nuevoDato = request.getParameter("nuevoDato");

	        daoCruce = new JDBCDaoCruce();
	        daoCruce.actualizaDatos(dato, idcruce, nuevoDato);
	        response.sendRedirect("/oratoria/oratoria/indexE");

	        IServletWebExchange webExchange = application.buildExchange(request, response);
	        WebContext context = new WebContext(webExchange, request.getLocale());
	        context.setVariable("mensaje", "Datos actualizados correctamente");
	        templateEngine.process("ActualizarCruce", context, response.getWriter());
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(500, "Error actualizando datos del cruce");
	    }
	}
}
