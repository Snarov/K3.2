<%-- 
    Document   : WalletAmount
    Created on : May 27, 2015, 8:04:41 PM
    Author     : kiskin
--%>

<%@tag import="model.Model"%>
<%@tag import="model.DB.Wallets"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>


<%-- any content can be specified here e.g.: --%>
<%
	Wallets userWallet = Model.getUserWallet((Integer)session.getAttribute("userid"));
	int currentAmount = userWallet.getAmount();
	%>

	<header>
		<h2>Текущий баланс: <b><%=currentAmount%> $</b></h2>
	</header>
	
	
