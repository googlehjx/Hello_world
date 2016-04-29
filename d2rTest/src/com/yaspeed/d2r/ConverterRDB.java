package com.yaspeed.d2r;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.resultset.ResultsFormat;

import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ; 

public class ConverterRDB {
	
	private static final String MappingFileName = "file/ontology.ttl";
	private static final String QueryFile = "file/qursubject.txt";
	
	public void queryBySPARQL(){ 

	      ModelD2RQ m = new ModelD2RQ(MappingFileName); 
	      
//	      String SPARQLPrefix = "PREFIX vocab: http://localhost:2020/vocab/resource/"; 
//	      String SPARQL =  SPARQLPrefix + 
//	        "SELECT  ?name ?email ?paper WHERE {" + 
//	        "    ?author vocab:Name ?name . " + 
//	        "    ?author vocab:Email ?email ." + 
//	            "    ?author vocab:Paper2Author ?paper . }"  ; 
	      String querystring = readQuery(QueryFile);
	      Query query = QueryFactory.create(querystring); 
	      ResultSet rs = QueryExecutionFactory.create(query, m).execSelect(); 
	      ResultSetFormatter.out(System.out, rs, query);
//	      ResultSetFormatter.output(System.out, rs, ResultsFormat.FMT_RS_JSON);
//	      while (rs.hasNext()) { 
//	 QuerySolution row = rs.nextSolution(); 
//	 System.out.println("name:"+row.get("name")+",email:"+row.get("email")+",paper:"+row.get("paper")); 
//	      } 
	   } 
	
	public static void main(String[] args) {
		ConverterRDB crb = new ConverterRDB();
		crb.queryBySPARQL();
	}
	
	
	private static String readQuery(String fileUrl) {
		
		String query = null;
		try {
						
			StringBuffer sb = new StringBuffer();
			BufferedReader bf = new BufferedReader(new FileReader(new File(fileUrl)));
			String line = null;
			while((line = bf.readLine()) != null) {
				sb.append(line);
			  }
			bf.close();
			query = sb.toString();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return query;
		
	}

}
