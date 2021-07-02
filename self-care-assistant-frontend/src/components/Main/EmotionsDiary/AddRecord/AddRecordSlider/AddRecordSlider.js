import React from 'react'
import {useDispatch,} from 'react-redux'
import {emotionDiaryAddRecordSliderAction} from '../../../../../redux/actions'
import Slider from 'rc-slider'


export const AddRecordSlider = () => {
    const dispatcn = useDispatch()

    const getSliderValue = (value) => {
        dispatcn(emotionDiaryAddRecordSliderAction(value))
    }
    const style = {
        track: {
            height:15,
            width: '100%',
            background: 'linear-gradient(90deg, rgba(247,214,185,0.7822479333530288) 24%, rgba(236,159,187,0.6758053563222164) 77%)'
        },
        handle: {
            height:17,
            width:17,
            marginTop:-1,
            border: '1px solid #e9e9e9'
        },
        rail: {
            height:15,
        }
    }
    return (
        <Slider min={0} max={10} step={1}
                defaultValue={6}
                trackStyle={style.track}
                handleStyle={style.handle}
                railStyle={style.rail}
                onChange={getSliderValue}/>
    )
}