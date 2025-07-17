import React from 'react'
import { Link, NavLink } from 'react-router-dom'
import './Sidebar.css'

const Sidebar = () => {
  return (
    <div className='sidebar'>
      <h2>My online store</h2>
      <nav>
        <ul>
          <li><NavLink to="/">Home</NavLink></li>
          <li><NavLink to="/products">Products</NavLink></li>
          <li><NavLink to="/customers">Customers</NavLink></li>
          <li><NavLink to="/productLines">Product Lines</NavLink></li>
          <li><NavLink>Orders</NavLink></li>
          <li><NavLink>Payments</NavLink></li>
          <li><NavLink to="/employees">Employees</NavLink></li>
          <li><NavLink to="/offices">Offices</NavLink></li>
        </ul>
      </nav>
    </div>
  )
}

export default Sidebar