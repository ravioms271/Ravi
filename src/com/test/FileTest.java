package com.test;
//Test With GIT
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileTest {

	private static final int DEFAULT_BUFFER_SIZE = 500;

	public FileTest()
	{
		String key="";
		Map invoiceNoFiles = new HashMap();
		invoiceNoFiles.put("205724", "Test1.pdf#Test1.doc#Test2.pdf,Test2.jpg");
		invoiceNoFiles.put("205724", "Test2.pdf#Test2.doc#Test2.pdf");
		invoiceNoFiles.put("205724", "Test3.pdf#Test3.doc#Test3.pdf,Test3.jpg");
		invoiceNoFiles.put("205724", "Test4.pdf,Test4.jpg");

	}




	public void download(String contentType, int formDataLength, InputStream in)
	{
		String contentType1 = contentType;
		int formDataLength1 = formDataLength;
		DataInputStream in1=null;
		try{
			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
			{
				in1 = new DataInputStream(in);

				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				while (totalBytesRead < formDataLength) 
				{
					byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
					totalBytesRead += byteRead;
				}
				String file = new String(dataBytes);
				String saveFile = file.substring(file.indexOf("filename=\"") + 10);
				//out.print("FileName:" + saveFile.toString());
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				//out.print("FileName:" + saveFile.toString());
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
				//out.print("FileName:" + saveFile.toString());
				//out.print(dataBytes);
				int lastIndex = contentType.lastIndexOf("=");
				String boundary = contentType.substring(lastIndex + 1,contentType.length());
				//out.println(boundary);
				int pos;
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
				saveFile = "D:\\dumpfile\\" + saveFile;
				FileOutputStream fileOut = new FileOutputStream(saveFile);
				//fileOut.write(dataBytes);
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.flush();
				fileOut.close();
				System.out.println("File saved as " +saveFile);
			}

		}
		catch (IOException e) {
			System.out.println("IO Exception"+e.getMessage());
		}
		catch (Exception e) {
			System.out.println("EXception:-"+e.getMessage());
		}


	}


	/**
	 * download
	 */
	public String downloadfile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String result=null;
		// you  can get your base and parent from the database
		String fileDir="D:\\dumpfile\\";
		//String parent="e2";   
		//String filename=base+"card11.pdf";
		// you can  write http://localhost
		//String filepath="http://www.codemiles.com/example/"+base+"/";
		BufferedInputStream buf=null;
		ServletOutputStream myOut=null;

		try{

			String fileToFind = request.getParameter("fileName");
			System.out.println("File Name:-"+fileToFind);
			if(fileToFind == null) 
			{
				result ="fail";
			}



			File filename = new File(fileDir+fileToFind);

			System.out.println("Save As: "+filename.getName() );

			if(!filename.exists()) 
			{
				result ="fail";
				return result;
			}


			myOut = response.getOutputStream( );
			//File myfile = new File(filename);

			//set response headers
			//response.setContentType("text/plain");
			response.setContentType("application/pdf;charset=ISO-8859-1");
			response.addHeader(	"Content-Disposition","attachment; filename="+filename );
			response.setContentLength((int) filename.length( ) );

			FileInputStream input = new FileInputStream(filename);
			buf = new BufferedInputStream(input);
			int readBytes = 0;

			//read from the file; write to the ServletOutputStream
			while((readBytes = buf.read( )) != -1)
				myOut.write(readBytes);

		} catch (IOException ioe){
			throw new ServletException(ioe.getMessage( ));
		} finally {
			//close the input/output streams
			if (myOut != null)
				myOut.close( );
			if (buf != null)
				buf.close( );

		}

		return result;

	}

	/**
	 * download
	 */
	public void downloadfile2(HttpServletRequest request, HttpServletResponse response) throws Exception {/*

		// you  can get your base and parent from the database
		String base="D:\\dumpfile\\";
		String parent="e2";   
		String filename=base+"card11.pdf";
		// you can  write http://localhost
		String filepath="http://www.codemiles.com/example/"+base+"/";
		BufferedInputStream buf=null;
		ServletOutputStream myOut=null;

		try{

			String fileToFind = request.getParameter("file");

			if(fileToFind == null) return;

			File fname = new File(fileToFind);
			System.out.println("Save As: "+fname.getName() );
			if(!fname.exists()) return;
			FileInputStream istr = null;
			response.setContentType("application/pdf;charset=ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename="" + fname.getName() + "";");
			try {
				istr = new FileInputStream(fname);
				int curByte=-1;
				while( (curByte=istr.read()) !=-1){
					out.write(curByte);

				}
				out.flush();
			} catch(Exception ex){
				ex.printStackTrace(System.out);
			} finally{
				try {
					if(istr!=null) istr.close();
				} catch(Exception ex){
					System.out.println("Major Error Releasing Streams: "+ex.toString());
				}
			}
			try {
				response.flushBuffer();
			} catch(Exception ex){
				System.out.println("Error flushing the Response: "+ex.toString());
			} 

		} catch (IOException ioe){

			throw new ServletException(ioe.getMessage( ));

		} finally {

			//close the input/output streams
			if (myOut != null)
				myOut.close( );
			if (buf != null)
				buf.close( );

		}



	 */}


	
	/**
	 * download
	 */
	
	public String viewFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result=null;
		System.out.println("ViewFile");
		// you  can get your base and parent from the database
		String fileDir="D:\\dumpfile\\";
		//String parent="e2";   
		//String filename=base+"card11.pdf";
		// you can  write http://localhost
		//String filepath="http://www.codemiles.com/example/"+base+"/";
		BufferedInputStream buf=null;
		ServletOutputStream myOut=null;

		try{

			String fileToFind = request.getParameter("fileName");
			System.out.println("File Name:-"+fileToFind);
			if(fileToFind == null) 
			{
				result ="fail";
			}



			File filename = new File(fileDir+fileToFind);

			System.out.println("Save As: "+filename.getName() );

			if(!filename.exists()) 
			{
				result ="fail";
				return result;
			}


			myOut = response.getOutputStream( );
			//File myfile = new File(filename);

			//set response headers
			//response.setContentType("text/plain");
			//response.setContentType("application/pdf;charset=ISO-8859-1");
			//response.setContentType("application/pdf");
			//response.addHeader(	"Content-Disposition","attachment; filename="+filename );
			//response.setContentLength((int) filename.length( ) );
			
			
			//response.setContentLength(statementVO.getOutputStream().size());
			//response.setContentType("application/pdf");
			//response.setHeader("Expires", "0");
			//response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			//response.setHeader("Pragma", "public");
			
response.setHeader("Content-Type", "application/pdf"); 
response.addHeader("Content-Disposition","attachment; filename="+filename );
response.sendRedirect("/showpdf.jsp");
			//response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\""); 

			/*FileInputStream input = new FileInputStream(filename);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			//read from the file; write to the ServletOutputStream
			while((readBytes = buf.read( )) != -1)
				myOut.write(readBytes);*/

		}
		catch (IOException ioe){
			throw new ServletException(ioe.getMessage( ));
		} finally {
			//close the input/output streams
			if (myOut != null)
				myOut.close( );
			if (buf != null)
				buf.close( );

		}

		return result;

	}

	public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    // TODO Auto-generated method stub
	    try {
	    	
	    	System.out.println("Render------------");
	    	String fileDir="D:\\dumpfile\\";
	    	String fileToFind = request.getParameter("fileName");
	    	File filename = new File(fileDir+fileToFind); 
	    	
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	        int inputStreamLength = 0;
	        int length = 0;
	        while ((length = request.getInputStream().read(buffer)) > 0) {
	            inputStreamLength += length;
	            baos.write(buffer, 0, length);
	        }

	        if (inputStreamLength > request.getContentLength()) {
	            response.setContentLength(inputStreamLength);
	        }

	        if (response instanceof HttpServletResponse) {
	            HttpServletResponse httpResponse = (HttpServletResponse) response;
	            httpResponse.reset();
	            httpResponse.setHeader("Content-Type", response.getContentType());
	            httpResponse.setHeader("Content-Length", String.valueOf(request.getContentLength()));
	            //httpResponse.setHeader("Content-Disposition", "\"" + request.getContentDisposition() + "\"" + ((filename != null && ! filename.isEmpty()) ? "; filename=\"" + getFileName() + "\"": ""));
	            httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\""); 
	        }

	        response.getOutputStream().write(baos.toByteArray(), 0, (int)request.getContentLength());

	        //finally
	        response.getOutputStream().flush();

	        //clear
	        baos = null;
	    } finally {
	        // TODO Auto-generated catch block
	        close(response.getOutputStream());
	        close(request.getInputStream());
	    }
	}

	private void close(Closeable resource) throws IOException {
	    if (resource != null) {
	        resource.close();
	    }
	}

	
	

}
