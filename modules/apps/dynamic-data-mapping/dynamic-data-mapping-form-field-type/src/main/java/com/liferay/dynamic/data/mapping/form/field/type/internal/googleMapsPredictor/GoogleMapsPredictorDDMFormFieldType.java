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

package com.liferay.dynamic.data.mapping.form.field.type.internal.googleMapsPredictor;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author renato
 */
@Component(
	immediate = true,
	property = {
		"ddm.form.field.type.description=google-maps-predictor-description",
		"ddm.form.field.type.display.order:Integer=10",
		"ddm.form.field.type.group=customized",
		"ddm.form.field.type.icon=control-panel",
		"ddm.form.field.type.label=google-maps-predictor",
		"ddm.form.field.type.name=google-maps-predictor"
	},
	service = DDMFormFieldType.class
)
public class GoogleMapsPredictorDDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public String getModuleName() {
		return _npmResolver.resolveModuleName(
			"dynamic-data-mapping-form-field-type/googleMapsPredictor" +
				"/GoogleMapsPredictor.es");
	}

	@Override
	public String getName() {
		return "google-maps-predictor";
	}

	@Override
	public boolean isCustomDDMFormFieldType() {
		return true;
	}

	@Reference
	private NPMResolver _npmResolver;

}