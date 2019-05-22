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

package com.liferay.opensocial.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.OAuthConsumerModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the OAuthConsumer service. Represents a row in the &quot;OpenSocial_OAuthConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>OAuthConsumerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuthConsumerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerImpl
 * @generated
 */
@ProviderType
public class OAuthConsumerModelImpl
	extends BaseModelImpl<OAuthConsumer> implements OAuthConsumerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth consumer model instance should use the <code>OAuthConsumer</code> interface instead.
	 */
	public static final String TABLE_NAME = "OpenSocial_OAuthConsumer";

	public static final Object[][] TABLE_COLUMNS = {
		{"oAuthConsumerId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"gadgetKey", Types.VARCHAR}, {"serviceName", Types.VARCHAR},
		{"consumerKey", Types.VARCHAR}, {"consumerSecret", Types.CLOB},
		{"keyType", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("oAuthConsumerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("gadgetKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("consumerKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("consumerSecret", Types.CLOB);
		TABLE_COLUMNS_MAP.put("keyType", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OpenSocial_OAuthConsumer (oAuthConsumerId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,gadgetKey VARCHAR(75) null,serviceName VARCHAR(75) null,consumerKey VARCHAR(75) null,consumerSecret TEXT null,keyType VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table OpenSocial_OAuthConsumer";

	public static final String ORDER_BY_JPQL =
		" ORDER BY oAuthConsumer.serviceName ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OpenSocial_OAuthConsumer.serviceName ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.opensocial.model.OAuthConsumer"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.opensocial.model.OAuthConsumer"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.opensocial.model.OAuthConsumer"),
		true);

	public static final long GADGETKEY_COLUMN_BITMASK = 1L;

	public static final long SERVICENAME_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.util.service.ServiceProps.get(
			"lock.expiration.time.com.liferay.opensocial.model.OAuthConsumer"));

	public OAuthConsumerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthConsumerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthConsumerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthConsumerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthConsumer.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthConsumer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OAuthConsumer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OAuthConsumer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthConsumer, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OAuthConsumer)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OAuthConsumer, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OAuthConsumer, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OAuthConsumer)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OAuthConsumer, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OAuthConsumer, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OAuthConsumer>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OAuthConsumer.class.getClassLoader(), OAuthConsumer.class,
			ModelWrapper.class);

		try {
			Constructor<OAuthConsumer> constructor =
				(Constructor<OAuthConsumer>)proxyClass.getConstructor(
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

	private static final Map<String, Function<OAuthConsumer, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OAuthConsumer, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OAuthConsumer, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<OAuthConsumer, Object>>();
		Map<String, BiConsumer<OAuthConsumer, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OAuthConsumer, ?>>();

		attributeGetterFunctions.put(
			"oAuthConsumerId", OAuthConsumer::getOAuthConsumerId);
		attributeSetterBiConsumers.put(
			"oAuthConsumerId",
			(BiConsumer<OAuthConsumer, Long>)OAuthConsumer::setOAuthConsumerId);
		attributeGetterFunctions.put("companyId", OAuthConsumer::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<OAuthConsumer, Long>)OAuthConsumer::setCompanyId);
		attributeGetterFunctions.put(
			"createDate", OAuthConsumer::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<OAuthConsumer, Date>)OAuthConsumer::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", OAuthConsumer::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<OAuthConsumer, Date>)OAuthConsumer::setModifiedDate);
		attributeGetterFunctions.put("gadgetKey", OAuthConsumer::getGadgetKey);
		attributeSetterBiConsumers.put(
			"gadgetKey",
			(BiConsumer<OAuthConsumer, String>)OAuthConsumer::setGadgetKey);
		attributeGetterFunctions.put(
			"serviceName", OAuthConsumer::getServiceName);
		attributeSetterBiConsumers.put(
			"serviceName",
			(BiConsumer<OAuthConsumer, String>)OAuthConsumer::setServiceName);
		attributeGetterFunctions.put(
			"consumerKey", OAuthConsumer::getConsumerKey);
		attributeSetterBiConsumers.put(
			"consumerKey",
			(BiConsumer<OAuthConsumer, String>)OAuthConsumer::setConsumerKey);
		attributeGetterFunctions.put(
			"consumerSecret", OAuthConsumer::getConsumerSecret);
		attributeSetterBiConsumers.put(
			"consumerSecret",
			(BiConsumer<OAuthConsumer, String>)
				OAuthConsumer::setConsumerSecret);
		attributeGetterFunctions.put("keyType", OAuthConsumer::getKeyType);
		attributeSetterBiConsumers.put(
			"keyType",
			(BiConsumer<OAuthConsumer, String>)OAuthConsumer::setKeyType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getOAuthConsumerId() {
		return _oAuthConsumerId;
	}

	@Override
	public void setOAuthConsumerId(long oAuthConsumerId) {
		_oAuthConsumerId = oAuthConsumerId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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
	public String getGadgetKey() {
		if (_gadgetKey == null) {
			return "";
		}
		else {
			return _gadgetKey;
		}
	}

	@Override
	public void setGadgetKey(String gadgetKey) {
		_columnBitmask |= GADGETKEY_COLUMN_BITMASK;

		if (_originalGadgetKey == null) {
			_originalGadgetKey = _gadgetKey;
		}

		_gadgetKey = gadgetKey;
	}

	public String getOriginalGadgetKey() {
		return GetterUtil.getString(_originalGadgetKey);
	}

	@Override
	public String getServiceName() {
		if (_serviceName == null) {
			return "";
		}
		else {
			return _serviceName;
		}
	}

	@Override
	public void setServiceName(String serviceName) {
		_columnBitmask = -1L;

		if (_originalServiceName == null) {
			_originalServiceName = _serviceName;
		}

		_serviceName = serviceName;
	}

	public String getOriginalServiceName() {
		return GetterUtil.getString(_originalServiceName);
	}

	@Override
	public String getConsumerKey() {
		if (_consumerKey == null) {
			return "";
		}
		else {
			return _consumerKey;
		}
	}

	@Override
	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	@Override
	public String getConsumerSecret() {
		if (_consumerSecret == null) {
			return "";
		}
		else {
			return _consumerSecret;
		}
	}

	@Override
	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	@Override
	public String getKeyType() {
		if (_keyType == null) {
			return "";
		}
		else {
			return _keyType;
		}
	}

	@Override
	public void setKeyType(String keyType) {
		_keyType = keyType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), OAuthConsumer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuthConsumer toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OAuthConsumerImpl oAuthConsumerImpl = new OAuthConsumerImpl();

		oAuthConsumerImpl.setOAuthConsumerId(getOAuthConsumerId());
		oAuthConsumerImpl.setCompanyId(getCompanyId());
		oAuthConsumerImpl.setCreateDate(getCreateDate());
		oAuthConsumerImpl.setModifiedDate(getModifiedDate());
		oAuthConsumerImpl.setGadgetKey(getGadgetKey());
		oAuthConsumerImpl.setServiceName(getServiceName());
		oAuthConsumerImpl.setConsumerKey(getConsumerKey());
		oAuthConsumerImpl.setConsumerSecret(getConsumerSecret());
		oAuthConsumerImpl.setKeyType(getKeyType());

		oAuthConsumerImpl.resetOriginalValues();

		return oAuthConsumerImpl;
	}

	@Override
	public int compareTo(OAuthConsumer oAuthConsumer) {
		int value = 0;

		value = getServiceName().compareTo(oAuthConsumer.getServiceName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthConsumer)) {
			return false;
		}

		OAuthConsumer oAuthConsumer = (OAuthConsumer)obj;

		long primaryKey = oAuthConsumer.getPrimaryKey();

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
		OAuthConsumerModelImpl oAuthConsumerModelImpl = this;

		oAuthConsumerModelImpl._setModifiedDate = false;

		oAuthConsumerModelImpl._originalGadgetKey =
			oAuthConsumerModelImpl._gadgetKey;

		oAuthConsumerModelImpl._originalServiceName =
			oAuthConsumerModelImpl._serviceName;

		oAuthConsumerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<OAuthConsumer> toCacheModel() {
		OAuthConsumerCacheModel oAuthConsumerCacheModel =
			new OAuthConsumerCacheModel();

		oAuthConsumerCacheModel.oAuthConsumerId = getOAuthConsumerId();

		oAuthConsumerCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuthConsumerCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuthConsumerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			oAuthConsumerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			oAuthConsumerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		oAuthConsumerCacheModel.gadgetKey = getGadgetKey();

		String gadgetKey = oAuthConsumerCacheModel.gadgetKey;

		if ((gadgetKey != null) && (gadgetKey.length() == 0)) {
			oAuthConsumerCacheModel.gadgetKey = null;
		}

		oAuthConsumerCacheModel.serviceName = getServiceName();

		String serviceName = oAuthConsumerCacheModel.serviceName;

		if ((serviceName != null) && (serviceName.length() == 0)) {
			oAuthConsumerCacheModel.serviceName = null;
		}

		oAuthConsumerCacheModel.consumerKey = getConsumerKey();

		String consumerKey = oAuthConsumerCacheModel.consumerKey;

		if ((consumerKey != null) && (consumerKey.length() == 0)) {
			oAuthConsumerCacheModel.consumerKey = null;
		}

		oAuthConsumerCacheModel.consumerSecret = getConsumerSecret();

		String consumerSecret = oAuthConsumerCacheModel.consumerSecret;

		if ((consumerSecret != null) && (consumerSecret.length() == 0)) {
			oAuthConsumerCacheModel.consumerSecret = null;
		}

		oAuthConsumerCacheModel.keyType = getKeyType();

		String keyType = oAuthConsumerCacheModel.keyType;

		if ((keyType != null) && (keyType.length() == 0)) {
			oAuthConsumerCacheModel.keyType = null;
		}

		return oAuthConsumerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OAuthConsumer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OAuthConsumer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthConsumer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((OAuthConsumer)this));
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
		Map<String, Function<OAuthConsumer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OAuthConsumer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthConsumer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OAuthConsumer)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, OAuthConsumer>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _oAuthConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _gadgetKey;
	private String _originalGadgetKey;
	private String _serviceName;
	private String _originalServiceName;
	private String _consumerKey;
	private String _consumerSecret;
	private String _keyType;
	private long _columnBitmask;
	private OAuthConsumer _escapedModel;

}