package com.vrv.im.fsearch.impl;
/**
 *@author chengnl
 *@date 2015年4月1日 上午11:03:30
 *@version 1.0
 *@Description:查询工厂类
 */
public class VRVSearcherFactory {
	 private static VRVSearcher vrvSearcher;
	 private static VRVSearcher vrvSuggestSearcher;
     public static synchronized VRVSearcher getVRVSearcher(){
    	 if(vrvSearcher==null)
    		 vrvSearcher= new VRVSearcher(FSearchHelp.indexPath());
    	 return vrvSearcher;    	 
     }
     public static synchronized VRVSearcher getSuggestVRVSearcher(){
    	 if(vrvSuggestSearcher==null)
    		 vrvSuggestSearcher= new VRVSearcher(FSearchHelp.suggestPath());
    	 return vrvSuggestSearcher;    	 
     }
}
