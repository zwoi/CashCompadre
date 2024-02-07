import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { API_ROOT } from '@/config/development';
import axios from 'axios';
import { Category } from '@/model/category';


    
export  async function addanExpense(expense:Expense, id:number) {
    const config = {        
        withCredentials: true,
        body:expense
    }
    
    
    try {
        const response = await axios.post(API_ROOT+"/api/category", config,)
        console.log(response);
        return response;
    } catch (error) {
        return <any>error;   
    }
  }



  

  
