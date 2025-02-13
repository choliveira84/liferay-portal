/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {request} from 'data-engine-js-components-web/js/utils/client.es';

export const METRIC_INDEXES_KEY = 'Metric';

export function refreshIndex(key) {
	return request({
		data: {key},
		endpoint: '/o/portal-workflow-metrics/v1.0/indexes/refresh',
		method: 'PATCH',
	});
}
