/*package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*//** * Servlet implementation class LoginServlet *//*

public class FileOperation extends HttpServlet {

	//private static final String PROXY_PORT = "8080";
	//private static final String PROXY_HOST = "192.168.1.200";
	FileInputStream input = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	doPost(req, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse res) {

		ServletOutputStream out = null;
		String fileDir = "D:\\dumpfile\\";
		String fileToFind = null;
		String fileDisplay = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			out = res.getOutputStream();
			//fileToFind = request.getParameter("fileName");
			fileToFind = "card11.pdf";
			System.out.println("::fileToFind:::" +fileToFind);
			input = new FileInputStream(fileDir + fileToFind);

			// Set the output data's mime type
			// ---------------------------------------------------------------
			 res.setContentType("application/pdf"); // MIME type for pdf doc
			// res.setContentType( "application/msword" ); // MIME type for
			// MSWord doc
			// ---------------------------------------------------------------
			 
			if((res.getContentType()) == null){
				System.out.println("::Setting ContentType()) :::");
				res.setContentType("application/pdf");
			}
			
			if ((res.getContentType()) != null) {
				if ((res.getContentType()).equalsIgnoreCase("application/pdf")) {
					System.out.println(":::if::" + res.getContentType());
					// Content-disposition header - don't open in browser and
					// set the "Save As..." filename.
					res.addHeader("Content-Disposition",
							"attachment; filename=" + fileToFind);
					res.setContentLength((int) fileToFind.length());

				} else {
					System.out.println("::else:::" + res.getContentType());
				}
			} else {
				System.out.println("::res.getContentType()) is null:::");
				res.setContentType("application/pdf");
			}

			// ------------------------------------------------------------
			fileDisplay = (String) request.getParameter("filedisplay");			
			
			if((fileDisplay).equalsIgnoreCase("view1")){
			}
			else if((fileDisplay).equalsIgnoreCase("download1")){
				res.addHeader("Content-Disposition","attachment; filename=" + fileToFind);
				res.setContentLength((int) fileToFind.length());
			}else{
				System.out.println("::No File:::");
			}
			
			bis = new BufferedInputStream(input);
			bos = new BufferedOutputStream(out);
			//bos = new BufferedOutputStream(new FileOutputStream(fileDir + fileToFind));
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				
				bos.write(buff, 0, bytesRead);
			}

		} catch (FileNotFoundException fex) {
			System.out.println("FileNotFoundException:" + fex);

		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
}
*/

package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

/** * Servlet implementation class LoginServlet */

public class FileOperation extends HttpServlet {

	//private static final String PROXY_PORT = "8080";
	//private static final String PROXY_HOST = "192.168.1.200";
	FileInputStream input = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		
		String fileDir = "D:\\dumpfile\\";
		String fileToFind = null;
		String fileDisplay = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		int length=0;

		try {
			//ServletOutputStream out = null;
			ServletOutputStream op=response.getOutputStream(); 
			fileToFind = "card11.pdf";
			System.out.println("::fileToFind:::" +fileToFind);
			String fileName=fileDir+fileToFind;  
			File f=new File(fileName); 
			

			// Set the output data's mime type
			// ---------------------------------------------------------------
			 response.setContentType("application/pdf"); // MIME type for pdf doc
			// response.setContentType( "application/msword" ); // MIME type for
			// MSWord doc
			// ---------------------------------------------------------------
			 /*
			if((response.getContentType()) == null){
				System.out.println("::Setting ContentType()) :::");
				response.setContentType("application/pdf");
			}
			
			if ((response.getContentType()) != null) {
				if ((response.getContentType()).equalsIgnoreCase("application/pdf")) {
					System.out.println(":::if::" + response.getContentType());
					// Content-disposition header - don't open in browser and
					// set the "Save As..." filename.
					response.addHeader("Content-Disposition",
							"attachment; filename=" + fileToFind);
					response.setContentLength((int) fileToFind.length());

				} else {
					System.out.println("::else:::" + response.getContentType());
				}
			} else {
				System.out.println("::response.getContentType()) is null:::");
				response.setContentType("application/pdf");
			}*/

			// ------------------------------------------------------------
			fileDisplay = (String) request.getParameter("filedisplay");			
		//	System.out.println("::filedisplay:::" +fileDisplay);
		//	System.out.println("::getParameter:::" +request.getParameter("filedisplay"));
		
			
			if((fileDisplay).equalsIgnoreCase("view1")){
				System.out.println("::viewFile:::" +fileDisplay);
			}
			else if((fileDisplay).equalsIgnoreCase("download1")){
				System.out.println("::downloadFile:::" +fileDisplay);
				response.addHeader("Content-Disposition","attachment; filename=" + fileToFind);
				response.setContentLength((int) fileToFind.length());
			}else{
				System.out.println("::No File:::");
			}
			
			byte[] bbuf=new byte[2048];  
			DataInputStream in=new DataInputStream(new FileInputStream(f));  
			  
			while((in!=null)&&((length=in.read(bbuf))!=-1))  
			{  
			    op.write(bbuf,0,length);  
			}  
				
			in.close();  
			op.flush();  
			op.close();

		} catch (FileNotFoundException fex) {
			System.out.println("FileNotFoundException:" + fex);

		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		} finally {
			
			
		}

	}
}
