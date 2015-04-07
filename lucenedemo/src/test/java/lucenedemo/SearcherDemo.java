package lucenedemo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.vrv.im.fsearch.impl.VRVSearcher;
import com.vrv.im.fsearch.impl.VRVSearcherFactory;
import com.vrv.im.fsearch.impl.VRVSearcherHandler;

/**
 *@author chengnl
 *@date 2015年3月25日 下午3:59:43
 *@version 1.0
 *@Description:TODO类描述
 */
public class SearcherDemo {
	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> list=VRVSearcherFactory.getVRVSearcher().search("黄 派",10,new VRVSearcherHandler());
		for(String str :list){
			System.out.println(str);
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		List<String> list1=VRVSearcherFactory.getVRVSearcher().search("钱八",10,new VRVSearcherHandler());
		for(String str :list1){
			System.out.println(str);
		}
		
		TimeUnit.SECONDS.sleep(10);
		
		List<String> list2=VRVSearcherFactory.getVRVSearcher().search("王五",10,new VRVSearcherHandler());
		for(String str :list2){
			System.out.println(str);
		}
		
		TimeUnit.SECONDS.sleep(10);
		
		List<String> list3=VRVSearcherFactory.getVRVSearcher().search("110",10,new VRVSearcherHandler());
		for(String str :list3){
			System.out.println(str);
		}
		TimeUnit.SECONDS.sleep(10);
		List<String> list4=VRVSearcherFactory.getVRVSearcher().search("钱八",10,new VRVSearcherHandler());
		for(String str :list4){
			System.out.println(str);
		}
		
	}
}
