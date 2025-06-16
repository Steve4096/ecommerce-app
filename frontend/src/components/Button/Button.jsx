import React from 'react'

const Button = ({text,onClick,type='button',variant='primary',style={},disabled=false}) => {
  return (
    <button className={`customButton ${variant}`} 
    type={type} 
    onClick={onClick} 
    style={style}>
      disabled={disabled}
      {text}
      </button>
  )
}

export default Button