package com.calculator.control;

import com.calculator.service.CalculatorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {

    private CalculatorService service;

    @Override
    public void init() {
        service = new CalculatorService();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String expression = request.getParameter("expression");

        if (expression == null || expression.trim().isEmpty()) {

            request.setAttribute("error", "Please enter an expression.");

            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);

            return;
        }

        try {

            double result = service.calculate(expression);

            request.setAttribute("expression", expression);
            request.setAttribute("result", result);

            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);

        } catch (ArithmeticException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            request.setAttribute("error", "Invalid Expression");

            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }
    }
}