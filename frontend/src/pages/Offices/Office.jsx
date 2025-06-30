import React, { useEffect, useState } from 'react'
import Button from '../../components/Button/Button'
import Searchbar from '../../components/Searchbar/Searchbar'
import axios from 'axios';
import  {ClipLoader} from 'react-spinners';
import './Office.css'


const Office = () => {
    const[offices,setOffices]=useState([]);
    const[searchQuery,setSearchQuery]=useState('');
    const[loading,setIsLoading]=useState(false)

    //This loads the page at the first instance
    useEffect(()=> {
        const fetchOffices=async()=>{
            try{
                setIsLoading(true)
                const response=await axios.get('http://localhost:8080/api/office/fetchAll');
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
    <Searchbar type='text' placeholder="Enter office details to search" value={searchQuery} onChange={(e)=>setSearchQuery(e.target.value)}/>
    <Button text="+ Add Office" variant='success' />
    </div>
    <div>{content}</div>
    </div>
  )
}

export default Office