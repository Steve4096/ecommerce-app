import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Button from '../../components/Button/Button';
import Searchbar from '../../components/Searchbar/Searchbar';
import './Customer.css'
import { Link, Navigate, useNavigate } from 'react-router-dom';
import AddCustomer from './AddCustomer';

const Customer = () => {
  const [customers, setCustomer] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/customer/fetchAll')
      .then(response => {
        setCustomer(response.data);
      })
      .catch(error => {
        console.log("Failed to fetch customers:", error);
      });
  }, []);

  const handleDelete = (customerNumber) => {
    axios.delete(`http://localhost:8080/fetchAllCustomers/${customerNumber}`)
    .then(response=>{
      console.log("Deleted:",response.data);
      alert(`Deleted customer: ${response.data.name} (ID: ${response.data.id})`);
      
      // Optionally refresh the list of customers:
      fetchCustomers();
    })
    .catch(error=>{
       console.error("Failed to delete customer:", error);
      alert("Customer not found or could not be deleted.");
    })
  }

  const navigate=useNavigate();

  const handleAddCustomer=()=>{
     navigate('/add-customer');
  }

  const filteredCustomers = customers.filter((customer) => {
    const fullName = `${customer.firstName} ${customer.lastName}`.toLowerCase();
    return fullName.includes(searchQuery.toLowerCase());
  });

  return (
    <div className='customersPage'>
      <h1 style={{textAlign:'center'}}>Customers</h1>
      <div className='topBar'>
        <Searchbar 
          type='text' 
          placeholder='Enter name to search' 
          value={searchQuery} 
          onChange={(e) => setSearchQuery(e.target.value)} 
        />
        <Button text="Add Customer" variant='success' onClick={handleAddCustomer} />
      </div>

      <table className='customersTable'>
        <thead>
          <tr>
            <th>Customer Number</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
            <th>Address line 1</th>
            <th>Address line 2</th>
            <th>City</th>
            <th>Sales representative number</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredCustomers.map(customer => (
            <tr key={customer.customerNumber}>
              <td>{customer.customerNumber}</td>
              <td>{customer.firstName}</td>
              <td>{customer.lastName}</td>
              <td>{customer.phoneNumber}</td>
              <td>{customer.addressLine1}</td>
              <td>{customer.addressLine2}</td>
              <td>{customer.city}</td>
              <td>{customer.salesRepEmployeeNumber}</td>
              <td>
                <Button 
                  text="DELETE" 
                  variant='danger' 
                  onClick={() => handleDelete(customer.customerNumber)} 
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Customer;
