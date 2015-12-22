package org.tinygroup.crud.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.springmvc.multipart.TinyMultipartFile;

@RequestMapping(value={"/fileupload"})
public class FileUploadAction {
	
	
	@RequestMapping("/upload.shtm")
	@ResponseBody
	public String upload(@RequestParam(value="file",required = false)TinyMultipartFile file,String title){
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		System.out.println(title);
		return "文件路径:"+file.toFileObject().getAbsolutePath();
	}
	
	@RequestMapping("/multiupload.shtm")
	@ResponseBody
	public String multiUpload(@RequestParam(value = "file",required = false)TinyMultipartFile[] files,String title){
		StringBuffer buffer=new StringBuffer("文件路径列表:");
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				TinyMultipartFile file=files[i];
				if(file!=null){
					System.out.println(file.getName());
					System.out.println(file.getOriginalFilename());
					System.out.println(file.getContentType());
					System.out.println(file.getSize());
					buffer.append(file.toFileObject().getAbsolutePath());
				}
				if(i<files.length){
					buffer.append(",");
				}
			}
		}
		System.out.println(title);
		return buffer.toString();
	}
	@RequestMapping("/springmultiupload.shtm")
	@ResponseBody
	public String springMultiUpload(@RequestParam("file")MultipartFile[] files,String title){
		StringBuffer buffer=new StringBuffer("上传的文件:");
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				MultipartFile file=files[i];
				if(file!=null){
					System.out.println(file.getName());
					System.out.println(file.getOriginalFilename());
					System.out.println(file.getContentType());
					System.out.println(file.getSize());
					buffer.append(file.getOriginalFilename());
				}
				if(i<files.length){
					buffer.append(",");
				}
			}
		}
		System.out.println(title);
		return buffer.toString();
	}

}
