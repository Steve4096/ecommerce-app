// import React from 'react'

// const Searchbar = ({type='text',placeholder,style={},value,onChange}) => {
//   return (
//     <input type={type}
//     placeholder={placeholder}
//     style={style}
//     value={value}
//     onChange={onChange}
//   />)
// }

// export default Searchbar
import React from 'react';
import './Searchbar.css'; // 👈 Make sure to import this

const Searchbar = ({ type = 'text', placeholder, style = {}, value, onChange }) => {
  return (
    <input
      type={type}
      placeholder={placeholder}
      className="customSearchbar"
      style={style}
      value={value}
      onChange={onChange}
    />
  );
};

export default Searchbar;
