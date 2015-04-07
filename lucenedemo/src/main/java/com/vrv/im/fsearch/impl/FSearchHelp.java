package com.vrv.im.fsearch.impl;

import org.apache.lucene.analysis.Analyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 *@author chengnl
 *@date 2015年3月25日 下午2:52:50
 *@version 1.0
 *@Description:全文检索帮助类
 */
public class FSearchHelp {
	 private static Analyzer analyzer = new IKAnalyzer(true);
     public static String indexPath(){
    	 return "/Users/chengnl/lucene/index";//lucene/index
     }
     public static String suggestPath(){
    	 return "/Users/chengnl/lucene/suggest/";//lucene/suggest
     }
     public static String seachField(){
    	 return "description";
     }
     public static String suggestField(){
    	 return "suggest";
     }
     
     public static Analyzer analyzer(){
    	 return analyzer;
     }        
}
