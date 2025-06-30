import React from 'react'
import Sidebar from './components/Sidebar/Sidebar'
import { Route, Routes } from 'react-router-dom'
import Home from './pages/home/Home'
import ProductLines from './pages/productLines/productLines'
import Customer from './pages/customers/Customer'
import AddCustomer from './pages/customers/AddCustomer'
import Office from './pages/Offices/Office'

const App = () => {
  return (
    <div style={{display:'flex'}}>
      <Sidebar/>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/customers' element={<Customer/>}/>
        <Route path='/productLines' element={<ProductLines/>}/>
        <Route path='/add-customer' element={<AddCustomer/>}/>
        <Route path='/Offices' element={<Office/>}/>
      </Routes>
    </div>
  )
}

export default App