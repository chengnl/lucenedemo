package com.vrv.im.fsearch.impl;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *@author chengnl
 *@date 2015年3月25日 下午5:56:58
 *@version 1.0
 *@Description：创建索引
 */
public class VRVIndexer{
	private final IndexWriter writer;
	public  VRVIndexer(OpenMode openMode) throws IOException{
       Directory dir=FSDirectory.open(new File(FSearchHelp.indexPath()));
       IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_4_10_2,FSearchHelp.analyzer());  
       iwc.setOpenMode(openMode); 
       writer=new IndexWriter(dir, iwc);
	}

	public void addIndex(Document doc) throws IOException {
       writer.addDocument(doc);
       System.out.println("add ok");  		
	}

	public void updateIndex(Term term, Document doc) throws IOException {
       writer.updateDocument(term, doc);
       System.out.println("update ok");  		
	}

	public void deleteIndex(Term term) throws IOException {
       writer.deleteDocuments(term);
       System.out.println("delete ok");  		
	}
	public void close() {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
