/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.device.rules.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstanceModel;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstanceSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>MDRRuleGroupInstanceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MDRRuleGroupInstanceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class MDRRuleGroupInstanceModelImpl
	extends BaseModelImpl<MDRRuleGroupInstance>
	implements MDRRuleGroupInstanceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a mdr rule group instance model instance should use the <code>MDRRuleGroupInstance</code> interface instead.
	 */
	public static final String TABLE_NAME = "MDRRuleGroupInstance";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"ruleGroupInstanceId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"ruleGroupId", Types.BIGINT}, {"priority", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ruleGroupInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ruleGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MDRRuleGroupInstance (uuid_ VARCHAR(75) null,ruleGroupInstanceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,ruleGroupId LONG,priority INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table MDRRuleGroupInstance";

	public static final String ORDER_BY_JPQL =
		" ORDER BY mdrRuleGroupInstance.ruleGroupInstanceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MDRRuleGroupInstance.ruleGroupInstanceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.mobile.device.rules.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.mobile.device.rules.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.mobile.device.rules.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"),
		true);

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long RULEGROUPID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long RULEGROUPINSTANCEID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MDRRuleGroupInstance toModel(
		MDRRuleGroupInstanceSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		MDRRuleGroupInstance model = new MDRRuleGroupInstanceImpl();

		model.setUuid(soapModel.getUuid());
		model.setRuleGroupInstanceId(soapModel.getRuleGroupInstanceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setRuleGroupId(soapModel.getRuleGroupId());
		model.setPriority(soapModel.getPriority());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MDRRuleGroupInstance> toModels(
		MDRRuleGroupInstanceSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<MDRRuleGroupInstance> models = new ArrayList<MDRRuleGroupInstance>(
			soapModels.length);

		for (MDRRuleGroupInstanceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.mobile.device.rules.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"));

	public MDRRuleGroupInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRuleGroupInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MDRRuleGroupInstance.class;
	}

	@Override
	public String getModelClassName() {
		return MDRRuleGroupInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MDRRuleGroupInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRRuleGroupInstance, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MDRRuleGroupInstance)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MDRRuleGroupInstance, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MDRRuleGroupInstance, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MDRRuleGroupInstance)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MDRRuleGroupInstance, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MDRRuleGroupInstance, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MDRRuleGroupInstance>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MDRRuleGroupInstance.class.getClassLoader(),
			MDRRuleGroupInstance.class, ModelWrapper.class);

		try {
			Constructor<MDRRuleGroupInstance> constructor =
				(Constructor<MDRRuleGroupInstance>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<MDRRuleGroupInstance, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MDRRuleGroupInstance, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<MDRRuleGroupInstance, Object>>();
		Map<String, BiConsumer<MDRRuleGroupInstance, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<MDRRuleGroupInstance, ?>>();

		attributeGetterFunctions.put("uuid", MDRRuleGroupInstance::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<MDRRuleGroupInstance, String>)
				MDRRuleGroupInstance::setUuid);
		attributeGetterFunctions.put(
			"ruleGroupInstanceId",
			MDRRuleGroupInstance::getRuleGroupInstanceId);
		attributeSetterBiConsumers.put(
			"ruleGroupInstanceId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setRuleGroupInstanceId);
		attributeGetterFunctions.put(
			"groupId", MDRRuleGroupInstance::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setGroupId);
		attributeGetterFunctions.put(
			"companyId", MDRRuleGroupInstance::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setCompanyId);
		attributeGetterFunctions.put("userId", MDRRuleGroupInstance::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setUserId);
		attributeGetterFunctions.put(
			"userName", MDRRuleGroupInstance::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<MDRRuleGroupInstance, String>)
				MDRRuleGroupInstance::setUserName);
		attributeGetterFunctions.put(
			"createDate", MDRRuleGroupInstance::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MDRRuleGroupInstance, Date>)
				MDRRuleGroupInstance::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", MDRRuleGroupInstance::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<MDRRuleGroupInstance, Date>)
				MDRRuleGroupInstance::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", MDRRuleGroupInstance::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", MDRRuleGroupInstance::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setClassPK);
		attributeGetterFunctions.put(
			"ruleGroupId", MDRRuleGroupInstance::getRuleGroupId);
		attributeSetterBiConsumers.put(
			"ruleGroupId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setRuleGroupId);
		attributeGetterFunctions.put(
			"priority", MDRRuleGroupInstance::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<MDRRuleGroupInstance, Integer>)
				MDRRuleGroupInstance::setPriority);
		attributeGetterFunctions.put(
			"lastPublishDate", MDRRuleGroupInstance::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<MDRRuleGroupInstance, Date>)
				MDRRuleGroupInstance::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getRuleGroupInstanceId() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setRuleGroupInstanceId(long ruleGroupInstanceId) {
		_ruleGroupInstanceId = ruleGroupInstanceId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@JSON
	@Override
	public long getRuleGroupId() {
		return _ruleGroupId;
	}

	@Override
	public void setRuleGroupId(long ruleGroupId) {
		_columnBitmask |= RULEGROUPID_COLUMN_BITMASK;

		if (!_setOriginalRuleGroupId) {
			_setOriginalRuleGroupId = true;

			_originalRuleGroupId = _ruleGroupId;
		}

		_ruleGroupId = ruleGroupId;
	}

	public long getOriginalRuleGroupId() {
		return _originalRuleGroupId;
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		_priority = priority;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(MDRRuleGroupInstance.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), MDRRuleGroupInstance.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MDRRuleGroupInstance toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MDRRuleGroupInstanceImpl mdrRuleGroupInstanceImpl =
			new MDRRuleGroupInstanceImpl();

		mdrRuleGroupInstanceImpl.setUuid(getUuid());
		mdrRuleGroupInstanceImpl.setRuleGroupInstanceId(
			getRuleGroupInstanceId());
		mdrRuleGroupInstanceImpl.setGroupId(getGroupId());
		mdrRuleGroupInstanceImpl.setCompanyId(getCompanyId());
		mdrRuleGroupInstanceImpl.setUserId(getUserId());
		mdrRuleGroupInstanceImpl.setUserName(getUserName());
		mdrRuleGroupInstanceImpl.setCreateDate(getCreateDate());
		mdrRuleGroupInstanceImpl.setModifiedDate(getModifiedDate());
		mdrRuleGroupInstanceImpl.setClassNameId(getClassNameId());
		mdrRuleGroupInstanceImpl.setClassPK(getClassPK());
		mdrRuleGroupInstanceImpl.setRuleGroupId(getRuleGroupId());
		mdrRuleGroupInstanceImpl.setPriority(getPriority());
		mdrRuleGroupInstanceImpl.setLastPublishDate(getLastPublishDate());

		mdrRuleGroupInstanceImpl.resetOriginalValues();

		return mdrRuleGroupInstanceImpl;
	}

	@Override
	public int compareTo(MDRRuleGroupInstance mdrRuleGroupInstance) {
		long primaryKey = mdrRuleGroupInstance.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MDRRuleGroupInstance)) {
			return false;
		}

		MDRRuleGroupInstance mdrRuleGroupInstance = (MDRRuleGroupInstance)obj;

		long primaryKey = mdrRuleGroupInstance.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		MDRRuleGroupInstanceModelImpl mdrRuleGroupInstanceModelImpl = this;

		mdrRuleGroupInstanceModelImpl._originalUuid =
			mdrRuleGroupInstanceModelImpl._uuid;

		mdrRuleGroupInstanceModelImpl._originalGroupId =
			mdrRuleGroupInstanceModelImpl._groupId;

		mdrRuleGroupInstanceModelImpl._setOriginalGroupId = false;

		mdrRuleGroupInstanceModelImpl._originalCompanyId =
			mdrRuleGroupInstanceModelImpl._companyId;

		mdrRuleGroupInstanceModelImpl._setOriginalCompanyId = false;

		mdrRuleGroupInstanceModelImpl._setModifiedDate = false;

		mdrRuleGroupInstanceModelImpl._originalClassNameId =
			mdrRuleGroupInstanceModelImpl._classNameId;

		mdrRuleGroupInstanceModelImpl._setOriginalClassNameId = false;

		mdrRuleGroupInstanceModelImpl._originalClassPK =
			mdrRuleGroupInstanceModelImpl._classPK;

		mdrRuleGroupInstanceModelImpl._setOriginalClassPK = false;

		mdrRuleGroupInstanceModelImpl._originalRuleGroupId =
			mdrRuleGroupInstanceModelImpl._ruleGroupId;

		mdrRuleGroupInstanceModelImpl._setOriginalRuleGroupId = false;

		mdrRuleGroupInstanceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MDRRuleGroupInstance> toCacheModel() {
		MDRRuleGroupInstanceCacheModel mdrRuleGroupInstanceCacheModel =
			new MDRRuleGroupInstanceCacheModel();

		mdrRuleGroupInstanceCacheModel.uuid = getUuid();

		String uuid = mdrRuleGroupInstanceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mdrRuleGroupInstanceCacheModel.uuid = null;
		}

		mdrRuleGroupInstanceCacheModel.ruleGroupInstanceId =
			getRuleGroupInstanceId();

		mdrRuleGroupInstanceCacheModel.groupId = getGroupId();

		mdrRuleGroupInstanceCacheModel.companyId = getCompanyId();

		mdrRuleGroupInstanceCacheModel.userId = getUserId();

		mdrRuleGroupInstanceCacheModel.userName = getUserName();

		String userName = mdrRuleGroupInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mdrRuleGroupInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mdrRuleGroupInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mdrRuleGroupInstanceCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mdrRuleGroupInstanceCacheModel.classNameId = getClassNameId();

		mdrRuleGroupInstanceCacheModel.classPK = getClassPK();

		mdrRuleGroupInstanceCacheModel.ruleGroupId = getRuleGroupId();

		mdrRuleGroupInstanceCacheModel.priority = getPriority();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mdrRuleGroupInstanceCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return mdrRuleGroupInstanceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MDRRuleGroupInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRRuleGroupInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((MDRRuleGroupInstance)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MDRRuleGroupInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRRuleGroupInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((MDRRuleGroupInstance)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, MDRRuleGroupInstance>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private String _uuid;
	private String _originalUuid;
	private long _ruleGroupInstanceId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _ruleGroupId;
	private long _originalRuleGroupId;
	private boolean _setOriginalRuleGroupId;
	private int _priority;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private MDRRuleGroupInstance _escapedModel;

}