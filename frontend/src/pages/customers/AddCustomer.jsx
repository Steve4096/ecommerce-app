import React, { useState } from 'react'
import Button from '../../components/Button/Button';
import axios from 'axios';




const AddCustomer = () => {
    const [formData,setFormData]=useState({ firstName: '',
  lastName: '',
  phoneNumber: '',
  addressLine1: '',
  addressLine2: '',
  city: '',
  state: '',
  country: '',
  postalCode: '',
  salesRepEmployeeNumber: ''})

    const handleFormSubmit=async(event)=>{
    event.preventDefault();
    console.log("Form submitted");  
    try{
        const response=await axios.post('http://localhost:8080/registerCustomer',formData)
    console.log("Data submitted:",response.data)
    setFormData({ firstName: '',
        lastName: '',
        phoneNumber: '',
        addressLine1: '',
        addressLine2: '',
        city: '',
        state: '',
        country: '',
        postalCode: '',
        salesRepEmployeeNumber: ''
    });
    } catch(error){
        console.log("Error saving customer.",error)
        alert("Failed to save customer")
    } 
    
}

  return (
    <form className='addCustomerPage' onSubmit={handleFormSubmit}>
        <h2 style={{textAlign:'center'}}>Add Customer</h2>
      <label htmlFor='firstName'>First name:</label>
        <input type='text' id='firstName' name='firstName' value={formData.firstName} onChange={(e)=>setFormData({...formData,firstName:e.target.value})}/><br/>
        <label htmlFor='lastName'>Last name:</label>
        <input type='text' id='lastName' name='lastName' value={formData.lastName} onChange={(e)=>setFormData({...formData,lastName:e.target.value})}/><br/>
        <label htmlFor='phoneNumber'>Phone number:</label>
        
        <label htmlFor='addressLine1'>Address Line 1:</label>
        <input type='text' id='addressLine1' name='addressLine1' value={formData.addressLine1} onChange={(e)=>setFormData({...formData,addressLine1:e.target.value})}/><br/>
        <label htmlFor='addressLine2'>Address Line 2:</label>
        <input type='text' id='addressLine2' name='addressLine2' value={formData.addressLine2} onChange={(e)=>setFormData({...formData,addressLine2:e.target.value})} /><br/>
        <label htmlFor='city'>City:</label>
        <input type='text' id='city' name='city' value={formData.city} onChange={(e)=>setFormData({...formData,city:e.target.value})} /><br/>
        <label htmlFor='state'>State:</label>
        <input type='text' id='state' name='state' value={formData.state} onChange={(e)=>setFormData({...formData,state:e.target.value})}/><br/>
        <label htmlFor='country'>Country</label>
        <input type='text' id='country' name='country' value={formData.country} onChange={(e)=>setFormData({...formData,country:e.target.value})}/><br/>
        <label htmlFor='customerName'>Postal code:</label>
        <input type='number' id='postalCode' name='postalcode' value={formData.postalCode} onChange={(e)=>setFormData({...formData,postalCode:e.target.value})} /><br/>
        <label htmlFor='salesRepEmployeeNumber'>Sales rep employee number:</label>
        <input type='number' id='salesRepEmployeeNumber' name='salesRepEmployeeNumber' value={formData.salesRepEmployeeNumber} onChange={(e)=>setFormData({...formData,salesRepEmployeeNumber:e.target.value})}/><br/>
        
        
        <Button text='Save' type='Submit'/>
    </form>
  )
}

export default AddCustomer