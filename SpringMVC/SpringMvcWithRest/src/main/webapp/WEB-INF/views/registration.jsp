<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<div id="successMessage"></div>
	<script>
		/*function sendobject(){
		var username  = document.getElementById("uname").value;
		var pass = document.getElementById("pass").value;
		var add=document.getElementById("add").value;
		var prof=document.getElementById("prof").value;
		var age=document.getElementById("age").value;
		var mob=document.getElementById("mob").value;
		var userData = {"userName" : username,"password" : pass,"address":add,"profession":prof,"age":age,"mobile":mob};
		  $.ajax({
		        type: "GET",
		        url: "http://localhost:6060/SpringMVC/insertData?userName="+username+"&"+"password="+pass+"&"+"address="+add+"&"+"profession="+prof+"&"+"age="+age+"&"+"mobile="+mob,
		        contentType: "application/json; charset=utf-8",
		        dataType: "json",
		        data : JSON.stringify(userData),           
		        success : function(data, textStatus, jqXHR) {
							var locJsonObj = eval('(' + data + ')');
							alert(locJsonObj);
				}
		  });
		} */
		  
		
		function sendobject() {
			var username = document.getElementById("uname").value;
			var pass = document.getElementById("pass").value;
			var add = document.getElementById("add").value;
			var prof = document.getElementById("prof").value;
			var age = document.getElementById("age").value;
			var mob = document.getElementById("mob").value;
			var userData = {
				"userName" : username,
				"password" : pass,
				"address" : add,
				"profession" : prof,
				"age" : age,
				"mobile" : mob
			};
			$.ajax({
				type : "POST",
				url : "http://localhost:6060/SpringMVC/insertRegistrationDetails",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				data : JSON.stringify(userData),
				success : function(data) {
					alert(data);
				},
				error: function() {
			        alert('Error occured');
			    }
			});
		} 
	</script>

	<form name="user_data">
		username : <input type="text" name="userName" id="uname"><br />
		password : <input type="text" name="password" id="pass"><br />
		address : <input type="text" name=address id="add"><br />
		profession: <input type="text" name="profession" id="prof"><br />
		age : <input type="text" name="age" id="age"><br /> mobile :
		<input type="text" name="mobile" id="mob"><br /> <input
			type="button" value="Submit" onclick="sendobject()">
	</form>
</body>
</html>