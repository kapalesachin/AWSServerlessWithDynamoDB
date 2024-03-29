package com.sachin.lambda.dynamodb;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sachin.lambda.dynamodb.bean.PersonRequest;
import com.sachin.lambda.dynamodb.bean.PersonResponse;

public class SavePersonHandler implements RequestHandler<PersonRequest, PersonResponse> {

    private static DynamoDB dynamoDb;

    private static String DYNAMODB_TABLE_NAME = "Person";
    private static Regions REGION = Regions.US_EAST_1;

    public PersonResponse handleRequest(PersonRequest personRequest, Context context) {
        this.initDynamoDbClient();
        persistData(personRequest);
        PersonResponse personResponse = new PersonResponse();
        personResponse.setMessage("Saved the record without any issues & Successfully!!!");
        return personResponse;
    }
    
    
    
    
    public static void main(String[] args) {
    	
    	initDynamoDbClient();
    	
    	 PersonRequest personRequest = new PersonRequest();
         personRequest.setId(1);
         personRequest.setFirstName("Sachin");
         personRequest.setLastName("Kapale");
         personRequest.setAge(30);
         personRequest.setAddress("United States");
         System.out.println(personRequest);
	        persistData(personRequest);
	        PersonResponse personResponse = new PersonResponse();
	        personResponse.setMessage("Saved Successfully!!!");
    	
    	
    	
    }
    
    
    private static PutItemOutcome persistData(PersonRequest personRequest) throws ConditionalCheckFailedException {
        return dynamoDb.getTable(DYNAMODB_TABLE_NAME)
          .putItem(
            new PutItemSpec().withItem(new Item()
              .withNumber("id", personRequest.getId())
              .withString("firstName", personRequest.getFirstName())
              .withString("lastName", personRequest.getLastName())
              .withNumber("age", personRequest.getAge())
              .withString("address", personRequest.getAddress())));
    }

    @SuppressWarnings("deprecation")
	private static  void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
       dynamoDb = new DynamoDB(client);
    }
}
