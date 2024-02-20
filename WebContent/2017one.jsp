<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.IOException" %>
<%@page import="java.sql.*" %>
<%@ page import="Connection.Database " %>
<%@page import="java.io.OutputStream" %>
<%@page import="org.jfree.chart.ChartFactory" %>
<%@page import="org.jfree.chart.ChartUtilities" %>
<%@page import="org.jfree.chart.JFreeChart" %>
<%@page import="org.jfree.chart.plot.PlotOrientation" %>
<%@page import="org.jfree.data.category.DefaultCategoryDataset" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
PreparedStatement ps = null;
	ResultSet resultSet= null;
Statement st=null;
PreparedStatement ps1 = null;
ResultSet rs= null;
Statement st1=null;
String query=null;
int aethroughput=0;
int ramthroughput=0;
int data1=0;
int data2=0;
int data3=0;
int data4=0;
int data5=0;
try
{
		
		String sql="SELECT sum(Rab_Chks),sum(Ram_Chks) FROM deduplication.file_upload";
		ps1=Database.con().prepareStatement(sql);
		rs=ps1.executeQuery();

		while(rs.next())
			
	   {  
			
			   data1=Integer.valueOf(rs.getString(1));
			   data2=Integer.valueOf(rs.getString(2));

		} 
		aethroughput=data1/30;
		ramthroughput=data2/30;
}catch(Exception e)
{
	out.println("error" +e);
}
		

		 out.print("hai");
			 response.setContentType("image/png");
OutputStream outputStream = response.getOutputStream();

final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
out.print("hai");
dataset.setValue(aethroughput, "AE", "AE Algorithm");
dataset.setValue(ramthroughput, "RAM", "RAM Algorithm");



JFreeChart barChart = ChartFactory.createBarChart3D("Chunking Throughput","Dataset", "Throughput in MBps", dataset,PlotOrientation.VERTICAL, true, true, false);
int width =1000 ;
int height =500;

ChartUtilities.writeChartAsPNG(outputStream, barChart, width, height);

%>

</body>
</html>