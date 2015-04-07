package com.vrv.im;

import java.util.ArrayList;
import java.util.List;

/**
 *@author chengnl
 *@date 2015年3月24日 下午6:39:11
 *@version 1.0
 *@Description:TODO类描述
 */
public class DemoData {
    public static List<Org> getOrgs(){
    	List<Org> list = new ArrayList<Org>();
    	int[] orgIDs= {201,202,203,204,205};
    	String[] orgNames={"北京","大兴区","武汉","黑龙江","黄村派出所"};
    	String[] description={"北京","北京大兴区","湖北武汉","黑龙江","北京大兴区黄村派出所"};//组织全名
    	for(int i=0;i<5;i++){
    		Org  org = new Org(orgIDs[i],orgNames[i],description[i]);
    		list.add(org);
    	}   	
    	return list;
    }
    
    public static List<User> getUsers(){
    	List<User> list = new ArrayList<User>();
    	int[] userIDs= {101,102,103,104,105};
    	int[] orgIDs= {201,202,203,204,205};
    	String[] userNames={"张三","李四","王五","赵六","孙七"};
    	String[] userNamePYs={"zhangsan","lisi","wangwu","zhaoliu","sunqi"};//名字拼音
    	String[] description={"北京大兴区黄村派出所 张三^zhangsan",
    			"北京西城区月坛派出所 李四^lisi",
    			"湖北武汉江岸区车站路派出所 王五^wangwu",
    			"湖北武汉江汉区大桥派出所 赵六^zhaoliu",
    			"北京西城区月坛派出所 孙七^sunqi"};//人员描述最全信息
    	for (int i = 0; i < userIDs.length; i++) {
			User user = new User(userIDs[i],orgIDs[i],userNames[i],
					userNamePYs[i],description[i]);
			list.add(user);
		}    	
		return list;
    	
    }
} 
