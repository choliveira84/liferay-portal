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

package com.liferay.portal.tools.service.builder.test.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.tools.service.builder.test.model.LVEntry;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalization;
import com.liferay.portal.tools.service.builder.test.model.LVEntryModel;
import com.liferay.portal.tools.service.builder.test.model.LVEntryVersion;
import com.liferay.portal.tools.service.builder.test.service.LVEntryLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the LVEntry service. Represents a row in the &quot;LVEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>LVEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LVEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryImpl
 * @generated
 */
@ProviderType
public class LVEntryModelImpl
	extends BaseModelImpl<LVEntry> implements LVEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lv entry model instance should use the <code>LVEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "LVEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"headId", Types.BIGINT}, {"head", Types.BOOLEAN},
		{"defaultLanguageId", Types.VARCHAR}, {"lvEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"uniqueGroupKey", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("defaultLanguageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lvEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uniqueGroupKey", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LVEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,headId LONG,head BOOLEAN,defaultLanguageId VARCHAR(75) null,lvEntryId LONG not null primary key,companyId LONG,groupId LONG,uniqueGroupKey VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table LVEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY lvEntry.lvEntryId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY LVEntry.lvEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.entity.cache.enabled.com.liferay.portal.tools.service.builder.test.model.LVEntry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.finder.cache.enabled.com.liferay.portal.tools.service.builder.test.model.LVEntry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.column.bitmask.enabled.com.liferay.portal.tools.service.builder.test.model.LVEntry"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long HEAD_COLUMN_BITMASK = 4L;

	public static final long HEADID_COLUMN_BITMASK = 8L;

	public static final long UNIQUEGROUPKEY_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long LVENTRYID_COLUMN_BITMASK = 64L;

	public static final String MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_NAME =
		"BigDecimalEntries_LVEntries";

	public static final Object[][]
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"bigDecimalEntryId", Types.BIGINT},
			{"lvEntryId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_SQL_CREATE =
			"create table BigDecimalEntries_LVEntries (companyId LONG not null,bigDecimalEntryId LONG not null,lvEntryId LONG not null,primary key (bigDecimalEntryId, lvEntryId))";

	public static final boolean
		FINDER_CACHE_ENABLED_BIGDECIMALENTRIES_LVENTRIES =
			GetterUtil.getBoolean(
				com.liferay.portal.tools.service.builder.test.service.util.
					ServiceProps.get(
						"value.object.finder.cache.enabled.BigDecimalEntries_LVEntries"),
				true);

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.LVEntry"));

	public LVEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lvEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLvEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lvEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LVEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LVEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((LVEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LVEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LVEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LVEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LVEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LVEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LVEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LVEntry.class.getClassLoader(), LVEntry.class, ModelWrapper.class);

		try {
			Constructor<LVEntry> constructor =
				(Constructor<LVEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LVEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LVEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LVEntry, Object>>();
		Map<String, BiConsumer<LVEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LVEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object mvccVersion) {
					lvEntry.setMvccVersion((Long)mvccVersion);
				}

			});
		attributeGetterFunctions.put(
			"uuid",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object uuid) {
					lvEntry.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"headId",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getHeadId();
				}

			});
		attributeSetterBiConsumers.put(
			"headId",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object headId) {
					lvEntry.setHeadId((Long)headId);
				}

			});
		attributeGetterFunctions.put(
			"defaultLanguageId",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getDefaultLanguageId();
				}

			});
		attributeSetterBiConsumers.put(
			"defaultLanguageId",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object defaultLanguageId) {
					lvEntry.setDefaultLanguageId((String)defaultLanguageId);
				}

			});
		attributeGetterFunctions.put(
			"lvEntryId",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getLvEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"lvEntryId",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object lvEntryId) {
					lvEntry.setLvEntryId((Long)lvEntryId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object companyId) {
					lvEntry.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object groupId) {
					lvEntry.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"uniqueGroupKey",
			new Function<LVEntry, Object>() {

				@Override
				public Object apply(LVEntry lvEntry) {
					return lvEntry.getUniqueGroupKey();
				}

			});
		attributeSetterBiConsumers.put(
			"uniqueGroupKey",
			new BiConsumer<LVEntry, Object>() {

				@Override
				public void accept(LVEntry lvEntry, Object uniqueGroupKey) {
					lvEntry.setUniqueGroupKey((String)uniqueGroupKey);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		List<LVEntryLocalization> lvEntryLocalizations =
			LVEntryLocalServiceUtil.getLVEntryLocalizations(getPrimaryKey());

		String[] availableLanguageIds = new String[lvEntryLocalizations.size()];

		for (int i = 0; i < availableLanguageIds.length; i++) {
			LVEntryLocalization lvEntryLocalization = lvEntryLocalizations.get(
				i);

			availableLanguageIds[i] = lvEntryLocalization.getLanguageId();
		}

		return availableLanguageIds;
	}

	@Override
	public String getTitle() {
		return getTitle(getDefaultLanguageId(), false);
	}

	@Override
	public String getTitle(String languageId) {
		return getTitle(languageId, true);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		if (useDefault) {
			return LocalizationUtil.getLocalization(
				new Function<String, String>() {

					@Override
					public String apply(String languageId) {
						return _getTitle(languageId);
					}

				},
				languageId, getDefaultLanguageId());
		}

		return _getTitle(languageId);
	}

	@Override
	public String getTitleMapAsXML() {
		return LocalizationUtil.getXml(
			getLanguageIdToTitleMap(), getDefaultLanguageId(), "Title");
	}

	@Override
	public Map<String, String> getLanguageIdToTitleMap() {
		Map<String, String> languageIdToTitleMap =
			new HashMap<String, String>();

		List<LVEntryLocalization> lvEntryLocalizations =
			LVEntryLocalServiceUtil.getLVEntryLocalizations(getPrimaryKey());

		for (LVEntryLocalization lvEntryLocalization : lvEntryLocalizations) {
			languageIdToTitleMap.put(
				lvEntryLocalization.getLanguageId(),
				lvEntryLocalization.getTitle());
		}

		return languageIdToTitleMap;
	}

	private String _getTitle(String languageId) {
		LVEntryLocalization lvEntryLocalization =
			LVEntryLocalServiceUtil.fetchLVEntryLocalization(
				getPrimaryKey(), languageId);

		if (lvEntryLocalization == null) {
			return "";
		}

		return lvEntryLocalization.getTitle();
	}

	@Override
	public String getContent() {
		return getContent(getDefaultLanguageId(), false);
	}

	@Override
	public String getContent(String languageId) {
		return getContent(languageId, true);
	}

	@Override
	public String getContent(String languageId, boolean useDefault) {
		if (useDefault) {
			return LocalizationUtil.getLocalization(
				new Function<String, String>() {

					@Override
					public String apply(String languageId) {
						return _getContent(languageId);
					}

				},
				languageId, getDefaultLanguageId());
		}

		return _getContent(languageId);
	}

	@Override
	public String getContentMapAsXML() {
		return LocalizationUtil.getXml(
			getLanguageIdToContentMap(), getDefaultLanguageId(), "Content");
	}

	@Override
	public Map<String, String> getLanguageIdToContentMap() {
		Map<String, String> languageIdToContentMap =
			new HashMap<String, String>();

		List<LVEntryLocalization> lvEntryLocalizations =
			LVEntryLocalServiceUtil.getLVEntryLocalizations(getPrimaryKey());

		for (LVEntryLocalization lvEntryLocalization : lvEntryLocalizations) {
			languageIdToContentMap.put(
				lvEntryLocalization.getLanguageId(),
				lvEntryLocalization.getContent());
		}

		return languageIdToContentMap;
	}

	private String _getContent(String languageId) {
		LVEntryLocalization lvEntryLocalization =
			LVEntryLocalServiceUtil.fetchLVEntryLocalization(
				getPrimaryKey(), languageId);

		if (lvEntryLocalization == null) {
			return "";
		}

		return lvEntryLocalization.getContent();
	}

	public boolean getHead() {
		return _head;
	}

	@Override
	public boolean isHead() {
		return _head;
	}

	public boolean getOriginalHead() {
		return _originalHead;
	}

	public void setHead(boolean head) {
		_columnBitmask |= HEAD_COLUMN_BITMASK;

		if (!_setOriginalHead) {
			_setOriginalHead = true;

			_originalHead = _head;
		}

		_head = head;
	}

	@Override
	public void populateVersionModel(LVEntryVersion lvEntryVersion) {
		lvEntryVersion.setUuid(getUuid());
		lvEntryVersion.setDefaultLanguageId(getDefaultLanguageId());
		lvEntryVersion.setCompanyId(getCompanyId());
		lvEntryVersion.setGroupId(getGroupId());
		lvEntryVersion.setUniqueGroupKey(getUniqueGroupKey());
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

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

	@Override
	public long getHeadId() {
		return _headId;
	}

	@Override
	public void setHeadId(long headId) {
		_columnBitmask |= HEADID_COLUMN_BITMASK;

		if (!_setOriginalHeadId) {
			_setOriginalHeadId = true;

			_originalHeadId = _headId;
		}

		if (headId >= 0) {
			setHead(false);
		}
		else {
			setHead(true);
		}

		_headId = headId;
	}

	public long getOriginalHeadId() {
		return _originalHeadId;
	}

	@Override
	public String getDefaultLanguageId() {
		if (_defaultLanguageId == null) {
			return "";
		}
		else {
			return _defaultLanguageId;
		}
	}

	@Override
	public void setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
	}

	@Override
	public long getLvEntryId() {
		return _lvEntryId;
	}

	@Override
	public void setLvEntryId(long lvEntryId) {
		_lvEntryId = lvEntryId;
	}

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

	@Override
	public String getUniqueGroupKey() {
		if (_uniqueGroupKey == null) {
			return "";
		}
		else {
			return _uniqueGroupKey;
		}
	}

	@Override
	public void setUniqueGroupKey(String uniqueGroupKey) {
		_columnBitmask |= UNIQUEGROUPKEY_COLUMN_BITMASK;

		if (_originalUniqueGroupKey == null) {
			_originalUniqueGroupKey = _uniqueGroupKey;
		}

		_uniqueGroupKey = uniqueGroupKey;
	}

	public String getOriginalUniqueGroupKey() {
		return GetterUtil.getString(_originalUniqueGroupKey);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LVEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LVEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LVEntryImpl lvEntryImpl = new LVEntryImpl();

		lvEntryImpl.setMvccVersion(getMvccVersion());
		lvEntryImpl.setUuid(getUuid());
		lvEntryImpl.setHeadId(getHeadId());
		lvEntryImpl.setDefaultLanguageId(getDefaultLanguageId());
		lvEntryImpl.setLvEntryId(getLvEntryId());
		lvEntryImpl.setCompanyId(getCompanyId());
		lvEntryImpl.setGroupId(getGroupId());
		lvEntryImpl.setUniqueGroupKey(getUniqueGroupKey());

		lvEntryImpl.resetOriginalValues();

		return lvEntryImpl;
	}

	@Override
	public int compareTo(LVEntry lvEntry) {
		long primaryKey = lvEntry.getPrimaryKey();

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

		if (!(obj instanceof LVEntry)) {
			return false;
		}

		LVEntry lvEntry = (LVEntry)obj;

		long primaryKey = lvEntry.getPrimaryKey();

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
		LVEntryModelImpl lvEntryModelImpl = this;

		lvEntryModelImpl._originalUuid = lvEntryModelImpl._uuid;

		lvEntryModelImpl._originalHeadId = lvEntryModelImpl._headId;

		lvEntryModelImpl._setOriginalHeadId = false;

		lvEntryModelImpl._originalHead = lvEntryModelImpl._head;

		lvEntryModelImpl._setOriginalHead = false;

		lvEntryModelImpl._originalCompanyId = lvEntryModelImpl._companyId;

		lvEntryModelImpl._setOriginalCompanyId = false;

		lvEntryModelImpl._originalGroupId = lvEntryModelImpl._groupId;

		lvEntryModelImpl._setOriginalGroupId = false;

		lvEntryModelImpl._originalUniqueGroupKey =
			lvEntryModelImpl._uniqueGroupKey;

		lvEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LVEntry> toCacheModel() {
		LVEntryCacheModel lvEntryCacheModel = new LVEntryCacheModel();

		lvEntryCacheModel.mvccVersion = getMvccVersion();

		lvEntryCacheModel.uuid = getUuid();

		String uuid = lvEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			lvEntryCacheModel.uuid = null;
		}

		lvEntryCacheModel.headId = getHeadId();

		lvEntryCacheModel.head = isHead();

		lvEntryCacheModel.defaultLanguageId = getDefaultLanguageId();

		String defaultLanguageId = lvEntryCacheModel.defaultLanguageId;

		if ((defaultLanguageId != null) && (defaultLanguageId.length() == 0)) {
			lvEntryCacheModel.defaultLanguageId = null;
		}

		lvEntryCacheModel.lvEntryId = getLvEntryId();

		lvEntryCacheModel.companyId = getCompanyId();

		lvEntryCacheModel.groupId = getGroupId();

		lvEntryCacheModel.uniqueGroupKey = getUniqueGroupKey();

		String uniqueGroupKey = lvEntryCacheModel.uniqueGroupKey;

		if ((uniqueGroupKey != null) && (uniqueGroupKey.length() == 0)) {
			lvEntryCacheModel.uniqueGroupKey = null;
		}

		return lvEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LVEntry)this));
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
		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LVEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, LVEntry>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _headId;
	private long _originalHeadId;
	private boolean _setOriginalHeadId;
	private boolean _head;
	private boolean _originalHead;
	private boolean _setOriginalHead;
	private String _defaultLanguageId;
	private long _lvEntryId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _uniqueGroupKey;
	private String _originalUniqueGroupKey;
	private long _columnBitmask;
	private LVEntry _escapedModel;

}