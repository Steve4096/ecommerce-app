import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Button from '../../components/Button/Button';
import Searchbar from '../../components/Searchbar/Searchbar';

const Customer = () => {
  const [customers, setCustomer] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/fetchAllCustomers')
      .then(response => {
        setCustomer(response.data);
      })
      .catch(error => {
        console.log("Failed to fetch customers:", error);
      });
  }, []);

  const handleDelete = (id) => {
    console.log("Delete customer with ID:", id);
    // Call delete API here
  };

  const handleSave = () => {
    console.log("Saving customer details");
    // Call save API here
  };

  const filteredCustomers = customers.filter((customer) => {
    const fullName = `${customer.firstName} ${customer.lastName}`.toLowerCase();
    return fullName.includes(searchQuery.toLowerCase());
  });

  return (
    <div className='customersPage'>
      <h1>Customers</h1>
      <div className='topBar'>
        <Searchbar 
          type='text' 
          placeholder='Enter name to search' 
          value={searchQuery} 
          onChange={(e) => setSearchQuery(e.target.value)} 
        />
        <Button text="Add Customer" variant='success' onClick={handleSave} />
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
