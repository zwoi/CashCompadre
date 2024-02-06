import { ref } from 'vue';
import axios from 'axios';
import { Category } from '@/model/category';
import { API_ROOT } from "@/config/development";

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

  export  async function getCategory() {
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

  export  async function addCategory() {
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
  
