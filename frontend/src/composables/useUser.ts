import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { getAllCategories, getCategory } from '@/api/categories';
import { getUser, getBalance, changeBalance } from '@/api/users';
import { Category } from '@/model/category';
import { Role } from '@/model/role';
import { User } from '@/model/user';

export function useUser() {

    
    const user = ref<User>();

    const getUserValues = async () => {
        try {
            user.value = await getUser();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const setBalance = async (changedBalance: number) => {
        if (!user.value) return;
        try {
            await changeBalance(changedBalance);
            user.value.balance = changedBalance;
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    return { user, getUserValues, setBalance };

}