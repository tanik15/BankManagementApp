<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.model.CustomerDetails"%>
<%
List<CustomerDetails> customers = (List<CustomerDetails>) session.getAttribute("customers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Customers - Smart Bank</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container-fluid mt-5">
        <div class="card shadow-lg">
            <div class="card-header bg-primary text-white text-center">
                <h3>All Customers</h3>
            </div>
            <!-- 🔎 Filter inputs -->
            <div class="p-3 bg-light border-bottom">
                <div class="row g-3 justify-content-center">
                    <div class="col-md-3">
                        <input type="text" id="nameFilter" class="form-control"
                            placeholder="Search by Name">
                    </div>
                    <div class="col-md-3">
                        <input type="text" id="aadharFilter" class="form-control"
                            placeholder="Search by Aadhar No">
                    </div>
                    <div class="col-md-3">
                        <input type="text" id="panFilter" class="form-control"
                            placeholder="Search by PAN No">
                    </div>
                </div>
            </div>

            <div class="card-body">
                <% if (customers != null && !customers.isEmpty()) { %>
                <div class="table-responsive">
                    <table id="customerTable"
                        class="table table-bordered table-striped text-center align-middle w-100">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Full Name</th>
                                <th>Date of Birth</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Aadhar No</th>
                                <th>PAN No</th>
                                <th>Created At</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            int id = 1;
                            for (CustomerDetails c : customers) {
                            %>
                            <tr>
                                <td><%=id%></td>
                                <td><%=c.getUserName()%></td>
                                <td><%=c.getFullName()%></td>
                                <td><%=c.getDob()%></td>
                                <td><%=c.getEmail()%></td>
                                <td><%=c.getPhone()%></td>
                                <td><%=c.getAddress()%></td>
                                <td><%=c.getAadharNo()%></td>
                                <td><%=c.getPanNo()%></td>
                                <td><%=c.getCreatedAt()%></td>
                            </tr>
                            <%
                            id++;
                            }
                            %>
                        </tbody>
                    </table>
                </div>
                <% } else { %>
                <div class="alert alert-warning text-center">No customers found.</div>
                <% } %>
            </div>

            <div class="card-footer text-center">
                <a href="adminHome.jsp" class="btn btn-secondary">Back to Dashboard</a>
            </div>
        </div>
    </div>

<!-- ✅ Script moved to bottom so elements exist -->
<script>
    function filterTable() {
        let nameInput = document.getElementById("nameFilter").value.toLowerCase();
        let aadharInput = document.getElementById("aadharFilter").value.toLowerCase();
        let panInput = document.getElementById("panFilter").value.toLowerCase();

        let rows = document.querySelectorAll("#customerTable tbody tr");

        rows.forEach(row => {
            let name = row.cells[2].innerText.toLowerCase();   // Full Name column
            let aadhar = row.cells[7].innerText.toLowerCase(); // Aadhar column
            let pan = row.cells[8].innerText.toLowerCase();    // PAN column

            if (
                (name.includes(nameInput) || nameInput === "") &&
                (aadhar.includes(aadharInput) || aadharInput === "") &&
                (pan.includes(panInput) || panInput === "")
            ) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    }

    // Attach events AFTER DOM is loaded
    document.getElementById("nameFilter").addEventListener("keyup", filterTable);
    document.getElementById("aadharFilter").addEventListener("keyup", filterTable);
    document.getElementById("panFilter").addEventListener("keyup", filterTable);
</script>
</body>
</html>
