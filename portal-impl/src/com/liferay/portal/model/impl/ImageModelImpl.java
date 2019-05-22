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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.ImageModel;
import com.liferay.portal.kernel.model.ImageSoap;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the Image service. Represents a row in the &quot;Image&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ImageModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ImageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImageImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ImageModelImpl extends BaseModelImpl<Image> implements ImageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a image model instance should use the <code>Image</code> interface instead.
	 */
	public static final String TABLE_NAME = "Image";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"imageId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"modifiedDate", Types.TIMESTAMP},
		{"type_", Types.VARCHAR}, {"height", Types.INTEGER},
		{"width", Types.INTEGER}, {"size_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("imageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("height", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("width", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("size_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Image (mvccVersion LONG default 0 not null,imageId LONG not null primary key,companyId LONG,modifiedDate DATE null,type_ VARCHAR(75) null,height INTEGER,width INTEGER,size_ INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table Image";

	public static final String ORDER_BY_JPQL = " ORDER BY image.imageId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY Image.imageId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Image"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Image"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.Image"),
		true);

	public static final long SIZE_COLUMN_BITMASK = 1L;

	public static final long IMAGEID_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Image toModel(ImageSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Image model = new ImageImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setImageId(soapModel.getImageId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setType(soapModel.getType());
		model.setHeight(soapModel.getHeight());
		model.setWidth(soapModel.getWidth());
		model.setSize(soapModel.getSize());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Image> toModels(ImageSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Image> models = new ArrayList<Image>(soapModels.length);

		for (ImageSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Image"));

	public ImageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _imageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setImageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Image.class;
	}

	@Override
	public String getModelClassName() {
		return Image.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Image, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Image, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Image, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Image)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Image, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Image, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Image)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Image, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Image, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Image>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Image.class.getClassLoader(), Image.class, ModelWrapper.class);

		try {
			Constructor<Image> constructor =
				(Constructor<Image>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Image, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Image, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Image, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Image, Object>>();
		Map<String, BiConsumer<Image, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Image, ?>>();

		attributeGetterFunctions.put("mvccVersion", Image::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<Image, Long>)Image::setMvccVersion);
		attributeGetterFunctions.put("imageId", Image::getImageId);
		attributeSetterBiConsumers.put(
			"imageId", (BiConsumer<Image, Long>)Image::setImageId);
		attributeGetterFunctions.put("companyId", Image::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Image, Long>)Image::setCompanyId);
		attributeGetterFunctions.put("modifiedDate", Image::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Image, Date>)Image::setModifiedDate);
		attributeGetterFunctions.put("type", Image::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<Image, String>)Image::setType);
		attributeGetterFunctions.put("height", Image::getHeight);
		attributeSetterBiConsumers.put(
			"height", (BiConsumer<Image, Integer>)Image::setHeight);
		attributeGetterFunctions.put("width", Image::getWidth);
		attributeSetterBiConsumers.put(
			"width", (BiConsumer<Image, Integer>)Image::setWidth);
		attributeGetterFunctions.put("size", Image::getSize);
		attributeSetterBiConsumers.put(
			"size", (BiConsumer<Image, Integer>)Image::setSize);

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
	public long getImageId() {
		return _imageId;
	}

	@Override
	public void setImageId(long imageId) {
		_columnBitmask = -1L;

		_imageId = imageId;
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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
	public int getHeight() {
		return _height;
	}

	@Override
	public void setHeight(int height) {
		_height = height;
	}

	@JSON
	@Override
	public int getWidth() {
		return _width;
	}

	@Override
	public void setWidth(int width) {
		_width = width;
	}

	@JSON
	@Override
	public int getSize() {
		return _size;
	}

	@Override
	public void setSize(int size) {
		_columnBitmask |= SIZE_COLUMN_BITMASK;

		if (!_setOriginalSize) {
			_setOriginalSize = true;

			_originalSize = _size;
		}

		_size = size;
	}

	public int getOriginalSize() {
		return _originalSize;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Image.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Image toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ImageImpl imageImpl = new ImageImpl();

		imageImpl.setMvccVersion(getMvccVersion());
		imageImpl.setImageId(getImageId());
		imageImpl.setCompanyId(getCompanyId());
		imageImpl.setModifiedDate(getModifiedDate());
		imageImpl.setType(getType());
		imageImpl.setHeight(getHeight());
		imageImpl.setWidth(getWidth());
		imageImpl.setSize(getSize());

		imageImpl.resetOriginalValues();

		return imageImpl;
	}

	@Override
	public int compareTo(Image image) {
		int value = 0;

		if (getImageId() < image.getImageId()) {
			value = -1;
		}
		else if (getImageId() > image.getImageId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof Image)) {
			return false;
		}

		Image image = (Image)obj;

		long primaryKey = image.getPrimaryKey();

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
		ImageModelImpl imageModelImpl = this;

		imageModelImpl._originalSize = imageModelImpl._size;

		imageModelImpl._setOriginalSize = false;

		imageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Image> toCacheModel() {
		ImageCacheModel imageCacheModel = new ImageCacheModel();

		imageCacheModel.mvccVersion = getMvccVersion();

		imageCacheModel.imageId = getImageId();

		imageCacheModel.companyId = getCompanyId();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			imageCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			imageCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		imageCacheModel.type = getType();

		String type = imageCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			imageCacheModel.type = null;
		}

		imageCacheModel.height = getHeight();

		imageCacheModel.width = getWidth();

		imageCacheModel.size = getSize();

		return imageCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Image, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Image, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Image, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Image)this));
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
		Map<String, Function<Image, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Image, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Image, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Image)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Image>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _mvccVersion;
	private long _imageId;
	private long _companyId;
	private Date _modifiedDate;
	private String _type;
	private int _height;
	private int _width;
	private int _size;
	private int _originalSize;
	private boolean _setOriginalSize;
	private long _columnBitmask;
	private Image _escapedModel;

}