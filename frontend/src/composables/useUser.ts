import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { getAllCategories, getCategory } from '@/api/categories';
import { getUser, changeBalance } from '@/api/users';
import { Category } from '@/model/category';
import { Role } from '@/model/role';
import { User } from '@/model/user';

export function useUser() {


    const thisuser = ref<User>();

    const getUserValues = async () => {
        try {
            thisuser.value = await getUser();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }
 
    const setBalance = async (changedBalance: number) => {
        if (thisuser.value) {
            try {
                await changeBalance(changedBalance);
                thisuser.value.balance = changedBalance;
            } catch (error) {
                console.log(error); // FIXME: Errorhandling
            }
        }
    }
    return { thisuser, getUserValues, setBalance,  };

}