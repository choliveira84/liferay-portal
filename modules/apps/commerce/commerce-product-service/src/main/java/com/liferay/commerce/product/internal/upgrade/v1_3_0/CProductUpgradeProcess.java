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

package com.liferay.commerce.product.internal.upgrade.v1_3_0;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CPDefinitionModelImpl;
import com.liferay.commerce.product.model.impl.CProductModelImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CProductUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CProductModelImpl.TABLE_NAME)) {
			runSQL(CProductModelImpl.TABLE_SQL_CREATE);
		}

		addColumn(
			CPDefinitionModelImpl.class, CPDefinitionModelImpl.TABLE_NAME,
			"CProductId", "LONG");
		addColumn(
			CPDefinitionModelImpl.class, CPDefinitionModelImpl.TABLE_NAME,
			"version", "INTEGER");

		String insertCProductSQL = StringBundler.concat(
			"insert into CProduct (uuid_, CProductId, groupId, companyId, ",
			"userId, userName, createDate, modifiedDate, ",
			"publishedCPDefinitionId, latestVersion) values (?, ?, ?, ?, ?, ",
			"?, ?, ?, ?, 1)");
		String updateCPDefinitionSQL =
			"update CPDefinition set CProductId = ?, version = 1 where " +
				"CPDefinitionId = ?";

		try (PreparedStatement preparedStatement1 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, insertCProductSQL);
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPDefinitionSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = s.executeQuery(
				"select cpDefinitionId, groupId, companyId, userId, userName " +
					"from CPDefinition")) {

			while (resultSet.next()) {
				String uuid = PortalUUIDUtil.generate();
				long cProductId = increment();
				long groupId = resultSet.getLong("groupId");
				long companyId = resultSet.getLong("companyId");
				long userId = resultSet.getLong("userId");
				String userName = resultSet.getString("userName");
				long cpDefinitionId = resultSet.getLong("CPDefinitionId");

				preparedStatement1.setString(1, uuid);
				preparedStatement1.setLong(2, cProductId);
				preparedStatement1.setLong(3, groupId);
				preparedStatement1.setLong(4, companyId);
				preparedStatement1.setLong(5, userId);
				preparedStatement1.setString(6, userName);

				Date now = new Date(System.currentTimeMillis());

				preparedStatement1.setDate(7, now);
				preparedStatement1.setDate(8, now);

				preparedStatement1.setLong(9, cpDefinitionId);

				preparedStatement1.addBatch();

				preparedStatement2.setLong(1, cProductId);
				preparedStatement2.setLong(2, cpDefinitionId);

				preparedStatement2.addBatch();
			}

			preparedStatement1.executeBatch();
			preparedStatement2.executeBatch();
		}
	}

}