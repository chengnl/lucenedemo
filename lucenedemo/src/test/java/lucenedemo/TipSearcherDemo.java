package lucenedemo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.vrv.im.fsearch.impl.VRVSearcherFactory;
import com.vrv.im.fsearch.impl.VRVSuggestSearcherHandler;

/**
 *@author chengnl
 *@date 2015年3月26日 下午3:38:12
 *@version 1.0
 *@Description:TODO类描述
 */
public class TipSearcherDemo {
	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> list=VRVSearcherFactory.getSuggestVRVSearcher().search("zhangsan",10,new VRVSuggestSearcherHandler());
		for(String str :list){
			System.out.println(str);
		}
		
		TimeUnit.SECONDS.sleep(10);
		
		List<String> list2=VRVSearcherFactory.getSuggestVRVSearcher().search("钱",10,new VRVSuggestSearcherHandler());
		for(String str :list2){
			System.out.println(str);
		}
		
		TimeUnit.SECONDS.sleep(10);
		
		List<String> list3=VRVSearcherFactory.getSuggestVRVSearcher().search("北京大兴区",10,new VRVSuggestSearcherHandler());
		for(String str :list3){
			System.out.println(str);
		}
		
		TimeUnit.SECONDS.sleep(10);
		
		List<String> list4=VRVSearcherFactory.getSuggestVRVSearcher().search("A110124",10,new VRVSuggestSearcherHandler());
		for(String str :list4){
			System.out.println(str);
		}
		
	}
}
