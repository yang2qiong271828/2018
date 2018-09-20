package com.lingnet.vocs.action.reported;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.vocs.entity.ClewFile;
import com.lingnet.vocs.service.reported.ClewFileService;

/**
 * 证据上传
 * @ClassName: ClewFileAction 
 * @Description: TODO 
 * @author lizk
 * @date 2017年1月19日 上午9:45:22 
 *
 */
@Controller
public class ClewFileAction extends BaseAction {

    private static final long serialVersionUID = 8172856832743447951L;

    @Resource(name = "clewFileService")
    private ClewFileService clewFileService;
   /* @Resource(name = "clewService")
    private ClewService clewService;
    @Resource(name = "dataDictionaryService")
    private DataDictionaryService dataDictionaryService;*/
    private ClewFile clewFile;// 企业文件信息对象

    private String formdata;// 页面传递的form信息
    private String firstCombo;// 级联select回传信息s
    private String secondCombo;// 级联select回传信息

    private File uploadFile;// 上传文件
    private List<File> uploadFileList;//文件列表 
    private String uploadFileListFileName;//文件名列表 
    private String uploadFileFileName;// 上传文件名称
    private String msg;// 上传文件回传信息
    private String flag;//数据字典类型
    private String threadId;
    private Integer state;

    
    public String xstp(){
        clewFile = this.getBeanByCriteria(ClewFile.class, Restrictions.eq("id", id));
        return "xstp";
    }
    
    
    /**
     * 跳转文件上传页面
     * @Title: listFile 
     * @return 
     * String 
     * @author lizk
     * @since 2017年2月9日 V 1.0
     */
    public String listFile() {
        return "file";
    }
    
    /**
     * 跳转被反映人列表
     * @Title: select 
     * @return 
     * String 
     * @author lizk
     * @since 2017年2月9日 V 1.0
     */
    public String select(){
        return "select";
    }
    
    /**
     * 文件上传
     * @Title: add 
     * @return 
     * String 
     * @author lizk
     * @since 2017年2月14日 V 1.0
     */
    public String add(){
        return "add";
    }
    
    public String show(){
        return "show";
    }
    
  
    
    
  /*  //查询被反映人
    @SuppressWarnings("rawtypes")
    public String search() {
        HashMap<String, String> searchMap = new HashMap<String, String>();
        HashMap result = dataDictionaryService.search(pager, searchMap,threadId);
        String json = JsonUtil.Encode(result);
        
        return ajax(Status.success, json);
    } */
    
    
    private void deleteSl(String id) {
        clewFileService.delete(id);
	}


	public String listFileData() {
        String json = clewFileService.listData(pager, threadId);
        return ajax(Status.success, json);
    }

    public String upload() {
        Properties properties = new Properties();
        // 根据企业用户id创建文件夹
        String fileUrl = properties.getProperty("filepath.properties", "upload.url");
        String basePath = this.getRequest().getSession().getServletContext()
                .getRealPath(fileUrl);
        String filePath = basePath + "\\" + threadId;
        
        UUID ufile = UUID.randomUUID();//生成随机文件名
        String newFileName = ufile.toString() + uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
        

        try {
            // 判断文件夹是否存在，不存在则创建
            File dirTest = new File(filePath);
            if (!dirTest.exists()) {
                dirTest.mkdirs();
            }
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(uploadFile);
                File saveFile = new File(filePath, newFileName);
                out = new FileOutputStream(saveFile);
                byte[] buffer = new byte[1024 * 1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                in.close();
                out.close();
            }

           /* ClewFile cfTemp = this.getBeanByCriteria(
                    ClewFile.class,
                    eq("filename", uploadFileFileName));
            if (cfTemp != null) {
                cfTemp.copyFrom(clewFile);
                String url = fileUrl + "/";
                String filepath = url + threadId + "/" + newFileName;
                cfTemp.setFilepath(filepath);
                 if(state!=null&&state==3){
                    cfTemp.setJdstatus("审理");
                }else if(state!=null&&state==1){
                	cfTemp.setJdstatus("处置");
                }else if(state!=null&&state==2){
                	cfTemp.setJdstatus("移交");
                }else if(state!=null&&state==4){
                	cfTemp.setJdstatus("办结");
                }else if(state!=null&&state==0){
                    cfTemp.setJdstatus("基础");
                }
                clewFileService.update(cfTemp);
            } else {*/
                clewFile.setFilename(uploadFileFileName);
               
                String url = fileUrl + "/";
                String filepath = url + threadId + "/" + newFileName;
//                String filepath = filePath + "/" + newFileName;
                clewFile.setFilepath(filepath);
                 if(state!=null&&state==3){
                    clewFile.setJdstatus("跟进");
                 }else if(state!=null&&state==1){
                	 clewFile.setJdstatus("备案");
                 }else if(state!=null&&state==2){
                	 clewFile.setJdstatus("合作商");
                 }else if(state!=null&&state==4){
                	 clewFile.setJdstatus("视频");
                 }else if(state!=null&&state==0){
                     clewFile.setJdstatus("基础");
                 }
                
                clewFileService.save(clewFile);

//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        msg = "上传成功！";
        this.getRequest().getSession().setAttribute("message", "success");
        return "add";
       
    }
    
    public void download() {
        HttpServletResponse response = getResponse();
        FileInputStream in = null;
        OutputStream out = null;
        clewFile = clewFileService.get(ClewFile.class,Restrictions.eq("id", id));
        try {
            // 设置响应头以及类型，控制浏览器下载该文件
            // response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(clewFile.getFilename(), "UTF-8"));
            String basePath = this.getRequest().getSession()
                    .getServletContext().getRealPath("/");
            File file = new File(basePath + clewFile.getFilepath());
            // 输入文件流
            in = new FileInputStream(file);
            // 输出文件流
            out = response.getOutputStream();
            // 创建缓冲区，进行写文件
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //文件删除
    public String deleteFile(){
        if(ids != null){
            ids = ids[0].split(",");
            for(String id : ids){
                try {
                    clewFileService.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ajax(Status.success, "error");
                }
            }
            
        }
        return ajax(Status.success, "success");
    }
    
  //文件删除
    public String deleteFilePic(){
        if(ids != null){
            ids = ids[0].split(",");
            for(String id : ids){
                try {
                	String findById = clewFileService.findById(id);
                    clewFileService.delete(findById);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ajax(Status.success, "error");
                }
            }
            
        }
        return ajax(Status.success, "success");
    }
    
    
    
    


    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }


    public String getFirstCombo() {
        return firstCombo;
    }

    public void setFirstCombo(String firstCombo) {
        this.firstCombo = firstCombo;
    }

    public String getSecondCombo() {
        return secondCombo;
    }

    public void setSecondCombo(String secondCombo) {
        this.secondCombo = secondCombo;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public List<File> getUploadFileList() {
        return uploadFileList;
    }

    public void setUploadFileList(List<File> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    public String getUploadFileListFileName() {
        return uploadFileListFileName;
    }

    public void setUploadFileListFileName(String uploadFileListFileName) {
        this.uploadFileListFileName = uploadFileListFileName;
    }


    public ClewFile getClewFile() {
        return clewFile;
    }

    public void setClewFile(ClewFile clewFile) {
        this.clewFile = clewFile;
    }
    public String getThreadId() {
        return threadId;
    }
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    

}
