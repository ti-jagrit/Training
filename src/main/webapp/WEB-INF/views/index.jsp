<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Training Management System</title>
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/image/tms.png">

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
	text-align: center;
}

.container {
	max-width: 800px;
	margin: 50px auto;
	padding: 20px;
	background: white;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

img {
	max-width: 150px;
}

h1 {
	color: #333;
}

p {
	font-size: 18px;
	color: #666;
}
</style>
</head>
<body>

	<div class="container">
<img src="${pageContext.request.contextPath}/image/tms.png" alt="Logo">
		<h1>Welcome to Training Management System</h1>
		<p>Manage your training programs efficiently with our easy-to-use
			platform.</p>

	</div>

</body>
</html>
