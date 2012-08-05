<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head />
<body>

	<p>
		<a href="<c:url value="/users/new/" />">New User</a>
	</p>


	<table>
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Standard</td>
			<td>Age</td>
			<td>Sex</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${usersListModel.users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.standard}</td>
				<td>${user.age}</td>
				<td>${user.sex}</td>
				<td><a href="<c:url value="/users/${user.id}/edit/" />">Edit</a></td>
				<td><a href="<c:url value="/users/${user.id}/delete/" />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
