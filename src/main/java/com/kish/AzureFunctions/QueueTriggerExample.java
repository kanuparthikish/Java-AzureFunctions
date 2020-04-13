package com.kish.AzureFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BlobInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueTrigger;

public class QueueTriggerExample {
//	
	@FunctionName("QueueTriger")
	public void execute(@QueueTrigger(name = "myQueueItem", dataType = "", queueName = "httpRequestQueue", connection = "AzureWebJobsStorage") Employee message,
			 @BlobInput(
				      name = "file", 
				      dataType = "binary", connection = "AzureWebJobsStorage",
				      path = "employee/{Id}.txt")byte[] content,
	        final ExecutionContext executionContext )
	{
		 executionContext.getLogger().info("QueueTriger trigger input: " + message.getId());
		 executionContext.getLogger().info("QueueTriger trigger input: " + message.getEname());

		 executionContext.getLogger().info("The size of \"" + message.getId() + "\" is: " + content.length + " bytes");
		 executionContext.getLogger().info("The conent of file is -->"+new String(content));

	}

	
}
