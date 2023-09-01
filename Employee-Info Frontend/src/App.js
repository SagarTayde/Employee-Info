import axios from 'axios';
import React, { useState } from 'react';
import './App.css';
import FileUpload from './components/FileUpload';

function App() {
  const [query, setQuery] = useState("");
  const [data, setData] = useState(null);
  const [employeeFile, setEmployeeFile] = useState(null);
const [projectFile, setProjectFile] = useState(null);
const [dropdown,setDropDown] = useState([]);
const [selectedName,setSelectedName] = useState(null);

  const handleSubmit = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/repo?employeeName=${selectedName}`);
      if (response.status === 200) {
        setData(response.data); // Set the received data to the data state
      } else {
        setData(null); // Clear the data if there's an errorn
      }
    } catch (error) {
      console.error('Error searching employee:', error);
      setData(null); // Clear the data if there's an error
    }
  };
  const [file, setFile] = useState(null);
  

  const handleFileChange = (event) => {
    console.log(event.target.files);
    setFile(event.target.files);
  };

  const handleUploadfile1 = async () => {
    if (file) {
    for(var singleFile of file){
      const formData = new FormData();
      formData.append('file', singleFile);

      try {
        const response = await axios.post("http://localhost:8080/api/upload-excel", formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        alert('uploaded successfull')
      } catch (error) {
        alert('Error uploading file:', error);
      }
    }    
    }
  };

  const handleUploadfile2 = async () => {
    if (file) {
    for(var singleFile of file){
      const formData = new FormData();
      formData.append('file', singleFile);

      try {
        const response = await axios.post("http://localhost:8080/api/upload", formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        alert('uploaded successfull')
      } catch (error) {
        alert('Error uploading file:', error);
      }
    }    
    }
   
  };

  const searchHandler = async(e) =>{
    setQuery(e.target.value)
    setSelectedName(null)
    try {
      const response = await axios.get(`http://localhost:8080/api/employees?name=${e.target.value}`);
      setDropDown(response);      
    } catch (error) {
      alert('not found:', error);
    }

  }
  
  return (
    <div className='main-div'>
      <h1 className='header'>Employee Management</h1>
      <div className='button1'>
      <FileUpload endpoint="/api/uploadEmployee" handleFileChange={handleFileChange} handleUploadfile={handleUploadfile1} file={file}  />
      <button onClick={handleUploadfile1} className='fileUpload'>Upload project files</button>
      </div>
      <div className='button2'>
      <FileUpload endpoint="/api/uploadEmployee" handleFileChange={handleFileChange} handleUploadfile={handleUploadfile2} file={file}  />
      <button onClick={handleUploadfile2} className='fileUpload'>Upload Employee files</button>
      </div>
      <div className='searchBox'>
        <input type='text' placeholder='Enter the name' value={selectedName||query} onChange={searchHandler}></input>
        <button className='button' onClick={handleSubmit}>Search</button>
        {(!selectedName&&query.length>0)&&<div className='dropdown'>
          {dropdown?.data?.map(item=><p onClick={()=>{setSelectedName(item?.employeeName);}}>{item?.employeeName}</p>)}
          {dropdown?.length===0&&<p>No employees found</p>}
        </div>}
      </div>

      {data && (
        <div className='employeedata'>
          <div className='employeedata_col1'>
          <div className='employeedata_col1_row' ><p><b>Employee Id :</b></p> <p>{data?.employeeId}</p></div>
          <div className='employeedata_col1_row'><p><b>Employee name :</b></p> <p>{data?.employeeName}</p></div>
          <div className='employeedata_col1_row'><p><b>Global Job Code :</b></p> <p>{data?.globalJobcode}</p></div>
          <div className='employeedata_col1_row'><p><b>Level and Grade:</b></p> <p>{data?.levelandgrade}</p></div>
          </div>
          <div className='employeedata_col2'>
          <div className='employeedata_col1_row'><p><b>Reporting Manager :</b></p> <p>{data?.reportingManager}</p></div>
          <div className='employeedata_col1_row'><p><b>Designation :</b></p> <p>{data?.designation}</p></div>
          <div className='employeedata_col1_row'><p><b>Total Experienece :</b></p> <p>{data?.totalExperience}</p></div>
          <div className='employeedata_col1_row'><p><b>Joining Experience :</b></p> <p>{data?.joiningExperience}</p></div>
          </div>
        </div>
      )}

      {data && (
        <div>
          <h1>Project List</h1>
          <table className='project_list'>
            <thead>
              <tr>
                <th>Project Name</th>
                <th>Start Date</th>
                <th>End Date</th>
              </tr>
            </thead>
            <tbody>
             
              {data.projectName.map((item, index) => (
                <tr key={index}>
                  <td>{item}</td>
                  <td>{data.weekStartDate[index]}</td>
                  <td>{data.weekEndDate[index]}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default App;
