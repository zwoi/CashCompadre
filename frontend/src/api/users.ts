import { ref } from 'vue';
import axios from 'axios';
import { Category } from '@/model/category';
import { Role} from '@/model/role';
import { User } from '@/model/user';

import { API_ROOT } from "@/config/development";

export  async function getUser() {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT+"/api/user", config);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
  }

 

  export async function changeBalance(changedBalance: number) {
    console.log("api Balance: "+changedBalance);
    console.log("api BalanceDatatype: "+typeof(changedBalance));
    const config = {
        withCredentials: true,
        headers: {
            'Content-Type': 'application/json' // Make sure to set the appropriate content type if needed
        }
    }

    try {
        const response = await axios.put(API_ROOT + "/api/balance", changedBalance, config);
        changedBalance = Number(changedBalance);
        console.log(response);
        return response.data; // Return the response data
    } catch (error) {
        console.log("ERROR: "+changedBalance);
        console.log("api BalanceDatatype after: "+typeof(changedBalance));
        console.error("Error while changing balance:", error);
        throw error; // Throw the error so that the caller can handle it
    }
}
  
