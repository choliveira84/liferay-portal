import {FieldBase} from '../FieldBase/ReactFieldBase.es';
import {useSyncValue} from '../hooks/useSyncValue.es';
import React from 'react';
/**
 * Slider React Component
 */

 const getAddress = () => {

     const idText = document.getElementById("myRange");
     const address = new google.maps.places.Autocomplete(idText);
    
     console.log(address);
 }

const Slider = ({readOnly, name, onChange, predefinedValue, value}) => {

    return <input 
        className="ddm-field-slider form-control slider"
        onInput={getAddress}
        disabled={readOnly}
        id="myRange"
        name={name}
        type="text"
        value={value ? value : predefinedValue}/>
    }
const Main = ({label, name, onChange, predefinedValue, readOnly, value, ...otherProps}) =>{
    const [currentValue, setCurrentValue] = useSyncValue(
      value ? value : predefinedValue
   );
    return <FieldBase
         label={label}
            name={name}
            predefinedValue={predefinedValue}
         {...otherProps}
      >
        <Slider 
            name={name}
            onChange={(event) => {
                setCurrentValue(event.target.value);
                onChange(event);
            }}
            predefinedValue={predefinedValue}
            readOnly={readOnly}
            value={currentValue}
        />
    </FieldBase>
}
Main.displayName = 'Slider';
export default Main;
