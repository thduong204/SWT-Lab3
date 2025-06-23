<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="duongth.registration.Bike"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
    HttpSession sessionObj = request.getSession();
    String username = (String) sessionObj.getAttribute("USERNAME");
    if (username == null) {
        username = "Guest";
    }

    List<Bike> bikeList = (List<Bike>) request.getAttribute("BIKE_LIST");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Bikes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #4a0614;
            color: white;
            padding: 15px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .header .logo {
            font-size: 20px;
            font-weight: bold;
        }
        .header .search-bar {
            display: flex;
            align-items: center;
        }
        .header input {
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 200px;
        }
        .header button {
            background: none;
            border: none;
            cursor: pointer;
            margin-left: 5px;
        }
        .header .logout-btn {
            background: black;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            text-decoration: none;
        }
        .header .welcome {
            font-weight: bold;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            text-align: center;
        }
        .bike-list {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }
        .bike-card {
            background: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 280px;
            text-align: center;
            position: relative;
        }
        .bike-card img {
            max-width: 100%;
            border-radius: 10px;
        }
        .bike-title {
            font-weight: bold;
            margin: 10px 0;
        }
        .bike-price {
            color: #4a0614;
            font-size: 18px;
            font-weight: bold;
        }
        .sale-tag {
            background: orange;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 14px;
            position: absolute;
            margin: -10px 0 0 -10px;
        }
    </style>
</head>
<body>

    <!-- Header -->
    <div class="header">
        <div class="logo">Bike Store</div>
        <div class="search-bar">
            <form action="MainController" method="GET">
                <input type="text" name="txtSearchValue" placeholder="Search...">
                <button type="submit" name="btAction" value="Search">🔍</button>
            </form>
        </div>
        <div class="header-right">
            <span class="welcome">Welcome, <%= username %>!</span>
            <a href="home.jsp" class="logout-btn">Logout</a>
        </div>
    </div>

    <!-- Main content -->
    
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
