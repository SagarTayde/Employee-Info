import React, { useState } from 'react';
import axios from 'axios';
import './FileUpload.css';

const FileUpload = ({ endpoint, label, onUpload,handleFileChange,handleUploadfile,file,setFile }) => {
  return (
    <div className='fileUpload'>
      <input type="file"accept=".xlsx, .xls"  onChange={handleFileChange} multiple/>
    </div>
  );
};

export default FileUpload;
