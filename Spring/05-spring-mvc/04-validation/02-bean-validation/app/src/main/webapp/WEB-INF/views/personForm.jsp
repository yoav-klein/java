<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person Form</title>
</head>
<body>
    <h2>Person Form</h2>
    <form action="person" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="age">Age:</label>
        <input type="text" id="age" name="age" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>