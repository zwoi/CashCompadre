import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { API_ROOT } from '@/config/development';
import axios from 'axios';
import { Category } from '@/model/category';


    
export  async function getAllCategories() {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT+"/api/categories", config);
        console.log(response);
        return response;
    } catch (error) {
        return <any>error;   
    }
  }

  export  async function getCategory(category:String) {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT+"/api/categories/"+category, config);
        console.log(response);
        return response;
    } catch (error) {
        return <any>error;   
    }
  }

  export  async function addCategory(category:Category) {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.post(API_ROOT+"/api/category", config);
        console.log(response);
        return response;
    } catch (error) {
        return <any>error;   
    }
  }

  
