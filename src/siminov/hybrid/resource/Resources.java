/** 
 * [SIMINOV FRAMEWORK]
 * Copyright [2013] [Siminov Software Solution LLP|support@siminov.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/


package siminov.hybrid.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import siminov.hybrid.adapter.AdapterHandler;
import siminov.hybrid.adapter.AdapterResources;
import siminov.hybrid.adapter.constants.HybridDatabaseDescriptor;
import siminov.hybrid.adapter.constants.HybridDatabaseMappingDescriptor;
import siminov.hybrid.adapter.constants.HybridLibraryDescriptor;
import siminov.hybrid.events.EventHandler;
import siminov.hybrid.model.AdapterDescriptor;
import siminov.hybrid.model.AdapterDescriptor.Handler;
import siminov.hybrid.model.AdapterDescriptor.Handler.Parameter;
import siminov.hybrid.model.AdapterDescriptor.Handler.Return;
import siminov.hybrid.model.HybridDescriptor;
import siminov.hybrid.model.HybridSiminovDatas.HybridSiminovData;
import siminov.hybrid.model.HybridSiminovDatas.HybridSiminovData.HybridSiminovValue;
import siminov.orm.model.DatabaseDescriptor;
import siminov.orm.model.DatabaseMappingDescriptor;
import siminov.orm.model.DatabaseMappingDescriptor.Column;
import siminov.orm.model.DatabaseMappingDescriptor.Index;
import siminov.orm.model.DatabaseMappingDescriptor.Relationship;
import android.app.Activity;
import android.webkit.WebView;

/**
 * It handles and provides all resources needed by SIMINOV HYBRID.
 * <p>
 * Such As: Provides HybridDescriptor, AdapterDescriptor.
 */
public class Resources {

	private static Resources hybridResources = null;
	private siminov.orm.resource.Resources ormResources = null;
	
	private HybridDescriptor hybridDescriptor = null;
	
	private WebView webView = null;
	private Activity webActivity = null;
	
	private EventHandler eventHandler = null;
	
	private AdapterHandler adapterHandler = null;
	private AdapterResources adapterResources = null;

	private Map<String, String> webNativeClassMapping = new ConcurrentHashMap<String, String>();
	
	private Resources() {
		ormResources = siminov.orm.resource.Resources.getInstance();
		eventHandler = EventHandler.getInstance();
	}
	
	/**
	 * It provides an instance of Resources class.
	 * 
	 * @return Resources instance.
	 */
	public static Resources getInstance() {
		
		if(hybridResources == null) {
			hybridResources = new Resources();
		}
		
		return hybridResources;
	}

	/**
	 * Get Hybrid Descriptor.
	 * @return Hybrid Descriptor.
	 */
	public HybridDescriptor getHybridDescriptor() {
		return this.hybridDescriptor;
	}
	
	/**
	 * Set Hybrid Descriptor.
	 * @param hybridDescriptor Hybrid Descriptor.
	 */
	public void setHybridDescriptor(HybridDescriptor hybridDescriptor) {
		this.hybridDescriptor = hybridDescriptor;
	}

	
	
	/**
	 * Get All Adapter Descriptors defined by Application.
	 * @return All Adapter Descriptors.
	 */
	public Iterator<AdapterDescriptor> getAdapterDescriptors() {
		return hybridDescriptor.getAdapterDescriptors();
	}
	
	
	/**
	 * Get All Adapter Descriptors Defined By Paths.
	 * @return All Adapter Descriptors.
	 */
	public Iterator<AdapterDescriptor> getAdapterDescriptorsBasedOnPaths() {
		
		Iterator<String> adapterDescriptorPaths = hybridDescriptor.getAdapterDescriptorPaths();
		Collection<AdapterDescriptor> adapterDescriptors = new ArrayList<AdapterDescriptor>();
		
		while(adapterDescriptorPaths.hasNext()) {
			String adapterDescriptorPath = adapterDescriptorPaths.next();
			adapterDescriptors.add(hybridDescriptor.getAdapterBasedOnPath(adapterDescriptorPath));
		}
		
		return adapterDescriptors.iterator();
	}
	

	/**
	 * Get Adapter Descriptor based on Adapter Descriptor Name.
	 * @param adapterDescriptorName Name of Adapter Descriptor.
	 * @return Adapter Descriptor
	 */
	public AdapterDescriptor getAdapterDescriptor(final String adapterDescriptorName) {
		boolean contain = hybridDescriptor.containAdapterDescriptorBasedOnName(adapterDescriptorName);
		if(contain) {
			return hybridDescriptor.getAdapterDescriptorBasedOnName(adapterDescriptorName);
		}
		
		return null;
	}
	
	/**
	 * Get Adapter Descriptor based on Adapter Descriptor Name.
	 * @param adapterDescriptorName Name of Adapter Descriptor.
	 * @return Adapter Descriptor.
	 */
	public AdapterDescriptor getAdapterBasedOnName(final String adapterDescriptorName) {
		
		boolean contain = hybridDescriptor.containAdapterDescriptorBasedOnName(adapterDescriptorName);
		if(contain) {
			return hybridDescriptor.getAdapterDescriptorBasedOnName(adapterDescriptorName);
		}
		
		return null;
	}
	
	/**
	 * Get Adapter Descriptor based on adapter descriptor path.
	 * @param adapterDescriptorPath Path of Adapter Descriptor.
	 * @return Adapter Descriptor.
	 */
	public AdapterDescriptor getAdapterBasedOnPath(final String adapterDescriptorPath) {
		
		boolean contain = hybridDescriptor.containAdapterDescriptorBasedOnPath(adapterDescriptorPath);
		if(contain) {
			return hybridDescriptor.getAdapterBasedOnPath(adapterDescriptorPath);
		}
		
		return null;
	}
	
	
	/**
	 * Check whether adapter descriptor exist or not based on adapter descriptor name.
	 * @param adapterDescriptorName Name of adapter descriptor.
	 * @return true/false; TRUE if adapter descriptor exist, FALSE if adapter descriptor does not exist.
	 */
	public boolean containAdapterBasedOnName(final String adapterDescriptorName) {
		
		boolean contain = hybridDescriptor.containAdapterDescriptorBasedOnName(adapterDescriptorName);
		if(contain) {
			return contain;
		}
		
		return false;
	}
	
	/**
	 * Check whether adapter descriptor exist or not based on adapter descriptor path.
	 * @param adapterDescriptorPath Path of Adapter Descriptor.
	 * @return true/false; TRUE if adapter descriptor exist, FALSE if adapter descriptor does not exist.
	 */
	public boolean containAdapterBasedOnPath(final String adapterDescriptorPath) {
		return hybridDescriptor.containAdapterDescriptorBasedOnPath(adapterDescriptorPath);
	}
	
	
	/**
	 * Get All Handlers defined by Application.
	 * @return All Handlers.
	 */
	public Iterator<Handler> getHandlers() {
		
		Collection<Handler> handlers = new ArrayList<Handler>();
		
		Iterator<AdapterDescriptor> adapterDescriptors = getAdapterDescriptors();
		while(adapterDescriptors.hasNext()) {
			AdapterDescriptor adapterDescriptor = adapterDescriptors.next();
			Iterator<Handler> adapterHandlers = adapterDescriptor.getHandlers();
			
			while(adapterHandlers.hasNext()) {
				handlers.add(adapterHandlers.next());
			}
		}
		
		return handlers.iterator();
	}

	/**
	 * Get Handler based on Adapter Name and Handler Name.
	 * @param adapterDescriptorName Name of Adapter.
	 * @param handlerName Name of Handler.
	 * @return Handler.
	 */
	public Handler getHandler(final String adapterDescriptorName, final String handlerName) {
		
		AdapterDescriptor adapterDescriptor = getAdapterDescriptor(adapterDescriptorName);
		boolean contain = adapterDescriptor.containHandler(handlerName);
		
		if(contain) {
			return adapterDescriptor.getHandler(handlerName);
		}
		
		return null;
	}
	
	/**
	 * Check whether Handler exist or not based on handler name.
	 * @param handlerName Name of Handler.
	 * @return true/false; TRUE if handler exist, FALSE if handler does not exist.
	 */
	public boolean containHandler(final String handlerName) {
		
		Iterator<AdapterDescriptor> adapterDescriptors = getAdapterDescriptors();
		while(adapterDescriptors.hasNext()) {
			AdapterDescriptor adapterDescriptor = adapterDescriptors.next();
			Iterator<Handler> handlers = adapterDescriptor.getHandlers();
			
			while(handlers.hasNext()) {
				Handler handler = handlers.next();
				if(handler.getName().equalsIgnoreCase(handlerName)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Check whether Handler exist or not based on adapter name and handler name.
	 * @param adapterDescriptorName Name of Adapter.
	 * @param handlerName Name of Handler.
	 * @return true/false; TRUE if handler exist, FALSE if handler does not exist.
	 */
	public boolean containHandler(final String adapterDescriptorName, final String handlerName) {
		AdapterDescriptor adapterDescriptor = getAdapterDescriptor(adapterDescriptorName);
		return adapterDescriptor.containHandler(handlerName);
	}
	
	/**
	 * Get Web View.
	 * @return Web View.
	 */
	public WebView getWebView() {
		return this.webView;
	}
	
	/**
	 * Set Web View.
	 * @param webView Web View.
	 */
	public void setWebView(final WebView webView) {
		this.webView = webView;
	}

	/**
	 * Get Web Activity
	 * @return Web Activity
	 */
	public Activity getWebActivity() {
		return this.webActivity;
	}
	
	/**
	 * Set Web Activity
	 * @param webActivity Web Activity
	 */
	public void setWebActivity(Activity webActivity) {
		this.webActivity = webActivity;
	}
	
	
	/**
	 * Check whether any event registered by application or not.
	 * @return true/false; TRUE if event registered by application, FALSE if event not registered by application.
	 */
	public boolean doesEventsRegistered() {
		return this.eventHandler.doesEventsRegistered();
	}
	
	/**
	 * Add Event.
	 * @param event Event.
	 */
	public void addEvent(String event) {
		this.eventHandler.addEvent(event);
	}
	
	/**
	 * Get All Events registered by application.
	 * @return All Events.
	 */
	public Iterator<String> getEvents() {
		return this.eventHandler.getEvents();
	}

	/**
	 * Get AdapterHandler.
	 * @return AdapterHandler.
	 */
	public AdapterHandler getAdapterHandler() {
		return this.adapterHandler;
	}
	
	/**
	 * Set AdapterHandler.
	 * @param adapterHandler AdapterHandler.
	 */
	public void setAdapterHandler(final AdapterHandler adapterHandler) {
		this.adapterHandler = adapterHandler;
	}
	
	/**
	 * Get Adapter Resources.
	 * @return Adapter Resources.
	 */
	public AdapterResources getAdapterResources() {
		return this.adapterResources;
	}
	
	/**
	 * Set Adapter Resources.
	 * @param adapterResources Adapter Resources.
	 */
	public void setAdapterResources(final AdapterResources adapterResources) {
		this.adapterResources = adapterResources;
	}


	/*
	 * Siminov ORM API's.
	 */

	/**
	 * Get Database Descriptor based on Web model class name.
	 * @param className Name of Web Model Class.
	 * @return Database Descriptor.
	 */
	public DatabaseDescriptor getDatabaseDescriptorBasedOnClassName(final String className) {

		String nativeClassName = webNativeClassMapping.get(className);
		return ormResources.getDatabaseDescriptorBasedOnClassName(nativeClassName);
		
	}
	
	/**
	 * Get Database Descriptor Name based on Web Model class name.
	 * @param className Name of Web Model Class.
	 * @return Database Descriptor Name.
	 */
	public String getDatabaseDescriptorNameBasedOnClassName(final String className) {
	
		String nativeClassName = webNativeClassMapping.get(className);
		return ormResources.getDatabaseDescriptorNameBasedOnClassName(nativeClassName);
		
	}
	
	/**
	 * Get Database Descriptor Name based on table name.
	 * @param tableName Name of table.
	 * @return Database Descriptor Name.
	 */
	public String getDatabaseDescriptorNameBasedOnTableName(final String tableName) {
		return ormResources.getDatabaseDescriptorNameBasedOnTableName(tableName);
	}

	/**
	 * Get Database Descriptor based on table name.
	 * @param tableName Name of Table.
	 * @return Database Descriptor.
	 */
	public DatabaseDescriptor getDatabaseDescriptorBasedOnTableName(final String tableName) {
		return ormResources.getDatabaseDescriptorBasedOnTableName(tableName);
	}
	
	/**
	 * Get Database Mapping Descriptor based on Web Model Class Name.
	 * @param className Name of Web Model Class.
	 * @return Database Mapping Descriptor.
	 */
	public DatabaseMappingDescriptor getDatabaseMappingDescriptorBasedOnClassName(final String className) {
		
		String nativeClassName = webNativeClassMapping.get(className);
		if(nativeClassName == null ||nativeClassName.length() <= 0) {
			DatabaseMappingDescriptor databaseMappingDescriptor = ormResources.requiredDatabaseMappingDescriptorBasedOnClassName(className);
			synchronizeMappings();
			
			return databaseMappingDescriptor;
		}
		
		return ormResources.requiredDatabaseMappingDescriptorBasedOnClassName(nativeClassName);
		
	}
	
	/**
	 * Get Database Mapping Descriptor based on table name.
	 * @param tableName Name of Table.
	 * @return Database Mapping Descriptor.
	 */
	public DatabaseMappingDescriptor getDatabaseMappingDescriptorBasedOnTableName(final String tableName) {
		return ormResources.getDatabaseMappingDescriptorBasedOnTableName(tableName);
	}

	/**
	 * Get Mapped Native Model Class Name.
	 * @param hybridClassName Web Model Class Name.
	 * @return Native Model Class Name.
	 */
	public String getMappedNativeClassName(final String hybridClassName) {
		return webNativeClassMapping.get(hybridClassName);
	}
	
	/**
	 * Get Mapped Web Model Class Name.
	 * @param nativeClassName Native Model Class Name.
	 * @return Web Model Class Name.
	 */
	public String getMappedWebClassName(final String nativeClassName) {
		Iterator<String> keys = webNativeClassMapping.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			String value = webNativeClassMapping.get(key);
			
			if(value.equalsIgnoreCase(nativeClassName)) {
				return key;
			}
			
		}
		
		return null;
	}
	
	/**
	 * Map Native Model Class Name with Web Model Class Name.
	 */
	public void synchronizeMappings() {

		Iterator<DatabaseMappingDescriptor> databaseMappingDescriptors = ormResources.getDatabaseMappingDescriptors();
		while(databaseMappingDescriptors.hasNext()) {
			DatabaseMappingDescriptor databaseMappingDescriptor = databaseMappingDescriptors.next();
			
			String nativeClassName = databaseMappingDescriptor.getClassName();
			String webClassName = nativeClassName.substring(nativeClassName.lastIndexOf(".") + 1, nativeClassName.length());
			
			webNativeClassMapping.put(webClassName, nativeClassName);
			
		}
	}

	
	/**
	 * Generates Hybrid Database Descriptor
	 * @param databaseDescriptor Database Descriptor.
	 * @return Hybrid Application Descriptor. 
	 */
	public HybridSiminovData generateHybridDatabaseDescriptor(DatabaseDescriptor databaseDescriptor) {
		
		HybridSiminovData hybridDatabaseDescriptor = new HybridSiminovData();
		hybridDatabaseDescriptor.setDataType(HybridDatabaseDescriptor.DATABASE_DESCRIPTOR);
		
		HybridSiminovValue databaseName = new HybridSiminovValue();
		databaseName.setType(HybridDatabaseDescriptor.NAME);
		databaseName.setValue(databaseDescriptor.getDatabaseName());

		hybridDatabaseDescriptor.addValue(databaseName);
		
		HybridSiminovValue databaseDescription = new HybridSiminovValue();
		databaseDescription.setType(HybridDatabaseDescriptor.DESCRIPTION);
		databaseDescription.setValue(databaseDescriptor.getDescription());
		
		hybridDatabaseDescriptor.addValue(databaseDescription);
		
		HybridSiminovValue isLockingRequired = new HybridSiminovValue();
		isLockingRequired.setType(HybridDatabaseDescriptor.IS_LOCKING_REQUIRED);
		isLockingRequired.setValue(Boolean.toString(databaseDescriptor.isTransactionSafe()));
		
		hybridDatabaseDescriptor.addValue(isLockingRequired);
		
		HybridSiminovValue externalStorage = new HybridSiminovValue();
		externalStorage.setType(HybridDatabaseDescriptor.EXTERNAL_STORAGE);
		externalStorage.setValue(Boolean.toString(databaseDescriptor.isExternalStorageEnable()));
		
		hybridDatabaseDescriptor.addValue(externalStorage);
		
		HybridSiminovData webDatabaseMappingDescriptorPaths = new HybridSiminovData();
		webDatabaseMappingDescriptorPaths.setDataType(HybridDatabaseDescriptor.DATABASE_MAPPING_DESCRIPTORS);
		
		Iterator<String> databaseMappingDescriptors = databaseDescriptor.getDatabaseMappingPaths();
		while(databaseMappingDescriptors.hasNext()) {
			
			HybridSiminovValue databaseMappingDescriptorPath = new HybridSiminovValue();
			databaseMappingDescriptorPath.setType(HybridDatabaseDescriptor.DATABASE_MAPPING_DESCRIPTOR_PATH);
			databaseMappingDescriptorPath.setValue(databaseMappingDescriptors.next());
			
			webDatabaseMappingDescriptorPaths.addValue(databaseMappingDescriptorPath);
			
		}
		
		hybridDatabaseDescriptor.addData(webDatabaseMappingDescriptorPaths);
		
		return hybridDatabaseDescriptor;
	}

	
	/**
	 * Generate Hybrid Database Mapping Descriptor.
	 * @param databaseMappingDescriptor Database Mapping Descriptor.
	 * @return Hybrid Database Mapping Descriptor.
	 */
	public HybridSiminovData generateHybridDatabaseMappingDescriptor(final DatabaseMappingDescriptor databaseMappingDescriptor) {
		
		HybridSiminovData hybridDatabaseMappingDescriptor = new HybridSiminovData();
		hybridDatabaseMappingDescriptor.setDataType(HybridDatabaseMappingDescriptor.DATABASE_MAPPING_DESCRIPTOR);
		
		
		HybridSiminovValue tableName = new HybridSiminovValue();
		tableName.setType(HybridDatabaseMappingDescriptor.TABLE_NAME);
		tableName.setValue(databaseMappingDescriptor.getTableName());
		
		hybridDatabaseMappingDescriptor.addValue(tableName);
		
		
		HybridSiminovValue className = new HybridSiminovValue();
		className.setType(HybridDatabaseMappingDescriptor.CLASS_NAME);
		className.setValue(databaseMappingDescriptor.getClassName());
		
		hybridDatabaseMappingDescriptor.addValue(className);
		
		
		HybridSiminovData hybridColumns = new HybridSiminovData();
		hybridColumns.setDataType(HybridDatabaseMappingDescriptor.COLUMNS);
		
		Iterator<Column> columns = databaseMappingDescriptor.getColumns();
		while(columns.hasNext()) {
			
			Column column = columns.next();
			HybridSiminovData hybridColumn = generateHybridDatabaseMappingDescriptorColumn(column);
			hybridColumns.addData(hybridColumn);
			
		}

		
		hybridDatabaseMappingDescriptor.addData(hybridColumns);
		
		
		HybridSiminovData hybridIndexs = new HybridSiminovData();
		hybridIndexs.setDataType(HybridDatabaseMappingDescriptor.INDEXS);
		
		Iterator<Index> indexs = databaseMappingDescriptor.getIndexes();
		while(indexs.hasNext()) {
			
			Index index = indexs.next();
			HybridSiminovData webIndex = generateHybridDatabaseMappingDescriptorIndex(index);
			hybridIndexs.addData(webIndex);
		}
			
		hybridDatabaseMappingDescriptor.addData(hybridIndexs);
		
		
		HybridSiminovData hybridRelationships = new HybridSiminovData();
		hybridRelationships.setDataType(HybridDatabaseMappingDescriptor.RELATIONSHIPS);
		
		Iterator<Relationship> relationships = databaseMappingDescriptor.getRelationships();
		while(relationships.hasNext()) {
			Relationship relationship = relationships.next();
			
			HybridSiminovData hybridRelationship = generateHybridDatabaseMappingDescriptorRelationship(relationship);
			hybridRelationships.addData(hybridRelationship);
		}

		hybridDatabaseMappingDescriptor.addData(hybridRelationships);
			
		return hybridDatabaseMappingDescriptor;
	}

	
	/**
	 * Generate Hybrid Database Mapping Descriptor Column.
	 * @param column Database Mapping Descriptor Column.
	 * @return Hybrid Siminov Data.
	 */
	public HybridSiminovData generateHybridDatabaseMappingDescriptorColumn(final Column column) {
		
		HybridSiminovData hybridColumn = new HybridSiminovData();
		hybridColumn.setDataType(HybridDatabaseMappingDescriptor.COLUMN);
		
		HybridSiminovValue variableName = new HybridSiminovValue();
		variableName.setType(HybridDatabaseMappingDescriptor.VARIABLE_NAME);
		variableName.setValue(column.getVariableName());
		
		hybridColumn.addValue(variableName);
		
		
		HybridSiminovValue columnName = new HybridSiminovValue();
		columnName.setType(HybridDatabaseMappingDescriptor.COLUMN_NAME);
		columnName.setValue(column.getColumnName());
		
		hybridColumn.addValue(columnName);
		
		
		HybridSiminovValue type = new HybridSiminovValue();
		type.setType(HybridDatabaseMappingDescriptor.TYPE);
		type.setValue(column.getType());
		
		hybridColumn.addValue(type);
		
		
		HybridSiminovValue primaryKey = new HybridSiminovValue();
		primaryKey.setType(HybridDatabaseMappingDescriptor.PRIMARY_KEY);
		primaryKey.setValue(Boolean.toString(column.isPrimaryKey()));
		
		hybridColumn.addValue(primaryKey);
		
		
		HybridSiminovValue notNull = new HybridSiminovValue();
		notNull.setType(HybridDatabaseMappingDescriptor.NOT_NULL);
		notNull.setValue(Boolean.toString(column.isNotNull()));
		
		hybridColumn.addValue(notNull);
		
		
		HybridSiminovValue unique = new HybridSiminovValue();
		unique.setType(HybridDatabaseMappingDescriptor.UNIQUE);
		unique.setValue(Boolean.toString(column.isUnique()));
		
		hybridColumn.addValue(unique);
		
		
		HybridSiminovValue check = new HybridSiminovValue();
		check.setType(HybridDatabaseMappingDescriptor.CHECK);
		check.setValue(column.getCheck());
		
		hybridColumn.addValue(check);
		
		
		HybridSiminovValue defaultValue = new HybridSiminovValue();
		defaultValue.setType(HybridDatabaseMappingDescriptor.DEFAULT);
		defaultValue.setValue(column.getDefaultValue());
		
		hybridColumn.addValue(defaultValue);

		return hybridColumn;

	}
	
	
	/**
	 * Generate Hybrid Database Mapping Descriptor Index.
	 * @param index Database Mapping Descriptor Index.
	 * @return Hybrid Database Mapping Descriptor Index.
	 */
	public HybridSiminovData generateHybridDatabaseMappingDescriptorIndex(final Index index) {
		
		HybridSiminovData hybridIndex = new HybridSiminovData();
		hybridIndex.setDataType(HybridDatabaseMappingDescriptor.INDEX);
		
		HybridSiminovValue name = new HybridSiminovValue();
		name.setType(HybridDatabaseMappingDescriptor.INDEX_NAME);
		name.setValue(index.getName());
		
		hybridIndex.addValue(name);
		
		
		HybridSiminovValue unique = new HybridSiminovValue();
		unique.setType(HybridDatabaseMappingDescriptor.INDEX_UNIQUE);
		unique.setValue(Boolean.toString(index.isUnique()));
		
		hybridIndex.addValue(unique);
		
		HybridSiminovData hybridIndexColumns = new HybridSiminovData();
		
		Iterator<String> columnNames = index.getColumns();
		while(columnNames.hasNext()) {
			HybridSiminovValue column = new HybridSiminovValue();
			column.setType(HybridDatabaseMappingDescriptor.INDEX_COLUMN);
			column.setValue(columnNames.next());

			hybridIndexColumns.addValue(column);
			
		}

		return hybridIndex;
		
	}
	
	
	/**
	 * Generate Hybrid Database Mapping Descriptor Relationship.
	 * @param relationship Database Mapping Descriptor Relationship.
	 * @return Hybrid Database Mapping Descriptor Relationship.
	 */
	public HybridSiminovData generateHybridDatabaseMappingDescriptorRelationship(final Relationship relationship) {
		
		HybridSiminovData hybridRelationship = new HybridSiminovData();
		hybridRelationship.setDataType(HybridDatabaseMappingDescriptor.RELATIONSHIP);
		
		HybridSiminovValue relationshipType = new HybridSiminovValue();
		relationshipType.setType(HybridDatabaseMappingDescriptor.RELATIONSHIP_TYPE);
		relationshipType.setValue(relationship.getRelationshipType());
		
		hybridRelationship.addValue(relationshipType);
		
		
		HybridSiminovValue refer = new HybridSiminovValue();
		refer.setType(HybridDatabaseMappingDescriptor.REFER);
		refer.setValue(relationship.getRefer());
		
		hybridRelationship.addValue(refer);
		
		
		HybridSiminovValue referTo = new HybridSiminovValue();
		referTo.setType(HybridDatabaseMappingDescriptor.REFER_TO);
		referTo.setValue(relationship.getReferTo());
		
		hybridRelationship.addValue(referTo);
		
		
		HybridSiminovValue onUpdate = new HybridSiminovValue();
		onUpdate.setType(HybridDatabaseMappingDescriptor.ON_UPDATE);
		onUpdate.setValue(relationship.getOnUpdate());
		
		hybridRelationship.addValue(onUpdate);
		
		
		HybridSiminovValue  onDelete = new HybridSiminovValue();
		onDelete.setType(HybridDatabaseMappingDescriptor.ON_DELETE);
		onDelete.setValue(relationship.getOnDelete());
		
		hybridRelationship.addValue(onDelete);
		
		
		HybridSiminovValue load = new HybridSiminovValue();
		load.setType(HybridDatabaseMappingDescriptor.LOAD);
		load.setValue(Boolean.toString(relationship.isLoad()));
		
		hybridRelationship.addValue(load);

		return hybridRelationship;
		
	}

	
	/**
	 * Generate Hybrid Library Descriptor.
	 * @param libraryDescriptor Library Descriptor.
	 * @return Hybrid Library Descriptor.
	 */
	public HybridSiminovData generateHybridLibraryDescriptor(final siminov.orm.model.LibraryDescriptor libraryDescriptor) {
		
		HybridSiminovData hybridLibraryDescriptor = new HybridSiminovData();
		hybridLibraryDescriptor.setDataType(HybridLibraryDescriptor.LIBRARY_DESCRIPTOR);
		
		
		HybridSiminovValue name = new HybridSiminovValue();
		name.setType(HybridLibraryDescriptor.NAME);
		name.setValue(libraryDescriptor.getName());
		
		hybridLibraryDescriptor.addValue(name);
		
		
		HybridSiminovValue description = new HybridSiminovValue();
		description.setType(HybridLibraryDescriptor.DESCRIPTION);
		description.setValue(libraryDescriptor.getDescription());
		
		
		HybridSiminovData hybridDatabaseMappingDescriptorPaths = new HybridSiminovData();
		hybridDatabaseMappingDescriptorPaths.setDataType(HybridLibraryDescriptor.DATABASE_MAPPING_DESCRIPTOR_PATHS);
		
		Iterator<String> databaseMappingDescriptorPaths = libraryDescriptor.getDatabaseMappingPaths();
		while(databaseMappingDescriptorPaths.hasNext()) {
			
			HybridSiminovValue hybridDatabaseMappingDescriptorPath = new HybridSiminovValue();
			hybridDatabaseMappingDescriptorPath.setType(HybridLibraryDescriptor.DATABASE_MAPPING_DESCRIPTOR_PATH);
			
			hybridDatabaseMappingDescriptorPaths.addValue(hybridDatabaseMappingDescriptorPath);
		}
		
		hybridLibraryDescriptor.addData(hybridDatabaseMappingDescriptorPaths);
		
		return hybridLibraryDescriptor;
		
	}

	
	/**
	 * Generate Hybrid Descriptor.
	 * @param hybridDescriptor Hybrid Descriptor.
	 * @return Web Hybrid Descriptor.
	 */
	public HybridSiminovData generateHybridDescriptor(final HybridDescriptor hybridDescriptor) {
		
		HybridSiminovData hybridWebDescriptor = new HybridSiminovData();
		hybridWebDescriptor.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.HRBRID_DESCRIPTOR);
		
		
		HybridSiminovData hybridAdapters = new HybridSiminovData();
		hybridAdapters.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTERS);
		
		Iterator<AdapterDescriptor> adapterDescriptors = hybridDescriptor.getAdapterDescriptors();
		while(adapterDescriptors.hasNext()) {
			hybridAdapters.addData(generateHybridAdapterDescriptor(adapterDescriptors.next()));
		}

		HybridSiminovData hybridAdapterDescriptorPaths = new HybridSiminovData();
		hybridAdapterDescriptorPaths.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER_PATHS);
		
		Iterator<String> adapterDescriptorPaths = hybridDescriptor.getAdapterDescriptorPaths();
		while(adapterDescriptorPaths.hasNext()) {
			
			HybridSiminovValue hybridAdapterPath = new HybridSiminovValue();
			hybridAdapterPath.setType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER_PATH);
			hybridAdapterPath.setValue(adapterDescriptorPaths.next());
			
		}
		
		hybridWebDescriptor.addData(hybridAdapterDescriptorPaths);
		
		hybridWebDescriptor.addData(hybridAdapters);

		return hybridWebDescriptor;
		
	}
	
	
	/**
	 * Generate Hybrid Adapter.
	 * @param adapterDescriptor Adapter.
	 * @return Hybrid Adapter.
	 */
	public HybridSiminovData generateHybridAdapterDescriptor(final AdapterDescriptor adapterDescriptor) {
		
		HybridSiminovData hybridAdapterDescriptor = new HybridSiminovData();
		hybridAdapterDescriptor.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER);
		
		
		HybridSiminovValue adapterName = new HybridSiminovValue();
		adapterName.setType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER_NAME);
		adapterName.setValue(adapterDescriptor.getName());
		
		hybridAdapterDescriptor.addValue(adapterName);
		

		HybridSiminovValue adapterDescriptorDescription = new HybridSiminovValue();
		adapterDescriptorDescription.setType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER_DESCRIPTION);
		adapterDescriptorDescription.setValue(adapterDescriptor.getDescription());
		
		hybridAdapterDescriptor.addValue(adapterDescriptorDescription);
		
		
		HybridSiminovValue adapterDescriptorMapTo = new HybridSiminovValue();
		adapterDescriptorMapTo.setType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER_MAP_TO);
		adapterDescriptorMapTo.setValue(adapterDescriptor.getMapTo());
		
		hybridAdapterDescriptor.addValue(adapterDescriptorMapTo);
		
		
		HybridSiminovValue cache = new HybridSiminovValue();
		cache.setType(siminov.hybrid.adapter.constants.HybridDescriptor.ADAPTER_CACHE);
		cache.setValue(Boolean.toString(adapterDescriptor.isCache()));
		
		hybridAdapterDescriptor.addValue(cache);
		
		
		HybridSiminovData hybridHandlers = new HybridSiminovData();
		hybridHandlers.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLERS);
		
		Iterator<Handler> handlers = adapterDescriptor.getHandlers();
		while(handlers.hasNext()) {
			hybridHandlers.addData(generateHybridHandler(handlers.next()));
		}
		

		hybridAdapterDescriptor.addData(hybridHandlers);
		
		return hybridAdapterDescriptor;
	}

	
	/**
	 * Generate Hybrid Handler.
	 * @param handler Handler.
	 * @return Hybrid Handler.
	 */
	public HybridSiminovData generateHybridHandler(final Handler handler) {
		
		HybridSiminovData hybridHandler = new HybridSiminovData();
		hybridHandler.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER);
		
		
		HybridSiminovValue handlerName = new HybridSiminovValue();
		handlerName.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_NAME);
		handlerName.setValue(handler.getName());
		
		hybridHandler.addValue(handlerName);
		
		
		HybridSiminovValue handlerMapTo = new HybridSiminovValue();
		handlerMapTo.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_MAP_TO);
		handlerMapTo.setValue(handler.getMapTo());
		
		hybridHandler.addValue(handlerMapTo);
		
		
		HybridSiminovValue handlerDescription = new HybridSiminovValue();
		handlerDescription.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_DESCRIPTION);
		handlerDescription.setValue(handler.getDescription());
		
		hybridHandler.addValue(handlerDescription);
		
		
		HybridSiminovData hybridHandlerParameters = new HybridSiminovData();
		hybridHandlerParameters.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_PARAMETERS);
		
		Iterator<Parameter> parameters = handler.getParameters();
		while(parameters.hasNext())  {
			
			Parameter parameter = parameters.next();
			
			HybridSiminovData hybridHandlerParameter = new HybridSiminovData();
			hybridHandlerParameter.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_PARAMETER);
			
			
			HybridSiminovValue parameterType = new HybridSiminovValue();
			parameterType.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_PARAMETER_TYPE);
			parameterType.setValue(parameter.getType());
			
			hybridHandlerParameter.addValue(parameterType);
			
			
			HybridSiminovValue parameterDescription = new HybridSiminovValue();
			parameterDescription.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_PARAMETER_DESCRIPTION);
			parameterDescription.setValue(parameter.getDescription());
			
			hybridHandlerParameter.addValue(parameterDescription);
			
			hybridHandlerParameters.addData(hybridHandlerParameter);
			
		}
		
		hybridHandler.addData(hybridHandlerParameters);

		Return returnType = handler.getReturn();
		if(returnType != null) {

			HybridSiminovData hybridReturn = new HybridSiminovData();
			hybridReturn.setDataType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_RETURN);
			
			
			HybridSiminovValue hybridReturnType = new HybridSiminovValue();
			hybridReturnType.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_RETURN_TYPE);
			hybridReturnType.setValue(returnType.getType());
			
			hybridReturn.addValue(hybridReturnType);
					
			
			HybridSiminovValue hybridReturnDescription = new HybridSiminovValue();
			hybridReturnDescription.setType(siminov.hybrid.adapter.constants.HybridDescriptor.HANDLER_RETURN_DESCRIPTION);
			hybridReturnDescription.setValue(returnType.getDescription());
			
			hybridReturn.addValue(hybridReturnDescription);
			
			hybridHandler.addData(hybridReturn);
		}

		return hybridHandler;
	}

	
}
