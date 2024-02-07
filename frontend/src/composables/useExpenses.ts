import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { } from '@/api/expenses';

export function useExpenses() {

    const expenses = ref<Expense[]>([]);
    const newExpense = ref<Expense>();

    const getExpenses = async () => {
       /*  try {
            expenses.value = await getAllExpenses();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        } */
    }




    return { expenses, getExpenses };

}