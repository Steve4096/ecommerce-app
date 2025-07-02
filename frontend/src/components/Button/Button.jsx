import React from 'react'
import './Button.css'

const Button = ({text,onClick,type='button',variant='primary',style={},disabled=false}) => {
  return (
    <button className={`customButton ${variant}`} 
    type={type} 
    onClick={onClick} 
    style={style}
      disabled={disabled}
      >
      {text}
      </button>
  )
}

export default Button