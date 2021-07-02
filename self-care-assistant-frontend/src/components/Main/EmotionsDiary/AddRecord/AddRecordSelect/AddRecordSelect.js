import React from 'react'
import Select from 'react-select'
import {useDispatch} from 'react-redux'
import {emotionDiaryAddRecordSelectAction} from '../../../../../redux/actions'


export const AddRecordSelect = (props) => {
    const dispatch = useDispatch()

    const getSelectValue = (item) => {
        dispatch(emotionDiaryAddRecordSelectAction({id: item.value, name: item.label}))
    }

    const customStyles = {
        control: styles => ({
            ...styles,
            borderRadius: 30,
            width: 200,
            paddingLeft: 10,
            paddingRight: 5,
            fontSize: 15,
            minHeight: 30
        }),
        option: (styles, {data, isDisabled, isFocused, isSelected}) => {
            return {
                ...styles,
                width: 200,
                textAlign: 'left'
            }
        },
        menu: styles => ({
            ...styles,
            width: 200,
        }),
        dropdownIndicator: styles => ({
            ...styles,
            padding: 4
        }),
    }


    return (
        <Select options={props.selectOption} styles={customStyles} onChange={getSelectValue}/>
    )
}