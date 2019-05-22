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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.Account;
import com.liferay.portal.kernel.model.AccountModel;
import com.liferay.portal.kernel.model.AccountSoap;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

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
 * The base model implementation for the Account service. Represents a row in the &quot;Account_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>AccountModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class AccountModelImpl
	extends BaseModelImpl<Account> implements AccountModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account model instance should use the <code>Account</code> interface instead.
	 */
	public static final String TABLE_NAME = "Account_";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"accountId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"parentAccountId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"legalName", Types.VARCHAR},
		{"legalId", Types.VARCHAR}, {"legalType", Types.VARCHAR},
		{"sicCode", Types.VARCHAR}, {"tickerSymbol", Types.VARCHAR},
		{"industry", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"size_", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("legalName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("legalId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("legalType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sicCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tickerSymbol", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("industry", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("size_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Account_ (mvccVersion LONG default 0 not null,accountId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentAccountId LONG,name VARCHAR(75) null,legalName VARCHAR(75) null,legalId VARCHAR(75) null,legalType VARCHAR(75) null,sicCode VARCHAR(75) null,tickerSymbol VARCHAR(75) null,industry VARCHAR(75) null,type_ VARCHAR(75) null,size_ VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Account_";

	public static final String ORDER_BY_JPQL =
		" ORDER BY account.accountId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Account_.accountId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Account"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Account"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Account toModel(AccountSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Account model = new AccountImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setAccountId(soapModel.getAccountId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentAccountId(soapModel.getParentAccountId());
		model.setName(soapModel.getName());
		model.setLegalName(soapModel.getLegalName());
		model.setLegalId(soapModel.getLegalId());
		model.setLegalType(soapModel.getLegalType());
		model.setSicCode(soapModel.getSicCode());
		model.setTickerSymbol(soapModel.getTickerSymbol());
		model.setIndustry(soapModel.getIndustry());
		model.setType(soapModel.getType());
		model.setSize(soapModel.getSize());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Account> toModels(AccountSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Account> models = new ArrayList<Account>(soapModels.length);

		for (AccountSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Account"));

	public AccountModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _accountId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Account.class;
	}

	@Override
	public String getModelClassName() {
		return Account.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Account, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Account, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Account, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Account)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Account, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Account, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Account)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Account, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Account, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Account>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Account.class.getClassLoader(), Account.class, ModelWrapper.class);

		try {
			Constructor<Account> constructor =
				(Constructor<Account>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Account, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Account, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Account, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Account, Object>>();
		Map<String, BiConsumer<Account, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Account, ?>>();

		attributeGetterFunctions.put("mvccVersion", Account::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<Account, Long>)Account::setMvccVersion);
		attributeGetterFunctions.put("accountId", Account::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId", (BiConsumer<Account, Long>)Account::setAccountId);
		attributeGetterFunctions.put("companyId", Account::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Account, Long>)Account::setCompanyId);
		attributeGetterFunctions.put("userId", Account::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Account, Long>)Account::setUserId);
		attributeGetterFunctions.put("userName", Account::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Account, String>)Account::setUserName);
		attributeGetterFunctions.put("createDate", Account::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Account, Date>)Account::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Account::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Account, Date>)Account::setModifiedDate);
		attributeGetterFunctions.put(
			"parentAccountId", Account::getParentAccountId);
		attributeSetterBiConsumers.put(
			"parentAccountId",
			(BiConsumer<Account, Long>)Account::setParentAccountId);
		attributeGetterFunctions.put("name", Account::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Account, String>)Account::setName);
		attributeGetterFunctions.put("legalName", Account::getLegalName);
		attributeSetterBiConsumers.put(
			"legalName", (BiConsumer<Account, String>)Account::setLegalName);
		attributeGetterFunctions.put("legalId", Account::getLegalId);
		attributeSetterBiConsumers.put(
			"legalId", (BiConsumer<Account, String>)Account::setLegalId);
		attributeGetterFunctions.put("legalType", Account::getLegalType);
		attributeSetterBiConsumers.put(
			"legalType", (BiConsumer<Account, String>)Account::setLegalType);
		attributeGetterFunctions.put("sicCode", Account::getSicCode);
		attributeSetterBiConsumers.put(
			"sicCode", (BiConsumer<Account, String>)Account::setSicCode);
		attributeGetterFunctions.put("tickerSymbol", Account::getTickerSymbol);
		attributeSetterBiConsumers.put(
			"tickerSymbol",
			(BiConsumer<Account, String>)Account::setTickerSymbol);
		attributeGetterFunctions.put("industry", Account::getIndustry);
		attributeSetterBiConsumers.put(
			"industry", (BiConsumer<Account, String>)Account::setIndustry);
		attributeGetterFunctions.put("type", Account::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<Account, String>)Account::setType);
		attributeGetterFunctions.put("size", Account::getSize);
		attributeSetterBiConsumers.put(
			"size", (BiConsumer<Account, String>)Account::setSize);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	@JSON
	@Override
	public long getParentAccountId() {
		return _parentAccountId;
	}

	@Override
	public void setParentAccountId(long parentAccountId) {
		_parentAccountId = parentAccountId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getLegalName() {
		if (_legalName == null) {
			return "";
		}
		else {
			return _legalName;
		}
	}

	@Override
	public void setLegalName(String legalName) {
		_legalName = legalName;
	}

	@JSON
	@Override
	public String getLegalId() {
		if (_legalId == null) {
			return "";
		}
		else {
			return _legalId;
		}
	}

	@Override
	public void setLegalId(String legalId) {
		_legalId = legalId;
	}

	@JSON
	@Override
	public String getLegalType() {
		if (_legalType == null) {
			return "";
		}
		else {
			return _legalType;
		}
	}

	@Override
	public void setLegalType(String legalType) {
		_legalType = legalType;
	}

	@JSON
	@Override
	public String getSicCode() {
		if (_sicCode == null) {
			return "";
		}
		else {
			return _sicCode;
		}
	}

	@Override
	public void setSicCode(String sicCode) {
		_sicCode = sicCode;
	}

	@JSON
	@Override
	public String getTickerSymbol() {
		if (_tickerSymbol == null) {
			return "";
		}
		else {
			return _tickerSymbol;
		}
	}

	@Override
	public void setTickerSymbol(String tickerSymbol) {
		_tickerSymbol = tickerSymbol;
	}

	@JSON
	@Override
	public String getIndustry() {
		if (_industry == null) {
			return "";
		}
		else {
			return _industry;
		}
	}

	@Override
	public void setIndustry(String industry) {
		_industry = industry;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@JSON
	@Override
	public String getSize() {
		if (_size == null) {
			return "";
		}
		else {
			return _size;
		}
	}

	@Override
	public void setSize(String size) {
		_size = size;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Account.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Account toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AccountImpl accountImpl = new AccountImpl();

		accountImpl.setMvccVersion(getMvccVersion());
		accountImpl.setAccountId(getAccountId());
		accountImpl.setCompanyId(getCompanyId());
		accountImpl.setUserId(getUserId());
		accountImpl.setUserName(getUserName());
		accountImpl.setCreateDate(getCreateDate());
		accountImpl.setModifiedDate(getModifiedDate());
		accountImpl.setParentAccountId(getParentAccountId());
		accountImpl.setName(getName());
		accountImpl.setLegalName(getLegalName());
		accountImpl.setLegalId(getLegalId());
		accountImpl.setLegalType(getLegalType());
		accountImpl.setSicCode(getSicCode());
		accountImpl.setTickerSymbol(getTickerSymbol());
		accountImpl.setIndustry(getIndustry());
		accountImpl.setType(getType());
		accountImpl.setSize(getSize());

		accountImpl.resetOriginalValues();

		return accountImpl;
	}

	@Override
	public int compareTo(Account account) {
		long primaryKey = account.getPrimaryKey();

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

		if (!(obj instanceof Account)) {
			return false;
		}

		Account account = (Account)obj;

		long primaryKey = account.getPrimaryKey();

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
		AccountModelImpl accountModelImpl = this;

		accountModelImpl._setModifiedDate = false;
	}

	@Override
	public CacheModel<Account> toCacheModel() {
		AccountCacheModel accountCacheModel = new AccountCacheModel();

		accountCacheModel.mvccVersion = getMvccVersion();

		accountCacheModel.accountId = getAccountId();

		accountCacheModel.companyId = getCompanyId();

		accountCacheModel.userId = getUserId();

		accountCacheModel.userName = getUserName();

		String userName = accountCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			accountCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			accountCacheModel.createDate = createDate.getTime();
		}
		else {
			accountCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			accountCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			accountCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		accountCacheModel.parentAccountId = getParentAccountId();

		accountCacheModel.name = getName();

		String name = accountCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			accountCacheModel.name = null;
		}

		accountCacheModel.legalName = getLegalName();

		String legalName = accountCacheModel.legalName;

		if ((legalName != null) && (legalName.length() == 0)) {
			accountCacheModel.legalName = null;
		}

		accountCacheModel.legalId = getLegalId();

		String legalId = accountCacheModel.legalId;

		if ((legalId != null) && (legalId.length() == 0)) {
			accountCacheModel.legalId = null;
		}

		accountCacheModel.legalType = getLegalType();

		String legalType = accountCacheModel.legalType;

		if ((legalType != null) && (legalType.length() == 0)) {
			accountCacheModel.legalType = null;
		}

		accountCacheModel.sicCode = getSicCode();

		String sicCode = accountCacheModel.sicCode;

		if ((sicCode != null) && (sicCode.length() == 0)) {
			accountCacheModel.sicCode = null;
		}

		accountCacheModel.tickerSymbol = getTickerSymbol();

		String tickerSymbol = accountCacheModel.tickerSymbol;

		if ((tickerSymbol != null) && (tickerSymbol.length() == 0)) {
			accountCacheModel.tickerSymbol = null;
		}

		accountCacheModel.industry = getIndustry();

		String industry = accountCacheModel.industry;

		if ((industry != null) && (industry.length() == 0)) {
			accountCacheModel.industry = null;
		}

		accountCacheModel.type = getType();

		String type = accountCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			accountCacheModel.type = null;
		}

		accountCacheModel.size = getSize();

		String size = accountCacheModel.size;

		if ((size != null) && (size.length() == 0)) {
			accountCacheModel.size = null;
		}

		return accountCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Account, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Account, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Account, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Account)this));
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
		Map<String, Function<Account, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Account, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Account, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Account)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Account>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _mvccVersion;
	private long _accountId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentAccountId;
	private String _name;
	private String _legalName;
	private String _legalId;
	private String _legalType;
	private String _sicCode;
	private String _tickerSymbol;
	private String _industry;
	private String _type;
	private String _size;
	private Account _escapedModel;

}