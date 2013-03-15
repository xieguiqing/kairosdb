package org.kairosdb.datastore.h2.orm;

import java.util.*;
import genorm.runtime.*;

/**
	This class has been automatically generated by GenORMous.  This file
	should not be modified.
	
*/
public class DataPoint_base extends GenOrmRecord
	{
	protected static final Logger s_logger = LoggerFactory.getLogger(DataPoint.class.getName());

	public static final String COL_ID = "id";
	public static final String COL_METRIC_ID = "metric_id";
	public static final String COL_TIMESTAMP = "timestamp";
	public static final String COL_LONG_VALUE = "long_value";
	public static final String COL_DOUBLE_VALUE = "double_value";

	//Change this value to true to turn on warning messages
	private static final boolean WARNINGS = false;
	private static final String SELECT = "SELECT this.\"id\", this.\"metric_id\", this.\"timestamp\", this.\"long_value\", this.\"double_value\" ";
	private static final String FROM = "FROM data_point this ";
	private static final String WHERE = "WHERE ";
	private static final String KEY_WHERE = "WHERE \"id\" = ?";
	
	public static final String TABLE_NAME = "data_point";
	public static final int NUMBER_OF_COLUMNS = 5;
	
	
	private static final String s_fieldEscapeString = "\""; 
	
	public static final GenOrmFieldMeta ID_FIELD_META = new GenOrmFieldMeta("id", "integer", 0, true, false);
	public static final GenOrmFieldMeta METRIC_ID_FIELD_META = new GenOrmFieldMeta("metric_id", "string", 1, false, true);
	public static final GenOrmFieldMeta TIMESTAMP_FIELD_META = new GenOrmFieldMeta("timestamp", "timestamp", 2, false, false);
	public static final GenOrmFieldMeta LONG_VALUE_FIELD_META = new GenOrmFieldMeta("long_value", "long", 3, false, false);
	public static final GenOrmFieldMeta DOUBLE_VALUE_FIELD_META = new GenOrmFieldMeta("double_value", "double", 4, false, false);

	
	//===========================================================================
	public static class DataPointKeyGenerator
			implements GenOrmKeyGenerator
		{
		private static final String MAX_QUERY = "SELECT MAX(\"id\") FROM data_point";
		
		private volatile int m_nextKey;
		
		public DataPointKeyGenerator(javax.sql.DataSource ds)
			{
			m_nextKey = 0;
			java.sql.Connection con = null;
			java.sql.Statement stmnt = null;
			try
				{
				con = ds.getConnection();
				con.setAutoCommit(true);
				stmnt = con.createStatement();
				java.sql.ResultSet rs = stmnt.executeQuery(MAX_QUERY);
				if (rs.next())
					m_nextKey = rs.getInt(1);
				
				rs.close();
				}
			catch (java.sql.SQLException sqle)
				{
				//The exception may occur if the table does not yet exist
				if (WARNINGS)
					System.out.println(sqle);
				}
			finally
				{
				try
					{
					if (stmnt != null)
						stmnt.close();
						
					if (con != null)
						con.close();
					}
				catch (java.sql.SQLException sqle) {}
				}
			}
			
		/**
		This resets the key generator from the values in the database
		Usefull if the generated key has been modified via some other means
		Connection must be open before calling this
		*/
		public synchronized void reset()
			{
			m_nextKey = 0;
			java.sql.Statement stmnt = null;
			java.sql.ResultSet rs = null;
			try
				{
				stmnt = GenOrmDataSource.createStatement();
				rs = stmnt.executeQuery(MAX_QUERY);
				
				if (rs.next())
					m_nextKey = rs.getInt(1);
				}
			catch (java.sql.SQLException sqle)
				{
				//The exception may occur if the table does not yet exist
				if (WARNINGS)
					System.out.println(sqle);
				}
			finally
				{
				try
					{
					if (rs != null)
						rs.close();
						
					if (stmnt != null)
						stmnt.close();
					}
				catch (java.sql.SQLException sqle2)
					{
					throw new GenOrmException(sqle2);
					}
				}
			}
			
		public synchronized Object generateKey()
			{
			m_nextKey++;
			return (m_nextKey);
			}
		}
		
	//===========================================================================
	public static DataPointFactoryImpl factory = new DataPointFactoryImpl();
	
	public static interface DataPointFactory extends GenOrmRecordFactory
		{
		public boolean delete(int id);
		public DataPoint find(int id);
		public DataPoint findOrCreate(int id);
		/**
		*/
		public ResultSet getForMetricId(String metricId, java.sql.Timestamp startTime, java.sql.Timestamp endTime);/**
		*/
		public ResultSet getByMetric(String metricId);
		}
	
	public static class DataPointFactoryImpl //Inherit interfaces
			implements DataPointFactory 
		{
		public static final String CREATE_SQL = "CREATE CACHED TABLE data_point (\n	\"id\" INT  NOT NULL,\n	\"metric_id\" VARCHAR  NULL,\n	\"timestamp\" TIMESTAMP  NULL,\n	\"long_value\" BIGINT  NULL,\n	\"double_value\" DOUBLE  NULL,\n	PRIMARY KEY (\"id\"),\n	CONSTRAINT data_point_metric_id_fkey FOREIGN KEY (\"metric_id\")\n		REFERENCES metric (\"id\") \n	)";

		private ArrayList<GenOrmFieldMeta> m_fieldMeta;
		private ArrayList<GenOrmConstraint> m_foreignKeyConstraints;
		
		protected DataPointFactoryImpl()
			{
			m_fieldMeta = new ArrayList<GenOrmFieldMeta>();
			m_fieldMeta.add(ID_FIELD_META);
			m_fieldMeta.add(METRIC_ID_FIELD_META);
			m_fieldMeta.add(TIMESTAMP_FIELD_META);
			m_fieldMeta.add(LONG_VALUE_FIELD_META);
			m_fieldMeta.add(DOUBLE_VALUE_FIELD_META);

			m_foreignKeyConstraints = new ArrayList<GenOrmConstraint>();
			m_foreignKeyConstraints.add(new GenOrmConstraint("metric", "data_point_metric_id_fkey", "CONSTRAINT data_point_metric_id_fkey FOREIGN KEY (\"metric_id\")\n	REFERENCES metric (\"id\")"));

			}
			
		protected DataPoint newDataPoint(java.sql.ResultSet rs)
			{
			DataPoint rec = new DataPoint();
			((DataPoint_base)rec).initialize(rs);
			return ((DataPoint)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
	
		//---------------------------------------------------------------------------
		/**
			Returns a list of the feild meta for the class that this is a factory of
		*/
		public List<GenOrmFieldMeta> getFields()
			{
			return (m_fieldMeta);
			}

		//---------------------------------------------------------------------------
		/**
			Returns a list of foreign key constraints
		*/
		public List<GenOrmConstraint> getForeignKeyConstraints()
			{
			return (m_foreignKeyConstraints);
			}
			
		//---------------------------------------------------------------------------
		/**
			Returns the SQL create statement for this table
		*/
		public String getCreateStatement()
			{
			return (CREATE_SQL);
			}
			
		//---------------------------------------------------------------------------
		/**
			Creates a new entry with the specified primary keys.
		*/
		public DataPoint create(int id)
			{
			DataPoint rec = new DataPoint();
			rec.m_isNewRecord = true;
			
			((DataPoint_base)rec).setId(id);

			
			return ((DataPoint)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
		//---------------------------------------------------------------------------
		/**
			Creates a new entry that is empty
		*/
		public DataPoint createRecord()
			{
			DataPoint rec = new DataPoint();
			rec.m_isNewRecord = true;
			
			return ((DataPoint)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
			
		//---------------------------------------------------------------------------
		/**
		If the table has a primary key that has a key generator this method will 
		return a new table entry with a generated primary key.
		@return DataPoint with generated primary key
		*/
		public DataPoint createWithGeneratedKey()
			{
			DataPoint rec = new DataPoint();
			
			rec.m_isNewRecord = true;
			
			GenOrmKeyGenerator keyGen = GenOrmDataSource.getKeyGenerator("data_point");
			if (keyGen != null)
				{
				rec.setId(
						(Integer)keyGen.generateKey());
				}
			
			return ((DataPoint)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
			
		//---------------------------------------------------------------------------
		/**
		A generic api for finding a record.
		@param keys This must match the primary key for this record.  If the 
		record has multiple primary keys this parameter must be of type Object[] 
		where each element is the corresponding key.
		@return DataPoint or null if no record is found
		*/
		public DataPoint findRecord(Object keys)
			{
			return (find((Integer)keys));
			}
			
		//---------------------------------------------------------------------------
		/**
			Deletes the record with the specified primary keys.
			The point of this api is to prevent a hit on the db to see if the record
			is there.  This call will add a record to the next transaction that is 
			marked for delete. 
			
			@return Returns true if the record was previous created and existed
			either in the transaction cache or the db.
		*/
		public boolean delete(int id)
			{
			boolean ret = false;
			DataPoint rec = new DataPoint();
			
			((DataPoint_base)rec).initialize(id);
			GenOrmConnection con = GenOrmDataSource.getGenOrmConnection();
			DataPoint cachedRec = (DataPoint)con.getCachedRecord(rec.getRecordKey());
			
			if (cachedRec != null)
				{
				ret = true;
				cachedRec.delete();
				}
			else
				{
				rec = (DataPoint)con.getUniqueRecord(rec);  //This adds the record to the cache
				rec.delete();
				ret = rec.flush();
				rec.setIgnored(true); //So the system does not try to delete it again at commmit
				}
				
			return (ret);
			}
			
		//---------------------------------------------------------------------------
		/**
		Find the record with the specified primary keys
		@return DataPoint or null if no record is found
		*/
		public DataPoint find(int id)
			{
			DataPoint rec = new DataPoint();
			
			//Create temp object and look in cache for it
			((DataPoint_base)rec).initialize(id);
			rec = (DataPoint)GenOrmDataSource.getGenOrmConnection().getCachedRecord(rec.getRecordKey());
			
			java.sql.PreparedStatement genorm_statement = null;
			java.sql.ResultSet genorm_rs = null;
			
			if (rec == null)
				{
				try
					{
					//No cached object so look in db
					genorm_statement = GenOrmDataSource.prepareStatement(SELECT+FROM+KEY_WHERE);
					genorm_statement.setInt(1, id);

					s_logger.debug(genorm_statement.toString());
						
					genorm_rs = genorm_statement.executeQuery();
					if (genorm_rs.next())
						rec = newDataPoint(genorm_rs);
					}
				catch (java.sql.SQLException sqle)
					{
					throw new GenOrmException(sqle);
					}
				finally
					{
					try
						{
						if (genorm_rs != null)
							genorm_rs.close();
							
						if (genorm_statement != null)
							genorm_statement.close();
						}
					catch (java.sql.SQLException sqle2)
						{
						throw new GenOrmException(sqle2);
						}
					}
				}
				
			return (rec);
			}
		
		//---------------------------------------------------------------------------
		/**
		This is the same as find except if the record returned is null a new one 
		is created with the specified primary keys
		@return A new or existing record.  
		*/
		public DataPoint findOrCreate(int id)
			{
			DataPoint rec = find(id);
			if (rec == null)
				rec = create(id);
				
			return (rec);
			}
			
		//---------------------------------------------------------------------------
		/**
			Convenience method for selecting records.  Ideally this should not be use, 
			instead a custom query for this table should be used.
			@param where sql where statement.
		*/
		public ResultSet select(String where)
			{
			return (select(where, null));
			}
			
		//---------------------------------------------------------------------------
		/**
			Convenience method for selecting records.  Ideally this should not be use, 
			instead a custom query for this table should be used.
			@param where sql where statement.
			@param orderBy sql order by statement
		*/
		public ResultSet select(String where, String orderBy)
			{
			ResultSet rs = null;
			java.sql.Statement stmnt = null;
			
			try
				{
				stmnt = GenOrmDataSource.createStatement();
				StringBuilder sb = new StringBuilder();
				sb.append(SELECT);
				sb.append(FROM);
				if (where != null)
					{
					sb.append(WHERE);
					sb.append(where);
					}
					
				if (orderBy != null)
					{
					sb.append(" ");
					sb.append(orderBy);
					}
				
				String query = sb.toString();
				rs = new SQLResultSet(stmnt.executeQuery(query), query, stmnt);
				}
			catch (java.sql.SQLException sqle)
				{
				try
					{
					if (stmnt != null)
						stmnt.close();
					}
				catch (java.sql.SQLException sqle2) { }
					
				throw new GenOrmException(sqle);
				}
				
			return (rs);
			}
			
		//---------------------------------------------------------------------------
		/**
		*/
		public ResultSet getForMetricId(String metricId, java.sql.Timestamp startTime, java.sql.Timestamp endTime)
			{
			String query = SELECT+"from data_point this\n				where\n				this.\"metric_id\" = ?\n				and this.\"timestamp\" >= ?\n				and this.\"timestamp\" <= ?\n				order by this.\"timestamp\"";
			
			java.sql.PreparedStatement genorm_statement = null;
			
			try
				{
				genorm_statement = GenOrmDataSource.prepareStatement(query);
				genorm_statement.setString(1, metricId);genorm_statement.setTimestamp(2, startTime);genorm_statement.setTimestamp(3, endTime);
				
				s_logger.debug(genorm_statement.toString());
				
				ResultSet rs = new SQLResultSet(genorm_statement.executeQuery(), query, genorm_statement);
				
				return (rs);
				}
			catch (java.sql.SQLException sqle)
				{
				try
					{
					if (genorm_statement != null)
						genorm_statement.close();
					}
				catch (java.sql.SQLException sqle2) { }
					
				if (s_logger.isDebug())
					sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
			}
			
		//---------------------------------------------------------------------------
		/**
		*/
		public ResultSet getByMetric(String metricId)
			{
			String query = SELECT+"FROM data_point this WHERE this.\"metric_id\" = ?";
			
			java.sql.PreparedStatement genorm_statement = null;
			
			try
				{
				genorm_statement = GenOrmDataSource.prepareStatement(query);
				genorm_statement.setString(1, metricId);
				
				s_logger.debug(genorm_statement.toString());
				
				ResultSet rs = new SQLResultSet(genorm_statement.executeQuery(), query, genorm_statement);
				
				return (rs);
				}
			catch (java.sql.SQLException sqle)
				{
				try
					{
					if (genorm_statement != null)
						genorm_statement.close();
					}
				catch (java.sql.SQLException sqle2) { }
					
				if (s_logger.isDebug())
					sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
			}
			

		
		//---------------------------------------------------------------------------
		/**
			Calls all query methods with test parameters.
		*/
		public void testQueryMethods()
			{
			ResultSet rs;
			System.out.println("DataPoint.getForMetricId");
			rs = getForMetricId("foo", new java.sql.Timestamp(0L), new java.sql.Timestamp(0L));
			rs.close();

			}
		}
		
	//===========================================================================
	public static interface ResultSet extends GenOrmResultSet
		{
		public ArrayList<DataPoint> getArrayList(int maxRows);
		public ArrayList<DataPoint> getArrayList();
		public DataPoint getRecord();
		public DataPoint getOnlyRecord();
		}
		
	//===========================================================================
	private static class SQLResultSet 
			implements ResultSet
		{
		private java.sql.ResultSet m_resultSet;
		private java.sql.Statement m_statement;
		private String m_query;
		private boolean m_onFirstResult;
		
		//------------------------------------------------------------------------
		protected SQLResultSet(java.sql.ResultSet resultSet, String query, java.sql.Statement statement)
			{
			m_resultSet = resultSet;
			m_statement = statement;
			m_query = query;
			m_onFirstResult = false;
			}
		
		//------------------------------------------------------------------------
		/**
			Closes any underlying java.sql.Result set and java.sql.Statement 
			that was used to create this results set.
		*/
		public void close()
			{
			try
				{
				m_resultSet.close();
				m_statement.close();
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the reults as an ArrayList of Record objects.
			The Result set is closed within this call
			@param maxRows if the result set contains more than this param
				then an exception is thrown
		*/
		public ArrayList<DataPoint> getArrayList(int maxRows)
			{
			ArrayList<DataPoint> results = new ArrayList<DataPoint>();
			int count = 0;
			
			try
				{
				if (m_onFirstResult)
					{
					count ++;
					results.add(factory.newDataPoint(m_resultSet));
					}
					
				while (m_resultSet.next() && (count < maxRows))
					{
					count ++;
					results.add(factory.newDataPoint(m_resultSet));
					}
					
				if (m_resultSet.next())
					throw new GenOrmException("Bound of "+maxRows+" is too small for query ["+m_query+"]");
				}
			catch (java.sql.SQLException sqle)
				{
				sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
				
			close();
			return (results);
			}
		
		//------------------------------------------------------------------------
		/**
			Returns the reults as an ArrayList of Record objects.
			The Result set is closed within this call
		*/
		public ArrayList<DataPoint> getArrayList()
			{
			ArrayList<DataPoint> results = new ArrayList<DataPoint>();
			
			try
				{
				if (m_onFirstResult)
					results.add(factory.newDataPoint(m_resultSet));
					
				while (m_resultSet.next())
					results.add(factory.newDataPoint(m_resultSet));
				}
			catch (java.sql.SQLException sqle)
				{
				sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
				
			close();
			return (results);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the underlying java.sql.ResultSet object
		*/
		public java.sql.ResultSet getResultSet()
			{
			return (m_resultSet);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the current record in the result set
		*/
		public DataPoint getRecord()
			{
			return (factory.newDataPoint(m_resultSet));
			}
			
		//------------------------------------------------------------------------
		/**
			This call expects only one record in the result set.  If multiple records
			are found an excpetion is thrown.
			The ResultSet object is automatically closed by this call.
		*/
		public DataPoint getOnlyRecord()
			{
			DataPoint ret = null;
			
			try
				{
				if (m_resultSet.next())
					ret = factory.newDataPoint(m_resultSet);
					
				if (m_resultSet.next())
					throw new GenOrmException("Multiple rows returned in call from DataPoint.getOnlyRecord");
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
				
			close();
			return (ret);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns true if there is another record in the result set.
		*/
		public boolean next()
			{
			boolean ret = false;
			m_onFirstResult = true;
			try
				{
				ret = m_resultSet.next();
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
			
			return (ret);
			}
		}
		
	//===========================================================================
		
	private GenOrmInt m_id;
	private GenOrmString m_metricId;
	private GenOrmTimestamp m_timestamp;
	private GenOrmLong m_longValue;
	private GenOrmDouble m_doubleValue;

	
	private List<GenOrmRecordKey> m_foreignKeys;
	
	public List<GenOrmRecordKey> getForeignKeys() { return (m_foreignKeys); }


	//---------------------------------------------------------------------------
	/**
	*/
	public int getId() { return (m_id.getValue()); }
	public DataPoint setId(int data)
		{
		boolean changed = m_id.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(ID_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_id.setPrevValue(data);
			}
			
		return ((DataPoint)this);
		}
		

	//---------------------------------------------------------------------------
	/**
	*/
	public String getMetricId() { return (m_metricId.getValue()); }
	public DataPoint setMetricId(String data)
		{
		boolean changed = m_metricId.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(METRIC_ID_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_metricId.setPrevValue(data);
			}
			
		return ((DataPoint)this);
		}
		
	public boolean isMetricIdNull()
		{
		return (m_metricId.isNull());
		}
		
	public DataPoint setMetricIdNull()
		{
		boolean changed = m_metricId.setNull();
		
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(METRIC_ID_FIELD_META.getDirtyFlag());
			}
		
		return ((DataPoint)this);
		}

	//---------------------------------------------------------------------------
	/**
	*/
	public java.sql.Timestamp getTimestamp() { return (m_timestamp.getValue()); }
	public DataPoint setTimestamp(java.sql.Timestamp data)
		{
		boolean changed = m_timestamp.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(TIMESTAMP_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_timestamp.setPrevValue(data);
			}
			
		return ((DataPoint)this);
		}
		
	public boolean isTimestampNull()
		{
		return (m_timestamp.isNull());
		}
		
	public DataPoint setTimestampNull()
		{
		boolean changed = m_timestamp.setNull();
		
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(TIMESTAMP_FIELD_META.getDirtyFlag());
			}
		
		return ((DataPoint)this);
		}

	//---------------------------------------------------------------------------
	/**
	*/
	public long getLongValue() { return (m_longValue.getValue()); }
	public DataPoint setLongValue(long data)
		{
		boolean changed = m_longValue.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(LONG_VALUE_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_longValue.setPrevValue(data);
			}
			
		return ((DataPoint)this);
		}
		
	public boolean isLongValueNull()
		{
		return (m_longValue.isNull());
		}
		
	public DataPoint setLongValueNull()
		{
		boolean changed = m_longValue.setNull();
		
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(LONG_VALUE_FIELD_META.getDirtyFlag());
			}
		
		return ((DataPoint)this);
		}

	//---------------------------------------------------------------------------
	/**
	*/
	public double getDoubleValue() { return (m_doubleValue.getValue()); }
	public DataPoint setDoubleValue(double data)
		{
		boolean changed = m_doubleValue.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(DOUBLE_VALUE_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_doubleValue.setPrevValue(data);
			}
			
		return ((DataPoint)this);
		}
		
	public boolean isDoubleValueNull()
		{
		return (m_doubleValue.isNull());
		}
		
	public DataPoint setDoubleValueNull()
		{
		boolean changed = m_doubleValue.setNull();
		
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(DOUBLE_VALUE_FIELD_META.getDirtyFlag());
			}
		
		return ((DataPoint)this);
		}
	
	
	//---------------------------------------------------------------------------
	public Metric getMetricRef()
		{
		return (Metric.factory.find(m_metricId.getValue()));
		}
		
	//--------------------------------------------------------------------------
	public DataPoint setMetricRef(Metric table)
		{
		//We add the record to the transaction if one of the key values change
		if (m_metricId.setValue(table.getId()))
			{
			if ((m_dirtyFlags.isEmpty()) && (GenOrmDataSource.getGenOrmConnection() != null))
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
			
			m_dirtyFlags.set(METRIC_ID_FIELD_META.getDirtyFlag());
			}


			
		return ((DataPoint)this);
		}


	
	
	//---------------------------------------------------------------------------
	protected void initialize(int id)
		{
		m_id.setValue(id);
		m_id.setPrevValue(id);

		}
		
	//---------------------------------------------------------------------------
	protected void initialize(java.sql.ResultSet rs)
		{
		try
			{
			if (s_logger.isDebug())
				{
				java.sql.ResultSetMetaData meta = rs.getMetaData();
				for (int I = 1; I <= meta.getColumnCount(); I++)
					{
					s_logger.debug("Reading - "+meta.getColumnName(I) +" : "+rs.getString(I));
					}
				}
			m_id.setValue(rs, 1);
			m_metricId.setValue(rs, 2);
			m_timestamp.setValue(rs, 3);
			m_longValue.setValue(rs, 4);
			m_doubleValue.setValue(rs, 5);

			}
		catch (java.sql.SQLException sqle)
			{
			throw new GenOrmException(sqle);
			}
		}
	
	//---------------------------------------------------------------------------
	/*package*/ DataPoint_base()
		{
		super(TABLE_NAME);
		m_logger = s_logger;
		m_foreignKeys = new ArrayList<GenOrmRecordKey>();
		m_dirtyFlags = new java.util.BitSet(NUMBER_OF_COLUMNS);
		

		m_id = new GenOrmInt(ID_FIELD_META);
		addField(m_id);

		m_metricId = new GenOrmString(METRIC_ID_FIELD_META);
		addField(m_metricId);

		m_timestamp = new GenOrmTimestamp(TIMESTAMP_FIELD_META);
		addField(m_timestamp);

		m_longValue = new GenOrmLong(LONG_VALUE_FIELD_META);
		addField(m_longValue);

		m_doubleValue = new GenOrmDouble(DOUBLE_VALUE_FIELD_META);
		addField(m_doubleValue);

		GenOrmRecordKey foreignKey;
		foreignKey = new GenOrmRecordKey("metric");
		foreignKey.addKeyField("id", m_metricId);

		m_foreignKeys.add(foreignKey);

		}
	
	//---------------------------------------------------------------------------
	@Override
	public GenOrmConnection getGenOrmConnection()
		{
		return (GenOrmDataSource.getGenOrmConnection());
		}
		
	//---------------------------------------------------------------------------
	@Override
	public String getFieldEscapeString()
		{
		return (s_fieldEscapeString);
		}
		
	//---------------------------------------------------------------------------
	@Override
	public void setMTS()
		{
		}
		
	//---------------------------------------------------------------------------
	@Override
	public void setCTS()
		{
		}
		
	//---------------------------------------------------------------------------
	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		
		sb.append("id=\"");
		sb.append(m_id.getValue());
		sb.append("\" ");
		sb.append("metric_id=\"");
		sb.append(m_metricId.getValue());
		sb.append("\" ");
		sb.append("timestamp=\"");
		sb.append(m_timestamp.getValue());
		sb.append("\" ");
		sb.append("long_value=\"");
		sb.append(m_longValue.getValue());
		sb.append("\" ");
		sb.append("double_value=\"");
		sb.append(m_doubleValue.getValue());
		sb.append("\" ");

		
		return (sb.toString().trim());
		}
		
	//===========================================================================

	
	
	}
	
	