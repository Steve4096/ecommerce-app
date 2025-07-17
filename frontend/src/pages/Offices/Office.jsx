import React, { useEffect, useState } from 'react'
import Button from '../../components/Button/Button'
import Searchbar from '../../components/Searchbar/Searchbar'
import axios from 'axios';
import  {ClipLoader} from 'react-spinners';
import './Office.css'
import Modal from '../../components/Modal/Modal';


const Office = () => {
    const[offices,setOffices]=useState([]);
    const[searchQuery,setSearchQuery]=useState('');
    const[loading,setIsLoading]=useState(false);
    const[selectedOfficeCode,setSelectedOfficeCode]=useState(null);
    const[showModal,setShowModal]=useState(false);

    //This loads the page at the first instance
    useEffect(()=> {
        const fetchOffices=async()=>{
            try{
                setIsLoading(true)
                const response=await axios.get('http://localhost:8080/api/office/fetchAll');
                console.log("Fetched office data:", response.data);
                setOffices(response.data);
            }catch(error){
                console.log("Failed to fetch offices",error)
            }finally{
                setIsLoading(false);
            }
        };

        fetchOffices();
    },[])

    const filteredOffices=offices.filter((office)=>{
        const searchFields=`${office.addressLine2}${office.territory}${office.city}`.toLowerCase();
        return searchFields.includes(searchQuery.toLowerCase());
    });

    const handleDelete=async(officeCode)=>{
        try{
            await axios.delete(`http://localhost:8080/api/office/delete/${officeCode}`)
            setOffices((prev)=>prev.filter((office)=>office.officeCode !==officeCode))
            setShowModal(false)
            setSelectedOfficeCode(null)
            alert(`Office ${officeCode} has been deleted successfully`)
        }catch(error){
            console.log("Failed to delete office",error)
        }
    }

    //Conditional rendering to determine what message to show on the screen
    let content;
    if(loading){
        content=(<div className='office-loading'>
            <ClipLoader/>
            <span>Loading offices</span>
        </div>)
    }else if(filteredOffices.length===0){
        content=<p className='office-empty'>No content to show here</p>
    }else{
        content=(<table className='office-table'>
            <thead>
                <tr>
                    <th>Office code</th>
                    <th>City</th>
                    <th>Phone</th>
                    <th>Address line 1</th>
                    <th>Address line 2</th>
                    <th>State</th>
                    <th>Country</th>
                    <th>Postal code</th>
                    <th>Territory</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {filteredOffices.map((office)=> (
                    <tr key={office.officeCode}>
                    <td>{office.officeCode}</td>
                    <td>{office.city}</td>
                    <td>{office.phone}</td>
                    <td>{office.addressLine1}</td>
                    <td>{office.addressLine2}</td>
                    <td>{office.state}</td>
                    <td>{office.country}</td>
                    <td>{office.postalCode}</td>
                    <td>{office.territory}</td>
                    <td><Button text='DELETE' variant='Danger' onClick={(e)=>{
                        e.stopPropagation();
                        setSelectedOfficeCode(office.officeCode);
                        setShowModal(true);
                    }}/>
                       </td>
                    </tr>
                )                
                )}
            </tbody>
        </table>)
    }


  return (
    <div className='office-container'>
        <h1>Offices</h1>
    <div className='topBar'>
    <Searchbar type='text' placeholder="Enter details to search" value={searchQuery} onChange={(e)=>setSearchQuery(e.target.value)}/>
    <Button text="+ Add Office" variant='success' />
    </div>
    <div>{content}</div>
    {showModal&&<Modal message={`ARE YOU SURE YOU WANT TO DELETE OFFICE ${selectedOfficeCode}?`} 
    onCancel={()=>{
         setShowModal(false);
         setSelectedOfficeCode(null);
    }
    }
    onConfirm={handleDelete(selectedOfficeCode)}/>}
    </div>
  )
}

export default Office