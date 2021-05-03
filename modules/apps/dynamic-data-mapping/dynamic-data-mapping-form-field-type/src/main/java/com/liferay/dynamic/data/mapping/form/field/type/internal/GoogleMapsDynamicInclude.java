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

package com.liferay.dynamic.data.mapping.form.field.type.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eric Chi
 * @since 1.0.0
 * @version 1.0.0
 * TODO: Add documentation.
 */
@Component(immediate = true, service = DynamicInclude.class)
public class GoogleMapsDynamicInclude extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		String apiKey = "AIzaSyB4zMjGQhavlM2gTC6wrQK69APD0suTuG0";

		StringBundler sb = new StringBundler(5);

		sb.append("<script data-senna-track=\"permanent\" src=\"");
		sb.append("https://maps.googleapis.com/maps/api/js?key=");
		sb.append(HtmlUtil.escapeJS(apiKey));
		sb.append("&libraries=places");
		sb.append("\" async defer></script>");

		PrintWriter printWriter = httpServletResponse.getWriter();

		printWriter.print(sb.toString());
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			"/html/common/themes/top_js.jspf#resources");
	}

}