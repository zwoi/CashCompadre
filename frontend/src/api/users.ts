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

  export  async function getBalance() {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT+"/api/balance", config);
        console.log(response);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
  }

  export async function changeBalance(changedBalance: number) {
    console.log(changedBalance);
    const config = {
        withCredentials: true,
    }

    try {
        const response = await axios.put(API_ROOT + "/api/balance", changedBalance, config);
        
        console.log(response);
        return response.data; // Return the response data
    } catch (error) {
        console.log("ERROR: "+changedBalance);
        console.error("Error while changing balance:", error);
        throw error; // Throw the error so that the caller can handle it
    }
}
  
