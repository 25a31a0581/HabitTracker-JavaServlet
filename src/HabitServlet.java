import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Maps requests sent to /habit directly to this Servlet
@WebServlet("/habit")
public class HabitServlet extends HttpServlet {

    // In-memory data store using ArrayList
    // Static so data persists across multiple HTTP requests while Tomcat runs
    private static List<Habit> habitList = new ArrayList<>();
    private static int idCounter = 1;

    // Pre-populate with sample data if list is empty
    static {
        habitList.add(new Habit(idCounter++, "Study Java"));
        habitList.add(new Habit(idCounter++, "Exercise"));
        habitList.add(new Habit(idCounter++, "Read Book"));
        habitList.get(0).setCompleted(true);
        habitList.get(2).setCompleted(true);
    }

    // Handles HTTP GET requests (When loading or refreshing the page)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        renderPage(request, response);
    }

    // Handles HTTP POST requests (When adding, completing, or deleting a habit)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if (action == null || action.equals("add")) {
            // Add new habit
            String habitName = request.getParameter("habitName");
            if (habitName != null && !habitName.trim().isEmpty()) {
                habitList.add(new Habit(idCounter++, habitName.trim()));
            }
        } else if (action.equals("toggle")) {
            // Toggle complete / incomplete status
            int id = Integer.parseInt(request.getParameter("id"));
            for (Habit h : habitList) {
                if (h.getId() == id) {
                    h.setCompleted(!h.isCompleted());
                    break;
                }
            }
        } else if (action.equals("delete")) {
            // Delete habit by ID
            int id = Integer.parseInt(request.getParameter("id"));
            habitList.removeIf(h -> h.getId() == id);
        }

        // Render updated page after action
        renderPage(request, response);
    }

    // Helper method to dynamically generate HTML and write to response
    private void renderPage(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int completedCount = 0;
        for (Habit h : habitList) {
            if (h.isCompleted()) {
                completedCount++;
            }
        }
        int totalCount = habitList.size();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Habit Tracker</title>");
        out.println("    <link rel='stylesheet' href='style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>Habit Tracker</h1>");
        
        // Add Habit Form
        out.println("        <div class='card'>");
        out.println("            <h3>Add New Habit:</h3>");
        out.println("            <form action='habit' method='POST'>");
        out.println("                <input type='hidden' name='action' value='add'>");
        out.println("                <input type='text' name='habitName' placeholder='e.g. Study Java' required>");
        out.println("                <button type='submit'>Add</button>");
        out.println("            </form>");
        out.println("        </div>");

        // Habit List View
        out.println("        <div class='card'>");
        out.println("            <h3>My Habits:</h3>");
        out.println("            <ul class='habit-list'>");

        for (Habit h : habitList) {
            String completedClass = h.isCompleted() ? "completed" : "";
            String checkbox = h.isCompleted() ? "☑" : "☐";

            out.println("                <li class='" + completedClass + "'>");
            
            // Form to toggle status on click
            out.println("                    <form action='habit' method='POST' style='display:inline;'>");
            out.println("                        <input type='hidden' name='action' value='toggle'>");
            out.println("                        <input type='hidden' name='id' value='" + h.getId() + "'>");
            out.println("                        <button type='submit' style='background:none;border:none;color:inherit;font:inherit;cursor:pointer;'>");
            out.println("                            " + checkbox + " " + h.getName());
            out.println("                        </button>");
            out.println("                    </form>");

            // Form to delete habit
            out.println("                    <form action='habit' method='POST' style='display:inline;'>");
            out.println("                        <input type='hidden' name='action' value='delete'>");
            out.println("                        <input type='hidden' name='id' value='" + h.getId() + "'>");
            out.println("                        <button type='submit' class='btn-delete'>Delete</button>");
            out.println("                    </form>");

            out.println("                </li>");
        }

        out.println("            </ul>");
        
        // Progress display
        out.println("            <div class='progress'>");
        out.println("                <strong>Progress:</strong> " + completedCount + " / " + totalCount + " completed");
        out.println("            </div>");

        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}