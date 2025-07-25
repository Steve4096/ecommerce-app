import React, { useEffect, useState } from 'react'
import './Products.css'
import axios from 'axios';
import { ClipLoader } from 'react-spinners';
import Button from '../../components/Button/Button';
import Searchbar from '../../components/Searchbar/Searchbar';



const Products = () => {
    const[products,setProducts]=useState([]);
const[selectedProduct,setSelectedProduct]=useState(null);
const[loading,setLoading]=useState(false);
const[modalType,setModalType]=useState('');
const[showModal,setShowModal]=useState(false);
const[searchQuery,setSearchQuery]=useState('');

useEffect(()=>{
    const fetchProducts=async()=>{
        try{
            setLoading(true);
            const response=await axios.get('http://localhost:8080/api/product/fetchAll');
            setProducts(response.data);
        }catch(error){
            console.log('Failed to fetch products',error);
        }finally{
            setLoading(false);
        }
    };

    fetchProducts();
},[])

const filteredProducts=products.filter((item)=>{
    const searchFields=`${item.productName} ${item.productVendor}`.toLowerCase();
    return searchFields.includes(searchQuery.toLowerCase())
})

//Conditional rendering to determine what is shown on the frontend
let content;
if(loading){
    content=<div className='products-loading'>
        <ClipLoader/>
        <span>Loading..</span>
    </div>
}else if(filteredProducts.length===0){
   content=(<p className='products-empty'>No content to show here</p>) 
}else{
    content=(
        <table className='products-table'>
            <thead>
                <tr>
                    <th>Product Code</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Vendor</th>
                    <th>Quantity in stock</th>
                    <th>Buying price</th>
                </tr>
            </thead>
            <tbody>
                {filteredProducts.map((product)=>(
                    <tr key={product.productCode}>
                        <td>{product.productCode}</td>
                        <td>{product.productName}</td>
                        <td>{product.productDescription}</td>
                        <td>{product.productVendor}</td>
                        <td>{product.quantityInStock}</td>
                        <td>{product.buyingPrice}</td>
                        <td><Button text='DELETE' type='danger' variant='danger' onClick={(e)=>{
                            e.stopPropagation();
                        }}/></td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}


  return (
    <div className='products-page'>
        <h1>Products</h1>
        <div className='topBar'>
            <Searchbar type='text' placeholder='Enter product name to search' value={searchQuery} onChange={(e)=>setSearchQuery(e.target.value)}/>
            <Button text='+Add Product' type='text' variant='success'/>
        </div>
        {content}
    </div>
  )
}

export default Products