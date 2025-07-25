import React, { useEffect, useState } from 'react'
import Card from '../../components/Card/Card'
import { FaBox, FaUsers, FaShoppingCart } from 'react-icons/fa';
import './Home.css'
import axios from 'axios';

const Home = () => {
  const[customers,setCustomersCount]=useState(0);
  const[products,setProductsCount]=useState(0);
  const[orders,setOrdersCount]=useState(0);

  useEffect(()=>{
    //Fetch customer count
    const countCustomers=async()=>{
      try{
        const response=await axios.get('http://localhost:8080/api/customer/count')
        setCustomersCount(response.data.count)
      }catch(error){
        console.log("Encountered an error while counting customers",error)
      }
    }

    //Fetch product count
    const countProducts=async()=>{
      try{
        const response=await axios.get('http://localhost:8080/api/product/count')
        setProductsCount(response.data.count)
      }catch(error){
        console.log("Encountered an error while counting products",error)
      }
    }

    countCustomers();
    countProducts();
  },[])

  return (
    <div className='body'>
      <Card title="Total products" count={products} icon={<FaBox/>}/>
      <Card title="Total customers" count={customers} icon={<FaUsers/>}/>
      <Card title='Pending orders' icon={<FaShoppingCart/>}/>
    </div>
  )
}

export default Home