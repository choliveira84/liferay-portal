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

import {ClayInput} from '@clayui/form';
import React, {useEffect} from 'react';

import {FieldBase} from '../FieldBase/ReactFieldBase.es';
import {useSyncValue} from '../hooks/useSyncValue.es';

/**
 * GoogleMapsPredictor React Component
 */

const getValue = (value) => {
	if (!value) {
		return '';
	}

	if (typeof value === 'string') {
		return value;
	}

	return JSON.stringify(value);
};

const GoogleMapsPredictor = ({
	editingLanguageId,
	id,
	name,
	onChange,
	onFocus,
	placeholder,
	predefinedValue,
	value,
}) => {
	const [currentValue, setCurrentValue] = useSyncValue(
		value ? value : predefinedValue
	);

	useEffect(() => {
		const idText = document.getElementById('myRange');
		const autocomplete = new google.maps.places.Autocomplete(idText);

		autocomplete.setFields(['address_component', 'formatted_address']);

		autocomplete.addListener('place_changed', () => {
			const place = autocomplete.getPlace();

			if (place != null) {
				const addressComponents = place.address_components;

				const addressTypes = {
					administrative_area_level_1: 'short_name',
					administrative_area_level_2: 'short_name',
					country: 'short_name',
					formatted_address: place.formatted_address,
					locality: 'long_name',
					postal_code: 'short_name',
					route: 'long_name',
					street_number: 'short_name',
				};

				const address = {
					administrative_area_level_1: '',
					administrative_area_level_2: '',
					country: '',
					formatted_address: place.formatted_address,
					locality: '',
					postal_code: '',
					route: '',
					street_number: '',
				};

				for (let i = 0; i < addressComponents.length; i++) {
					const addressType = addressComponents[i].types[0];

					address[addressType] =
						addressComponents[i][addressTypes[addressType]];
				}

				setCurrentValue(address);

				onChange({target: {value: address}});
			}
		});
	}, []);

	return (
		<>
			<ClayInput
				className="ddm-field-text"
				disabled={false}
				id="myRange"
				lang={editingLanguageId}
				onChange={(event) => {
					onChange(event);
				}}
				onFocus={onFocus}
				placeholder={placeholder}
				type="text"
				value={currentValue ? currentValue.formatted_address : ''}
			/>

			<ClayInput
				id={id}
				name={name}
				onChange={(event) => {
					onChange(event);
				}}
				placeholder={placeholder}
				type="hidden"
				value={getValue(currentValue)}
			/>
		</>
	);
};

const Main = ({
	editingLanguageId,
	id,
	localizedValue = {},
	name,
	onChange,
	onFocus,
	placeholder,
	predefinedValue = '',
	readOnly,
	value,
	...otherProps
}) => {
	return (
		<FieldBase
			{...otherProps}
			id={id}
			localizedValue={localizedValue}
			name={name}
			readOnly={readOnly}
		>
			<GoogleMapsPredictor
				editingLanguageId={editingLanguageId}
				id={id}
				name={name}
				onChange={onChange}
				onFocus={onFocus}
				placeholder={placeholder}
				predefinedValue={predefinedValue}
				readOnly={readOnly}
				value={value ? value : predefinedValue}
			/>
		</FieldBase>
	);
};
Main.displayName = 'GoogleMapsPredictor';
export default Main;
