package com.ayopro;

import java.io.File;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.internal.GraphDatabaseAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Neo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
		GraphDatabaseService graphservice=new GraphDatabaseFactory().newEmbeddedDatabase(new File("/Users/aayodele/Documents/company.db"));
		System.out.println("Database started ... ");
		Transaction tx=graphservice.beginTx();//neo4j as to be in a transaction to be able to execute it query 
		//Result result=graphservice.execute("MATCH (d:department) RETURN d,d.name");
		Result result=graphservice.execute("MATCH ()-[r]->() RETURN r");
		while(result.hasNext()){
			Map<String, Object>row=result.next();
			//String value=(String)row.get("d.name");//we actually know it gonna return a 
			//Node value=(Node)row.get("r");
			Relationship value=(Relationship)row.get("r");
			String type=value.getType().toString();
			Node endNode=value.getEndNode();
			Node startNode=value.getOtherNode(endNode);
			System.out.println(startNode.getProperty("name")+"-"+type+"->"+endNode.getProperty("name"));
			//System.out.println(value.getProperty("name"));
			/*Node value=(Node)row.get("d");
			System.out.println(value.getProperty("name"));
			for(Relationship relationship:value.getRelationships()){
				System.out.println("This is the relationship : "+relationship.getType()+" the end node is "+relationship.getOtherNode(value).getProperty("name"));
			}*/
			}
		tx.success();
		System.out.println("Database shutdown ...");
		graphservice.shutdown();
	}
}
