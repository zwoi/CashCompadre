import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { API_ROOT } from '@/config/development';
import axios from 'axios';
import { Category } from '@/model/category';


    
export  async function addanExpense(expense:Expense, id:number) {
    const config = {        
        withCredentials: true,
        
    }
    
    
    try {
        const response = await axios.post(API_ROOT+"/api/categories/"+id,  expense,config,);
        console.log(response);
        return response;
    } catch (error) {
        return <any>error;   
    }
  }

  export  async function getAllExpenses(categoryName) {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT+"/api/categories/"+categoryName, config);
        console.log(response);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
}



  

  
