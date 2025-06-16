import React from 'react'

const Searchbar = ({type='text',placeholder,style={},value,onChange}) => {
  return (
    <input type={type}
    text={placeholder}
    style={style}
    value={value}
    onChange={onChange}
  />)
}

export default Searchbar