import React from 'react'
import './Modal.css'
import Button from '../Button/Button'

//<button className='onCancel' onClick={onCancel}>CANCEL</button>
          //<button className='onDelete' onClick={onDelete}>DELETE</button>

const Modal = ({title,message,onCancel,onDelete,onSubmit,formData,onFormChange,variant='confirm',}) => {
  return (
    <div className='modal-backdrop'>
        <div className='modal-box'>
          <h2>{title || (variant==='confirm' ? 'CONFIRM ACTION':'ADD PRODUCT LINE')}</h2>
          {variant==='confirm'?(
            <>
            <p>{message || 'Are you sure you want to delete this item?'}</p>
            <div className='modal-actions'>
          <Button text='CANCEL' onClick={onCancel}/>
          <Button text='DELETE' onClick={onDelete}/>
          </div>
            </>
          ):(<form onSubmit={onSubmit}>
            <div className='form-group'>
                <label htmlFor='textDescription'>Text Description</label>
                <input type='text' id='textDescription' name='textDescription' value={formData.textDescription} onChange={onFormChange} required/>
                </div>
                <div className='form-group'>
                <label htmlFor='htmlDescription'>HTML Description</label>
                <textarea id='htmlDescription' name='htmlDescription' value={formData.htmlDescription} onChange={onFormChange} required/>
                </div>
                <div className='form-group'>
                <label htmlFor='imagePath'>Image Path</label>
                <input type='text' id='imagePath' name='imagePath' value={formData.imagePath} onChange={onFormChange}/>
            </div>
            <div className='modal-actions'>
                <Button text='CANCEL' onClick={onCancel}/>
                <Button text='SUBMIT' type='submit'/>
            </div>
          </form>)}
         
        </div>
    </div>
  )
}

export default Modal