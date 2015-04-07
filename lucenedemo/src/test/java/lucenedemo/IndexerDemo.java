package lucenedemo;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;

import com.vrv.im.DemoData;
import com.vrv.im.Org;
import com.vrv.im.User;
import com.vrv.im.fsearch.impl.FSearchHelp;
import com.vrv.im.fsearch.impl.VRVIndexer;
import com.vrv.im.fsearch.impl.VRVSuggestIndexer;

/**
 *@author chengnl
 *@date 2015年3月25日 下午6:05:15
 *@version 1.0
 *@Description:TODO类描述
 */
public class IndexerDemo {
	public static void main(String[] args) throws IOException {
		//fullIndex();
		addIndex();
		//updateIndex();
		//deleteIndex();
		
	}
	
	private static void fullIndex(){
		VRVIndexer indexer=null;
		VRVSuggestIndexer searchIndexer=null;
		try {
			indexer=new VRVIndexer(OpenMode.CREATE);
			searchIndexer=new VRVSuggestIndexer(OpenMode.CREATE);
			
			 List<Org> orgs = DemoData.getOrgs();
		       List<User> users= DemoData.getUsers();
		       for(int n=0;n<100;n++){
			       for(int i=0; i < orgs.size(); i++) {  
			           Document doc=new Document();  
			           Field contentTypeField = new LongField("contentType", 1, Field.Store.YES);
			           doc.add(contentTypeField);
			           Field orgIDField = new StringField("orgID", String.valueOf(orgs.get(i).getOrgID())+n, Field.Store.YES);
			           doc.add(orgIDField);
			           Field orgNameField = new StringField("orgName", orgs.get(i).getOrgName()+n, Field.Store.YES);
			           doc.add(orgNameField);       
			           Field descriptionField = new TextField("description", orgs.get(i).getDescription()+n, Field.Store.YES);
			           doc.add(descriptionField);
			           System.out.println("add org index"+n );
			           indexer.addIndex(doc);
			           searchIndexer.updateIndex(FSearchHelp.suggestField(), String.valueOf(orgs.get(i).getOrgName()+n));
			           searchIndexer.updateIndex(FSearchHelp.suggestField(), String.valueOf(orgs.get(i).getDescription()+n));
			           
			       }  
			       for(int i=0; i < users.size(); i++) {  
			           Document doc=new Document(); 
			           Field contentTypeField = new LongField("contentType", 2, Field.Store.YES);
			           doc.add(contentTypeField);      
			           Field userIDField = new StringField("userID", String.valueOf(users.get(i).getUserID())+n, Field.Store.YES);
			           doc.add(userIDField);      
			           Field orgIDField = new StringField("orgID", String.valueOf(users.get(i).getOrgID())+n, Field.Store.YES);
			           doc.add(orgIDField);                     
			           Field userNameField = new StringField("userName", users.get(i).getUserName()+n, Field.Store.YES);
			           doc.add(userNameField);   
			           Field descriptionField = new TextField("description", users.get(i).getDescription()+n, Field.Store.YES);
			           doc.add(descriptionField);
			           indexer.addIndex(doc);
			           System.out.println("add user index"+n );
			           searchIndexer.updateIndex(FSearchHelp.suggestField(), String.valueOf(users.get(i).getUserName()+n));
			           searchIndexer.updateIndex(FSearchHelp.suggestField(), String.valueOf(users.get(i).getUserNamePY()+n));
			       }  
		       }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(indexer!=null)
				indexer.close();
			if(searchIndexer!=null)
				searchIndexer.close();
		}
	}
	
	private static void addIndex(){
		VRVIndexer indexer=null;
		VRVSuggestIndexer searchIndexer=null;
		try {
			indexer=new VRVIndexer(OpenMode.APPEND);
			searchIndexer=new VRVSuggestIndexer(OpenMode.APPEND);
			
	        Document doc=new Document();  
	        Field contentTypeField = new LongField("contentType", 2, Field.Store.YES);
	        doc.add(contentTypeField);	      
	        Field userIDField = new StringField("userID", String.valueOf(106), Field.Store.YES);
	        doc.add(userIDField); 
	        Field orgIDField = new StringField("orgID", String.valueOf(3000000), Field.Store.YES);
	        doc.add(orgIDField);       
	        Field userNameField = new StringField("userName", "钱八", Field.Store.YES);
	        doc.add(userNameField);         
	        Field descriptionField = new TextField("description", "北京西城区月坛派出所 钱八^qianba", Field.Store.YES);
	        doc.add(descriptionField);	
			indexer.addIndex(doc);
			
           searchIndexer.updateIndex(FSearchHelp.suggestField(), "钱八");
           searchIndexer.updateIndex(FSearchHelp.suggestField(), "qianba");

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(indexer!=null)
				indexer.close();
			if(searchIndexer!=null)
				searchIndexer.close();
		}			
	}
	private static void updateIndex(){
		VRVIndexer indexer=null;
		VRVSuggestIndexer searchIndexer=null;
		try {
			indexer=new VRVIndexer(OpenMode.APPEND);
			searchIndexer=new VRVSuggestIndexer(OpenMode.APPEND);
			
	        Document doc=new Document();  
	        Field contentTypeField = new LongField("contentType", 2, Field.Store.YES);
	        doc.add(contentTypeField);	      
	        Field userIDField = new StringField("userID", String.valueOf(106), Field.Store.YES);
	        doc.add(userIDField); 
	        Field orgIDField = new StringField("orgID", String.valueOf(3000000), Field.Store.YES);
	        doc.add(orgIDField);       
	        Field userNameField = new StringField("userName", "钱八", Field.Store.YES);
	        doc.add(userNameField);          
	        Field descriptionField = new TextField("description", "北京西城区地坛派出所 钱八^qianba", Field.Store.YES);
	        doc.add(descriptionField);	
			indexer.updateIndex(new Term("userID","106"), doc);
			
           searchIndexer.updateIndex(FSearchHelp.suggestField(), "钱八");
           searchIndexer.updateIndex(FSearchHelp.suggestField(), "qianba");

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(indexer!=null)
				indexer.close();
			if(searchIndexer!=null)
				searchIndexer.close();
		}			
		
	}
	private static void deleteIndex(){	
		VRVIndexer indexer=null;
		try {
			indexer=new VRVIndexer(OpenMode.APPEND);
			indexer.deleteIndex(new Term("userID","106"));

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(indexer!=null)
				indexer.close();
		}
	}
}
