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

package com.liferay.commerce.account.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceAccount service. Represents a row in the &quot;CommerceAccount&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.account.model.impl.CommerceAccountModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.account.model.impl.CommerceAccountImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccount
 * @see com.liferay.commerce.account.model.impl.CommerceAccountImpl
 * @see com.liferay.commerce.account.model.impl.CommerceAccountModelImpl
 * @generated
 */
@ProviderType
public interface CommerceAccountModel extends AuditedModel,
	BaseModel<CommerceAccount>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce account model instance should use the {@link CommerceAccount} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce account.
	 *
	 * @return the primary key of this commerce account
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce account.
	 *
	 * @param primaryKey the primary key of this commerce account
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the external reference code of this commerce account.
	 *
	 * @return the external reference code of this commerce account
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce account.
	 *
	 * @param externalReferenceCode the external reference code of this commerce account
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce account ID of this commerce account.
	 *
	 * @return the commerce account ID of this commerce account
	 */
	public long getCommerceAccountId();

	/**
	 * Sets the commerce account ID of this commerce account.
	 *
	 * @param commerceAccountId the commerce account ID of this commerce account
	 */
	public void setCommerceAccountId(long commerceAccountId);

	/**
	 * Returns the company ID of this commerce account.
	 *
	 * @return the company ID of this commerce account
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce account.
	 *
	 * @param companyId the company ID of this commerce account
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce account.
	 *
	 * @return the user ID of this commerce account
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce account.
	 *
	 * @param userId the user ID of this commerce account
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce account.
	 *
	 * @return the user uuid of this commerce account
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce account.
	 *
	 * @param userUuid the user uuid of this commerce account
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce account.
	 *
	 * @return the user name of this commerce account
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce account.
	 *
	 * @param userName the user name of this commerce account
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce account.
	 *
	 * @return the create date of this commerce account
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce account.
	 *
	 * @param createDate the create date of this commerce account
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce account.
	 *
	 * @return the modified date of this commerce account
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce account.
	 *
	 * @param modifiedDate the modified date of this commerce account
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this commerce account.
	 *
	 * @return the name of this commerce account
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this commerce account.
	 *
	 * @param name the name of this commerce account
	 */
	public void setName(String name);

	/**
	 * Returns the parent commerce account ID of this commerce account.
	 *
	 * @return the parent commerce account ID of this commerce account
	 */
	public long getParentCommerceAccountId();

	/**
	 * Sets the parent commerce account ID of this commerce account.
	 *
	 * @param parentCommerceAccountId the parent commerce account ID of this commerce account
	 */
	public void setParentCommerceAccountId(long parentCommerceAccountId);

	/**
	 * Returns the tax ID of this commerce account.
	 *
	 * @return the tax ID of this commerce account
	 */
	@AutoEscape
	public String getTaxId();

	/**
	 * Sets the tax ID of this commerce account.
	 *
	 * @param taxId the tax ID of this commerce account
	 */
	public void setTaxId(String taxId);

	/**
	 * Returns the active of this commerce account.
	 *
	 * @return the active of this commerce account
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this commerce account is active.
	 *
	 * @return <code>true</code> if this commerce account is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this commerce account is active.
	 *
	 * @param active the active of this commerce account
	 */
	public void setActive(boolean active);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceAccount commerceAccount);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceAccount> toCacheModel();

	@Override
	public CommerceAccount toEscapedModel();

	@Override
	public CommerceAccount toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}