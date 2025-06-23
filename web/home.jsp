<%@page import="java.util.List"%>
<%@page import="duongth.registration.Bike"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bike Store</title>
    <style>
        /* Reset mặc định */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        /* Navbar */
        .navbar {
            background-color: #4a0614;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 50px;
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
            text-decoration: none;
            color: white;
        }

        .search-bar {
            display: flex;
            align-items: center;
            background: white;
            padding: 5px;
            border-radius: 5px;
        }

        .search-bar input {
            border: none;
            outline: none;
            padding: 5px;
        }

        .search-bar button {
            background: none;
            border: none;
            cursor: pointer;
            font-size: 18px;
        }

        .login-btn {
            text-decoration: none;
            background: black;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
        }

        /* Hero Section */
        .hero {
            background: url('your-image-path.jpg') no-repeat center center/cover;
            height: 300px;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            color: white;
            font-size: 32px;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
            width: 100%;
        }

        /* Product List */
        .product-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .product-card {
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            width: 250px;
            margin: 15px;
            padding: 15px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .product-card h3 {
            font-size: 18px;
            margin-bottom: 10px;
        }

        .product-card p {
            font-size: 14px;
            margin: 5px 0;
        }

        .product-card .price {
            font-size: 16px;
            font-weight: bold;
            color: #d9534f;
        }

        .product-card button {
            background: #28a745;
            color: white;
            border: none;
            padding: 8px 12px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
        }

        .product-card button:hover {
            background: #218838;
        }
    </style>
</head>
<body>

<nav class="navbar">
    <a href="#" class="logo">Bike Store</a>
    <div class="search-bar">
        <form action="MainController" method="GET">
            <input type="text" name="txtSearchValue" placeholder="Search...">
            <button type="submit" name="btAction" value="Search">🔍</button>
        </form>
    </div>
    <a href="login.html" class="login-btn">Login</a>
</nav>

<%
    String searchValue = request.getParameter("txtSearchValue");
    List<Bike> result = (List<Bike>) request.getAttribute("SEARCHRESULT");

    if (result != null && !result.isEmpty()) {
%>

<div class="product-container">
    <%
        for (Bike dto : result) {
    %>
    <div class="product-card">
        <h3><%= dto.getName()%></h3>
        <p>Category: <%= dto.getCategory()%></p>
        <p class="price">Price: $<%= dto.getPrice()%></p>
        <p>Stock: <%= dto.getStock()%> left</p>
        <button>Add to Cart</button>
    </div>
    <% } %>
</div>

<%
} else if (searchValue != null && !searchValue.trim().isEmpty()) {
%>
<h2 style="text-align:center; color:red; margin-top:20px;">No records matched your search!</h2>
<% } %>

</body>
</html>
