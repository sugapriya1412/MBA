package org.vtop.CourseRegistration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

	private static final Logger logger = LogManager.getLogger(TransactionRoutingDataSource.class);
			
	@Override
	protected Object determineCurrentLookupKey() {

		List<Object> connectionDB = new ArrayList<Object>();
		
		connectionDB.add(DataSourceType.READ_ONLY);
		connectionDB.add(DataSourceType.READ_WRITE);
		logger.trace("Current Transcation : "+TransactionSynchronizationManager.isCurrentTransactionReadOnly());
		
		if (TransactionSynchronizationManager.isCurrentTransactionReadOnly())  
		{
			int index = (int)(Math.random() * connectionDB.size());
			logger.trace("Index : "+index);

			return connectionDB.get(index);
		}
		else
		{
			return DataSourceType.READ_WRITE;
		}
	}
		
	@Override
	public Connection getConnection() throws SQLException {
		
		Connection con = determineTargetDataSource().getConnection();
		con.setAutoCommit(false);
		
		return con;
	}
}
