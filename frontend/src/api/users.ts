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
        console.log(response);
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

  export  async function changeBalance(changedBalance: number) {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.put(API_ROOT+"/api/balance", config);
        console.log(response);
        return response;
    } catch (error) {
        return <any>error;   
    }
  }
  
