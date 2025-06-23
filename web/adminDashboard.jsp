<%@ page import="java.util.List" %>
<%@ page import="duongth.registration.Bike" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Bikes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #4B0000;
            color: white;
        }
        .add-bike-form {
            text-align: right;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #4B0000;
            color: white;
        }
        input, select, button {
            padding: 10px;
            margin: 5px;
        }
        button {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            opacity: 0.8;
        }
    </style>
    <script>
        function showUpdateForm(id) {
            document.getElementById('update-form-' + id).style.display = 'table-row';
        }
    </script>
</head>
<body>
    <div class="top-bar">
        <h2>Bike Management</h2>
        <div class="add-bike-form">
            <form action="AdminController" method="POST">
                <input type="text" name="name" placeholder="Bike Name" required>
                <select name="category">
                    <option value="Road">Road</option>
                    <option value="Mountain">Mountain</option>
                    <option value="Hybrid">Hybrid</option>
                    <option value="Electric">Electric</option>
                    <option value="BMX">BMX</option>
                    <option value="Folding">Folding</option>
                    <option value="Touring">Touring</option>
                    <option value="Kids">Kids</option>
                    <option value="Cruiser">Cruiser</option>
                    <option value="Fat Bike">Fat Bike</option>
                    <option value="Gravel">Gravel</option>
                    <option value="City">City</option>
                    <option value="Track">Track</option>
                </select>
                <input type="number" name="stock" placeholder="Stock" required>
                <input type="number" name="price" placeholder="Price" step="0.01" required>
                <button type="submit" name="action" value="Add">Add Bike</button>
                <a href="home.jsp" class="Home-btn" style="color: white">Home</a>
            </form>
        </div>
    </div>
    
    <div class="container">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Stock</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <% 
                List<Bike> bikeList = (List<Bike>) request.getAttribute("BIKE_LIST");
                if (bikeList != null) {
                    for (Bike bike : bikeList) {
            %>
            <tr>
                <td><%= bike.getId() %></td>
                <td><%= bike.getName() %></td>
                <td><%= bike.getCategory() %></td>
                <td><%= bike.getStock() %></td>
                <td><%= bike.getPrice() %></td>
                <td>
                    <button onclick="showUpdateForm(<%= bike.getId() %>)">Update</button>
                </td>
            </tr>
            <tr id="update-form-<%= bike.getId() %>" style="display:none;">
                <form action="AdminController" method="POST">
                    <td><%= bike.getId() %></td>
                    <td><input type="text" name="name" value="<%= bike.getName() %>"></td>
                    <td>
                        <select name="category">
                            <option value="Road" <%= bike.getCategory().equals("Road") ? "selected" : "" %>>Road</option>
                            <option value="Mountain" <%= bike.getCategory().equals("Mountain") ? "selected" : "" %>>Mountain</option>
                            <option value="Hybrid" <%= bike.getCategory().equals("Hybrid") ? "selected" : "" %>>Hybrid</option>
                            <option value="Electric" <%= bike.getCategory().equals("Electric") ? "selected" : "" %>>Electric</option>
                            <option value="BMX" <%= bike.getCategory().equals("BMX") ? "selected" : "" %>>BMX</option>
                            <option value="Folding" <%= bike.getCategory().equals("Folding") ? "selected" : "" %>>Folding</option>
                            <option value="Touring" <%= bike.getCategory().equals("Touring") ? "selected" : "" %>>Touring</option>
                            <option value="Kids" <%= bike.getCategory().equals("Kids") ? "selected" : "" %>>Kids</option>
                            <option value="Cruiser" <%= bike.getCategory().equals("Cruiser") ? "selected" : "" %>>Cruiser</option>
                            <option value="Fat Bike" <%= bike.getCategory().equals("Fat Bike") ? "selected" : "" %>>Fat Bike</option>
                            <option value="Gravel" <%= bike.getCategory().equals("Gravel") ? "selected" : "" %>>Gravel</option>
                            <option value="City" <%= bike.getCategory().equals("City") ? "selected" : "" %>>City</option>
                            <option value="Track" <%= bike.getCategory().equals("Track") ? "selected" : "" %>>Track</option>
                        </select>
                    </td>
                    <td><input type="number" name="stock" value="<%= bike.getStock() %>"></td>
                    <td><input type="number" name="price" value="<%= bike.getPrice() %>" step="0.01"></td>
                    <td>
                        <input type="hidden" name="bikeId" value="<%= bike.getId() %>">
                        <button type="submit" name="action" value="Update">Save</button>
                        <button type="submit" name="action" value="Delete">Delete</button>
                    </td>
                </form>
            </tr>
            <% 
                    }
                }
            %>
        </table>
    </div>
</body>
</html>
