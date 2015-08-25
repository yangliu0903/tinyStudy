/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.crud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.tinygroup.crud.pojo.FileInfo;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.View;
import org.tinygroup.weblayer.webcontext.parser.impl.ItemFileObject;

@Controller()
@RequestMapping(value={"/filter"})
public class UploadFileAction implements WebContextAware{

	private WebContext webContext;
	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}
	
	@RequestMapping(value={"/list.do"})
	@View(value="/filter/upload/list.page")
	public void getFileListMethod(){
		List<FileInfo> filelist = new ArrayList<FileInfo>();
		String[] names={"upload_file1","upload_file2","upload_file3","upload_file4","upload_file5"};
		for(String name:names){
			FileInfo info = parseFileInfo(name);
			if(info!=null){
			   filelist.add(info);
			}
		}
		webContext.getRequest().setAttribute("files", filelist);
	}
	
	private FileInfo parseFileInfo(String name){
		Object o = webContext.getRequest().getAttribute(name);
		if(o instanceof ItemFileObject){
			ItemFileObject item = (ItemFileObject) o;
			
			if(item.getFileItem()!=null && item.getSize()>0){
				return new FileInfo(item.getFileItem().getName(),item.getSize());
			}
			
		}
		return null;
	}
	
	@RequestMapping(value={"/checkcookie.do"})
	@View(value="/filter/cookie/check.page")
	public void checkCookieMethod(){
		
//		Cookie[] cookies=webContext.getRequest().getCookies();
//		int size=0;
//		int num=0;
//		if(cookies!=null){
//			for(Cookie cookie:cookies){
//				size += (cookie.getValue()==null?0:cookie.getValue().length());
//			}
//			num = cookies.length;
//		}
		
		String name =webContext.getRequest().getParameter("name");
		if(name == null || name.equals("")){
			name = "testCookie";
		}
		int times = 0;
		try{
			times = Integer.parseInt(webContext.getRequest().getParameter("times"));
		}catch(Exception e){
			times = 1000;
		}
		
		//cookie大小限制是名称+键值
		times = times>name.length()+1?times-(name.length()+1):0;
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<times;i++){
			sb.append("X");
		}
		Cookie cookie = new Cookie(name, sb.toString());
		webContext.getResponse().addCookie(cookie);
		webContext.getRequest().setAttribute("cookie_size", times+name.length()+1);
		webContext.getRequest().setAttribute("cookie_name", name);
	}

}