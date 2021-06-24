import React from 'react'
import Select from 'react-select'
import {useDispatch} from 'react-redux'
import {emotionDiaryAddRecordSelectAction} from '../../../../../redux/actions'


export const AddRecordSelect = () => {
    const dispatch = useDispatch()

    const getSelectValue = (arr) => {
        dispatch(emotionDiaryAddRecordSelectAction(arr.value))
    }

    const customStyles = {
        control: styles => ({...styles,
            borderRadius: 30,
            width: 200,
            paddingLeft: 10,
            paddingRight:5,
            fontSize:15,
            minHeight:30
        }),
        option: (styles, {data, isDisabled, isFocused, isSelected}) => {
            return {
                ...styles,
                width: 200,
                textAlign:'left'
            }
        },
        menu: styles => ({...styles,
            width: 200,
        }),
        dropdownIndicator: styles => ({...styles,
            padding:4
        }),
    }


    const selectOption = [
        {value: 'sad', label: 'sad'},
        {value: 'happy', label: 'happy'},
        {value: 'lol', label: 'lol'},
        {value: 'keke', label: 'keke'},
    ]
    return (
            <Select options={selectOption} styles={customStyles} onChange={getSelectValue}/>
    )
}