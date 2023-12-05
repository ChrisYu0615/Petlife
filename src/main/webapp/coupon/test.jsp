<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Coupon</title>
</head>
<body>

<h2>Add Coupon</h2>

<form action="coupon.do?action=insert" method="post">
    <!-- Add your form fields here -->
    Name: <input type="text" name="couponName" required><br>
    Content: <input type="text" name="couponContent" required><br>
    Conditions of Use: <input type="number" name="conditionsOfUse" required><br>
    Start Date: <input type="datetime-local" name="startDate" required><br>
    End Date: <input type="datetime-local" name="endDate" required><br>
    Discount Amount: <input type="number" name="discountAmount" required><br>

    <input type="submit" value="Add Coupon">
</form>

</body>
</html>
