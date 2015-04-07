package com.vrv.im.fsearch.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 *@author chengnl
 *@date 2015年3月26日 下午3:05:52
 *@version 1.0
 *@Description:提示搜索
 */
public class VRVSuggestSearcherHandler implements VRVSearcher.SearchHandle<String>{

	public List<String> doSearch(IndexSearcher searcher,String queryStr, int showCount) throws IOException {
		List<String> list = new ArrayList<String>();
		Query query =new PrefixQuery(new Term(FSearchHelp.suggestField(),queryStr));
        System.out.println("Query = " + query);  
        TopDocs topdocs=searcher.search(query, showCount); 
        ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
        System.out.println("查询结果总数---" + topdocs.totalHits+"最大的评分--"+topdocs.getMaxScore()); 
        for(int i=0; i < scoreDocs.length; i++) { 
            int doc = scoreDocs[i].doc; 
            Document document = searcher.doc(doc);
            StringBuilder sb = new StringBuilder();	                   
            sb.append("score="+scoreDocs[i].score+"@");
            sb.append(document.get("suggest")); 
            list.add(sb.toString());
        } 
        return list;
	}
}
