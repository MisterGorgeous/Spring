package web;



@WebServlet("/FrontController")
public class FrontController extends HttpServlet{

        private static final String JSP = "currentJSP";

        public void init() throws ServletException {
        }


        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
        }


        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
        }


        protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /*   HttpSession session = request.getSession();
            String[] commands = request.getParameterValues("command");

            try {
                CommandFactory factory = new CommandFactory();
                factory.create(commands);
                factory.execute(request);
            } catch (CommandExeption e) {
                throw new ServletException("Command exception ", e);
            }

            request.getRequestDispatcher((String)session.getAttribute(JSP)).forward(request, response);*/
        }

        public void destroy() {
            super.destroy();
        }
    }
