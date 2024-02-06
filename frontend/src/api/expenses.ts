import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { API_ROOT } from '@/config/development';


    
   //use fetch to get all expenses
export  async function getAllExpenses() : Promise<Expense[]>{
    const config = {        
        withCredentials: true
    }
    try {
        const response = await fetch(API_ROOT+"/api/expense");
        return response.json();
    } catch (error) {
        return <any>error;   
    }
  }
  
