package com.vrv.im.fsearch.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author chengnl
 * @date 2015年3月25日 下午2:42:25
 * @version 1.0
 * @param <T>
 * @Description:查询类
 */
public class VRVSearcher{
	private  int DEFAULT_COUNT = 10;
	private  SearcherManager manager;
    public VRVSearcher(String indexPath){
    	try{
    		//索引需要先建立好
	    	File indexDir = new File(indexPath);
		    Directory dir=FSDirectory.open(indexDir); 
		    if(!DirectoryReader.indexExists(dir))
		       throw new IOException("索引文件没有创建好");
	    	manager = new SearcherManager(dir, new SearcherFactory());
	    	ScheduledExecutorService executor=Executors.newScheduledThreadPool(1);
	    	//定时检查是否需要刷新seearch，如果索引更新了，这里就会自动切换到新的searcher,
	    	//根据业务索引更新时间频率，设置定时执行时间。
		    executor.scheduleWithFixedDelay(new Runnable(){
				public void run() {
					try {
						manager.maybeRefresh();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		    },1,2, TimeUnit.SECONDS);
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
	public  <T> List<T> search(String queryStr,SearchHandle<T> searchHandle) throws IOException {
		return search(queryStr, DEFAULT_COUNT,searchHandle);
	}

	public  <T> List<T> search(String queryStr, int showCount,SearchHandle<T> searchHandle)
			throws IOException {
		List<T> list = new ArrayList<T>();
		IndexSearcher searcher = manager.acquire();
		 try {
			 long startTime= System.currentTimeMillis();
			 list=searchHandle.doSearch(searcher,queryStr,showCount);
			 long endTime= System.currentTimeMillis();	        
		     System.out.println("searcher cost time = "+(endTime-startTime));	   
		 } finally {
		   manager.release(searcher);
		 }
        return list;
	}
   
   public interface SearchHandle<T>{
	   public List<T> doSearch(IndexSearcher searcher,String queryStr,int showCount) throws IOException;
   }

}
