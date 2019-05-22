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

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupGroupRoleModel;
import com.liferay.portal.kernel.model.UserGroupGroupRoleSoap;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.persistence.UserGroupGroupRolePK;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the UserGroupGroupRole service. Represents a row in the &quot;UserGroupGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>UserGroupGroupRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserGroupGroupRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class UserGroupGroupRoleModelImpl
	extends BaseModelImpl<UserGroupGroupRole>
	implements UserGroupGroupRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user group group role model instance should use the <code>UserGroupGroupRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserGroupGroupRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"userGroupId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"roleId", Types.BIGINT},
		{"companyId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserGroupGroupRole (mvccVersion LONG default 0 not null,userGroupId LONG not null,groupId LONG not null,roleId LONG not null,companyId LONG,primary key (userGroupId, groupId, roleId))";

	public static final String TABLE_SQL_DROP = "drop table UserGroupGroupRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userGroupGroupRole.id.userGroupId ASC, userGroupGroupRole.id.groupId ASC, userGroupGroupRole.id.roleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserGroupGroupRole.userGroupId ASC, UserGroupGroupRole.groupId ASC, UserGroupGroupRole.roleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.UserGroupGroupRole"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.UserGroupGroupRole"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.UserGroupGroupRole"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long ROLEID_COLUMN_BITMASK = 2L;

	public static final long USERGROUPID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static UserGroupGroupRole toModel(UserGroupGroupRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserGroupGroupRole model = new UserGroupGroupRoleImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUserGroupId(soapModel.getUserGroupId());
		model.setGroupId(soapModel.getGroupId());
		model.setRoleId(soapModel.getRoleId());
		model.setCompanyId(soapModel.getCompanyId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<UserGroupGroupRole> toModels(
		UserGroupGroupRoleSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<UserGroupGroupRole> models = new ArrayList<UserGroupGroupRole>(
			soapModels.length);

		for (UserGroupGroupRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserGroupGroupRole"));

	public UserGroupGroupRoleModelImpl() {
	}

	@Override
	public UserGroupGroupRolePK getPrimaryKey() {
		return new UserGroupGroupRolePK(_userGroupId, _groupId, _roleId);
	}

	@Override
	public void setPrimaryKey(UserGroupGroupRolePK primaryKey) {
		setUserGroupId(primaryKey.userGroupId);
		setGroupId(primaryKey.groupId);
		setRoleId(primaryKey.roleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new UserGroupGroupRolePK(_userGroupId, _groupId, _roleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((UserGroupGroupRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return UserGroupGroupRole.class;
	}

	@Override
	public String getModelClassName() {
		return UserGroupGroupRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserGroupGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserGroupGroupRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserGroupGroupRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserGroupGroupRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserGroupGroupRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserGroupGroupRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserGroupGroupRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserGroupGroupRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserGroupGroupRole.class.getClassLoader(), UserGroupGroupRole.class,
			ModelWrapper.class);

		try {
			Constructor<UserGroupGroupRole> constructor =
				(Constructor<UserGroupGroupRole>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserGroupGroupRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserGroupGroupRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<UserGroupGroupRole, Object>>();
		Map<String, BiConsumer<UserGroupGroupRole, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<UserGroupGroupRole, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", UserGroupGroupRole::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setMvccVersion);
		attributeGetterFunctions.put(
			"userGroupId", UserGroupGroupRole::getUserGroupId);
		attributeSetterBiConsumers.put(
			"userGroupId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setUserGroupId);
		attributeGetterFunctions.put("groupId", UserGroupGroupRole::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setGroupId);
		attributeGetterFunctions.put("roleId", UserGroupGroupRole::getRoleId);
		attributeSetterBiConsumers.put(
			"roleId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setRoleId);
		attributeGetterFunctions.put(
			"companyId", UserGroupGroupRole::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<UserGroupGroupRole, Long>)
				UserGroupGroupRole::setCompanyId);

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
	public long getUserGroupId() {
		return _userGroupId;
	}

	@Override
	public void setUserGroupId(long userGroupId) {
		_columnBitmask |= USERGROUPID_COLUMN_BITMASK;

		if (!_setOriginalUserGroupId) {
			_setOriginalUserGroupId = true;

			_originalUserGroupId = _userGroupId;
		}

		_userGroupId = userGroupId;
	}

	public long getOriginalUserGroupId() {
		return _originalUserGroupId;
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
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		_columnBitmask |= ROLEID_COLUMN_BITMASK;

		if (!_setOriginalRoleId) {
			_setOriginalRoleId = true;

			_originalRoleId = _roleId;
		}

		_roleId = roleId;
	}

	public long getOriginalRoleId() {
		return _originalRoleId;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public UserGroupGroupRole toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserGroupGroupRoleImpl userGroupGroupRoleImpl =
			new UserGroupGroupRoleImpl();

		userGroupGroupRoleImpl.setMvccVersion(getMvccVersion());
		userGroupGroupRoleImpl.setUserGroupId(getUserGroupId());
		userGroupGroupRoleImpl.setGroupId(getGroupId());
		userGroupGroupRoleImpl.setRoleId(getRoleId());
		userGroupGroupRoleImpl.setCompanyId(getCompanyId());

		userGroupGroupRoleImpl.resetOriginalValues();

		return userGroupGroupRoleImpl;
	}

	@Override
	public int compareTo(UserGroupGroupRole userGroupGroupRole) {
		UserGroupGroupRolePK primaryKey = userGroupGroupRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserGroupGroupRole)) {
			return false;
		}

		UserGroupGroupRole userGroupGroupRole = (UserGroupGroupRole)obj;

		UserGroupGroupRolePK primaryKey = userGroupGroupRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		UserGroupGroupRoleModelImpl userGroupGroupRoleModelImpl = this;

		userGroupGroupRoleModelImpl._originalUserGroupId =
			userGroupGroupRoleModelImpl._userGroupId;

		userGroupGroupRoleModelImpl._setOriginalUserGroupId = false;

		userGroupGroupRoleModelImpl._originalGroupId =
			userGroupGroupRoleModelImpl._groupId;

		userGroupGroupRoleModelImpl._setOriginalGroupId = false;

		userGroupGroupRoleModelImpl._originalRoleId =
			userGroupGroupRoleModelImpl._roleId;

		userGroupGroupRoleModelImpl._setOriginalRoleId = false;

		userGroupGroupRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserGroupGroupRole> toCacheModel() {
		UserGroupGroupRoleCacheModel userGroupGroupRoleCacheModel =
			new UserGroupGroupRoleCacheModel();

		userGroupGroupRoleCacheModel.userGroupGroupRolePK = getPrimaryKey();

		userGroupGroupRoleCacheModel.mvccVersion = getMvccVersion();

		userGroupGroupRoleCacheModel.userGroupId = getUserGroupId();

		userGroupGroupRoleCacheModel.groupId = getGroupId();

		userGroupGroupRoleCacheModel.roleId = getRoleId();

		userGroupGroupRoleCacheModel.companyId = getCompanyId();

		return userGroupGroupRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserGroupGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((UserGroupGroupRole)this));
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
		Map<String, Function<UserGroupGroupRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserGroupGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserGroupGroupRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, UserGroupGroupRole>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _mvccVersion;
	private long _userGroupId;
	private long _originalUserGroupId;
	private boolean _setOriginalUserGroupId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private long _companyId;
	private long _columnBitmask;
	private UserGroupGroupRole _escapedModel;

}