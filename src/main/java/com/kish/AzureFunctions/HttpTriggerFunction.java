package com.kish.AzureFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.QueueOutput;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) 
            HttpRequestMessage<Optional<Employee>> request,
            final ExecutionContext context,
            @QueueOutput(name = "httpQueue", queueName = "httpRequestQueue", connection = "AzureWebJobsStorage") final OutputBinding<Employee> result) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String name = request.getQueryParameters().get("name");
         Employee emp =null;
        if(request.getBody()!=null)
        {
         emp = request.getBody().get();
        context.getLogger().info("emp id."+emp.getId());
        context.getLogger().info("emp Name."+emp.getEname());
        try
        {
        	    // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	      String url ="";
                 Connection connect = DriverManager.getConnection(url);
                 String sql="INSERT INTO dbo.data_trx(id,name,email,ssn) Values (?,?,?,?)";
                 PreparedStatement statement = connect.prepareStatement(sql);
                 statement.setInt(1, emp.getId());
                 statement.setString(2, emp.getEname());
                 statement.setString(3, emp.getEmail());
                 statement.setString(4, emp.getSsn());
                 statement.execute();
                 }
                 catch(Exception e)
                 {
                 	return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Exception "+e.getMessage()).build();
                 }
        
        
        }
        result.setValue(emp);
        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Good Day , "+emp.getId() +" "+ name).build();
        }
    }

  
    

}
