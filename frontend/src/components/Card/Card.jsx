import React from 'react'
import './Card.css'

const Card = ({icon,title,count}) => {
  return (
    <div className='summary-card'>
        <div className='summary-icon'>{icon}</div>
        <div className='summary-content'>
             <h4 className='summary-title'>{title}</h4>
              <p className='summary-count'>{count}</p>
        </div>
    </div>
  )
}

export default Card