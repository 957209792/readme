<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%-- 使用taglib指令引入JSTL核心标签库描述文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//basePath=http://localhost:8080/mail/
//request.setAttribute("basePath", "http://localhost:8080/mail/");

String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/app/"; 
%> 

<%-- <base href="<%=basePath %>" /> --%>
	<div class="hmtop">
		<!--顶部导航条 -->
		<div class="am-container header">
			<ul class="message-l">
				<div class="topMessage">
					<div class="menu-hd">
					
					
						<c:choose>
							<c:when test="${not empty account}">
								欢迎回来! ${account}&nbsp;&nbsp;&nbsp; 
								<!--  <a href="#" target="_top"  id="headerOver">退出</a>-->
								<a id="headerOver" class="h"/>退出</a>
							</c:when>
							<c:otherwise>
								<a href="../home/login.jsp" target="_top" class="h">亲，请登录</a> 
								<a href="../home/register.jsp" target="_top">免费注册</a> 
								<a>当前访问人次: ${counts}</a>		
							</c:otherwise>
						</c:choose>
						
						
					</div>
				</div>
			</ul>
			<ul class="message-r">
				<div class="topMessage home">
					<div class="menu-hd">
						<a href="../home/home.jsp" target="_top" class="h">商城首页</a>
					</div>
				</div>
				<div class="topMessage my-shangcheng">
					<div class="menu-hd MyShangcheng">
						<a href="../person/index.jsp" target="_top"><i
							class="am-icon-user am-icon-fw"></i>个人中心</a>
					</div>
				</div>
				<div class="topMessage mini-cart">
					<div class="menu-hd">
						<a id="mc-menu-hd" href="#" target="_top"><i
							class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong
							id="J_MiniCartNum" class="h">0</strong></a>
					</div>
				</div>
				<div class="topMessage favorite">
					<div class="menu-hd">
						<a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>
					</div>
			</ul>
		</div>

		<!--悬浮搜索框-->

		<div class="nav white">
			<!-- <div class="logo">
				<img src="../images/logo.png" />
			</div> -->
			<div class="logoBig">
				<li><img src="../images/logobig.png" /></li>
			</div>

			<div class="search-bar pr">
				<a name="index_none_header_sysc" href="#"></a>
				<form>
					<input id="searchInput" name="index_none_header_sysc" type="text"
						placeholder="搜索" autocomplete="off"> <input
						id="ai-topsearch" class="submit am-btn" value="搜索" index="1"
						type="submit">
				</form>
			</div>
		</div>

		<div class="clear"></div>
	</div>


