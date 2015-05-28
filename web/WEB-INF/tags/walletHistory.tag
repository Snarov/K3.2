<%-- 
    Document   : walletHistory
    Created on : May 27, 2015, 8:41:07 PM
    Author     : kiskin
--%>

<%@tag import="java.text.SimpleDateFormat"%>
<%@tag import="java.text.DateFormat"%>
<%@tag import="model.DB.Operations"%>
<%@tag import="model.Model"%>
<%@tag import="java.util.List"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>


<%-- any content can be specified here e.g.: --%>
<style type="text/css">
	table {
		border-collapse: collapse;
		font-size: 2em;
	}	

	table th, table td {
		padding: .3em;
		margin: .1em;
		vertical-align: top;
		text-shadow: 0 1px 0 #fff;
	}

	thead th {
		border-right: 1px solid #fff;
	}

	th {
		text-align: left;
		font-weight: bold;
		color: #000;
		background: #f3f3f3;
		background-size: 100% 100%;
		background: -webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#cccccc));
		background: -webkit-linear-gradient(top, #ffffff, #cccccc);
		background: -moz-linear-gradient(top, #ffffff, #cccccc);
		background: -ms-linear-gradient(top, #ffffff, #cccccc);
		background: -o-linear-gradient(top, #ffffff, #cccccc);
	}

	tbody {
		display: table-row-group;
		vertical-align: middle;
		border-color: inherit;
	}

	table tr.odd th, .odd {
		background: #fff;
	}

	th, table tr.odd, table tr.even th, table tr.even {
		text-align: left;
	}

	table caption, table th, table td {
		padding: .3em;
		margin: .1em;
		vertical-align: top;
		text-shadow: 0 1px 0 #fff;
	}

	td span{
		font-size: 1.3em;
		padding-right: 30px;
	}

	table tr.even th, .even {
		background: #DFDFDF;
	}
</style>

<% List<Operations> operations = Model.getOperations((Integer) session.getAttribute("userid"));
	if (operations.size() > 0) { %>
<table>
	<thead>
		<tr>
			<th data-column="Операция">
				<span>Операция</span>
			</th>
			<th data-column="Время">
				<span>Время</span>
			</th>
			<th data-column="Cумма">
				<span>Cумма</span>
			</th>
		</tr>
	</thead>
	<tbody>
		<%
			boolean isOdd = true;
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy 'в' HH:mm:ss");
			for (Operations operation : operations) {
				out.write(String.format("<tr class=\"%s\"><td><span>%s</span></td><td><span>%s</span></td><td><span>%s $</span></td>",
						isOdd ? "odd" : "even", operation.getType(), df.format(operation.getTime()), operation.getValue()));
				isOdd = !isOdd;
			}
		} else {
		%>
	<span style="padding: 50px; text-align: center"><h1> Список операций пуст!</h1></span>
	<%
		}
	%>

</tbody>
</table>