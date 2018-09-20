package com.lingnet.vocs.action.attachments;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
@Controller
public class AttachmentsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String path;
	
	String name;
	/**查看图片**/
	public String look(){
		return "show";
	}
	
	/**下载文件*/
	@SuppressWarnings("unused")
	public void fileDownload(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			path=getRequest().getSession().getServletContext().getRealPath("")
					+File.separator+path;
			// path是指欲下载的文件的路径
			File file = new File(path);
			// 以流的形式下载文件
			 if (!file.exists()){
				 return;
			 }
			 FileInputStream fis = new FileInputStream(file);
	            
	            BufferedInputStream bis = new BufferedInputStream(fis);
	            String aString = URLEncoder.encode(name, "UTF-8");
	            response.setContentType("application/x-msdownload");
	            response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(name, "UTF-8"));
	           
	            // 输出文件流
	            ServletOutputStream out;
	            out = response.getOutputStream();
	            int len = -1;
	            while ((len = bis.read()) != -1) {
	                out.write(len);
	            }
	            bis.close();
	            out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ;  
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	
}
