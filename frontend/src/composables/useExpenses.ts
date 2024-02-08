import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { addanExpense, getAllExpenses } from '@/api/expenses';
 
export function useExpenses() {

    const expenses = ref<Expense[]>([]);
    const newExpense = ref<Expense>();

    const getExpenses = async (categoryName: string) => {
         try {
            expenses.value = await getAllExpenses(categoryName);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        } 
    }
    //error handling machen
    const addExpense = async (e:Expense , id:number) => {
         try {
             expenses.value = await addanExpense(e,id);
         } catch (error) {
             console.log(error); // FIXME: Errorhandling
         }
     }




    return { expenses, getExpenses, addExpense };

}