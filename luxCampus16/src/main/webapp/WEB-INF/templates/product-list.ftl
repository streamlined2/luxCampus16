<html>
<head>
<meta charset="UTF-8">
<title>List of products</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
th, td {
	padding: 5px;
}
.error {
	color: red;
}
</style>
</head>
<body>
	<#if error??>
		<div class="error">
			<p>${error}</p>
		</div>
	</#if>
	<table>
		<caption>
			<h3>Products by name</h3>
		</caption>
		<thead>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Created</th>
			</tr>
		</thead>
		<tbody>
            <#list products as product>
				<tr>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.creationDate}</td>
					<td><a href=${rc.getContextPath()}/products/edit/${product.id}>Edit</a></td>
					<td><a href=${rc.getContextPath()}/products/delete/${product.id}>Delete</a></td>
				</tr>
            </#list>
		</tbody>
	</table>
	<br>
	<a href=${rc.getContextPath()}/products/add>Create new</a>
</body>
</html>