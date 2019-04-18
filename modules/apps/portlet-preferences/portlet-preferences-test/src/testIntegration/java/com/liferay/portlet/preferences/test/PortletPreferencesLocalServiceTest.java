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

package com.liferay.portlet.preferences.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.deploy.hot.ServiceBag;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.PortletPreferencesIds;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactory;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.model.impl.PortletAppImpl;
import com.liferay.portal.spring.aop.AopInvocationHandler;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;
import com.liferay.portlet.PortletPreferencesImpl;
import com.liferay.portlet.StrictPortletPreferencesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class PortletPreferencesLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		_portletApp.setServletContext(
			(ServletContext)ProxyUtil.newProxyInstance(
				PortletPreferencesLocalServiceTest.class.getClassLoader(),
				new Class<?>[] {ServletContext.class},
				(proxy, method, args) -> {
					if ("getServletContextName".equals(method.getName())) {
						return StringPool.BLANK;
					}

					return null;
				}));
	}

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_groups.add(_group);

		_layout = LayoutTestUtil.addLayout(_group);

		_portlet = _portletLocalService.getPortletById(
			_layout.getCompanyId(), String.valueOf(_PORTLET_ID));

		_portlet.setPortletApp(_portletApp);
	}

	@After
	public void tearDown() {
		_portletLocalService.destroyPortlet(_portlet);
	}

	@Test
	public void testAddPortletPreferencesWithDefaultMultipleXML()
		throws Exception {

		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _MULTIPLE_VALUES);

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet, portletPreferencesXML);

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferences);

		assertOwner(_layout, portletPreferencesImpl);

		assertValues(portletPreferences, _NAME, _MULTIPLE_VALUES);
	}

	@Test
	public void testAddPortletPreferencesWithDefaultNullXML() throws Exception {
		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet, null);

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferences);

		assertOwner(_layout, portletPreferencesImpl);
		assertEmptyPortletPreferencesMap(portletPreferencesImpl);

		javax.portlet.PortletPreferences jxPortletPreferences =
			PortletPreferencesTestUtil.fetchLayoutJxPortletPreferences(
				_layout, _portlet);

		assertOwner(_layout, (PortletPreferencesImpl)jxPortletPreferences);
		assertEmptyPortletPreferencesMap(jxPortletPreferences);
	}

	@Test
	public void testAddPortletPreferencesWithDefaultNullXMLAndNullPortlet()
		throws Exception {

		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.addPortletPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId(), null, null);

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferences);

		assertOwner(_layout, portletPreferencesImpl);
		assertEmptyPortletPreferencesMap(portletPreferencesImpl);
	}

	@Test
	public void testAddPortletPreferencesWithDefaultSingleXML()
		throws Exception {

		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet, portletPreferencesXML);

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferences);

		assertOwner(_layout, portletPreferencesImpl);

		assertValues(portletPreferences, _NAME, _SINGLE_VALUE);
	}

	@Test
	public void testAddPortletPreferencesWithPortlet() throws Exception {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		_portlet.setDefaultPreferences(portletPreferencesXML);

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet);

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferences);

		assertOwner(_layout, portletPreferencesImpl);

		assertValues(portletPreferences, _NAME, _SINGLE_VALUE);
	}

	@Test
	public void testDeleteGroupPortletPreferencesByPlid() {
		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addGroupPortletPreferences(
				_layout, _portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
			_layout.getPlid());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteGroupPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addGroupPortletPreferences(
				_layout, _portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
			_layout.getPlid(), _portlet.getPortletId());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteLayoutPortletPreferencesByPlid() throws Exception {
		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteLayoutPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteOriginalGroupPortletPreferencesByPlid()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Group group = GroupTestUtil.addGroup();

		_groups.add(group);

		Layout layout = LayoutTestUtil.addLayout(group);

		Portlet portlet = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addGroupPortletPreferences(
				layout, portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
			_layout.getPlid());

		Assert.assertNotNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteOriginalGroupPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Group group = GroupTestUtil.addGroup();

		_groups.add(group);

		Layout layout = LayoutTestUtil.addLayout(group);

		Portlet portlet = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addGroupPortletPreferences(
				layout, portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
			_layout.getPlid(), _portlet.getPortletId());

		Assert.assertNotNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteOriginalLayoutPortletPreferencesByPlid()
		throws Exception {

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Group group = GroupTestUtil.addGroup();

		_groups.add(group);

		Layout layout = LayoutTestUtil.addLayout(group);

		Portlet portlet = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferences portletPreferences2 =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				layout, portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid());

		Assert.assertNotNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences2.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteOriginalLayoutPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Portlet portlet = _portletLocalService.getPortletById(
			_layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId());

		Assert.assertNotNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeleteOriginalPortletPreferencesByPlid() throws Exception {
		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Portlet portlet = _portletLocalService.getPortletById(
			_layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, portlet);

		_portletPreferencesLocalService.deletePortletPreferencesByPlid(
			_layout.getPlid());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeletePortletPreferencesByPlid() throws Exception {
		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet);

		_portletPreferencesLocalService.deletePortletPreferencesByPlid(
			_layout.getPlid());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testDeletePortletPreferencesByPortletPreferencesId()
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet);

		_portletPreferencesLocalService.deletePortletPreferences(
			portletPreferences.getPortletPreferencesId());

		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				portletPreferences.getPortletPreferencesId()));
	}

	@Test
	public void testFetchLayoutJxPortletPreferences() throws Exception {
		PortletPreferencesImpl portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesTestUtil.fetchLayoutJxPortletPreferences(
					_layout, _portlet);

		Assert.assertNull(portletPreferencesImpl);
	}

	@Test
	public void testFetchNonexistentPreferences() throws Exception {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, portletPreferencesXML);

		_portletPreferencesLocalService.deletePortletPreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId());

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.fetchPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		Assert.assertNull(jxPortletPreferences);
	}

	@Test
	public void testFetchPreferences() throws Exception {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, portletPreferencesXML);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.fetchPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
	}

	@Test
	public void testFetchPreferencesByPortletPreferencesIds() throws Exception {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, portletPreferencesXML);

		PortletPreferencesIds portletPreferencesIds = new PortletPreferencesIds(
			_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId());

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.fetchPreferences(
				portletPreferencesIds);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
	}

	@Test
	public void testGetAllPortletPreferences() throws Exception {
		List<PortletPreferences> initialPortletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences();

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> currentPortletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences();

		Assert.assertEquals(
			currentPortletPreferencesList.toString(),
			initialPortletPreferencesList.size() + 1,
			currentPortletPreferencesList.size());
	}

	@Test
	public void testGetGroupPortletPreferencesByCompanyIdAndGroupIdAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Layout layout = LayoutTestUtil.addLayout(GroupTestUtil.addGroup());

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_layout.getCompanyId(), _layout.getGroupId(),
				_layout.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), false);

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		PortletPreferences portletPreferences = portletPreferencesList.get(0);

		Assert.assertEquals(_layout.getPlid(), portletPreferences.getPlid());
	}

	@Test
	public void testGetGroupPortletPreferencesByOwnerAndPlid() {
		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());
	}

	@Test
	public void testGetGroupPortletPreferencesByOwnerAndPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.getPortletPreferences(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet.getPortletId());

		Assert.assertEquals(
			portletPreferences.getPortletId(), _portlet.getPortletId());

		assertOwner(_group, _toPortletPreferencesImpl(portletPreferences));
	}

	@Test
	public void testGetGroupPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
				_portlet.getPortletId());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferencesList.get(0));

		assertOwner(_layout.getGroup(), portletPreferencesImpl);
	}

	@Test
	public void testGetGroupPortletPreferencesCountByOwnerAndNotPlidAndPortlet() {
		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP, -1,
				_portlet, false));

		_portletPreferencesLocalService.addPortletPreferences(
			_portlet.getCompanyId(), _group.getGroupId(),
			PortletKeys.PREFS_OWNER_TYPE_GROUP, -1, _portlet.getPortletId(),
			_portlet, null);

		Assert.assertEquals(
			1,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP, -1,
				_portlet, false));
	}

	@Test
	public void testGetGroupPortletPreferencesCountByOwnerAndPlidAndPortlet() {
		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet, false));

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Assert.assertEquals(
			1,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet, false));
	}

	@Test
	public void testGetGroupPortletPreferencesCountByOwnerAndPlidAndPortletExcludeDefault() {
		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet, true));

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet, true));
	}

	@Test
	public void testGetGroupPortletPreferencesCountByOwnerAndPortletId() {
		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), false));

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Assert.assertEquals(
			1,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), false));
	}

	@Test
	public void testGetGroupPortletPreferencesCountByOwnerAndPortletIdExcludeDefault() {
		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), true));

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), true));
	}

	@Test
	public void testGetGroupPreferencesByOwnerAndPlidAndPortletIdNotAutoAdded() {
		String singleValuePortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet, singleValuePortletPreferencesXML);

		String multipleValuesPortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _MULTIPLE_VALUES);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_group.getCompanyId(), _group.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
				_portlet.getPortletId(), multipleValuesPortletPreferencesXML);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
		assertOwner(
			_layout.getGroup(), (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetGroupPreferencesByOwnerAndPlidAndPortletIdWithoutDefaultAutoAdded() {
		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_group.getCompanyId(), _group.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
				_portlet.getPortletId());

		assertEmptyPortletPreferencesMap(jxPortletPreferences);
		assertOwner(
			_layout.getGroup(), (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetGroupPreferencesByPortletPreferencesIds() {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet, portletPreferencesXML);

		PortletPreferencesIds portletPreferencesIds = new PortletPreferencesIds(
			_group.getCompanyId(), _group.getGroupId(),
			PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
			_portlet.getPortletId());

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				portletPreferencesIds);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
		assertOwner(
			_layout.getGroup(), (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetGroupreferencesByOwnerAndPlidAndPortletIdWithDefaultXMLAutoAdded() {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_group.getCompanyId(), _group.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
				_portlet.getPortletId(), portletPreferencesXML);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
		assertOwner(
			_layout.getGroup(), (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetLayoutPortletPreferencesByCompanyIdAndGroupIdAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Layout layout = LayoutTestUtil.addLayout(_layout.getGroup());

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_layout.getCompanyId(), _layout.getGroupId(),
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _portlet.getPortletId(),
				false);

		Assert.assertEquals(
			portletPreferencesList.toString(), 2,
			portletPreferencesList.size());

		PortletPreferences portletPreferences = portletPreferencesList.get(0);

		Assert.assertEquals(
			_portlet.getPortletId(), portletPreferences.getPortletId());
	}

	@Test
	public void testGetLayoutPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferencesList.get(0));

		assertOwner(_layout, portletPreferencesImpl);
	}

	@Test
	public void testGetLayoutPortletPreferencesCountByPlidAndPortletId()
		throws Exception {

		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId()));

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		_portletLocalService.deployPortlet(_portlet);

		Assert.assertEquals(
			1,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId()));
	}

	@Test
	public void testGetLayoutPortletPreferencesCountByPortletId()
		throws Exception {

		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _portlet.getPortletId()));

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Layout layout = LayoutTestUtil.addLayout(_group);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, _portlet);

		Assert.assertEquals(
			2,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _portlet.getPortletId()));
	}

	@Test
	public void testGetLayoutPreferencesByOwnerAndPlidAndPortletIdNotAutoAdded()
		throws Exception {

		String singleValuePortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, singleValuePortletPreferencesXML);

		String multipleValuesPortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _MULTIPLE_VALUES);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId(), multipleValuesPortletPreferencesXML);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
		assertOwner(_layout, (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetLayoutPreferencesByOwnerAndPlidAndPortletIdWithDefaultXMLAutoAdded() {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId(), portletPreferencesXML);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
		assertOwner(_layout, (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetLayoutPreferencesByOwnerAndPlidAndPortletIdWithoutDefaultAutoAdded() {
		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertEmptyPortletPreferencesMap(jxPortletPreferences);
		assertOwner(_layout, (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetLayoutPreferencesByPortletPreferencesIds()
		throws Exception {

		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, portletPreferencesXML);

		PortletPreferencesIds portletPreferencesIds = new PortletPreferencesIds(
			_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId());

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				portletPreferencesIds);

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
		assertOwner(_layout, (PortletPreferencesImpl)jxPortletPreferences);
	}

	@Test
	public void testGetLayoutPrivatePortletPreferences() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(
			GroupTestUtil.addGroup(), true);

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				layout.getCompanyId(), layout.getGroupId(), layout.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _portlet.getPortletId(),
				true);

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());
	}

	@Test
	public void testGetNotLayoutPrivatePortletPreferences() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(
			GroupTestUtil.addGroup(), false);

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				layout.getCompanyId(), layout.getGroupId(), layout.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _portlet.getPortletId(),
				false);

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());
	}

	@Test
	public void testGetNotStrictPortletPreferences() throws Exception {
		replaceService();

		try {
			javax.portlet.PortletPreferences jxPortletPreferences =
				_portletPreferencesLocalService.getStrictPreferences(
					_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
					_portlet.getPortletId());

			assertEmptyPortletPreferencesMap(jxPortletPreferences);
		}
		finally {
			resetService();
		}
	}

	@Test
	public void testGetOriginalGroupPortletPreferencesByCompanyIdAndGroupIdAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_layout.getCompanyId(), _layout.getGroupId(),
				_layout.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), false);

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		Layout layout = LayoutTestUtil.addLayout(GroupTestUtil.addGroup());

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, _portlet);

		portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_layout.getCompanyId(), _layout.getGroupId(),
				_layout.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_portlet.getPortletId(), false);

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());
	}

	@Test
	public void testGetOriginalGroupPortletPreferencesByOwnerAndPlid() {
		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		Portlet portlet = _portletLocalService.getPortletById(
			_layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferencesTestUtil.addGroupPortletPreferences(_layout, portlet);

		portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid());

		Assert.assertEquals(
			portletPreferencesList.toString(), 2,
			portletPreferencesList.size());
	}

	@Test
	public void testGetOriginalGroupPortletPreferencesByOwnerAndPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.getPortletPreferences(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet.getPortletId());

		Assert.assertEquals(
			portletPreferences.getPortletId(), _portlet.getPortletId());

		Layout layout = LayoutTestUtil.addLayout(GroupTestUtil.addGroup());

		Portlet portlet1 = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, portlet1);

		Portlet portlet2 = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 2));

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, portlet2);

		portletPreferences =
			_portletPreferencesLocalService.getPortletPreferences(
				_group.getGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
				_layout.getPlid(), _portlet.getPortletId());

		Assert.assertEquals(
			portletPreferences.getPortletId(), _portlet.getPortletId());
	}

	@Test
	public void testGetOriginalGroupPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
				_portlet.getPortletId());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		Group group = GroupTestUtil.addGroup();

		_groups.add(group);

		Layout layout = LayoutTestUtil.addLayout(group);

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, _portlet);

		portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				PortletKeys.PREFS_OWNER_TYPE_GROUP, _layout.getPlid(),
				_portlet.getPortletId());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());
	}

	@Test
	public void testGetOriginalLayoutPortletPreferencesByPlidAndPortletId()
		throws Exception {

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferencesList.get(0));

		assertOwner(_layout, portletPreferencesImpl);

		Group group = GroupTestUtil.addGroup();

		_groups.add(group);

		Layout layout = LayoutTestUtil.addLayout(group);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, _portlet);

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());
	}

	@Test
	public void testGetOriginalLayoutPortletPreferencesCountByPlidAndPortletId()
		throws Exception {

		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId()));

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		_portletLocalService.deployPortlet(_portlet);

		Assert.assertEquals(
			1,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId()));

		Group group = GroupTestUtil.addGroup();

		_groups.add(group);

		Layout layout = LayoutTestUtil.addLayout(group);

		PortletPreferencesTestUtil.addGroupPortletPreferences(layout, _portlet);

		Assert.assertEquals(
			1,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId()));
	}

	@Test
	public void testGetOriginalLayoutPortletPreferencesCountByPortletId()
		throws Exception {

		Assert.assertEquals(
			0,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _portlet.getPortletId()));

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Layout layout = LayoutTestUtil.addLayout(_group);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, _portlet);

		Assert.assertEquals(
			2,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _portlet.getPortletId()));

		PortletPreferencesTestUtil.addGroupPortletPreferences(
			_layout, _portlet);

		Assert.assertEquals(
			2,
			_portletPreferencesLocalService.getPortletPreferencesCount(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _portlet.getPortletId()));
	}

	@Test
	public void testGetPortletPreferencesByPlid() throws Exception {
		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Layout layout = LayoutTestUtil.addLayout(_group);

		Portlet portlet1 = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 1));

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, portlet1);

		Portlet portlet2 = _portletLocalService.getPortletById(
			layout.getCompanyId(), String.valueOf(_PORTLET_ID + 2));

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, portlet2);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferencesByPlid(
				layout.getPlid());

		Assert.assertEquals(
			portletPreferencesList.toString(), 2,
			portletPreferencesList.size());
	}

	@Test
	public void testGetPortletPreferencesByPlidAndPortletId() throws Exception {
		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet);

		Layout layout = LayoutTestUtil.addLayout(_group);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			layout, _portlet);

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				_layout.getPlid(), _portlet.getPortletId());

		Assert.assertEquals(
			portletPreferencesList.toString(), 1,
			portletPreferencesList.size());

		PortletPreferences portletPreferences = portletPreferencesList.get(0);

		Assert.assertEquals(_layout.getPlid(), portletPreferences.getPlid());
		Assert.assertEquals(
			_portlet.getPortletId(), portletPreferences.getPortletId());
	}

	@Test
	public void testGetStrictPreferences() {
		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getStrictPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertStrictPortletPreferences(jxPortletPreferences);
	}

	@Test
	public void testGetStrictPreferencesByPortletPreferencesIds() {
		PortletPreferencesIds portletPreferencesIds = new PortletPreferencesIds(
			_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId());

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getStrictPreferences(
				portletPreferencesIds);

		assertStrictPortletPreferences(jxPortletPreferences);
	}

	@Test
	public void testUpdatePortletPreferences() throws Exception {
		String singleValuePortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferences portletPreferences =
			PortletPreferencesTestUtil.addLayoutPortletPreferences(
				_layout, _portlet, singleValuePortletPreferencesXML);

		String multipleValuesPortletPreferencesAsXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _MULTIPLE_VALUES);

		portletPreferences.setPreferences(
			multipleValuesPortletPreferencesAsXML);

		_portletPreferencesLocalService.updatePortletPreferences(
			portletPreferences);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertValues(jxPortletPreferences, _NAME, _MULTIPLE_VALUES);
	}

	@Test
	public void testUpdatePreferences() throws Exception {
		String singleValuePortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, singleValuePortletPreferencesXML);

		String multipleValuesPortletPreferencesAsXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _MULTIPLE_VALUES);

		_portletPreferencesLocalService.updatePreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId(), multipleValuesPortletPreferencesAsXML);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertValues(jxPortletPreferences, _NAME, _MULTIPLE_VALUES);
	}

	@Test
	public void testUpdatePreferencesAutoAdd() {
		String portletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		_portletPreferencesLocalService.updatePreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId(), portletPreferencesXML);

		javax.portlet.PortletPreferences jxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertValues(jxPortletPreferences, _NAME, _SINGLE_VALUE);
	}

	@Test
	public void testUpdatePreferencesByJxPortletPreferences() throws Exception {
		String singleValuePortletPreferences =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _SINGLE_VALUE);

		PortletPreferencesTestUtil.addLayoutPortletPreferences(
			_layout, _portlet, singleValuePortletPreferences);

		String multipleValuesPortletPreferencesXML =
			PortletPreferencesTestUtil.getPortletPreferencesXML(
				_NAME, _MULTIPLE_VALUES);

		javax.portlet.PortletPreferences initialJxPortletPreferences =
			_portletPreferencesFactory.fromXML(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId(), multipleValuesPortletPreferencesXML);

		_portletPreferencesLocalService.updatePreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
			_portlet.getPortletId(), initialJxPortletPreferences);

		javax.portlet.PortletPreferences currentJxPortletPreferences =
			_portletPreferencesLocalService.getPreferences(
				_layout.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				_portlet.getPortletId());

		assertValues(currentJxPortletPreferences, _NAME, _MULTIPLE_VALUES);
	}

	protected void assertEmptyPortletPreferencesMap(
		javax.portlet.PortletPreferences jxPortletPreferences) {

		PortletPreferencesImpl portletPreferencesImpl =
			(PortletPreferencesImpl)jxPortletPreferences;

		Map<String, String[]> portletPreferencesMap =
			portletPreferencesImpl.getMap();

		Assert.assertTrue(
			portletPreferencesMap.toString(), portletPreferencesMap.isEmpty());
	}

	protected void assertOwner(
		Group group, PortletPreferencesImpl portletPreferencesImpl) {

		Assert.assertEquals(
			group.getGroupId(), portletPreferencesImpl.getOwnerId());
		Assert.assertEquals(
			PortletKeys.PREFS_OWNER_TYPE_GROUP,
			portletPreferencesImpl.getOwnerType());
	}

	protected void assertOwner(
		Layout layout, PortletPreferencesImpl portletPreferencesImpl) {

		Assert.assertEquals(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			portletPreferencesImpl.getOwnerId());
		Assert.assertEquals(
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
			portletPreferencesImpl.getOwnerType());
		Assert.assertEquals(layout.getPlid(), portletPreferencesImpl.getPlid());
	}

	protected void assertStrictPortletPreferences(
		javax.portlet.PortletPreferences jxPortletPreferences) {

		StrictPortletPreferencesImpl strictPortletPreferencesImpl =
			(StrictPortletPreferencesImpl)jxPortletPreferences;

		Map<String, String[]> strictPortletPreferencesMap =
			strictPortletPreferencesImpl.getMap();

		Assert.assertTrue(
			strictPortletPreferencesMap.toString(),
			strictPortletPreferencesMap.isEmpty());
	}

	protected void assertValues(
		javax.portlet.PortletPreferences jxPortletPreferences, String name,
		String[] values) {

		PortletPreferencesImpl portletPreferencesImpl =
			(PortletPreferencesImpl)jxPortletPreferences;

		Map<String, String[]> portletPreferencesMap =
			portletPreferencesImpl.getMap();

		Assert.assertFalse(
			portletPreferencesMap.toString(), portletPreferencesMap.isEmpty());
		Assert.assertArrayEquals(values, portletPreferencesMap.get(name));
	}

	protected void assertValues(
			PortletPreferences portletPreferences, String name, String[] values)
		throws Exception {

		PortletPreferencesImpl portletPreferencesImpl =
			_toPortletPreferencesImpl(portletPreferences);

		assertValues(portletPreferencesImpl, name, values);
	}

	protected void replaceService() {
		AopInvocationHandler aopInvocationHandler =
			ProxyUtil.fetchInvocationHandler(
				_portletPreferencesLocalService, AopInvocationHandler.class);

		Object previousService = aopInvocationHandler.getTarget();

		ServiceWrapper<PortletPreferencesLocalService> serviceWrapper =
			new TestPortletPreferencesLocalServiceWrapper(
				(PortletPreferencesLocalService)previousService);

		_serviceBag = new ServiceBag<>(
			PortalClassLoaderUtil.getClassLoader(), aopInvocationHandler,
			PortletPreferencesLocalService.class, serviceWrapper);
	}

	protected void resetService() throws Exception {
		_serviceBag.replace();
	}

	private PortletPreferencesImpl _toPortletPreferencesImpl(
			PortletPreferences portletPreferences)
		throws Exception {

		return (PortletPreferencesImpl)_portletPreferencesFactory.fromXML(
			TestPropsValues.getCompanyId(), portletPreferences.getOwnerId(),
			portletPreferences.getOwnerType(), portletPreferences.getPlid(),
			portletPreferences.getPortletId(),
			portletPreferences.getPreferences());
	}

	private static final String[] _MULTIPLE_VALUES = {"value1", "value2"};

	private static final String _NAME = "name";

	private static final int _PORTLET_ID = 1000;

	private static final String[] _SINGLE_VALUE = {"value"};

	private static final PortletApp _portletApp = new PortletAppImpl(
		StringPool.CONTENT);

	private Group _group;

	@DeleteAfterTestRun
	private final List<Group> _groups = new ArrayList<>();

	private Layout _layout;

	@DeleteAfterTestRun
	private Portlet _portlet;

	@Inject
	private PortletLocalService _portletLocalService;

	@Inject
	private PortletPreferencesFactory _portletPreferencesFactory;

	@Inject
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	private ServiceBag<PortletPreferencesLocalService> _serviceBag;

	private static class TestPortletPreferencesLocalServiceWrapper
		extends PortletPreferencesLocalServiceWrapper {

		@Override
		public javax.portlet.PortletPreferences getStrictPreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId) {

			ClassLoaderBeanHandler classLoaderBeanHandler =
				(ClassLoaderBeanHandler)ProxyUtil.getInvocationHandler(
					getWrappedService());

			PortletPreferencesLocalService portletPreferencesLocalService =
				(PortletPreferencesLocalService)
					classLoaderBeanHandler.getBean();

			return portletPreferencesLocalService.getPreferences(
				companyId, ownerId, ownerType, plid, portletId);
		}

		private TestPortletPreferencesLocalServiceWrapper(
			PortletPreferencesLocalService portletPreferencesLocalService) {

			super(portletPreferencesLocalService);
		}

	}

}