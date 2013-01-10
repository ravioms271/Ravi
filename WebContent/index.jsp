
<html>
<head>
<script type="text/javascript">
function View() {
	//alert('view');
	
	var elem = document.getElementById("filedisplay").value = "view1";
	document.getElementById("view").href = "card11.pdf";	
	//alert('view set');
	document.myform.submit();
}
function Download() {
	//alert('download');
	var elem = document.getElementById("filedisplay").value = "download1";
	document.getElementById("download").href = "card11.pdf";
	//alert('download set');
	document.myform.submit();
}
</script>
</head>
<body background="bgcircle.gif">
	<br></br>
	<form id="frm" name="myform" action="FileOperation" method="post" action="FileOperation">
		<table width="60%" border="0" cellspacing="1" cellpadding="1" align="center" class="style1">
			<tr>
				<td align="left"><b><font color="black">View and download file:</b></td>
			</tr>
			<TR>
				<TD>
					<!-- <a href="CICTesting.docx">View</a> &nbsp   --> <!--  <INPUT TYPE="HIDDEN" NAME="fileName" id="fileName" Value="CICTesting.docx">  -->
					<INPUT TYPE="HIDDEN" NAME="fileName" id="fileName" 	value="card11.pdf"> 
					<a href="javascript: View()" id="view">View</a> &nbsp; 	<a href=" javascript: Download()" id="download">Download</a> &nbsp;
					<input type="hidden" NAME="filedisplay" value="" id="filedisplay">
					<!-- <INPUT TYPE="SUBMIT" VALUE="Download"> -->
				</TD>
			</TR>

		</table>
	</form>

</body>
</html>