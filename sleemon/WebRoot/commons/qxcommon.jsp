<%@page import="com.hoperun.sys.model.UserBean,java.util.Map"%>
<%
 UserBean aUserBean = (UserBean) session.getAttribute("UserBean");
 Map qxMap=(Map)aUserBean.getQXMap();
 request.setAttribute("qxcom",qxMap);
 %>