package com.ayopro;
import java.io.File;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
public class Findthenode {


	/**
	 * Find all the node using there label 
	 * @author aayodele
	 *
	 */
	enum DatabaseLabel implements Label{employee,group,department}
	
		public static void main(String[] args) {
			
			GraphDatabaseService graphservice=new GraphDatabaseFactory().newEmbeddedDatabase(new File("/Users/aayodele/Documents/company.db"));
			System.out.println("Database started ... ");
			//Transaction tx=graphservice.beginTx();//neo4j as to be in a transaction to be able to execute it query 
			try(Transaction tx=graphservice.beginTx()){
				/*
				 * Creating a node you have to close your transactions
				 */
			/*Node newNodes=graphservice.createNode(DatabaseLabel.department);
			newNodes.setProperty("name", "Research and Development");
			newNodes.setProperty("ref","RD");*/
				
				/*
				 * deleting a node you have to close the transactions
				 */
			/*Node deletenode=graphservice.findNode(DatabaseLabel.department,"ref","RD");// find the node with property value which is RD and property key which is ref
			deletenode.delete();//delete node
*/			
		/**
		 * updating a node you have to close the transaction		
		 */
		/*Node foundNode=graphservice.findNode(DatabaseLabel.department,"ref","LG");
		//foundNode.setProperty("name", "Legal and boss");
		//System.out.println(foundNode.getProperty("name"));
*/		
		   ResourceIterator<Node>nodes=graphservice.findNodes(DatabaseLabel.department);
			while(nodes.hasNext()){
				Node node=nodes.next();
				System.out.println(node.getProperty("name")+" ref "+node.getProperty("ref"));
			}
			nodes.close();
			tx.success();
			}
			
			System.out.println("Database shutdown ...");
			graphservice.shutdown();
		}
	}

