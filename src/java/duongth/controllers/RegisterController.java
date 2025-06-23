/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongth.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import duongth.registration.RegistrationDAO;
import duongth.registration.RegistrationInsertError;

public class RegisterController extends HttpServlet {

    private final String LOGINPAGE = "login.html";
    private final String REGISTERPAGE = "CreateNewAccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = REGISTERPAGE;
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String fullname = request.getParameter("txtFullname");
            RegistrationInsertError errors = new RegistrationInsertError();
            boolean bError = false;
            try {
                if (username.trim().length() < 6 || username.trim().length() > 15) {
                    errors.setUsernameLengthErr("* username must be 6 - 15 chars *");
                    bError = true;
                }
                if (password.trim().length() < 6 || password.trim().length() > 20) {
                    errors.setPasswordLengthErr("* password must be 6 - 20 chars *");
                    bError = true;
                }
                if (!confirm.trim().equals(password.trim())) {
                    errors.setConfirmPasswordErr("* confirm password not matched! *");
                    bError = true;
                }
                if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                    errors.setLastnameLengthErr("* fullname must be 2 - 50 chars *");
                    bError = true;
                }
                if (bError) {
                    request.setAttribute("INSERTERRORS", errors);
                } else {
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.insertRecord(username, password, fullname, false);
                    if (result) {
                        url = LOGINPAGE;
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                
                errors.setUsernameIsExisted("* username is existed! *");
                request.setAttribute("INSERTERRORS", errors);
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
