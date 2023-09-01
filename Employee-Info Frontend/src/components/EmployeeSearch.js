import React, { useState } from 'react';
import axios from 'axios';

const EmployeeSearch = ({ onSearch }) => {
  const [employeeName, setEmployeeName] = useState('');

  const handleSearch = async () => {
    if (employeeName.trim() !== '') {
      try {
        const response = await axios.get(`/api/employee/${employeeName}`);
        onSearch(response.data); // Notify parent component with search results
      } catch (error) {
        console.error('Error searching employee:', error);
      }
    }
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Employee Name"
        value={employeeName}
        onChange={(event) => setEmployeeName(event.target.value)}
      />
      <button onClick={handleSearch}>Search</button>
    </div>
  );
};

export default EmployeeSearch;
