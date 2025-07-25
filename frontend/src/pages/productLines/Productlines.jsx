import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { ClipLoader } from 'react-spinners';
import Button from '../../components/Button/Button';
import Searchbar from '../../components/Searchbar/Searchbar';
import './Productlines.css'
import Modal from '../../components/Modal/Modal';

const ProductLines = () => {
  const[productLines,setProductLines]=useState([]);
  const[loading,setIsLoading]=useState(false);
  const[searchQuery,setSearchQuery]=useState('');
  const[selectedProductLine,setSelectedProductLine]=useState(null);
  const[showModal,setShowModal]=useState(false);
  const[modalType,setModalType]=useState('');
  const[notification,setNotification]=useState({type:'',message:''});
  const[formData,setFormData]=useState({
    textDescription: '',
    htmlDescription: '',
    imagePath: ''
  });

useEffect(()=>{
  const fetchProductLines=async()=>{
     try{
      setIsLoading(true);
      const response=await axios.get('http://localhost:8080/api/productLine/fetchAll');
      setProductLines(response.data)
    }catch(error){
      console.log("Failed to fetch product lines",error);
    }finally{
      setIsLoading(false);
    }
  };

  fetchProductLines();
   
},[])

function stripHtml(html) {
  const tempDiv = document.createElement('div');
  tempDiv.innerHTML = html;
  return tempDiv.textContent || tempDiv.innerText || '';
}


const handleAddProductLineClick=()=>{
  setFormData({ textDescription: '', htmlDescription: '', imagePath: '' });
  setModalType('add');
  setShowModal(true);
}

const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

const handleFormSubmit=async(e)=>{
  e.preventDefault();
  try{
    await axios.post('http://localhost:8080/api/productLine/register',formData);
    setShowModal(false);
    setNotification({type:'success',message:'Product line has been added successfully'})
    //fetchProductLines();
  }catch(error){
    console.log("Failed to register product line",error);
    setNotification({type:'error',message:'Failed to add product line.'})
  }
}

const handleDeleteClick=(productLine)=>{
  setSelectedProductLine(productLine.productLine);
  setModalType('delete');
  setShowModal(true);
}

const handleConfirmDelete=async()=>{
  try{
    await axios.delete(`http://localhost:8080/api/productLine/delete/${selectedProductLine}`);
    setShowModal(false);
    setNotification({ type: 'success', message: 'Product line deleted successfully.' });
    //fetchProductLines();
  }catch(error){
    console.log("Failed to delete product line",error)
    setNotification({ type: 'error', message: 'Failed to delete product line.' });
  }
};

// const handleDelete=async(productLine)=>{
//   try{
//     await axios.delete(`http://localhost:8080/api/productLine/delete/${productLine}`)
//     setProductLines((prev)=>prev.filter((productLine)=>productLine.productLine !==productLine))
//     setShowModal(false)
//     setSelectedOfficeCode(null)
//      alert(`Product line ${productLine} has been deleted successfully`)
//   }catch(error){
//     console.log("Failed to delete product line",error)
//   }
// }

const filteredProductLines=productLines.filter((item)=>{
  const searchFields=`${item.textDescription}`.toLowerCase();
  return searchFields.includes(searchQuery.toLowerCase());
})


let content;
if(loading){
 content=(<div className='productLines-loading'>
  <ClipLoader/>
  <span>Loading..</span>
 </div>)
}else if(filteredProductLines.length===0){
  content=(<p className='productLines-empty'>No content to show here</p>)
}else{
 content=(
  <table className='productLines-table'>
    <thead>
      <tr>
        <th>Product line ID</th>
        <th>Text Description</th>
        <th>Html Description</th>
        <th>Image path</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      {filteredProductLines.map((productLine)=>(
        <tr key={productLine.productLine}>
          <td>{productLine.productLine}</td>
          <td>{productLine.textDescription}</td>
          <td>{stripHtml(productLine.htmlDescription)}</td>
          <td>{productLine.imagePath}</td>
          <td><Button variant='danger' type='Danger' text='DELETE' onClick={(e)=>{
            e.stopPropagation();
           // setSelectedProductLine(productLine.productLine)
            handleDeleteClick(productLine);
            }}/></td>
        </tr>
      ))}
    </tbody>
  </table>
 ) 
}

let modalContent;
if(showModal){
  if(modalType==='add'){
   modalContent=(<Modal variant='form'
    title='Add a product line'
    onCancel={(e)=>setShowModal(false)}
    onSubmit={handleFormSubmit}
    formData={formData}
    onFormChange={handleFormChange}
     fields={[
    { name: 'textDescription', label: 'Text Description', type: 'text', required: true },
    { name: 'htmlDescription', label: 'HTML Description', type: 'textarea', required: true },
    { name: 'imagePath', label: 'Image Path', type: 'text', required: false }
  ]}/>) 
  }else{
    modalContent=(
      <Modal variant='confirm'
      title='CONFIRM DELETE'
      message={`Are you sure you want to delete product line ${selectedProductLine}?`}
      onCancel={(e)=>{
        setSelectedProductLine(null)
        setShowModal(false)
      }}
      onDelete={handleConfirmDelete}/>
    )
  }

}

  return (
    <div className='productLines'>
      <h1>Product Lines</h1>
      <div className='topBar'>
        {notification.message && (<div className={`notification ${notification.type}`}>
        {notification.message}
      </div>
       )}

        <Searchbar type='text' placeholder='Enter details to search' value={searchQuery} onChange={(e)=>setSearchQuery(e.target.value)}/>
          <Button type='text' text='+ Add Product Line' variant='success' onClick={handleAddProductLineClick}/>
      </div>
      {content}
      {modalContent}
    </div>
  )
}

export default ProductLines