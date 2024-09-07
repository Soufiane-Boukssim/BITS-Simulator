package com.simulator.iso.model;

import com.simulator.iso.services.AuthMsgLogService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Set;

@Data
public class DataEMV {
	private final Logger logger = LogManager.getLogger(AuthMsgLogService.class);

	HashMap<String, TagEMV> tagsMap = new HashMap<String, TagEMV>();

	public DataEMV() {
		super();
	}

	public DataEMV(String data) {
		super();
		parseData(data);
	}
	
	public void parseData(String data){
		for(int i=0; i<data.length();){
			String tag = data.substring(i,i+2);
			if(tag.equals("5F") || tag.equals("9F") || tag.substring(0+1,0+1+1).equals("F")){
				tag = data.substring(i,i+4);
				i+=2;
			}
			i+=2;
			
			String length = data.substring(i,i+2);i+=2;
			if(length.equals("81")){
				length = data.substring(i,i+2);i+=2;
			}
			else if(length.equals("82")){
						length = data.substring(i,i+4);i+=4;
			}
				
			Long len = Long.parseLong(length, 16);
			
			String value = data.substring(i,i+(len.intValue()*2));i+=(len.intValue()*2);
			addTag(tag,length,value);
			
		}
	}
	
	public void addTag(String tag, String length, String value){
		TagEMV tagEMV = new TagEMV(tag,length,value);
		logger.info("addTag() - " +"tagEMV=["+tagEMV+"]");
		tagsMap.put(tag, tagEMV);
	}
	
	public void removeTag(String tag){
		tagsMap.remove(tag);
	}
	
	public String getTagVal(String tag){
		TagEMV tagEMV = null;
		if((tagEMV = tagsMap.get(tag))==null)
			return null;
		else 
			return tagEMV.getValue();
	}
	
	public String buildData(){
		String data = "";
		Set<String> myKeys = tagsMap.keySet(); 
		for(String key: myKeys){
			TagEMV myEntry = tagsMap.get(key);
			data = data + myEntry.getTag()+myEntry.getLength()+myEntry.getValue();
        }
		
		return data;
	}
	
	@Override
	public String toString() {
		String data = "";
		Set<String> myKeys = tagsMap.keySet(); 
		for(String key: myKeys){
			TagEMV myEntry = tagsMap.get(key);
			data = data + myEntry+"\n";
        }
		
		return data;
	}

	public HashMap<String, TagEMV> getTagsMap() {
		return tagsMap;
	}

	public void setTagsMap(HashMap<String, TagEMV> tagsMap) {
		this.tagsMap = tagsMap;
	}
}
@Data
@RequiredArgsConstructor
@AllArgsConstructor
 class TagEMV{
	String tag;
	String length;
	String value;
}
