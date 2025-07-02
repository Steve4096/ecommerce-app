import React, { useState } from 'react'
import Button from '../../components/Button/Button';
import axios from 'axios';
import InputMask from 'react-input-mask';
import './AddCustomer.css'


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
        const response=await axios.post('http://localhost:8080/api/customer/register',formData)
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
        <div className='form-group'>
        <label htmlFor='firstName'>First name:</label>
        <input type='text' id='firstName' name='firstName' value={formData.firstName} onChange={(e)=>setFormData({...formData,firstName:e.target.value})} required/><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='lastName'>Last name:</label>
        <input type='text' id='lastName' name='lastName' value={formData.lastName} onChange={(e)=>setFormData({...formData,lastName:e.target.value})} required/><br/>
        </div>
        <div className='form-group'>
        <label htmlFor="phoneNumber">Phone number:</label>
        <input type='text' id='phoneNumber' name='phoneNumber' value={formData.phoneNumber} onChange={(e)=>setFormData({...formData,phoneNumber:e.target.value})} required/><br/>
        </div>

        <div className='form-group'>
        <label htmlFor='addressLine1'>Address Line 1:</label>
        <input type='text' id='addressLine1' name='addressLine1' value={formData.addressLine1} onChange={(e)=>setFormData({...formData,addressLine1:e.target.value})} required/><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='addressLine2'>Address Line 2:</label>
        <input type='text' id='addressLine2' name='addressLine2' value={formData.addressLine2} onChange={(e)=>setFormData({...formData,addressLine2:e.target.value})} required/><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='city'>City:</label>
        <input type='text' id='city' name='city' value={formData.city} onChange={(e)=>setFormData({...formData,city:e.target.value})} /><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='state'>State:</label>
        <input type='text' id='state' name='state' value={formData.state} onChange={(e)=>setFormData({...formData,state:e.target.value})}/><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='country'>Country</label>
        <input type='text' id='country' name='country' value={formData.country} onChange={(e)=>setFormData({...formData,country:e.target.value})}/><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='postalCode'>Postal code:</label>
        <input type='number' id='postalCode' name='postalCode' value={formData.postalCode} onChange={(e)=>setFormData({...formData,postalCode:e.target.value})} /><br/>
        </div>
        <div className='form-group'>
        <label htmlFor='salesRepEmployeeNumber'>Sales rep employee number:</label>
        <input type='number' id='salesRepEmployeeNumber' name='salesRepEmployeeNumber' value={formData.salesRepEmployeeNumber} onChange={(e)=>setFormData({...formData,salesRepEmployeeNumber:e.target.value})}/><br/>
        </div>
        
        <Button text='Save' type='Submit'/>
    </form>
  )
}

export default AddCustomer