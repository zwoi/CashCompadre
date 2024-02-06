import { ref } from 'vue';

import { API_ROOT } from '@/config/development';
import { Category } from '@/model/category';


    

export  async function getAllCategories() : Promise<Category[]>{
    const config = {        
        withCredentials: true
    }
    try {
        const response = await fetch(API_ROOT+"/api/category");
        return response.json();
    } catch (error) {
        return <any>error;   
    }
  }
  
