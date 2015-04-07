package com.vrv.im.fsearch.impl;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author chengnl
 * @date 2015年4月2日 下午5:45:12
 * @version 1.0
 * @Description:创建索引工具类
 */
public class VRVSuggestIndexer {
	private final IndexWriter writer;

	public VRVSuggestIndexer(OpenMode openMode) throws IOException {
		Directory dir = FSDirectory.open(new File(FSearchHelp.suggestPath()));
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_2,
				FSearchHelp.analyzer());
		iwc.setOpenMode(openMode);
		writer = new IndexWriter(dir, iwc);
	}

	public void updateIndex(String field, String text) throws IOException {
		Document doc = new Document();
		Field contentTypeField = new StringField(field, text, Field.Store.YES);
		doc.add(contentTypeField);
		Term term = new Term(field, text);
		writer.updateDocument(term, doc);
		System.out.println("update ok");
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
