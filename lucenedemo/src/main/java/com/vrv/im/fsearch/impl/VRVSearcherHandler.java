package com.vrv.im.fsearch.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 *@author chengnl
 *@date 2015年3月25日 下午3:22:13
 *@version 1.0
 *@Description:查询实现类
 */
public class VRVSearcherHandler implements VRVSearcher.SearchHandle<String>{

	public List<String> doSearch(IndexSearcher searcher,String queryStr,int showCount) throws IOException {
		List<String> list = new ArrayList<String>();
        Query query =getQuery(queryStr);
        System.out.println("Query = " + query);  
        //排序 相关度降序，相关度一样时后索引的排前面   默认排序先索引的排前面
        //Sort  sort = new Sort(new SortField[] { SortField.FIELD_SCORE, new SortField(null, (Parser) SortField.FIELD_DOC, true) });
        TopDocs topdocs=searcher.search(query, showCount); 
        ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
        System.out.println("查询结果总数---" + topdocs.totalHits+"最大的评分--"+topdocs.getMaxScore()); 
        for(int i=0; i < scoreDocs.length; i++) { 
            int doc = scoreDocs[i].doc; 
            Document document = searcher.doc(doc);
            StringBuilder sb = new StringBuilder();
            sb.append("score="+scoreDocs[i].score+"@");
            sb.append(document.get("contentType")+"@");
            sb.append(document.get("description")); 
            list.add(sb.toString());
        } 
        return list;
	}
	/**
	 * 根据查询串获取查询对象
	 * @param queryStr
	 * @return
	 */
	private Query getQuery(String queryStr) throws IOException{
		BooleanQuery luceneQuery = new BooleanQuery();
		// 把原字符串按空格分割
		String[] keywords = queryStr.split(" ");
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			if (!keyword.equals("")) {
				// 分词前设置为与的关系
				luceneQuery.add(getKeyWordQuery(keyword), Occur.MUST);
			}
		}
		return luceneQuery;
	}
    /**
     * 查询串根据空格分割后，关键字切分查询对象
     * @param keyword
     * @return
     * @throws IOException 
     */
	private Query getKeyWordQuery(String keyword) throws IOException {
		BooleanQuery termQuery = new BooleanQuery();//关联搜索
		Analyzer analyzer = FSearchHelp.analyzer();
		StringReader reader = new StringReader(keyword);
		// 获取Lucene的TokenStream对象
		TokenStream ts = analyzer.tokenStream(FSearchHelp.seachField(), reader);
		// 获取词元文本属性
		CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
		// 重置TokenStream（重置StringReader）
		ts.reset();
		// 迭代获取分词结果
		while (ts.incrementToken()) {
			String key = term.toString();
			if (key != null && !key.equals("")) {
				Term t = new Term(FSearchHelp.seachField(), key);
				//TermQuery q = new TermQuery(t);//词条搜索
				PrefixQuery q = new PrefixQuery(t);//前缀搜索
				termQuery.add(q, Occur.SHOULD);// 设置为或的关系
			}
		}
		// 关闭TokenStream（关闭StringReader）
		ts.end();		
		ts.close();
		return termQuery;
	}

}
