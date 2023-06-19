package org.unibl.etf.adminapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unibl.etf.adminapp.beans.*;
import org.unibl.etf.adminapp.dto.Client;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String address = "mainpage.jsp";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        session.setAttribute("notification", "");
//        System.out.println("action " + action);

        if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            AdminBean adminBean = new AdminBean();
            boolean result = adminBean.checkUsernameAndPassword(username, password);

            if (result) {
                session.setAttribute("adminBean", adminBean);
                ClientBean clientBean = new ClientBean();
                session.setAttribute("clientBean", clientBean);
                CategoryBean categoryBean = new CategoryBean();
                session.setAttribute("categoryBean", categoryBean);
                AttributeBean attributeBean = new AttributeBean();
                session.setAttribute("attributeBean", attributeBean);
                StatisticBean statisticBean = new StatisticBean();
                session.setAttribute("statisticBean", statisticBean);

                session.setAttribute("notification", "");

                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            } else {
                address = "index.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                session.setAttribute("notification", "Neispravno korisnicko ime ili lozinka");
                dispatcher.forward(request, response);
            }

        } else {
            AdminBean a = (AdminBean) session.getAttribute("adminBean");
            if (a == null || !a.isLoggedIn()) {
                response.sendRedirect("index.jsp");
            } else {
                if ("statistics".equals(action)) {
                    address = "mainpage.jsp";
                    response.sendRedirect(address);
                } else if ("categories".equals(action)) {
                    address = "categories.jsp";
                    response.sendRedirect(address);
                } else if ("appusers".equals(action)) {
                    address = "appusers.jsp";
                    response.sendRedirect(address);
                } else if ("add-client".equals((action))) {
                    String name = request.getParameter("client-name");
                    String surname = request.getParameter("client-surname");
                    String username = request.getParameter("client-username");
                    String password = request.getParameter("client-password");
                    String city = request.getParameter("client-city");
                    String email = request.getParameter("client-email");
                    Client client = new Client(name, surname, username, password, city, email, false);

                    ClientBean clientBean = new ClientBean();
                    if (clientBean.getClientWithUsername(username) == null) {
                        session.setAttribute("clientBean", clientBean);

                        boolean result = clientBean.createNewClient(client);
                        if (!result) {
                            session.setAttribute("notification", "Dodavanje klijenta je neuspjesno");
                        } else {
                            session.setAttribute("notification", "");
                        }
                    } else {
                        session.setAttribute("notification", "Klijent sa tim korisnickim imenom vec postoji.");
                    }

                    address = "appusers.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    session.setAttribute("notification", "");

                } else if ("update-client".equals(action)) {
                    String username = request.getParameter("client-username");
                    session.setAttribute("updateClientUsername", username);
                    address = "updateclient.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);

                } else if ("update-client-confirm".equals(action)) {
                    String name = request.getParameter("client-name");
                    String surname = request.getParameter("client-surname");
                    String username = request.getParameter("client-username");
                    String password = request.getParameter("client-password");
                    String city = request.getParameter("client-city");
                    String email = request.getParameter("client-email");
                    Client client = new Client(name, surname, username, password, city, email);

                    ClientBean clientBean = new ClientBean();
                    session.setAttribute("clientBean", clientBean);

                    boolean result = clientBean.updateClient(client);
                    if (!result) {
                        session.setAttribute("notification", "Azuriranje klijenta je neuspjesno");
                    } else {
                        session.setAttribute("notification", "");
                    }

                    address = "appusers.jsp";
                    //response.sendRedirect(address);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    session.setAttribute("notification", "");

                } else if ("delete-client".equals(action)) {
                    String username = request.getParameter("client-username");
                    session.setAttribute("deleteClientUsername", username);

                    address = "deleteclient.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);

                } else if ("delete-client-confirm".equals(action)) {
                    String username = request.getParameter("client-username");
                    ClientBean clientBean = new ClientBean();

                    boolean result = clientBean.deleteClient(username);
                    if (!result) {
                        session.setAttribute("notification", "Brisanje klijenta je neuspjesno");
                    } else {
                        session.setAttribute("notification", "");
                    }

                    address = "appusers.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    //response.sendRedirect(address);
                    session.setAttribute("notification", "");

                } else if ("add-category".equals(action)) {
                    String name = request.getParameter("category-name");
                    CategoryBean categoryBean = new CategoryBean();
                    session.setAttribute("categoryBean", categoryBean);

                    if (categoryBean.findIfCategoryNameIsFree(name)) {
                        boolean result = categoryBean.addNewCategory(name);
                        if (!result) {
                            session.setAttribute("notification", "Dodavanje kategorije je neuspjesno");
                        } else {
                            session.setAttribute("notification", "");
                        }
                    } else {
                        session.setAttribute("notification", "Kategorija sa tim imenom vec postoji");
                    }

                    AttributeBean attributeBean = new AttributeBean();
                    session.setAttribute("attributeBean", attributeBean);

                    address = "categories.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    session.setAttribute("notification", "");

                } else if ("update-category".equals(action)) {
                    String oldName = request.getParameter("category-name");
                    session.setAttribute("old-category-name", oldName);
                    address = "updatecategory.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);

                } else if ("update-category-confirm".equals(action)) {
                    String newName = request.getParameter("category-name-update");
                    String oldName = request.getParameter("category-name");

                    CategoryBean categoryBean = new CategoryBean();
                    if (categoryBean.findIfCategoryNameIsFree(newName)) {
                        boolean result = categoryBean.updateCategory(newName, oldName);
                        if (!result) {
                            session.setAttribute("notification", "Azuriranje kategorije je neuspjesno");
                        } else {
                            session.setAttribute("notification", "");
                        }

                        session.setAttribute("old-category-name", newName);
                    } else {
                        session.setAttribute("notification", "Kategorija sa tim imenom vec postoji");
                    }

                    address = "updatecategory.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    session.setAttribute("notification", "");

                } else if ("delete-category".equals(action)) {
                    String name = request.getParameter("category-name");
                    session.setAttribute("deleteCategoryName", name);

                    address = "deletecategory.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);

                } else if ("delete-category-confirm".equals(action)) {
                    String name = request.getParameter("category-name");

                    AttributeBean attributeBean = new AttributeBean();
                    attributeBean.deleteAttributeWithSpecificCategoryName(name);

                    CategoryBean categoryBean = new CategoryBean();
                    boolean result = categoryBean.deleteCategory(name);
                    if (!result) {
                        session.setAttribute("notification", "Brisanje kategorije je neuspjesno");
                    } else {
                        session.setAttribute("notification", "");
                    }

                    address = "categories.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    //response.sendRedirect(address);
                    session.setAttribute("notification", "");

                } else if ("add-attribute".equals(action)) {
                    String categoryName = request.getParameter("category-name");
                    request.setAttribute("addAttributeCategoryName", categoryName);

                    address = "addattribute.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);

                } else if ("add-attribute-confirm".equals(action)) {
                    String categoryName = request.getParameter("category-name");
                    String attributeName = request.getParameter("attribute-name");

                    String function = request.getParameter("function");
                    if (attributeName != null) {
                        AttributeBean attributeBean = new AttributeBean();
                        if (attributeBean.findIfAttributeNameIsFree(attributeName)) {
                            boolean result = attributeBean.addNewAttribute(attributeName, categoryName);
                            if (!result) {
                                session.setAttribute("notification", "Dodavanje atributa je neuspjesno");
                            } else {
                                session.setAttribute("notification", "");
                            }

                        } else {
                            session.setAttribute("notification", "Atribut sa tim nazivom vec postoji");
                        }
                        if ("update".equals(function)) {
                            address = "updatecategory.jsp";
                        } else {
                            request.setAttribute("addAttributeCategoryName", categoryName);
                            address = "addattribute.jsp";
                        }

                        request.setAttribute("attribute-name", null);
                        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                        dispatcher.forward(request, response);
                        session.setAttribute("notification", "");
                    }
                } else if ("update-attribute".equals(action)) {
                    String newName = request.getParameter("attribute-name-update");
                    String oldName = request.getParameter("attribute-name");

                    AttributeBean attributeBean = new AttributeBean();
                    if (attributeBean.findIfAttributeNameIsFree(newName)) {
                        boolean result = attributeBean.updateAttribute(newName, oldName);
                        if (!result) {
                            session.setAttribute("notification", "Azuriranje atributa je neuspjesno");
                        } else {
                            session.setAttribute("notification", "");
                        }
                    } else {
                        session.setAttribute("notification", "Atribut sa tim nazivom vec postoji");
                    }

                    address = "updatecategory.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    session.setAttribute("notification", "");

                } else if ("delete-attribute".equals(action)) {
                    String name = request.getParameter("attribute-name");

                    AttributeBean attributeBean = new AttributeBean();
                    boolean result = attributeBean.deleteAttributeWithSpecificName(name);
                    if (!result) {
                        session.setAttribute("notification", "Brisanje atributa je neuspjesno");
                    } else {
                        session.setAttribute("notification", "");
                    }

                    address = "updatecategory.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                    session.setAttribute("notification", "");

                } else if ("logout".equals(action)) {
                    session.invalidate();
                    address = "index.jsp";
                    response.sendRedirect(address);

                } else {
                    address = "404.jsp";
                    response.sendRedirect(address);
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
    }

}