package com.lepus.hikari.acgn.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lepus.hikari.acgn.bean.Video;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-11
 */
@Service
public class VideoService {

	public Map<String, Video> searchVideo(String[] scanFolders){
		Map<String, Video> map = new HashMap<String, Video>();
		if(scanFolders != null && scanFolders.length != 0){
			for(String scanFolder : scanFolders){
				File root = new File(scanFolder);
				if(root.exists()){
					File[] files = root.listFiles();
					if(files != null && files.length != 0){
						for(File file : files){
							String filename = file.getName();
							String ext = getExt(filename);
							if(".mp4".equals(ext)){
								String sign = UUID.randomUUID().toString();
								map.put(sign, new Video(sign, filename, scanFolder + File.separator + filename));
							}
						}
					}
				}
			}
		}
		return map;
	}
	
	private String getExt(String filename){
		if(filename != null && !"".equals(filename) && filename.indexOf(".") != -1){
			return filename.substring(filename.lastIndexOf("."));
		}
		return "null";
	}
	
}
